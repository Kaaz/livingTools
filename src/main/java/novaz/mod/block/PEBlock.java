package novaz.mod.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import novaz.mod.PassiveEnchanting;
import novaz.mod.references.Names;

public class PEBlock extends Block {

	public PEBlock(){
		super(Material.rock);
		setCreativeTab(PassiveEnchanting.TAB);
	}
	protected PEBlock(Material p_i45394_1_) {
		super(p_i45394_1_);
	}
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		blockIcon = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
	}

	protected String getUnwrappedUnlocalizedName(String unlocalizedName)
	{
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}
	@Override
	public String getUnlocalizedName() {
		return String.format("block.%s%s", Names.RESOURCE_PREFIX, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}
}
