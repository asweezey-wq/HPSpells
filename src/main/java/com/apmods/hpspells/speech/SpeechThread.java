package com.apmods.hpspells.speech;

import com.apmods.hpspells.extendedplayer.SpellSkills;
import com.apmods.hpspells.lib.SpellLib;
import com.apmods.hpspells.main.HPSpells;
import com.apmods.hpspells.network.CastSpellPacket;
import com.apmods.hpspells.network.HPNetwork;
import com.apmods.hpspells.network.SpeakingPacket;
import com.apmods.hpspells.proxy.ClientProxy;
import com.apmods.hpspells.spell.ISpell;

import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import net.minecraft.client.Minecraft;

public class SpeechThread implements Runnable {
	
	@Override
	public void run() {
		LiveSpeechRecognizer rec = HPSpells.proxy.lsp;
		SpellSkills ext = SpellSkills.get(Minecraft.getMinecraft().thePlayer);
		try{
			rec.startRecognition(true);
		}catch(IllegalStateException error){}
		
		try{
			HPNetwork.net.sendToServer(new SpeakingPacket.SpeakingMessage(true));
			SpeechResult result;
			System.out.println("[HP Spells]Sphinx4 Speech Recognition");
			if((result = rec.getResult()) != null){
				rec.stopRecognition();
				HPNetwork.net.sendToServer(new SpeakingPacket.SpeakingMessage(false));
				String s = result.getHypothesis();
				ISpell spell = SpellLib.getSpell(s);
				if(spell == null && s.length() > 0){
					int i = s.indexOf(' ');
					if(i > 0){
						s = s.substring(0, i);
						spell = SpellLib.getSpell(s);
					}
					
				}
				if(spell != null){
					HPNetwork.net.sendToServer(new CastSpellPacket.SpellMessage((byte) 0, spell));
				}
			}else{
				rec.stopRecognition();
				HPNetwork.net.sendToServer(new SpeakingPacket.SpeakingMessage(false));
			}
		}catch(IllegalStateException error){
		}
		
	}

}
