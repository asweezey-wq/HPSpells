package com.apmods.hpspells.client;

import com.apmods.hpspells.main.HPSpells;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderDeathEater extends RenderBiped{

	ResourceLocation text = new ResourceLocation(HPSpells.MODID + ":textures/entity/death_eater.png");
	
	public RenderDeathEater(ModelBiped p_i1257_1_, float p_i1257_2_) {
		super(p_i1257_1_, p_i1257_2_);
		// TODO Auto-generated constructor stub
	}
	protected ResourceLocation getEntityTexture(Entity p_110775_1_)
    {
		return text;
    }

}
