package com.apmods.hpspells.gui;

import com.apmods.hpspells.lib.SpellLib;
import com.apmods.hpspells.spell.ISpell;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;

public class GuiSpellButton extends GuiButton{
	public ISpell spell;
	public GuiSpellButton(int p_i1021_1_, int p_i1021_2_, int p_i1021_3_,
			int p_i1021_4_, int p_i1021_5_, String p_i1021_6_) {
		super(p_i1021_1_, p_i1021_2_, p_i1021_3_, p_i1021_4_, p_i1021_5_, p_i1021_6_);
		this.spell = SpellLib.getSpell("None");
	}
	public GuiSpellButton(int p_i1021_1_, int p_i1021_2_, int p_i1021_3_, String p_i1021_6_) {
		super(p_i1021_1_, p_i1021_2_, p_i1021_3_, 200, 20, p_i1021_6_);
		this.spell = SpellLib.getSpell("None");
	}
	public void drawButton(Minecraft p_146112_1_, int p_146112_2_, int p_146112_3_)
    {
        if (this.visible)
        {
            this.field_146123_n = p_146112_2_ >= this.xPosition && p_146112_3_ >= this.yPosition && p_146112_2_ < this.xPosition + this.width && p_146112_3_ < this.yPosition + this.height;
        	int k = this.getHoverState(this.field_146123_n);
        	int l = 0xffffff;
        	if (this.field_146123_n)
            {
                l = 16777120;
            }
            FontRenderer fontrenderer = p_146112_1_.fontRenderer;
            this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, l);
        }
    }

}
