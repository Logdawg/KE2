package mod.ke2.tileentity;

import java.util.Iterator;
import java.util.List;

import mod.ke2.KAGIC;
import mod.ke2.api.warping.WarpPadPos;
import mod.ke2.init.Ke2Messages;
import mod.ke2.networking.PacketEntityTeleport;
import mod.ke2.world.data.WorldDataWarps;
import net.minecraft.block.BlockQuartz;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockStairs.EnumHalf;
import net.minecraft.block.BlockStairs.EnumShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.ForgeChunkManager.Type;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityWarpPadCore extends TileEntity implements ITickable {
	public static final int TICKS_FOR_LOADING_CHUNKS = 100;
	public static final int TICKS_FOR_COOLDOWN = 20;
	public static final int TICKS_FOR_ACTION = 40;
	protected int remainingLoadingChunkTicks = 0;
	protected int remainingCooldownTicks = 0;
	protected int remainingActionTicks = 0;
	protected BlockPos destination = null;
	
	public int renderCooldown = 0;
	public int renderTicks = 0;
	
	public String name = "";
	private Ticket ticket = null;
	
	private int ticksSinceLastCheck = 0;
	
	protected final int clearanceHeight = 6;
	protected boolean isClear = false;
	protected boolean isPadValid = false;
	protected boolean warping = false;
	protected boolean cooling = false;
	
	protected void setDirty() {
		this.markDirty();
		this.world.notifyBlockUpdate(this.pos, this.world.getBlockState(this.pos), this.world.getBlockState(this.pos), 3);
		if (!this.world.isRemote) {
			//WorldDataWarpPads.get(this.world).addWarpPadEntry(this.name, this.isPadValid, this.isClear, this.pos);
		}
	}
	protected boolean isStair(IBlockState state) {
		if (state.getBlock() != Blocks.QUARTZ_STAIRS || state.getValue(BlockStairs.HALF) != EnumHalf.BOTTOM) {
			return false;
		}
		return true;
	}
	protected boolean validateStairs() {
		IBlockState state = this.world.getBlockState(this.pos.add(2, 0, 0));
		if (state.getBlock() != Blocks.QUARTZ_STAIRS || state.getValue(BlockStairs.FACING) != EnumFacing.WEST || state.getValue(BlockStairs.HALF) != EnumHalf.BOTTOM || state.getValue(BlockStairs.SHAPE) != EnumShape.STRAIGHT) {
			//KAGICTech.instance.chatInfoMessage("Pad has no west quartz stair");	
			return false;
		}
		state = this.world.getBlockState(this.pos.add(-2, 0, 0));
		if (state.getBlock() != Blocks.QUARTZ_STAIRS || state.getValue(BlockStairs.FACING) != EnumFacing.EAST || state.getValue(BlockStairs.HALF) != EnumHalf.BOTTOM || state.getValue(BlockStairs.SHAPE) != EnumShape.STRAIGHT) {
			//KAGICTech.instance.chatInfoMessage("Pad has no east quartz stair");	
			return false;
		}
		state = this.world.getBlockState(this.pos.add(0, 0, 2));
		if (state.getBlock() != Blocks.QUARTZ_STAIRS || state.getValue(BlockStairs.FACING) != EnumFacing.NORTH || state.getValue(BlockStairs.HALF) != EnumHalf.BOTTOM || state.getValue(BlockStairs.SHAPE) != EnumShape.STRAIGHT) {
			//KAGICTech.instance.chatInfoMessage("Pad has no north quartz stair");	
			return false;
		}
		state = this.world.getBlockState(this.pos.add(0, 0, -2));
		if (state.getBlock() != Blocks.QUARTZ_STAIRS || state.getValue(BlockStairs.FACING) != EnumFacing.SOUTH || state.getValue(BlockStairs.HALF) != EnumHalf.BOTTOM || state.getValue(BlockStairs.SHAPE) != EnumShape.STRAIGHT) {
			//KAGICTech.instance.chatInfoMessage("Pad has no south quartz stair");	
			return false;
		}
		
		//Corners
		state = this.world.getBlockState(this.pos.add(-2, 0, -1));
		if (!isStair(state)) {
			//KAGICTech.instance.chatInfoMessage("Pad has no NW quartz stair");	
			return false;
		}
		state = state.withProperty(BlockStairs.FACING, EnumFacing.SOUTH);
		state = state.withProperty(BlockStairs.SHAPE, EnumShape.OUTER_LEFT);
		this.world.setBlockState(this.pos.add(-2, 0, -1), state);
		state = this.world.getBlockState(this.pos.add(-1, 0, -2));
		if (!isStair(state)) {
			//KAGICTech.instance.chatInfoMessage("Pad has no NW quartz stair");	
			return false;
		}
		state = state.withProperty(BlockStairs.FACING, EnumFacing.EAST);
		state = state.withProperty(BlockStairs.SHAPE, EnumShape.OUTER_RIGHT);
		this.world.setBlockState(this.pos.add(-1, 0, -2), state);

		state = this.world.getBlockState(this.pos.add(2, 0, -1));
		if (!isStair(state)) {
			//KAGICTech.instance.chatInfoMessage("Pad has no NE quartz stair");	
			return false;
		}
		state = state.withProperty(BlockStairs.FACING, EnumFacing.SOUTH);
		state = state.withProperty(BlockStairs.SHAPE, EnumShape.OUTER_RIGHT);
		this.world.setBlockState(this.pos.add(2, 0, -1), state);
		state = this.world.getBlockState(this.pos.add(1, 0, -2));
		if (!isStair(state)) {
			//KAGICTech.instance.chatInfoMessage("Pad has no NE quartz stair");	
		return false;
		}
		state = state.withProperty(BlockStairs.FACING, EnumFacing.WEST);
		state = state.withProperty(BlockStairs.SHAPE, EnumShape.OUTER_LEFT);
		this.world.setBlockState(this.pos.add(1, 0, -2), state);
		
		state = this.world.getBlockState(this.pos.add(2, 0, 1));
		if (!isStair(state)) {
			//KAGICTech.instance.chatInfoMessage("Pad has no SE quartz stair");	
			return false;
		}
		state = state.withProperty(BlockStairs.FACING, EnumFacing.NORTH);
		state = state.withProperty(BlockStairs.SHAPE, EnumShape.OUTER_LEFT);
		this.world.setBlockState(this.pos.add(2, 0, 1), state);
		state = this.world.getBlockState(this.pos.add(1, 0, 2));
		if (!isStair(state)) {
			//KAGICTech.instance.chatInfoMessage("Pad has no SE quartz stair");	
			return false;
		}
		state = state.withProperty(BlockStairs.FACING, EnumFacing.WEST);
		state = state.withProperty(BlockStairs.SHAPE, EnumShape.OUTER_RIGHT);
		this.world.setBlockState(this.pos.add(1, 0, 2), state);
		
		state = this.world.getBlockState(this.pos.add(-2, 0, 1));
		if (!isStair(state)) {
			//KAGICTech.instance.chatInfoMessage("Pad has no SW quartz stair");	
			return false;
		}
		state = state.withProperty(BlockStairs.FACING, EnumFacing.NORTH);
		state = state.withProperty(BlockStairs.SHAPE, EnumShape.OUTER_RIGHT);
		this.world.setBlockState(this.pos.add(-2, 0, 1), state);
		state = this.world.getBlockState(this.pos.add(-1, 0, 2));
		if (!isStair(state)) {
			//KAGICTech.instance.chatInfoMessage("Pad has no SW quartz stair");	
			return false;
		}
		state = state.withProperty(BlockStairs.FACING, EnumFacing.EAST);
		state = state.withProperty(BlockStairs.SHAPE, EnumShape.OUTER_LEFT);
		this.world.setBlockState(this.pos.add(-1, 0, 2), state);
		
		//KAGICTech.instance.chatInfoMessage("Pad has all quartz stairs");	
		return true;
	}
	
	protected boolean validatePad() {
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (j == 0 && i == 0) {
					continue;
				}
				IBlockState state = this.world.getBlockState(this.pos.add(i, 0, j));		
				if (state.getBlock() != Blocks.QUARTZ_BLOCK || state.getValue(BlockQuartz.VARIANT) != BlockQuartz.EnumType.LINES_Y) {
					//KAGICTech.instance.chatInfoMessage("Pad is NOT surrounded by vertical quartz columns");
					return false;
				}				
			}
		}

		//KAGICTech.instance.chatInfoMessage("Pad is surrounded by vertical quartz columns");
		return true && validateStairs();
	}
	
	private boolean validateClearance() {
		for (int i = -1; i <= 1; ++i) {
			for (int j = 1; j <= clearanceHeight; ++j) {
				for (int k = -1; k <= 1; ++k) {
					if (this.world.isSideSolid(this.pos.add(i, j, k), EnumFacing.UP)) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public boolean validateWarpPad() {
		boolean valid = this.validatePad();
		if (this.isPadValid != valid) {
			this.isPadValid = valid;
			this.setDirty();
		}
		
		boolean clear = this.validateClearance();
		if (this.isClear != clear) {
			this.isClear = clear;
			this.setDirty();
		}
		
		return valid && clear;
	}

	public boolean isValidPad() {
		return this.isPadValid && this.isClear;
	}
	
	public boolean isClear() {
		return this.isClear;
	}
	
	public boolean isValid() {
		return this.isPadValid;
	}
	
	@Override
	public void update() {
		if (!this.world.isRemote) {
			++(this.ticksSinceLastCheck);
			if (this.isValid() && (ticksSinceLastCheck & 20) == 0) {
				this.validateWarpPad();
				this.ticksSinceLastCheck = 0;
			}
		
			if (this.remainingActionTicks > 0) {
				--this.remainingActionTicks;
				if (this.remainingActionTicks <= 0) {
					this.warp();
				}
			} 
			
			if (this.remainingCooldownTicks >= 0) {
				--this.remainingCooldownTicks;
			} else if (this.cooling) {
				this.cooling = false;
				this.setDirty();
			}
			
			if (this.ticket != null) {
				--this.remainingLoadingChunkTicks;
			}
			if (this.remainingLoadingChunkTicks < 0 && this.ticket != null) {
				//KAGIC.instance.chatInfoMessage("Releasing ticket");
				ForgeChunkManager.releaseTicket(ticket);
				this.ticket = null;
				this.remainingLoadingChunkTicks = 0;
			}
		}
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound){
		super.writeToNBT(compound);
		compound.setBoolean("valid", this.isPadValid);
		compound.setBoolean("clear", this.isClear);
		compound.setBoolean("warping", this.warping);
		compound.setBoolean("cooling", this.cooling);
		compound.setString("name", this.name);
		compound.setInteger("renderTicks", this.remainingActionTicks);
		return compound;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.isPadValid = compound.getBoolean("valid");
		this.isClear = compound.getBoolean("clear");
		this.warping = compound.getBoolean("warping");
		if (this.warping == false) {
			this.renderCooldown = 0;
		}
		this.cooling = compound.getBoolean("cooling");
		this.name = compound.getString("name");
		this.renderTicks = compound.getInteger("renderTicks");
	}
	
	@Override
	public NBTTagCompound getUpdateTag() {
		return this.writeToNBT(new NBTTagCompound());
	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(this.pos, 1, this.writeToNBT(new NBTTagCompound()));
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
		this.readFromNBT(packet.getNbtCompound());
	}
	
	public void setName(String name) {
		if (!this.world.isRemote) {
			System.out.println("Name Set");
			this.name = name;
			this.validateWarpPad();
			this.setDirty();
		} else {
			System.out.println("ERROR: setName called on client");
		}
	}
	public boolean canInteractWith(EntityPlayer player) {
		return !this.isInvalid() && player.getDistanceSq(this.pos.add(0.5D, 0.5D, 0.5D)) <= 3.0D;
	}
	public void destroy() {
		//WorldDataWarps.get(this.world).removeWarpPadEntry(new WarpPadPos(this.pos, ));
	}
	public void loadPadChunks(TileEntityWarpPadCore pad, Ticket ticket) {
		if (ticket == null) {
			System.out.println("WARNING: warp pad could not load pad chunks. Strange glitches may occur!");
			return;
		}
        int xStart = (pad.getPos().getX() - 2) >> 4;
        int zStart = (pad.getPos().getZ() - 2) >> 4;
        int xEnd = (pad.getPos().getX() + 2) >> 4;
        int zEnd = (pad.getPos().getZ() + 2) >> 4;
        for (int i = xStart; i <= xEnd; ++i) {
            for (int j = zStart; j <= zEnd; ++j) {
            	//KAGIC.instance.chatInfoMessage("Loading chunk " + i + ", " + j);
        		ForgeChunkManager.forceChunk(ticket, new ChunkPos(i, j));
            }
        }
        this.ticket = ticket;
        this.remainingLoadingChunkTicks = TileEntityWarpPadCore.TICKS_FOR_LOADING_CHUNKS;
	}
	public void beginWarp(BlockPos destination) {
		Ticket ticket = ForgeChunkManager.requestTicket(KAGIC.instance, this.world, Type.NORMAL);
		TileEntityWarpPadCore destPad = (TileEntityWarpPadCore) this.world.getTileEntity(destination);
		this.loadPadChunks(this, ticket);
		this.loadPadChunks(destPad, ticket);
		this.remainingActionTicks = TileEntityWarpPadCore.TICKS_FOR_ACTION;
		this.destination = destination;
		this.warping = true;
		this.setDirty();
		//this.world.playSound(null, this.pos, Ke2Sounds.WARP_PAD, SoundCategory.BLOCKS, 20.0f, 1.0f);
	}
	public void warp() {
		BlockPos minorCorner = new BlockPos(this.pos.getX() - 1, this.pos.getY() + 1, this.pos.getZ() - 1);
		BlockPos majorCorner = new BlockPos(this.pos.getX() + 2, this.pos.getY() + 5, this.pos.getZ() + 2);
		AxisAlignedBB warpArea = new AxisAlignedBB(minorCorner, majorCorner);
		List<Entity> entitiesToWarp = this.world.getEntitiesWithinAABB(Entity.class, warpArea);
		Iterator<Entity> it = entitiesToWarp.iterator();
		TileEntityWarpPadCore destPad = (TileEntityWarpPadCore) this.world.getTileEntity(this.destination);
		if (destPad == null || !destPad.isValidPad()) {
			this.remainingCooldownTicks = 1;
			this.warping = false;
			this.cooling = true;
			return;
		}
		ChunkPos cPos = destPad.world.getChunkFromBlockCoords(destPad.pos).getPos();
		while (it.hasNext()) {
			Entity entity = it.next();
			double posX = this.destination.getX() + entity.posX - this.pos.getX();
			double posY = this.destination.getY() + entity.posY - this.pos.getY();
			double posZ = this.destination.getZ() + entity.posZ - this.pos.getZ();
			if (entity instanceof EntityPlayerMP) {
				entity.setPositionAndUpdate(posX, posY, posZ);
			}
			else if (entity instanceof EntityLivingBase) {
				entity.setPositionAndUpdate(posX, posY, posZ);
			}
			else {
				entity.setLocationAndAngles(posX, posY, posZ, entity.rotationYaw, entity.rotationPitch);
				entity.setRotationYawHead(entity.rotationYaw);
			}
			for (EntityPlayer player : ((WorldServer) this.world).getEntityTracker().getTrackingPlayers(entity)) {
				Ke2Messages.INSTANCE.sendTo(new PacketEntityTeleport(entity.getEntityId(), posX, posY, posZ), (EntityPlayerMP) player);
			}
		}
		this.warping = false;
		this.cooling = true;
		this.remainingCooldownTicks = TileEntityWarpPadCore.TICKS_FOR_COOLDOWN;
		this.setDirty();
	}
	public static TileEntityWarpPadCore getEntityPad(Entity entityIn) {
		for (int i = -1; i <= 1; ++i) {
			for (int j = -1; j <= 1; ++j) {
				TileEntity te =  entityIn.getEntityWorld().getTileEntity(new BlockPos(entityIn.posX + i, entityIn.posY - 1, entityIn.posZ + j));
				if (te instanceof TileEntityWarpPadCore) {
					return (TileEntityWarpPadCore) te;
				}
			}
		}
		return null;
	}
	public boolean isWarping() {
		return this.warping || this.cooling;
	}
	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getRenderBoundingBox() {
		return new AxisAlignedBB(this.pos.add(-1, 0, -1), this.pos.add(1, 6, 1));
	}
}