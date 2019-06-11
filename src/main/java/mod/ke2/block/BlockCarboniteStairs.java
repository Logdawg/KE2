package mod.ke2.block;

import mod.ke2.init.Ke2CreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

public class BlockCarboniteStairs extends BlockStairs {
	public BlockCarboniteStairs(Block block) {
		super(block.getDefaultState());
		this.setUnlocalizedName(block.getUnlocalizedName().replace("tile.", "") + "_stairs");
    	this.setCreativeTab(Ke2CreativeTabs.GEM_TECH);
        this.setResistance(30);
        this.setHardness(2);
	}
}