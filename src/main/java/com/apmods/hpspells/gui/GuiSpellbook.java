package com.apmods.hpspells.gui;

import com.apmods.hpspells.extendedplayer.SpellSkills;
import com.apmods.hpspells.lib.SpellLib;
import com.apmods.hpspells.main.HPSpells;
import com.apmods.hpspells.network.HPNetwork;
import com.apmods.hpspells.network.LevelUpKnowledgePacket;
import com.apmods.hpspells.spell.ISpell;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

public class GuiSpellbook extends GuiScreen{
	public ISpell[] bookspells;
	public GuiSpellbook(String[] spellsAvailable){
		bookspells = new ISpell[spellsAvailable.length];
		for(int i = 0; i < spellsAvailable.length; i++){
			bookspells[i] = SpellLib.getSpell(spellsAvailable[i]);
		}
	}
	public GuiLearnFizzleSkillButton skill1, skill2, skill3, skill4;
    private int bookImageWidth = 192;
    private int bookImageHeight = 192;
	public void initGui(){
		int i = bookspells.length - 1;
		this.buttonList.add(skill1 = new GuiLearnFizzleSkillButton(0, this.width/2 - 58, this.height/2 - 70, 72, 16, bookspells[0].isComplicated() ? EnumChatFormatting.LIGHT_PURPLE + bookspells[0].getName() : bookspells[0].getName()));
		if(i >= 1) this.buttonList.add(skill2 = new GuiLearnFizzleSkillButton(1, this.width/2 - 58, this.height/2 - 49, 72, 16, bookspells[1].isComplicated() ? EnumChatFormatting.LIGHT_PURPLE + bookspells[1].getName() : bookspells[1].getName()));
		if(i >= 2) this.buttonList.add(skill3 = new GuiLearnFizzleSkillButton(2, this.width/2 - 58, this.height/2 - 28, 72, 16, bookspells[2].isComplicated() ? EnumChatFormatting.LIGHT_PURPLE + bookspells[2].getName() : bookspells[2].getName()));
		if(i == 3) this.buttonList.add(skill4 = new GuiLearnFizzleSkillButton(3, this.width/2 - 58, this.height/2 - 7, 72, 16, bookspells[3].isComplicated() ? EnumChatFormatting.LIGHT_PURPLE + bookspells[3].getName() : bookspells[3].getName()));
	}
	public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_)
	{
		this.mc.getTextureManager().bindTexture(new ResourceLocation(HPSpells.MODID + ":textures/gui/spellbook.png"));
		EntityPlayer player = mc.thePlayer;
		SpellSkills ext = SpellSkills.get(player);

		int k = (this.width - this.bookImageWidth) / 2;
        byte b0 = 2;
		this.drawTexturedModalRect(k, b0, 0, 0, this.bookImageWidth, this.bookImageHeight);
		for(int i = 0; i < bookspells.length; i++){
			String s = "Lvl: " + ext.getKnowledgeLevel(bookspells[i]);
			this.fontRendererObj.drawString(s, this.width/2 + 20, this.height/2 - (69 - (21 * i)), 0x000000, false);
		}
	    this.drawCenteredString(this.fontRendererObj, EnumChatFormatting.RED + "Spellbook", this.width/2, 20, 16777215);
		super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
	}
	protected void actionPerformed(GuiButton b)
    {
		EntityPlayer player = this.mc.thePlayer;
		SpellSkills ext = SpellSkills.get(player);
        if (b.enabled)
        {
        	if(b.id == 0){
        		HPNetwork.net.sendToServer(new LevelUpKnowledgePacket.KnowledgeMessage(bookspells[0]));
        		this.mc.displayGuiScreen((GuiScreen)null);
        		if(ext.isKnowledgeMaxed(bookspells[0])){
        			b.enabled = false;
        		}
        	}
        	if(b.id == 1){
        		HPNetwork.net.sendToServer(new LevelUpKnowledgePacket.KnowledgeMessage(bookspells[1]));
        		this.mc.displayGuiScreen((GuiScreen)null);
        		if(ext.isKnowledgeMaxed(bookspells[1])){
        			b.enabled = false;
        		}
        	}
        	if(b.id == 2){
        		HPNetwork.net.sendToServer(new LevelUpKnowledgePacket.KnowledgeMessage(bookspells[2]));
        		this.mc.displayGuiScreen((GuiScreen)null);
        		if(ext.isKnowledgeMaxed(bookspells[2])){
        			b.enabled = false;
        		}
        	}
        	if(b.id == 3){
        		HPNetwork.net.sendToServer(new LevelUpKnowledgePacket.KnowledgeMessage(bookspells[3]));
        		this.mc.displayGuiScreen((GuiScreen)null);
        		if(ext.isKnowledgeMaxed(bookspells[3])){
        			b.enabled = false;
        		}
        	}
        }
        if(ext.isKnowledgeMaxed(bookspells[b.id])){
			b.enabled = false;
		}
    }
}
