package mod.ke2.entity.gem;

import com.google.common.base.Predicate;

import mod.ke2.api.EntityGem;
import mod.ke2.api.variants.types.VariantColor;
import mod.ke2.api.variants.types.VariantPath;
import mod.ke2.entity.gem.ai.EntityAIDefendTheLeader;
import mod.ke2.entity.gem.ai.EntityAIEliminateThreats;
import mod.ke2.entity.gem.ai.EntityAIFightForLeader;
import mod.ke2.init.Ke2Sounds;
import mod.ke2.init.Ke2Variants;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.IMob;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityRuby extends EntityGem {
	static {
		Ke2Variants.addVariantToGem(Ke2Variants.registerVariant(new ResourceLocation("ke2:variants/ruby/hair_default"), VariantPath.class), EntityRuby.class);
		Ke2Variants.addVariantToGem(Ke2Variants.registerVariant(new ResourceLocation("ke2:variants/ruby/hair_forehead"), VariantPath.class), EntityRuby.class);
		Ke2Variants.addVariantToGem(Ke2Variants.registerVariant(new ResourceLocation("ke2:variants/ruby/outfit_default"), VariantPath.class), EntityRuby.class);
		Ke2Variants.addVariantToGem(Ke2Variants.registerVariant(new ResourceLocation("ke2:variants/ruby/outfit_doc"), VariantPath.class), EntityRuby.class);
		Ke2Variants.addVariantToGem(Ke2Variants.registerVariant(new ResourceLocation("ke2:variants/ruby/outfit_navy"), VariantPath.class), EntityRuby.class);
		Ke2Variants.addVariantToGem(Ke2Variants.registerVariant(new ResourceLocation("ke2:variants/ruby/skin_default"), VariantPath.class), EntityRuby.class);
		Ke2Variants.addVariantToGem(Ke2Variants.registerVariant(new ResourceLocation("ke2:variants/ruby/color_gemstone"), VariantColor.class), EntityRuby.class);
		Ke2Variants.addVariantToGem(Ke2Variants.registerVariant(new ResourceLocation("ke2:variants/ruby/color_hair"), VariantColor.class), EntityRuby.class);
		Ke2Variants.addVariantToGem(Ke2Variants.registerVariant(new ResourceLocation("ke2:variants/ruby/color_outfit"), VariantColor.class), EntityRuby.class);
		Ke2Variants.addVariantToGem(Ke2Variants.registerVariant(new ResourceLocation("ke2:variants/ruby/color_skin"), VariantColor.class), EntityRuby.class);
		Ke2Variants.addVariantToGem(Ke2Variants.registerVariant(new ResourceLocation("ke2:variants/ruby/color_visor"), VariantColor.class), EntityRuby.class);
	}

	public EntityRuby(World world) {
		super(world);
		this.targetTasks.addTask(1, new EntityAIDefendTheLeader(this));
		this.targetTasks.addTask(2, new EntityAIFightForLeader(this));
		this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, false, new Class[0]));
		//this.targetTasks.addTask(4, new EntityAIEliminateThreats(this));
	}

	@Override
	public void onInventoryChanged(IInventory inventory) {

	}

	@Override
	public int generateGemstoneCut() {
		return 1;
	}
	
	@Override
	public SoundEvent getGemSound() {
		return Ke2Sounds.GEM_RUBY;
	}
}
