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
	protected static Set worksAgainst = Sets.newHashSet(new Block[]{Blocks.cobblestone, Blocks.double_stone_slab, Blocks.stone_slab, Blocks.stone, Blocks.sandstone, Blocks.mossy_cobblestone, Blocks.iron_ore, Blocks.iron_block, Blocks.coal_ore, Blocks.gold_block, Blocks.gold_ore, Blocks.diamond_ore, Blocks.diamond_block, Blocks.ice, Blocks.netherrack, Blocks.lapis_ore, Blocks.lapis_block, Blocks.redstone_ore, Blocks.lit_redstone_ore, Blocks.rail, Blocks.detector_rail, Blocks.golden_rail, Blocks.activator_rail});
	//public final String[] stats = {"speed", "durability","regen" ,"damage", "mininglevel","fortune"};


	public ItemLivingPickaxe() {
		super(1f, ToolMaterial.WOOD, worksAgainst);
		setUnlocalizedName(Names.Items.LIVING_PICKAXE);

		addItemStat("speed", "speed", 50, 1, 0.5);
		addItemStat("mininglevel", "Mining level", 3, 1, 5);
		//addItemStat("damage", "Damage", 50, 1, 0);
		//addItemStat("fortune", "Fortune", 3, 1, 5);
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
	public Set<String> getToolClasses(ItemStack itemStack) {
		return ImmutableSet.of("pickaxe");
	}

	public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		return false;
	}
}
