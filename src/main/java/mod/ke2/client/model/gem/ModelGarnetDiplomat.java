package mod.ke2.client.model.gem;

import mod.ke2.client.model.ModelGem;
import mod.ke2.init.Ke2Gems;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelGarnetDiplomat extends ModelGem {
	public ModelRenderer bipedNeck;

	public ModelGarnetDiplomat() {
		super(0.0F, 0.0F, 80, 64);
		this.bipedHead = new ModelRenderer(this, 0, 0);
		this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
		this.bipedHead.offsetY = -0.5625F;
		this.bipedHeadwear = new ModelRenderer(this, 46, 0);
		this.bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 16, 8, 0.5F);
		this.bipedHead.addChild(this.bipedHeadwear);
		this.bipedRightArm = new ModelRenderer(this, 0, 36);
		this.bipedRightArm.addBox(-2.0F, 2.0F, -1.5F, 3, 16, 3, 0.0F);
		this.bipedRightArm.offsetY = -0.5F;
		this.bipedLeftArm = new ModelRenderer(this, 0, 16);
		this.bipedLeftArm.addBox(-1.0F, 2.0F, -1.5F, 3, 16, 3, 0.0F);
		this.bipedLeftArm.offsetY = -0.5F;
		this.bipedBody = new ModelRenderer(this, 12, 26);
		this.bipedBody.addBox(-4.0F, -6.0F, -3.0F, 8, 10, 6, 0.0F);
		this.bipedNeck = new ModelRenderer(this, 20, 16);
		this.bipedNeck.addBox(-1.5F, -9.0F, -1.5F, 3, 3, 3);
		this.bipedBody.addChild(this.bipedNeck);
		this.bipedSkirt = new ModelRenderer(this, 8, 44);
		this.bipedSkirt.addBox(-5.0F, 4.0F, -5.0F, 10, 20, 10, 0.0F);
		this.bipedBody.addChild(this.bipedSkirt);
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		super.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		this.bipedHead.render(scale);
		this.bipedRightArm.render(scale);
		this.bipedLeftArm.render(scale);
		this.bipedBody.render(scale);
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