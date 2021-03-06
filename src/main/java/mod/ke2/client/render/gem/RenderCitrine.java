package mod.ke2.client.render.gem;

import mod.ke2.api.EntityGem;
import mod.ke2.client.model.gem.ModelQuartz;
import mod.ke2.client.render.RenderGem;
import mod.ke2.client.render.gem.layers.LayerEyes;
import mod.ke2.client.render.gem.layers.LayerFlower;
import mod.ke2.client.render.gem.layers.LayerGemstone;
import mod.ke2.client.render.gem.layers.LayerHair;
import mod.ke2.client.render.gem.layers.LayerHeldItem;
import mod.ke2.client.render.gem.layers.LayerInsignia;
import mod.ke2.client.render.gem.layers.LayerJacket;
import mod.ke2.client.render.gem.layers.LayerNoDyeOverlay;
import mod.ke2.client.render.gem.layers.LayerSkin;
import mod.ke2.client.render.gem.layers.LayerUniform;
import mod.ke2.client.render.gem.layers.LayerVisor;
import mod.ke2.entity.gem.EntityCitrine;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderCitrine<T extends EntityCitrine> extends RenderGem<T> {
	public RenderCitrine(RenderManager manager) {
		super(manager, new ModelQuartz(), 0.5F);
		this.addLayer(new LayerSkin(this));
		this.addLayer(new LayerEyes(this));
		this.addLayer(new LayerUniform(this));
		this.addLayer(new LayerInsignia(this));
		this.addLayer(new LayerHair(this));
		this.addLayer(new LayerVisor(this));
		this.addLayer(new LayerNoDyeOverlay(this));
		this.addLayer(new LayerJacket(this));
		this.addLayer(new LayerGemstone(this));
		this.addLayer(new LayerHeldItem(this));
		this.addLayer(new LayerFlower(this));
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityGem gem) {
		return new ResourceLocation("ke2:textures/entities/quartz/quartz.png");
	}
}
