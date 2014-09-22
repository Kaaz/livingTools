package novaz.mod;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import novaz.mod.item.testItem;
import novaz.mod.block.testBlock;

@Mod(modid = PassiveEnchanting.MODID, version = PassiveEnchanting.VERSION, dependencies = "required-after:Forge@[10.10,);required-after:FML@[7.2,)")
public class PassiveEnchanting {
	public static final String MODID = "passiveEnchants";
	public static final String VERSION = "0.1";
	private static boolean EXAMPLE_BOOL = false;
	private static String EXAMPLE_STRING = "test";
	public static final CreativeTabs TAB = new CreativeTabPE(MODID);

	public static Item testItem;
	public static Block testBlock;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
		cfg.load();
		EXAMPLE_BOOL = cfg.get(Configuration.CATEGORY_GENERAL, "testBool", true).getBoolean(true);
		EXAMPLE_STRING = cfg.get(Configuration.CATEGORY_GENERAL, "testString", "TEST").getString();
		testItem = new testItem();
		GameRegistry.registerItem(testItem, "testItem");
		testBlock = new testBlock();
		GameRegistry.registerBlock(testBlock, "testBlock");
		cfg.save();

	}
}
