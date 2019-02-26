package mod.kagic.entity.gem;

import java.util.ArrayList;

import mod.kagic.entity.EntityGemGarnet;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityHessonite extends EntityGemGarnet {
	public static final ArrayList<ResourceLocation> INSIGNIAS = new ArrayList<ResourceLocation>();
	public static final ArrayList<ResourceLocation> UNIFORMS = new ArrayList<ResourceLocation>();
	static {
		
	}
	public EntityHessonite(World world) {
		super(world);
	}
	@Override
	public int generateSkinColor() {
		return 0;
	}
	@Override
	public int generateHairColor() {
		return 0;
	}
	@Override
	public int generateGemstoneColor() {
		return 0;
	}
	@Override
	public int generateInsigniaVariant() {
		return 0;
	}
	@Override
	public int generateUniformVariant() {
		return 0;
	}
	@Override
	public int generateHairVariant() {
		return 0;
	}
	@Override
	public int generateSkinVariant() {
		return 0;
	}
	@Override
	public int generateGemstonePosition() {
		return 0;
	}
	@Override
	public int generateGemstoneCut() {
		return 0;
	}
	@Override
	public boolean generateVisor() {
		return false;
	}
	@Override
	public void onInventoryChanged(IInventory inventory) {
		
	}
}