package novaz.mod.item;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import novaz.mod.PassiveEnchanting;
import novaz.mod.item.special.StatType;
import novaz.mod.references.Names;
import org.lwjgl.input.Keyboard;

import java.util.List;
import java.util.Set;

/**
 * Created by kaaz on 22-9-2014.
 */
public class ItemLivingPickaxe extends PEItemTool {
	private static final Set worksAgainst = Sets.newHashSet(new Block[]{Blocks.cobblestone, Blocks.double_stone_slab, Blocks.stone_slab, Blocks.stone, Blocks.sandstone, Blocks.mossy_cobblestone, Blocks.iron_ore, Blocks.iron_block, Blocks.coal_ore, Blocks.gold_block, Blocks.gold_ore, Blocks.diamond_ore, Blocks.diamond_block, Blocks.ice, Blocks.netherrack, Blocks.lapis_ore, Blocks.lapis_block, Blocks.redstone_ore, Blocks.lit_redstone_ore, Blocks.rail, Blocks.detector_rail, Blocks.golden_rail, Blocks.activator_rail});
	//public final String[] stats = {"speed", "durability","regen" ,"damage", "mininglevel","fortune"};


	public ItemLivingPickaxe() {
		super(1f, ToolMaterial.WOOD, worksAgainst);
		setUnlocalizedName(Names.Items.LIVING_PICKAXE);

		addItemStat("speed", "speed", 50, 1, 0.5);
		addItemStat("mininglevel", "Mining level", 3, 1, 5);
		addItemStat("damage", "Damage", 50, 1, 0);
		addItemStat("fortune", "Fortune", 3, 1, 5);
	}

	public boolean hitEntity(ItemStack itemStack, EntityLivingBase p_77644_2_, EntityLivingBase p_77644_3_) {
		//itemStack.damageItem(2, p_77644_3_);
		return true;
	}

	@Override
	public boolean canHarvestBlock(Block block, ItemStack itemStack) {
		int harvestLevel = this.toolMaterial.getHarvestLevel();
		if (itemStack.stackTagCompound != null) {
			harvestLevel = getItemStat(itemStack, "mininglevel");
		}
		return block == Blocks.obsidian ? harvestLevel >= 3 : (block != Blocks.diamond_block && block != Blocks.diamond_ore ? (block != Blocks.emerald_ore && block != Blocks.emerald_block ? (block != Blocks.gold_block && block != Blocks.gold_ore ? (block != Blocks.iron_block && block != Blocks.iron_ore ? (block != Blocks.lapis_block && block != Blocks.lapis_ore ? (block != Blocks.redstone_ore && block != Blocks.lit_redstone_ore ? (block.getMaterial() == Material.rock ? true : (block.getMaterial() == Material.iron ? true : block.getMaterial() == Material.anvil)) : harvestLevel >= 2) : harvestLevel >= 1) : harvestLevel >= 1) : harvestLevel >= 2) : harvestLevel >= 2) : harvestLevel >= 2);
	}

	public float func_150893_a(ItemStack itemStack, Block block) {
		return block.getMaterial() != Material.iron && block.getMaterial() != Material.anvil && block.getMaterial() != Material.rock ? super.func_150893_a(itemStack, block) : this.efficiencyOnProperMaterial;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack itemStack, World world, Block block, int x, int y, int z, EntityLivingBase player) {
		System.out.println(String.format("Item dmg/max %s/%s", itemStack.getItemDamage(), itemStack.getMaxDamage()));
		if ((double) block.getBlockHardness(world, x, y, z) != 0.0D && worksAgainst.contains(block)) {
			if (itemStack.stackTagCompound == null) {
				initItem(itemStack);
			}
			//p_150894_1_.damageItem(1, p_150894_7_);

			if (itemStack.stackTagCompound.getInteger("level") < MAX_LEVEL) {
				itemStack.stackTagCompound.setInteger("xp", itemStack.stackTagCompound.getInteger("xp") + 1);
			}

			checkLevelUp(itemStack, player);
		}
		//return super.onBlockDestroyed(p_150894_1_, p_150894_2_, p_150894_3_, p_150894_4_, p_150894_5_, p_150894_6_, p_150894_7_);
		return true;
	}

	public void checkLevelUp(ItemStack item, EntityLivingBase player) {
		int level = item.stackTagCompound.getInteger("level");
		int xp = item.stackTagCompound.getInteger("xp");
		int xpToNext = XP_PER_LEVEL * (level + 1);
		if (xp >= xpToNext) {

			item.stackTagCompound.setInteger("level", level + 1);
			item.stackTagCompound.setInteger("xp", xp - xpToNext);
			item.stackTagCompound.setInteger("points", item.stackTagCompound.getInteger("points") + 1);
		}
	}


	public void addInformation(ItemStack itemStack, EntityPlayer player,
							   List list, boolean par4) {
		boolean shiftPressed = Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT);
		if (itemStack.stackTagCompound != null) {
			int level = itemStack.stackTagCompound.getInteger("level");
			int xp = itemStack.stackTagCompound.getInteger("xp");
			int points = itemStack.stackTagCompound.getInteger("points");
			int xpToNext = XP_PER_LEVEL * (level + 1);
			int progress = (int) (((float) xp / (float) xpToNext) * 100);
			list.add("level " + colorfy(level));
			list.add(String.format("Experience: %s %% [%s / %s]", colorfy(progress, EnumChatFormatting.AQUA), colorfy(xp), colorfy(xpToNext)));
			if (!shiftPressed) {
				if (points > 0) {
					list.add(String.format("You have %s unspend point(s)!", colorfy(points)));
					list.add("" + EnumChatFormatting.ITALIC + " " + EnumChatFormatting.WHITE + "Rightclick to spend them ");
				}
				list.add("" + EnumChatFormatting.WHITE + " " + EnumChatFormatting.ITALIC + "press Shift to see itemStats");
			} else {
				for (StatType s : itemStats.values()) {
					list.add(String.format("%s: %s", s.name, colorfy(itemStack.stackTagCompound.getInteger(statsPrefix + s.name))));
				}
			}
		}
	}

	public String colorfy(Object o) {
		return colorfy(o, EnumChatFormatting.AQUA);
	}

	public String colorfy(Object o, EnumChatFormatting col) {
		return "" + col + o + EnumChatFormatting.GRAY;
	}

	@Override
	public Set<String> getToolClasses(ItemStack itemStack) {
		return ImmutableSet.of("pickaxe");
	}

	@Override
	public float getDigSpeed(ItemStack itemStack, Block block, int meta) {
		if (itemStack.stackTagCompound != null) {
			if (ForgeHooks.isToolEffective(itemStack, block, meta)) {
				return efficiencyOnProperMaterial + itemStack.stackTagCompound.getInteger(statsPrefix + "speed");
			}
		}
		return super.getDigSpeed(itemStack, block, meta);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
		if (itemStack.stackTagCompound != null) {
			if (world.isRemote && itemStack.stackTagCompound.getInteger("points") > 0) {//client
				player.openGui(PassiveEnchanting.instance, PassiveEnchanting.GUI_ITEM_UPGRADE, world, (int) player.posX, (int) player.posY, (int) player.posZ);

			}
		}
		return super.onItemRightClick(itemStack, world, player);
	}

	public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		return false;
	}
}
