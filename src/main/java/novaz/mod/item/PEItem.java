package kaaz.mod.item;

import kaaz.mod.PassiveEnchanting;
import net.minecraft.item.Item;

/**
 * Created by kaaz on 22-9-2014.
 */
abstract public class PEItem extends Item {

	public PEItem(){
		setCreativeTab(PassiveEnchanting.TAB);
	}
}
