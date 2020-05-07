package com.apmods.hpspells.client;

import com.apmods.hpspells.main.HPSpells;

import net.minecraft.client.model.ModelVillager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderWandmaker extends RenderLiving{

    private static final ResourceLocation villagerTextures = new ResourceLocation(HPSpells.MODID + ":textures/entity/wandmaker.png");
	
	public RenderWandmaker() {
		super(new ModelVillager(0.0f), 0.5f);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return villagerTextures;
	}
}
