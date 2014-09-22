package kaaz.mod;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.init.Blocks;

@Mod(modid = PassiveEnchanting.MODID, version = PassiveEnchanting.VERSION)
public class PassiveEnchanting {
	public static final String MODID = "passiveEnchants";
	public static final String VERSION = "0.1";

	@EventHandler
	public void init(FMLInitializationEvent event) {
		// some example code
		System.out.println("DIRT BLOCK >> " + Blocks.dirt.getUnlocalizedName());
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

	}
}
