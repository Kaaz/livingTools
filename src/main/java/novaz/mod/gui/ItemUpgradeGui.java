package novaz.mod.gui;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import novaz.mod.PassiveEnchanting;
import novaz.mod.item.ItemLivingPickaxe;
import novaz.mod.references.Names;
import novaz.mod.startup.PEItems;

import java.util.HashMap;

/**
 * Created by kaaz on 24-9-2014.
 */
public class ItemUpgradeGui extends GuiScreen {
	public static final int GUI_ID = PassiveEnchanting.GUI_ITEM_UPGRADE;
	private EntityPlayer player;
	private ItemStack equipedItem;
	private boolean pickaxe=false;
	private int startX=75,startY=50;
	private String owner="";
	private int level=0,xp=0,points=0;
	private String[] statNames;
	private int[] statValues;

	public ItemUpgradeGui(EntityPlayer player) {
		this.player = player;
		equipedItem = player.getCurrentEquippedItem();
		pickaxe = equipedItem.getUnlocalizedName().equals(Names.Items.getFullName(Names.Items.LIVING_PICKAXE));
		loadItem(equipedItem);
	}

	public void loadItem(ItemStack itemStack){
		if(pickaxe){
			statNames = ((ItemLivingPickaxe)PEItems.pickaxe).getSpecialStats();
			statValues = new int[statNames.length];
			if(itemStack.getTagCompound() != null){
				owner=itemStack.stackTagCompound.getString("owner");
				level=itemStack.stackTagCompound.getInteger("level");
				xp=itemStack.stackTagCompound.getInteger("xp");
				points=itemStack.stackTagCompound.getInteger("points");
				for(int i=0;i<statNames.length;i++){
					statValues[i]= itemStack.stackTagCompound.getInteger("stat_"+statValues[i]);
				}
			}
		}
	}

	@Override
	public void initGui() {//id,x,y,w,h,display
		buttonList.clear();
		//drawString are: string, x, y, color
		for(int i=0;i<statNames.length;i++){
			buttonList.add(new GuiButton(i, startX, startY+(i*20), 100, 20, String.format("%s [%s]",statNames[i],statValues[i])));
		}
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	public void drawScreen(int i, int j, float f) {
		drawDefaultBackground();
		super.drawScreen(i, j, f);
		fontRendererObj.drawStringWithShadow("Pickaxe upgrade menu", startX, startY-20, 0xffFFFFFF);
		for(int ii=0;i<statNames.length;i++){
			fontRendererObj.drawString("Current: "+statValues[i],startX+120,startY+5+(ii*20),0xffffffff);
		}
		if(points>0) {
			fontRendererObj.drawString("You have " + points + " point(s) left!", startX, startY + 105, 0xffffffff);
		}
	}

	@Override
	public void actionPerformed(GuiButton button) {
		System.out.println(String.format("Clicked on %s",statNames[button.id]));
	}
}
