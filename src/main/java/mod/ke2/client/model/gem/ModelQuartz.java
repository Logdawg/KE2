package mod.ke2.client.model.gem;

import mod.ke2.client.model.ModelGem;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelQuartz extends ModelGem {
	private ModelRenderer bipedTopBun;
	private ModelRenderer bipedSideBuns;
	private ModelRenderer bipedBackBun;
	public ModelQuartz() {
		super(0.0F, 0.0F, 64, 64, 4);
		this.bipedHead = new ModelRenderer(this, 0, 0);
		this.bipedHead.addBox(-4.0F, -12.0F, -4.0F, 8, 8, 8);
		this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bipedHeadwear = new ModelRenderer(this, 32, 0);
		this.bipedHeadwear.addBox(-4.0F, -12.0F, -4.0F, 8, 8, 8, 1.1F);
		this.bipedHeadwear.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bipedSideBuns = new ModelRenderer(this, 0, 56);
		this.bipedSideBuns.addBox(-9.0F, -10.0F, -2.0F, 18, 4, 4);
		this.bipedSideBuns.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bipedTopBun = new ModelRenderer(this, 48, 54);
		this.bipedTopBun.addBox(-2.0F, -17.0F, -2.0F, 4, 4, 4);
		this.bipedTopBun.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bipedBackBun = new ModelRenderer(this, 16, 38);
		this.bipedBackBun.addBox(-2.0F, -10.0F, 5.0F, 4, 4, 4);
		this.bipedBackBun.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bipedBody = new ModelRenderer(this, 16, 16);
		this.bipedBody.addBox(-5.0F, -4.0F, -3.0F, 10, 16, 6);
		this.bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bipedRightArm = new ModelRenderer(this, 48, 16);
		this.bipedRightArm.addBox(-4.0F, -4.0F, -2.0F, 4, 16, 4);
		this.bipedRightArm.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bipedLeftArm = new ModelRenderer(this, 48, 34);
		this.bipedLeftArm.addBox(0.0F, -4.0F, -2.0F, 4, 16, 4);
		this.bipedLeftArm.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bipedRightLeg = new ModelRenderer(this, 0, 16);
		this.bipedRightLeg.addBox(1.0F, 0.0F, -2.0F, 4, 16, 4);
		this.bipedRightLeg.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bipedLeftLeg = new ModelRenderer(this, 0, 32);
		this.bipedLeftLeg.addBox(-5.0F, 0.0F, -2.0F, 4, 16, 4);
		this.bipedLeftLeg.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bipedCape = new ModelRenderer(this, 0, 0);
		this.bipedCape.setTextureSize(64, 32);
		this.bipedCape.addBox(-5.0F, -4.0F, -2.0F, 10, 20, 1);
	}
	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
		this.bipedHead.render(scale);
		this.bipedHeadwear.render(scale);
		this.bipedSideBuns.render(scale);
		this.bipedTopBun.render(scale);
		this.bipedBackBun.render(scale);
		this.bipedBody.render(scale);
		this.bipedRightArm.render(scale);
		this.bipedLeftArm.render(scale);
		this.bipedRightLeg.render(scale);
		this.bipedLeftLeg.render(scale);
		this.bipedCape.render(scale);
	}
	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity);
		super.copyModelAngles(this.bipedHead, this.bipedSideBuns);
		super.copyModelAngles(this.bipedHead, this.bipedTopBun);
		super.copyModelAngles(this.bipedHead, this.bipedBackBun);
	}
}
