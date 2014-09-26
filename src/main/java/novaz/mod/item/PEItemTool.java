package novaz.mod.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import novaz.mod.PassiveEnchanting;
import novaz.mod.item.special.StatType;
import novaz.mod.references.Names;
import org.lwjgl.input.Keyboard;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public abstract class PEItemTool extends ItemTool {

	protected final String statsPrefix = "stat_";
	protected int XP_PER_LEVEL = 5;
	protected int MAX_LEVEL = 100;
	protected HashMap<String, StatType> itemStats = new HashMap<String, StatType>();
	protected Set worksAgainst;

	protected PEItemTool(float damage, ToolMaterial toolMaterial, Set goodAgainst) {
		super(damage, toolMaterial, goodAgainst);
		worksAgainst = goodAgainst;
		setCreativeTab(PassiveEnchanting.TAB);
		this.setNoRepair();
		itemStats.clear();
		setMaxDamage(1000);
		addItemStat("durability", "Durability", 50, 1, 0.2);
		addItemStat("regen", "Regeneration", 50, 1, 0.2);
		addItemStat("speed", "speed", 50, 1, 0.5);
	}

	protected void addItemStat(String name, String description, int maxLevel, int baseCost, double costPerLevel) {
		if (!itemStats.containsKey(name)) {
			itemStats.put(name, new StatType(name, description, maxLevel, baseCost, costPerLevel));
		}
	}

	public StatType[] getSpecialStats() {
		return itemStats.values().toArray(new StatType[0]);
	}

	@Override
	public boolean onBlockDestroyed(ItemStack itemStack, World world, Block block, int x, int y, int z, EntityLivingBase player) {
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

	public void upgradeStat(ItemStack itemStack, String statName) {
		if (itemStack.stackTagCompound != null && itemStats.containsKey(statName)) {
			StatType upgradeStat = itemStats.get(statName);
			float oldLevel = getItemStat(itemStack, statName);
			int points = itemStack.stackTagCompound.getInteger("points");
			int cost = upgradeStat.baseCost + (int) (upgradeStat.costPerLevel * oldLevel);
			if (points >= cost && oldLevel < upgradeStat.maxLevel) {
				addToItemStat(itemStack, statName, 1);
				itemStack.stackTagCompound.setInteger("points", points - cost);
				System.out.println("upgraded " + statName);
			}
		}
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

	@Override
	public float getDigSpeed(ItemStack itemStack, Block block, int meta) {
		if (itemStack.stackTagCompound != null) {
			if (ForgeHooks.isToolEffective(itemStack, block, meta)) {
				return efficiencyOnProperMaterial + getItemStat(itemStack, "speed");
			}
		}
		return super.getDigSpeed(itemStack, block, meta);
	}

	@Override
	public void onCreated(ItemStack itemStack, World world, EntityPlayer player) {
		initItem(itemStack);
	}

	protected void initItem(ItemStack itemStack) {
		if (itemStack.stackTagCompound == null) {
			itemStack.stackTagCompound = new NBTTagCompound();
			itemStack.stackTagCompound.setInteger("level", 0);
			itemStack.stackTagCompound.setInteger("xp", 0);
			itemStack.stackTagCompound.setInteger("points", 0);
			for (StatType st : itemStats.values()) {
				itemStack.stackTagCompound.setInteger(statsPrefix + st.name, 0);
			}
		}
	}

	protected void addToItemStat(ItemStack item, String statName, int toAdd) {
		initItem(item);
		item.stackTagCompound.setInteger(statsPrefix + statName, item.stackTagCompound.getInteger(statsPrefix + statName) + toAdd);
	}

	protected void setItemStat(ItemStack item, String statName, int value) {
		initItem(item);
		item.stackTagCompound.setInteger(statsPrefix + statName, value);
	}

	protected int getItemStat(ItemStack item, String statName) {
		initItem(item);
		return item.stackTagCompound.getInteger(statsPrefix + statName);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
	}

	@Override
	public String getUnlocalizedName() {
		return String.format("item.%s%s", Names.RESOURCE_PREFIX, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		return String.format("item.%s%s", Names.RESOURCE_PREFIX, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}

	protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}
}
