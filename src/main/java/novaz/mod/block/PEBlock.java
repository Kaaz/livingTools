package novaz.mod.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import novaz.mod.PassiveEnchanting;

public class PEBlock extends Block {

	public PEBlock(){
		super(Material.rock);
		setCreativeTab(PassiveEnchanting.TAB);
	}
	protected PEBlock(Material p_i45394_1_) {
		super(p_i45394_1_);
	}
}
