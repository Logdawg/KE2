package mod.ke2.client.model.gem;

import mod.ke2.client.model.ModelGem;
import mod.ke2.init.Ke2Gems;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRutile extends ModelGem {
	public ModelRutile() {
		super(0.0F, 0.0F, 72, 64);
		this.bipedHead = new ModelRenderer(this, 0, 0);
		this.bipedHead.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8, 0.0F);
		this.bipedHead.offsetY = -0.375F;
		this.bipedHeadwear = new ModelRenderer(this, 32, 3);
		this.bipedHeadwear.addBox(-4.0F, -8.0F, -5.0F, 8, 4, 9, 0.1F);
		this.bipedHead.addChild(this.bipedHeadwear);
		this.bipedLeftArm = new ModelRenderer(this, 0, 16);
		this.bipedLeftArm.addBox(-2.0F, 0.0F, -1.0F, 2, 14, 2, 0.0F);
		this.bipedLeftArm.offsetY = -0.125F;
		this.bipedRightArm = new ModelRenderer(this, 0, 32);
		this.bipedRightArm.addBox(0.0F, 0.0F, -1.0F, 2, 14, 2, 0.0F);
		this.bipedRightArm.offsetY = -0.125F;
		this.bipedBody = new ModelRenderer(this, 8, 16);
		this.bipedBody.addBox(-3.0F, -2.0F, -2.0F, 6, 12, 4, 0.0F);
		this.bipedLeftLeg = new ModelRenderer(this, 28, 16);
		this.bipedLeftLeg.addBox(-3.0F, 0.0F, -1.0F, 2, 14, 2, 0.0F);
		this.bipedLeftLeg.offsetY = -0.125F;
		this.bipedRightLeg = new ModelRenderer(this, 28, 32);
		this.bipedRightLeg.addBox(1.0F, 0.0F, -1.0F, 2, 14, 2, 0.0F);
		this.bipedRightLeg.offsetY = -0.125F;
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		super.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		this.bipedHead.render(scale);
		this.bipedLeftArm.render(scale);
		this.bipedRightArm.render(scale);
		this.bipedBody.render(scale);
		this.bipedLeftLeg.render(scale);
		this.bipedRightLeg.render(scale);
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity);
		this.bipedHeadwear.rotateAngleX = 0.0F;
		this.bipedHeadwear.rotateAngleY = 0.0F;
	}
	
	@Override
	public void preGemRenderCallback(int pos, float scale) {
		switch (pos) {
			case Ke2Gems.GEMSTONE_FOREHEAD :
				break;
			case Ke2Gems.GEMSTONE_BACK_OF_HEAD :
				break;
			case Ke2Gems.GEMSTONE_RIGHT_EYE :
				break;
			case Ke2Gems.GEMSTONE_LEFT_EYE :
				break;
			case Ke2Gems.GEMSTONE_NOSE :
				break;
			case Ke2Gems.GEMSTONE_RIGHT_CHEEK :
				break;
			case Ke2Gems.GEMSTONE_LEFT_CHEEK :
				break;
			case Ke2Gems.GEMSTONE_RIGHT_SHOULDER :
				break;
			case Ke2Gems.GEMSTONE_LEFT_SHOULDER :
				break;
			case Ke2Gems.GEMSTONE_RIGHT_HAND :
				break;
			case Ke2Gems.GEMSTONE_LEFT_HAND :
				break;
			case Ke2Gems.GEMSTONE_CHEST :
				break;
			case Ke2Gems.GEMSTONE_BACK :
				break;
			case Ke2Gems.GEMSTONE_NAVEL :
				break;
			case Ke2Gems.GEMSTONE_RIGHT_THIGH :
				break;
			case Ke2Gems.GEMSTONE_LEFT_THIGH :
				break;
			case Ke2Gems.GEMSTONE_RIGHT_KNEE :
				break;
			case Ke2Gems.GEMSTONE_LEFT_KNEE :
				break;
			case Ke2Gems.GEMSTONE_RIGHT_FOOT :
				break;
			case Ke2Gems.GEMSTONE_LEFT_FOOT :
				break;
		}
	}

	@Override
	public float getGemRenderScale(int pos) {
		switch (pos) {
			case Ke2Gems.GEMSTONE_FOREHEAD :
				return 1.0F;
			case Ke2Gems.GEMSTONE_BACK_OF_HEAD :
				return 1.0F;
			case Ke2Gems.GEMSTONE_RIGHT_EYE :
				return 1.0F;
			case Ke2Gems.GEMSTONE_LEFT_EYE :
				return 1.0F;
			case Ke2Gems.GEMSTONE_NOSE :
				return 1.0F;
			case Ke2Gems.GEMSTONE_RIGHT_CHEEK :
				return 1.0F;
			case Ke2Gems.GEMSTONE_LEFT_CHEEK :
				return 1.0F;
			case Ke2Gems.GEMSTONE_RIGHT_SHOULDER :
				return 1.0F;
			case Ke2Gems.GEMSTONE_LEFT_SHOULDER :
				return 1.0F;
			case Ke2Gems.GEMSTONE_RIGHT_HAND :
				return 1.0F;
			case Ke2Gems.GEMSTONE_LEFT_HAND :
				return 1.0F;
			case Ke2Gems.GEMSTONE_CHEST :
				return 1.0F;
			case Ke2Gems.GEMSTONE_BACK :
				return 1.0F;
			case Ke2Gems.GEMSTONE_NAVEL :
				return 1.0F;
			case Ke2Gems.GEMSTONE_RIGHT_THIGH :
				return 1.0F;
			case Ke2Gems.GEMSTONE_LEFT_THIGH :
				return 1.0F;
			case Ke2Gems.GEMSTONE_RIGHT_KNEE :
				return 1.0F;
			case Ke2Gems.GEMSTONE_LEFT_KNEE :
				return 1.0F;
			case Ke2Gems.GEMSTONE_RIGHT_FOOT :
				return 1.0F;
			case Ke2Gems.GEMSTONE_LEFT_FOOT :
				return 1.0F;
		}
		return 1.0F;
	}
}
