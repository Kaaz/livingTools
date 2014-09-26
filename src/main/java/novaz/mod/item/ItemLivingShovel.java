package novaz.mod.item;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import novaz.mod.PassiveEnchanting;
import novaz.mod.references.Names;

import java.util.Set;

/**
 * Created by kaaz on 22-9-2014.
 */
public class ItemLivingShovel extends PEItemTool {
	private static final Set blocksEffectiveAgainst = Sets.newHashSet(new Block[]{Blocks.grass, Blocks.dirt, Blocks.sand, Blocks.gravel, Blocks.snow_layer, Blocks.snow, Blocks.clay, Blocks.farmland, Blocks.soul_sand, Blocks.mycelium});

	public ItemLivingShovel() {
		super(1.0f, ToolMaterial.WOOD, blocksEffectiveAgainst);
		setUnlocalizedName(Names.Items.LIVING_SHOVEL);
	}

	@Override
	public Set<String> getToolClasses(ItemStack itemStack) {
		return ImmutableSet.of("shovel");
	}
}
