package novaz.mod.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

/**
 * Created by kaaz on 24-9-2014.
 */
public class ItemUpgradeContainer extends Container {

	@Override
	public boolean canInteractWith(EntityPlayer entity) {
		return true;
	}
}
