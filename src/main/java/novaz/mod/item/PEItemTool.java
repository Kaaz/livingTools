package novaz.mod.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import novaz.mod.PassiveEnchanting;
import novaz.mod.references.Names;

import java.util.Set;

/**
 * Created by kaaz on 22-9-2014.
 */
public class PEItemTool extends ItemTool{
	protected PEItemTool(float damage, ToolMaterial toolMaterial, Set goodAgainst) {
		super(damage, toolMaterial, goodAgainst);
		setCreativeTab(PassiveEnchanting.TAB);
		this.setNoRepair();
	}
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
	}
	@Override
	public String getUnlocalizedName() {
		return String.format("item.%s%s", Names.RESOURCE_PREFIX, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		return String.format("item.%s%s", Names.RESOURCE_PREFIX, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}

	protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}
}
