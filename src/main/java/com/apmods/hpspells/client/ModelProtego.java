package com.apmods.hpspells.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class ModelProtego extends ModelBase{
	public ModelRenderer cube;
	public ModelProtego(){
		this.cube = new ModelRenderer(this, 0, 0);
		this.cube.addBox(-10, -4, -14, 20, 20, 1);
	}
    public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
    	this.cube.render(p_78088_7_);
    }

}
