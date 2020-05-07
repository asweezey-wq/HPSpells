package com.apmods.hpspells.key;

import com.apmods.hpspells.gui.GuiSpell;
import com.apmods.hpspells.gui.GuiSpellTree;
import com.apmods.hpspells.item.ItemWand;
import com.apmods.hpspells.lib.SpellLib;
import com.apmods.hpspells.network.CastSpellPacket;
import com.apmods.hpspells.network.HPNetwork;
import com.apmods.hpspells.speech.SpeechThread;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;

public class KeyHandlerWand {
	@SubscribeEvent
	public void onKeyInput(KeyInputEvent evt){
		if(SpellKeys.castKey.getIsKeyPressed()){
			Minecraft mc = Minecraft.getMinecraft();
			if(mc.inGameHasFocus){
				mc.displayGuiScreen(new GuiSpell());
			}
		}
		else if(SpellKeys.spellkey.getIsKeyPressed()){
			Minecraft mc = Minecraft.getMinecraft();
			if(mc.inGameHasFocus){
				HPNetwork.net.sendToServer(new CastSpellPacket.SpellMessage((byte)1, SpellLib.getSpell("None")));
				ItemStack current  = mc.thePlayer.getHeldItem();
				if (current != null && current.getItem() instanceof ItemWand) {
					int counter = current.stackTagCompound.getInteger("last spell counter");
					int maxCounter = ((ItemWand)current.getItem()).getCooldownTime();
					if(counter == maxCounter){
						mc.thePlayer.swingItem();
					}
				}
			}
		}
		else if(SpellKeys.spellTreeKey.getIsKeyPressed()){
			Minecraft mc = Minecraft.getMinecraft();
			if(mc.inGameHasFocus){
				mc.displayGuiScreen(new GuiSpellTree(new GuiScreen(), Minecraft.getMinecraft().thePlayer.getStatFileWriter()));
			}
		}
		else if(SpellKeys.spellSpeechKey.getIsKeyPressed()){
			if((Minecraft.getMinecraft().thePlayer.getHeldItem().getItem() instanceof ItemWand)){
				new Thread(new SpeechThread()).start();
			}
		}
	}
}
