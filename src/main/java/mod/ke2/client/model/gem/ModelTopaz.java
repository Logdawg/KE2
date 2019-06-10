package mod.ke2.client.model.gem;

import mod.ke2.client.model.ModelGem;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTopaz extends ModelGem {
	public ModelTopaz() {
		super(0.0F, 0.0F, 64, 64);
		this.bipedHead = new ModelRenderer(this, 0, 0);
		this.bipedHead.addBox(-5.0F, -11.0F, -5.0F, 10, 9, 10, 0.0F);
		this.bipedHead.offsetY = -1.375F;
		this.bipedHeadwear = new ModelRenderer(this, 38, 4);
		this.bipedHeadwear.addBox(-5.0F, -14.0F, -6.0F, 10, 3, 11, 0.1F);
		this.bipedHead.addChild(this.bipedHeadwear);
		this.bipedRightArm = new ModelRenderer(this, 0, 58);
		this.bipedRightArm.addBox(-9.0F, 0.0F, -4.0F, 6, 32, 8, 0.0F);
		this.bipedRightArm.offsetY = -1.5F;
		this.bipedLeftArm = new ModelRenderer(this, 0, 18);
		this.bipedLeftArm.addBox(3.0F, 0.0F, -4.0F, 6, 32, 8, 0.0F);
		this.bipedLeftArm.offsetY = -1.5F;
		this.bipedBody = new ModelRenderer(this, 28, 18);
		this.bipedBody.addBox(-8.0F, -24.0F, -6.0F, 16, 32, 12, 0.0F);
		this.bipedBody.setRotationPoint(0F, 0F, 0F);
		this.bipedRightLeg = new ModelRenderer(this, 84, 18);
		this.bipedRightLeg.addBox(-8.0F, -4.0F, -3.0F, 6, 16, 6, 0.0F);
		this.bipedLeftLeg = new ModelRenderer(this, 84, 40);
		this.bipedLeftLeg.addBox(2.0F, -4.0F, -3.0F, 6, 16, 6, 0.0F);
	}
	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		super.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		this.bipedHead.render(scale);
		this.bipedBody.render(scale);
		this.bipedRightArm.render(scale);
		this.bipedLeftArm.render(scale);
		this.bipedRightLeg.render(scale);
		this.bipedLeftLeg.render(scale);
	}
	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity);
		this.bipedHeadwear.rotateAngleX = 0.0F;
		this.bipedHeadwear.rotateAngleY = 0.0F;
	}
}
