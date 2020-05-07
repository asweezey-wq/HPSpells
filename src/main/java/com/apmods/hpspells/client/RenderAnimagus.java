package com.apmods.hpspells.client;

import com.apmods.hpspells.item.ItemInvisibilityCloak;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class RenderAnimagus extends RenderLiving{

	public ResourceLocation texture;
	
	public RenderAnimagus(ModelBase model, RendererLivingEntity render, float p_i1262_2_) {
		super(model, p_i1262_2_);
	}
	
	@Override
	public void doRender(EntityLivingBase p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
		if(p_76986_1_.getEquipmentInSlot(1) != null && p_76986_1_.getEquipmentInSlot(1).getItem() instanceof ItemInvisibilityCloak){
			this.mainModel = new ModelCow();
			this.texture = new ResourceLocation("textures/entity/cow/cow.png");
		}else{
			this.mainModel = new ModelBiped();
			if(p_76986_1_ instanceof EntityPlayer){
				this.texture = AbstractClientPlayer.getLocationSkin(((EntityPlayer)p_76986_1_).getDisplayName());
			}
		}
		super.doRender(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return texture;
	}
	
}

