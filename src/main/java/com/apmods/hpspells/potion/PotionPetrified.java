package com.apmods.hpspells.potion;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PotionPetrified extends Potion{

	private ResourceLocation potionIcon;
	
	public PotionPetrified(int potionId, boolean isBad, int potionColor, ResourceLocation texture) {
		super(potionId, isBad, potionColor);
		potionIcon = texture;
	}

	@Override
	public int getStatusIconIndex() {
		Minecraft.getMinecraft().getTextureManager().bindTexture(potionIcon);
		return super.getStatusIconIndex();
	}
	
	@Override
	public Potion setIconIndex(int p_76399_1_, int p_76399_2_) {
		super.setIconIndex(p_76399_1_, p_76399_2_);
		return this;
	}
	
}
