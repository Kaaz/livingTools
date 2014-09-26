package novaz.mod;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemTool;
import net.minecraftforge.common.config.Configuration;
import novaz.mod.handler.server.ItemToolHandler;
import novaz.mod.network.ToolUpgradeMessage;
import novaz.mod.proxy.CommonProxy;
import novaz.mod.proxy.GuiProxy;
import novaz.mod.references.Names;
import novaz.mod.startup.PEBlocks;
import novaz.mod.startup.PEItems;
import novaz.mod.startup.PERecipes;

@Mod(modid = PassiveEnchanting.MOD_ID, acceptedMinecraftVersions = "[1.7.10]", version = PassiveEnchanting.VERSION, dependencies = "required-after:Forge@[10.10,);required-after:FML@[7.2,)")
public class PassiveEnchanting {
	public static final String MOD_ID = "passiveEnchants";
	public static final String VERSION = "0.2";
	private static boolean EXAMPLE_BOOL = false;
	private static String EXAMPLE_STRING = "test";
	public static SimpleNetworkWrapper network;
	public static final CreativeTabs TAB = new CreativeTabPE(MOD_ID);
	@Mod.Instance(PassiveEnchanting.MOD_ID)
	public static PassiveEnchanting instance = new PassiveEnchanting();
	@SidedProxy(clientSide = "novaz.mod.proxy.ClientProxy", serverSide = "novaz.mod.proxy.ServerProxy")
	public static CommonProxy proxy;

	private static int modGuiIndex = 0;
	public static final int GUI_ITEM_UPGRADE = modGuiIndex++;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiProxy());
		network = NetworkRegistry.INSTANCE.newSimpleChannel(Names.CHANNEL);
		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
		network.registerMessage(ToolUpgradeMessage.Handler.class, ToolUpgradeMessage.class, 0, Side.SERVER);
		cfg.load();
		EXAMPLE_BOOL = cfg.get(Configuration.CATEGORY_GENERAL, "testBool", true).getBoolean(true);
		EXAMPLE_STRING = cfg.get(Configuration.CATEGORY_GENERAL, "testString", "TEST").getString();
		cfg.save();
		PEItems.init();
		PEBlocks.init();
		PERecipes.initRecipes();
		this.proxy.preInit(event);
	}
	@Mod.EventHandler
	public void init(FMLInitializationEvent e) {
		this.proxy.init(e);
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		this.proxy.postInit(e);
	}
}
