package mod.ke2.client.model.gem;

import org.lwjgl.opengl.GL11;

import mod.ke2.client.model.ModelGem;
import mod.ke2.init.Ke2Gems;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelCoral extends ModelGem {
	public ModelRenderer bipedHair;
	public ModelRenderer bipedTorso;
	public ModelRenderer bipedNeck;

	public ModelCoral() {
		super(0.0F, 0.0F, 72, 64);
		this.bipedHeadwear = new ModelRenderer(this, 0, 0);
		this.bipedHeadwear.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8, 0.0F);
		this.bipedHeadwear.offsetY = -0.125F;
		this.bipedHair = new ModelRenderer(this, 32, 3);
		this.bipedHair.addBox(-3.0F, -5.0F, 2.0F, 6, 7, 6, 0.0F);
		this.bipedHeadwear.addChild(this.bipedHair);
		this.bipedLeftArm = new ModelRenderer(this, 0, 16);
		this.bipedLeftArm.addBox(-2.0F, 2.0F, -1.0F, 2, 12, 2, 0.0F);
		this.bipedRightArm = new ModelRenderer(this, 0, 30);
		this.bipedRightArm.addBox(0.0F, 2.0F, -1.0F, 2, 12, 2, 0.0F);
		this.bipedBody = new ModelRenderer(this, 8, 16);
		this.bipedBody.addBox(-3.0F, 2.0F, -2.0F, 6, 10, 4, 0.0F);
		this.bipedLeftLeg = new ModelRenderer(this, 28, 16);
		this.bipedLeftLeg.addBox(-3.0F, 0.0F, -1.0F, 2, 12, 2, 0.0F);
		this.bipedRightLeg = new ModelRenderer(this, 28, 30);
		this.bipedRightLeg.addBox(1.0F, 0.0F, -1.0F, 2, 12, 2, 0.0F);
		this.bipedHead = new ModelRenderer(this, 0, 52);
		this.bipedHead.setRotationPoint(0.0F, 3.3F, 0.0F);
		this.bipedHead.addBox(-3.0F, -5.0F, -3.0F, 6, 6, 6, 0.0F);
		this.bipedTorso = new ModelRenderer(this, 24, 54);
		this.bipedTorso.setRotationPoint(0.0F, 13.5F, 0.0F);
		this.bipedTorso.addBox(-2.0F, -10.5F, -1.0F, 4, 8, 2, 0.0F);
		this.bipedNeck = new ModelRenderer(this, 36, 61);
		this.bipedNeck.setRotationPoint(0.0F, 3.0F, 0.0F);
		this.bipedNeck.addBox(-0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		super.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		this.bipedHead.render(scale);
		this.bipedNeck.render(scale);
		this.bipedTorso.render(scale);
		GlStateManager.enableBlend();
		ModelBase.copyModelAngles(this.bipedHead, this.bipedHeadwear);
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 0.25F);
		this.bipedHeadwear.render(scale);
		this.bipedBody.render(scale);
		this.bipedRightArm.render(scale);
		this.bipedLeftArm.render(scale);
		this.bipedRightLeg.render(scale);
		this.bipedLeftLeg.render(scale);
		GlStateManager.disableBlend();
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity);
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