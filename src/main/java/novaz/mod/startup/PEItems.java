package novaz.mod.startup;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import novaz.mod.item.*;
import novaz.mod.references.Names;

/**
 * Created by kaaz on 22-9-2014.
 */
public class PEItems {
	public static final Item axe = new ItemLivingAxe();
	public static final Item test = new ItemTest();
	public static final Item hoe = new ItemLivingHoe();
	public static final Item pickaxe = new ItemLivingPickaxe();
	public static final Item shovel = new ItemLivingShovel();
	public static final Item sword = new ItemLivingSword();

	public static void init() {
		GameRegistry.registerItem(axe, Names.Items.LIVING_AXE);
		GameRegistry.registerItem(test, Names.Items.TEST);
		GameRegistry.registerItem(hoe, Names.Items.LIVING_HOE);
		GameRegistry.registerItem(pickaxe, Names.Items.LIVING_PICKAXE);
		GameRegistry.registerItem(shovel, Names.Items.LIVING_SHOVEL);
		GameRegistry.registerItem(sword, Names.Items.LIVING_SWORD);
	}
}
