package com.apmods.hpspells.client;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelPlayer - Either Mojang or a mod author
 * Created using Tabula 5.1.0
 */
public class ModelCloak extends ModelBiped {
    private ModelRenderer left_arm_top;
    private ModelRenderer right_arm_top;
    private ModelRenderer neckpiece;
    public ModelRenderer cape;

    public ModelCloak(float par1) {
    	super(par1, 0, 64, 64);
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.right_arm_top = new ModelRenderer(this, 16, 40);
        this.right_arm_top.setRotationPoint(-8.0F, 1.5F, -1.0F);
        this.right_arm_top.addBox(4, -5, -2, 5, 3, 6, 0.0F);
        this.setRotateAngle(right_arm_top, 0.0F, 0.0F, 0.10000736613927509F);
        this.neckpiece = new ModelRenderer(this, 28, 0);
        this.neckpiece.setRotationPoint(-4.0F, 0.0F, -3.0F);
        this.neckpiece.addBox(0.0F, 0.0F, 0.0F, 8, 2, 1, 0.0F);
        this.left_arm_top = new ModelRenderer(this, 16, 40);
        this.left_arm_top.setRotationPoint(4.0F, 2.0F, -1.0F);
        this.left_arm_top.addBox(-5, -5, -2, 5, 3, 6, 0.0F);
        this.setRotateAngle(left_arm_top, 0.0F, 0.0F, -0.10000736613927509F);
        this.cape = new ModelRenderer(this, 38, 40);
        this.cape.setRotationPoint(-4.5F, 0.0F, 2.0F);
        this.cape.addBox(0.0F, 0.0F, 0.0F, 9, 17, 1, 0.0F);
        bipedBody.addChild(cape);
        bipedBody.addChild(neckpiece);
        bipedLeftArm.addChild(left_arm_top);
        bipedRightArm.addChild(right_arm_top);
    }
    
    @Override
    public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
    	super.render(p_78088_1_, p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_);
    	if(p_78088_1_.motionX != 0 || p_78088_1_.motionZ != 0){
    		cape.rotateAngleX = (float) (Math.PI/6);
    	}
    }
    
    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
