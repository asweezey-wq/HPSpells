package com.apmods.hpspells.client;

import org.lwjgl.opengl.GL11;

import com.apmods.hpspells.main.HPSpells;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class RenderProtego extends RendererLivingEntity{

    private static final ResourceLocation armoredCreeperTextures = new ResourceLocation("textures/entity/creeper/creeper_armor.png");
	
	public RenderProtego() {
		super(new ModelBiped(1.5f), 0.5f);
	}

	public void doRender(EntityPlayer p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
		GL11.glDisable(GL11.GL_LIGHTING);
		super.doRender((EntityLivingBase)p_76986_1_, 0, -p_76986_1_.height, 0, p_76986_8_, p_76986_9_);
		GL11.glEnable(GL11.GL_LIGHTING);
	}
	 protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_)
	 {
		 	float f1 = 0f;
	        float f2 = 1.0F + MathHelper.sin(f1 * 100.0F) * f1 * 0.01F;

	        if (f1 < 0.0F)
	        {
	            f1 = 0.0F;
	        }

	        if (f1 > 1.0F)
	        {
	            f1 = 1.0F;
	        }

	        f1 *= f1;
	        f1 *= f1;
	        float f3 = (1.0F + f1 * 0.4F) * f2;
	        float f4 = (1.0F + f1 * 0.1F) / f2;
	        GL11.glScalef(f3, f4, f3);
	 }
	
	@Override
	protected int getColorMultiplier(EntityLivingBase p_77030_1_, float p_77030_2_, float p_77030_3_) {
		float f2 = 10000f;

        if ((int)(f2 * 10.0F) % 2 == 0)
        {
            return 0;
        }
        else
        {
            int i = (int)(f2 * 0.2F * 255.0F);

            if (i < 0)
            {
                i = 0;
            }

            if (i > 255)
            {
                i = 255;
            }

            short short1 = 255;
            short short2 = 255;
            short short3 = 255;
            return i << 24 | short1 << 16 | short2 << 8 | short3;
        }
	}
	
    protected int shouldRenderPass(EntityLivingBase p_77032_1_, int p_77032_2_, float p_77032_3_)
    {
    	GL11.glDepthMask(true);
    	if (p_77032_2_ == 1)
        {
            float f1 = (float)p_77032_1_.ticksExisted + p_77032_3_;
            this.bindTexture(armoredCreeperTextures);
            GL11.glMatrixMode(GL11.GL_TEXTURE);
            GL11.glLoadIdentity();
            float f2 = f1 * 0.01F;
            float f3 = f1 * 0.01F;
            GL11.glTranslatef(f2, f3, 0.0F);
            this.setRenderPassModel(this.mainModel);
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
            GL11.glEnable(GL11.GL_BLEND);
            float f4 = 0.5F;
            GL11.glColor4f(f4, f4, f4, 1.0F);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
            return 1;
        }

        if (p_77032_2_ == 2)
        {
            GL11.glMatrixMode(GL11.GL_TEXTURE);
            GL11.glLoadIdentity();
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_BLEND);
        }
        return -1;
    }
    
    @Override
    protected int inheritRenderPass(EntityLivingBase p_77035_1_, int p_77035_2_, float p_77035_3_) {
    	return -1;
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return armoredCreeperTextures;
	}
}
