package com.apmods.hpspells.gui;

import org.lwjgl.opengl.GL11;

import com.apmods.hpspells.main.HPSpells;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;

public class GuiLearnFizzleSkillButton extends GuiButton{
	protected static final ResourceLocation toggleTex = new ResourceLocation(HPSpells.MODID + ":textures/gui/spellbook_button.png");
	
	public GuiLearnFizzleSkillButton(int p_i1021_1_, int p_i1021_2_, int p_i1021_3_, int p_i1021_4_, int p_i1021_5_, String p_i1021_6_)
    {
		super(p_i1021_5_, p_i1021_5_, p_i1021_5_, p_i1021_5_, p_i1021_5_, p_i1021_6_);
        this.enabled = true;
        this.visible = true;
        this.id = p_i1021_1_;
        this.xPosition = p_i1021_2_;
        this.yPosition = p_i1021_3_;
        this.width = p_i1021_4_;
        this.height = p_i1021_5_;
        this.displayString = p_i1021_6_;
    }

	public GuiLearnFizzleSkillButton(int p_i1020_1_, int p_i1020_2_, int p_i1020_3_,
			String p_i1020_4_, int buff) {
		super(p_i1020_1_, p_i1020_2_, p_i1020_3_, p_i1020_4_);
		// TODO Auto-generated constructor stub
	}
	public boolean mousePressed(Minecraft mc, int p_146116_2_, int p_146116_3_)
    {
        return this.enabled && this.visible && p_146116_2_ >= this.xPosition && p_146116_3_ >= this.yPosition && p_146116_2_ < this.xPosition + this.width && p_146116_3_ < this.yPosition + this.height;
    }
	public void drawButton(Minecraft p_146112_1_, int p_146112_2_, int p_146112_3_)
    {
        if (this.visible)
        {
            FontRenderer fontrenderer = p_146112_1_.fontRenderer;
            p_146112_1_.getTextureManager().bindTexture(toggleTex);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.field_146123_n = p_146112_2_ >= this.xPosition && p_146112_3_ >= this.yPosition && p_146112_2_ < this.xPosition + this.width && p_146112_3_ < this.yPosition + this.height;
            int k = this.getHoverState(this.field_146123_n);
            GL11.glEnable(GL11.GL_BLEND);
            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            if(k == 1){
            	this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 0, 72, 16);
            }
            else{
            	this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, k * 16, 72, 16);
            }
//            this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, 200 - this.width / 2, k * 21, this.width / 2, this.height);
            this.mouseDragged(p_146112_1_, p_146112_2_, p_146112_3_);
            int l = 14737632;

            if (packedFGColour != 0)
            {
                l = packedFGColour;
            }
            else if (!this.enabled)
            {
                l = 10526880;
            }
            else if (this.field_146123_n)
            {
                l = 16777120;
            }

            if(this.displayString.length() < 16){
            	this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, l);
            	
            }
            else{
            	String s1;
            	s1 = this.displayString.substring(0, 13);
            	s1.trim();
            	this.drawCenteredString(fontrenderer, s1 + "...", this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, l);

            }
        }
    }

}
