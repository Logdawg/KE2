package mod.ke2.entity.gem;

import java.util.ArrayList;

import mod.ke2.api.EntityGem;
import mod.ke2.api.variants.VariantHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public abstract class AbstractGarnet extends EntityGem {
	public static final ArrayList<Class<? extends AbstractGarnet>> GLOBAL_VARIANT_CLASSES = new ArrayList<Class<? extends AbstractGarnet>>();
	public static final ArrayList<ResourceLocation> GLOBAL_VARIANT_PATHS = new ArrayList<ResourceLocation>();
	static {
		AbstractGarnet.GLOBAL_VARIANT_CLASSES.add(EntityDemantoid.class);
		AbstractGarnet.GLOBAL_VARIANT_CLASSES.add(EntityHessonite.class);
		AbstractGarnet.GLOBAL_VARIANT_CLASSES.add(EntityMelanite.class);
		AbstractGarnet.GLOBAL_VARIANT_CLASSES.add(EntityPyrope.class);
	}

	public AbstractGarnet(World world) {
		super(world);
		this.setSize(0.9F, 2.3F);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
	}

	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
		if (this.isPerfective()) {
			this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(16.0D);
			this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(180.0D);
		} else if (this.isDefective()) {
			this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8.0D);
			this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60.0D);
		} else {
			this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(12.0D);
			this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(120.0D);
		}
		return super.onInitialSpawn(difficulty, livingdata);
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (source instanceof EntityDamageSourceIndirect && this.teleportToEntity(source.getTrueSource())) {
			return true;
		}
		return super.attackEntityFrom(source, amount);
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		boolean attacked = super.attackEntityAsMob(entity);
		if (entity instanceof EntityLivingBase) {
			EntityLivingBase target = (EntityLivingBase) entity;
			if (attacked && this.rand.nextFloat() < this.getDisarmChance()) {
				this.disarm(target);
			}
		}
		return attacked;
	}

	private float getDisarmChance() {
		return this.isPerfective() ? 1.0F : this.isDefective() ? 0.0F : 0.5F;
	}

	public boolean disarm(EntityLivingBase enemy) {
		if (enemy.hasItemInSlot(EntityEquipmentSlot.MAINHAND)) {
			ItemStack stack = enemy.getHeldItemMainhand();
			if (stack.isItemStackDamageable()) {
				stack.setItemDamage(stack.getMaxDamage() - enemy.getRNG().nextInt(1 + enemy.getRNG().nextInt(Math.max(stack.getMaxDamage() - 3, 1))));
			}
			enemy.entityDropItem(stack, enemy.height / 2);
			enemy.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemStack.EMPTY);
			return true;
		} else {
			return false;
		}
	}

	private boolean teleportToEntity(Entity target) {
		Vec3d vec3d = new Vec3d(this.posX - target.posX, this.getEntityBoundingBox().minY + this.height / 2.0F - target.posY + target.getEyeHeight(), this.posZ - target.posZ);
		if (vec3d.lengthVector() < 4F) {
			return false;
		}
		double dX = target.posX + (this.rand.nextDouble() - 0.5D) * 2.0D;
		double dY = target.posY + 1;
		double dZ = target.posZ + (this.rand.nextDouble() - 0.5D) * 2.0D;
		return this.attemptTeleport(dX, dY, dZ);
	}

	@Override
	protected boolean canEquipItem(ItemStack stack) {
		Item weapon = stack.getItem();
		if (weapon instanceof ItemSword || weapon instanceof ItemTool || weapon instanceof ItemBow) {
			return true;
		} else {
			return false;
		}
	}

	public String getGarnetModel() {
		return "ke2:garnet.model.fighter";
	}

	@Override
	public int generateSkinColor() {
		return VariantHelper.loadVariantColor(this, "ke2:color.skin", this.getGarnetModel());
	}

	@Override
	public int generateHairColor() {
		return VariantHelper.loadVariantColor(this, "ke2:color.hair", this.getGarnetModel());
	}

	@Override
	public int generateOutfitColor() {
		return VariantHelper.loadVariantColor(this, "ke2:color.outfit", this.getGarnetModel());
	}

	@Override
	public int generateVisorColor() {
		return VariantHelper.loadVariantColor(this, "ke2:color.visor", this.getGarnetModel());
	}

	@Override
	public int generateGemstoneColor() {
		return VariantHelper.loadVariantColor(this, "ke2:color.gemstone", this.getGarnetModel());
	}

	@Override
	public String generateNameVariant() {
		return VariantHelper.loadVariantName(this, "ke2:name", this.getGarnetModel());
	}

	@Override
	public boolean canPickUpLoot() {
		return true;
	}
}
