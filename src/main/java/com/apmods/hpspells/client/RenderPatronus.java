package com.apmods.hpspells.client;

import com.apmods.hpspells.entity.EntityPatronus;
import com.apmods.hpspells.main.HPSpells;
import com.apmods.hpspells.proxy.ClientProxy;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderPatronus extends RenderLiving {
	private ResourceLocation tex = new ResourceLocation(HPSpells.MODID + ":textures/entity/patronus.png");

	public RenderPatronus(float p_i1265_3_) {
		super(ClientProxy.models[0], p_i1265_3_);
		this.setRenderPassModel(ClientProxy.models[0]);
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called
	 * unless you call Render.bindEntityTexture.
	 */
	protected ResourceLocation getEntityTexture(EntityPatronus patronus) {
		return tex;
	}

	@Override
	protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_) {
		this.preRenderCallback((EntityPatronus) p_77041_1_, p_77041_2_);
	}

	protected void preRenderCallback(EntityPatronus patronus, float p_77041_2_) {
		this.mainModel = ClientProxy.models[patronus.getAnimalIndex()];
		if (patronus.getAnimalIndex() == 1) {
			this.setRenderPassModel(new ModelPatronusSheep2());
		} else {
			this.setRenderPassModel(ClientProxy.models[patronus.getAnimalIndex()]);
		}

	}

	@Override
	protected int shouldRenderPass(EntityLivingBase p_77032_1_, int p_77032_2_, float p_77032_3_) {
		return this.shouldRenderPass((EntityPatronus) p_77032_1_, p_77032_2_, p_77032_3_);
	}

	protected int shouldRenderPass(EntityPatronus p_77032_1_, int p_77032_2_, float p_77032_3_) {
		return -1;
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called
	 * unless you call Render.bindEntityTexture.
	 */
	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return this.getEntityTexture((EntityPatronus) p_110775_1_);
	}
}
