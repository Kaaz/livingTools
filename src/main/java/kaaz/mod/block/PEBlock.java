package kaaz.mod.block;

import kaaz.mod.PassiveEnchanting;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class PEBlock extends Block {

	public PEBlock(){
		super(Material.rock);
		setCreativeTab(PassiveEnchanting.TAB);
	}
	protected PEBlock(Material p_i45394_1_) {
		super(p_i45394_1_);
	}
}
