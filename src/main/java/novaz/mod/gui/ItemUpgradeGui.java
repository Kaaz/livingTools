package novaz.mod.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import novaz.mod.PassiveEnchanting;

/**
 * Created by kaaz on 24-9-2014.
 */
public class ItemUpgradeGui extends GuiScreen {
	public static final int GUI_ID = PassiveEnchanting.GUI_ITEM_UPGRADE;

	public ItemUpgradeGui() {
		System.out.print("gui constructor.\n");

	}

	@Override
	public void initGui() {//id,x,y,w,h,display
		buttonList.clear();
		buttonList.add(new GuiButton(0, 0, 0, 100, 20, "Speed"));
		buttonList.add(new GuiButton(1, 0, 20, 100, 20, "Damage"));
		buttonList.add(new GuiButton(2, 0, 40, 100, 20, "Mining level"));
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	public void drawScreen(int i, int j, float f) {
		drawDefaultBackground();
		super.drawScreen(i, j, f);
	}

	@Override
	public void actionPerformed(GuiButton button) {
		System.out.println("Clicked on ="+button.id);

	}
}
