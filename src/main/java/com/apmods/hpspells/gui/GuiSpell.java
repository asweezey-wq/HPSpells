package com.apmods.hpspells.gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.lwjgl.input.Keyboard;

import com.apmods.hpspells.extendedplayer.SpellSkills;
import com.apmods.hpspells.lib.SpellLib;
import com.apmods.hpspells.network.CastSpellPacket;
import com.apmods.hpspells.network.HPNetwork;
import com.apmods.hpspells.spell.ISpell;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.EntityPlayer;

public class GuiSpell extends GuiScreen {
	public GuiTextField input;
	public GuiSpellButton s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12;
	public GuiSpellButton[] buttons = { s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12 };

	@Override
	public void initGui() {
		this.input = new GuiTextField(this.fontRendererObj, this.width / 2 - 50, 100, 100, 12);
		this.input.setMaxStringLength(100);
		this.input.setEnableBackgroundDrawing(false);
		this.input.setFocused(true);
		this.input.setCanLoseFocus(false);
		for (int i = 0; i < 12; i++) {
			this.buttonList.add(buttons[i] = new GuiSpellButton(i, this.width / 2 - 100, 100 + (10 * i), 200, 8, ""));
			buttons[i].enabled = true;
		}

	}

	@Override
	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}

	public static byte getByte() {
		return 0;
	}

	@Override
	protected void keyTyped(char p_73869_1_, int p_73869_2_) {
		if (p_73869_2_ == 1) {
			this.mc.displayGuiScreen((GuiScreen) null);
		} else if (p_73869_2_ == Keyboard.KEY_RETURN) {

			this.mc.displayGuiScreen((GuiScreen) null);
		} else {
			for (int i = 0; i < 12; i++) {
				buttons[i].displayString = "";
				buttons[i].enabled = false;
			}
			this.input.textboxKeyTyped(p_73869_1_, p_73869_2_);
		}
	}

	@Override
	public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRendererObj, input.getText(), this.width / 2, 40, 16777215);
		List<String> matchingspells = new ArrayList<String>();
		for (int i = 1; i < SpellLib.names.length; i++) {
			if (SpellLib.names[i].toLowerCase().startsWith(input.getText().trim().toLowerCase())) {
				ISpell spell = SpellLib.getSpell(SpellLib.names[i]);
				SpellSkills ext = SpellSkills.get(Minecraft.getMinecraft().thePlayer);
				int knowledge = ext.getKnowledgeLevel(spell);
				if(knowledge > 0){
					matchingspells.add(SpellLib.names[i]);
					Collections.sort(matchingspells);
				}
			}
		}

		if (matchingspells.size() <= 12) {
			for (int j = 0; j < matchingspells.size(); j++) {
				for (int i = 0; i < 12; i++) {
					if (j == i) {
						buttons[i].displayString = matchingspells.get(j);
						buttons[i].enabled = true;
						buttons[i].spell = SpellLib.getSpell(matchingspells.get(j));
					} else {
						this.drawCenteredString(this.fontRendererObj, matchingspells.get(j), this.width / 2, 100 + (j * 10), 16777215);
					}
				}

			}
		} else {
			for (int j = 0; j < 12; j++) {
				this.drawCenteredString(this.fontRendererObj, matchingspells.get(j), this.width / 2, 100 + (j * 10), 16777215);
			}
		}
		this.drawCenteredString(this.fontRendererObj, "Matching Spells:", this.width / 2, 80, 16777215);
		super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
	}

	@Override
	protected void actionPerformed(GuiButton b) {
		EntityPlayer player = this.mc.thePlayer;
		SpellSkills ext = SpellSkills.get(player);
		if (b instanceof GuiSpellButton) {
			GuiSpellButton sb = (GuiSpellButton) b;
			if (sb.enabled) {
				for (int i = 0; i < 12; i++) {
					if (sb.id == i) {
						HPNetwork.net.sendToServer(new CastSpellPacket.SpellMessage((byte) 0, sb.spell));
						this.mc.displayGuiScreen((GuiScreen) null);
					}
				}

			}
		}
	}

}
