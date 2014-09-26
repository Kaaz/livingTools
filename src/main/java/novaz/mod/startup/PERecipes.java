package novaz.mod.startup;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import novaz.mod.item.PEItem;

public class PERecipes {
	public static void initRecipes() {
		GameRegistry.addRecipe(new ItemStack(PEItems.pickaxe, 1), new Object[] { "aaa", " a ", " a ", 'a', Items.stick});
		GameRegistry.addRecipe(new ItemStack(PEItems.axe, 1), new Object[] { "aa ", "aa ", "   ", 'a', Items.stick});
		GameRegistry.addRecipe(new ItemStack(PEItems.shovel, 1), new Object[] { " a ", " a ", " a ", 'a', Items.stick});

	}
}
