package com.apmods.hpspells.potion;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PotionFlipped extends Potion {

	private ResourceLocation l;
	
	public PotionFlipped(int potionId, boolean isBad, int potionColor, ResourceLocation tex) {
		super(potionId, isBad, potionColor);
		l = tex;
	}
	
	@Override
	public int getStatusIconIndex() {
		Minecraft.getMinecraft().getTextureManager().bindTexture(l);
		return super.getStatusIconIndex();
	}
	
	@Override
	public Potion setIconIndex(int p_76399_1_, int p_76399_2_) {
		super.setIconIndex(p_76399_1_, p_76399_2_);
		return this;
	}

}
