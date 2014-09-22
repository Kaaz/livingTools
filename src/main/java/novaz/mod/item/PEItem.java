package novaz.mod.item;

import net.minecraft.item.Item;
import novaz.mod.PassiveEnchanting;

/**
 * Created by kaaz on 22-9-2014.
 */
abstract public class PEItem extends Item {

	public PEItem(){
		setCreativeTab(PassiveEnchanting.TAB);
	}
}
