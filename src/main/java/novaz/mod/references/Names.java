package novaz.mod.references;

import novaz.mod.PassiveEnchanting;

public class Names {
	public static final String RESOURCE_PREFIX = PassiveEnchanting.MOD_ID.toLowerCase() + ":";

	public static final class Items {
		public static final String getFullName(String name) {
			return String.format("item.%s%s", Names.RESOURCE_PREFIX, name);
		}

		public static final String LIVING_AXE = "itemLivingAxe";
		public static final String LIVING_HOE = "itemLivingHoe";
		public static final String LIVING_PICKAXE = "itemLivingPickaxe";
		public static final String LIVING_SHOVEL = "itemLivingShovel";
		public static final String LIVING_SWORD = "itemLivingSword";
		public static final String TEST = "itemTest";
	}

	public static final class Blocks {

	}
}