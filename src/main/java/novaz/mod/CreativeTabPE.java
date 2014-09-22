package novaz.mod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class CreativeTabPE extends CreativeTabs {
	public CreativeTabPE(String lable) {
		super(lable);
	}
	public String getTabLabel() {
		return "Passive Enchanting";
	}

	public Item getTabIconItem() {
		return Items.bed;
	}
};
