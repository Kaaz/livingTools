package novaz.mod.item;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import novaz.mod.references.Names;

import java.util.Set;

/**
 * Created by kaaz on 22-9-2014.
 */
public class ItemLivingPickaxe extends PEItemTool {
	private static final Set worksAgainst = Sets.newHashSet(new Block[]{Blocks.cobblestone, Blocks.double_stone_slab, Blocks.stone_slab, Blocks.stone, Blocks.sandstone, Blocks.mossy_cobblestone, Blocks.iron_ore, Blocks.iron_block, Blocks.coal_ore, Blocks.gold_block, Blocks.gold_ore, Blocks.diamond_ore, Blocks.diamond_block, Blocks.ice, Blocks.netherrack, Blocks.lapis_ore, Blocks.lapis_block, Blocks.redstone_ore, Blocks.lit_redstone_ore, Blocks.rail, Blocks.detector_rail, Blocks.golden_rail, Blocks.activator_rail});

	public ItemLivingPickaxe() {
		super(2f, ToolMaterial.IRON, worksAgainst);
		setUnlocalizedName(Names.Items.LIVING_PICKAXE);
	}
	@Override
	public Set<String> getToolClasses(ItemStack itemStack) {
		return ImmutableSet.of("pickaxe");
	}
	@Override
	public float getDigSpeed(ItemStack itemStack, Block block, int meta) {
		return super.getDigSpeed(itemStack, block, meta);
	}
	public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ){
		return false;
	}

}
