package novaz.mod.util;

import net.minecraft.util.ResourceLocation;
import novaz.mod.PassiveEnchanting;

public class ResourceUtil {
	public static ResourceLocation getResourceLocation(String modId, String path)
	{
		return new ResourceLocation(modId, path);
	}

	public static ResourceLocation getResourceLocation(String path)
	{
		return getResourceLocation(PassiveEnchanting.MOD_ID.toLowerCase(), path);
	}
}
