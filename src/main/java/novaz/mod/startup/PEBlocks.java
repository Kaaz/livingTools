package novaz.mod.startup;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import novaz.mod.block.testBlock;
import novaz.mod.item.*;

/**
 * Created by kaaz on 22-9-2014.
 */
public class PEBlocks {
	//public static final Block alchemicalBag = new ItemAlchemicalBag();
	public static final Block testBlock = new novaz.mod.block.testBlock();

	public static void init() {
		GameRegistry.registerBlock(testBlock, "blockTest");
	}
}
