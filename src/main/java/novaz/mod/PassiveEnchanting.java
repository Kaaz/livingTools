package novaz.mod;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.config.Configuration;
import novaz.mod.startup.PEBlocks;
import novaz.mod.startup.PEItems;
import novaz.mod.startup.PERecipes;

@Mod(modid = PassiveEnchanting.MOD_ID, version = PassiveEnchanting.VERSION, dependencies = "required-after:Forge@[10.10,);required-after:FML@[7.2,)")
public class PassiveEnchanting {
	public static final String MOD_ID = "passiveEnchants";
	public static final String VERSION = "0.1";
	private static boolean EXAMPLE_BOOL = false;
	private static String EXAMPLE_STRING = "test";
	public static final CreativeTabs TAB = new CreativeTabPE(MOD_ID);

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
		cfg.load();
		EXAMPLE_BOOL = cfg.get(Configuration.CATEGORY_GENERAL, "testBool", true).getBoolean(true);
		EXAMPLE_STRING = cfg.get(Configuration.CATEGORY_GENERAL, "testString", "TEST").getString();
		cfg.save();
		PEItems.init();
		PEBlocks.init();
		PERecipes.initRecipes();
	}
}
