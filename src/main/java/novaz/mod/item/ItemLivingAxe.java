package novaz.mod.item;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import novaz.mod.PassiveEnchanting;
import novaz.mod.references.Names;

import java.util.Set;

/**
 * Created by kaaz on 22-9-2014.
 */
public class ItemLivingAxe extends PEItemTool {
	private static final Set blocksEffectiveAgainst = Sets.newHashSet(new Block[]{Blocks.planks, Blocks.bookshelf, Blocks.log, Blocks.log2, Blocks.chest, Blocks.pumpkin, Blocks.lit_pumpkin});

	public ItemLivingAxe() {

		super(3f, ToolMaterial.IRON, blocksEffectiveAgainst);
		setUnlocalizedName(Names.Items.LIVING_AXE);
		setCreativeTab(PassiveEnchanting.TAB);
	}

	@Override
	public Set<String> getToolClasses(ItemStack itemStack) {
		return ImmutableSet.of("axe");
	}

	@Override
	public float func_150893_a(ItemStack itemStack, Block block) {
		return block.getMaterial() != net.minecraft.block.material.Material.wood && block.getMaterial() != net.minecraft.block.material.Material.plants && block.getMaterial() != net.minecraft.block.material.Material.vine ? super.func_150893_a(itemStack, block) : this.efficiencyOnProperMaterial;
	}
}
