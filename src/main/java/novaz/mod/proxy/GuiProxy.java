package novaz.mod.proxy;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import novaz.mod.PassiveEnchanting;
import novaz.mod.gui.ItemUpgradeGui;

/**
 * Created by kaaz on 24-9-2014.
 */
public class GuiProxy implements IGuiHandler {
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == PassiveEnchanting.GUI_ITEM_UPGRADE) {
			return new ItemUpgradeGui(player);
		}
		return null;
	}
}
