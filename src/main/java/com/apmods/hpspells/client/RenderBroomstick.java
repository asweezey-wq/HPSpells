package com.apmods.hpspells.client;

import org.lwjgl.opengl.GL11;

import com.apmods.hpspells.entity.EntityBroomstick;
import com.apmods.hpspells.entity.EnumBroomstick;
import com.apmods.hpspells.main.HPSpells;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class RenderBroomstick extends Render{
	
	public ResourceLocation texture = new ResourceLocation(HPSpells.MODID + ":textures/entity/broomstick.png");
	public ResourceLocation fireboltTexture = new ResourceLocation(HPSpells.MODID + ":textures/entity/firebolt.png");
	
	public ModelBase mainModel;
	
	public RenderBroomstick() {
		mainModel = new ModelBroomstick();
	}
	
	@Override
	public void doRender(Entity entity, double p_76986_2_, double p_76986_4_, double p_76986_6_, float var8, float var9) {
		GL11.glPushMatrix();
        GL11.glTranslatef((float)p_76986_2_, (float)p_76986_4_ + entity.height/2, (float)p_76986_6_);
        GL11.glRotatef(-var8, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef((float) (entity.rotationPitch * 180/Math.PI)/3, 1, 0, 0);
        float f2 = (float)((EntityBroomstick) entity).getTimeSinceHit() - var9;
	        float f3 = ((EntityBroomstick) entity).getDamageTaken() - var9;
	
	        if (f3 < 0.0F)
	        {
	            f3 = 0.0F;
	        }
	
	        if (f2 > 0.0F)
	        {
	            GL11.glRotatef(MathHelper.sin(f2) * f2 * f3 / 10.0F * (float)((EntityBroomstick) entity).getForwardDirection(), 1.0F, 0.0F, 0.0F);
	        }
//		}
        float f4 = 0.75F;
        GL11.glScalef(f4, f4, f4);
        GL11.glScalef(1.0F / f4, 1.0F / f4, 1.0F / f4);
        this.bindEntityTexture(entity);
        GL11.glScalef(-1.0F, -1.0F, 1.0F);
        this.mainModel.render(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return getEntityTexture((EntityBroomstick)p_110775_1_);
	}
	
	public ResourceLocation getEntityTexture(EntityBroomstick entity){
		return entity.getEB() == EnumBroomstick.BROOMSTICK ? texture : fireboltTexture;
	}

}
