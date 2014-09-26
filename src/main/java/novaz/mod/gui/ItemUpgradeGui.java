package novaz.mod.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import novaz.mod.PassiveEnchanting;
import novaz.mod.item.PEItemTool;
import novaz.mod.item.special.StatType;
import novaz.mod.network.ToolUpgradeMessage;

/**
 * Created by kaaz on 24-9-2014.
 */
public class ItemUpgradeGui extends GuiScreen {
	public static final int GUI_ID = PassiveEnchanting.GUI_ITEM_UPGRADE;
	private EntityPlayer player;
	private ItemStack equippedItem;
	private PEItemTool itemClass;
	private boolean validTool = false;
	private int startX = 75, startY = 50;
	private String owner = "";
	private int level = 0, xp = 0, points = 0;
	private StatType[] stats;
	private int[] statValues;

	public ItemUpgradeGui(EntityPlayer player) {
		this.player = player;
		equippedItem = player.getCurrentEquippedItem();
		loadItem(equippedItem);
	}

	public void loadItem(ItemStack itemStack) {
		validTool = equippedItem.getItem() instanceof PEItemTool;
		if (validTool) {
			itemClass = (PEItemTool) equippedItem.getItem();
			stats = itemClass.getSpecialStats();
			statValues = new int[stats.length];
			if (itemStack.getTagCompound() != null) {
				owner = itemStack.stackTagCompound.getString("owner");
				level = itemStack.stackTagCompound.getInteger("level");
				xp = itemStack.stackTagCompound.getInteger("xp");
				points = itemStack.stackTagCompound.getInteger("points");
				for (int i = 0; i < stats.length; i++) {
					statValues[i] = itemStack.stackTagCompound.getInteger("stat_" + stats[i].name);
				}
			}
		}
	}

	@Override
	public void initGui() {//id,x,y,w,h,display
		buttonList.clear();
		//drawString are: string, x, y, color
		for (int i = 0; i < stats.length; i++) {
			int cost = stats[i].baseCost + (int) (stats[i].costPerLevel * statValues[i]);
			GuiButton gb = new GuiButton(i, startX, startY + (i * 20), 100, 20, String.format("%s [%s]", stats[i].name, cost));
			if (cost > points || statValues[i] >= stats[i].maxLevel) {
				gb.enabled = false;
			}
			buttonList.add(gb);
		}
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	public void drawScreen(int i, int j, float f) {
		drawDefaultBackground();
		int ii = 0;
		super.drawScreen(i, j, f);
		fontRendererObj.drawStringWithShadow("Pickaxe upgrade menu", startX, startY - 20, 0xffFFFFFF);
		for (; ii < statValues.length; ii++) {
			fontRendererObj.drawString("Current: " + statValues[ii], startX + 120, startY + 5 + (ii * 20), 0xffffffff);
		}

		if (points > 0) {
			fontRendererObj.drawString("You have " + points + " point(s) left!", startX, startY + (ii * 20) + 5, 0xffffffff);
		}
	}

	@Override
	public void actionPerformed(GuiButton button) {
		System.out.println(String.format("Clicked on %s", stats[button.id].name));
		if (validTool) {
			itemClass.upgradeStat(equippedItem, stats[button.id].name);
			PassiveEnchanting.network.sendToServer(new ToolUpgradeMessage(stats[button.id].name));
			loadItem(equippedItem);
			initGui();
		}
	}
}
