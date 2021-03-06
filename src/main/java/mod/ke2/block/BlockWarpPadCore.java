package mod.ke2.block;

import mod.ke2.KAGIC;
import mod.ke2.init.Ke2Items;
import mod.ke2.proxy.CommonProxy;
import mod.ke2.tileentity.TileEntityWarpPadCore;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockWarpPadCore extends Block implements ITileEntityProvider {
	private final int color;

	public BlockWarpPadCore(int color) {
		super(Material.ROCK);
		this.color = color;
		this.setUnlocalizedName(EnumDyeColor.byMetadata(color).toString().toLowerCase() + "_warp_pad_core");
		this.setResistance(30);
		this.setHardness(2);
	}

	public int getColor() {
		return this.color;
	}

	protected TileEntityWarpPadCore getWarpPad(World world, BlockPos pos) {
		return (TileEntityWarpPadCore) world.getTileEntity(pos);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityWarpPadCore();
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (world.isRemote) {
			ItemStack heldItem = player.getHeldItem(hand);
			if (heldItem.getItem() == Ke2Items.GEM_STAFF) {
				TileEntityWarpPadCore entityPad = this.getWarpPad(world, pos);
				entityPad.validateWarpPad();
				if (entityPad.isValidPad()) {
					player.openGui(KAGIC.instance, CommonProxy.GUI_WARP_PAD, world, pos.getX(), pos.getY(), pos.getZ());
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		this.getWarpPad(world, pos).destroy();
		super.breakBlock(world, pos, state);
	}
}