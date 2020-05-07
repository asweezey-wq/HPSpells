package com.apmods.hpspells.wand;

import java.util.Random;

import com.apmods.hpspells.spell.EnumSpellType;

public enum EnumWandCore {
	PHEONIX("Pheonix Feather", 1.5, 1, 1, 1.5, 0.5),
	DRAGON("Dragon Heartstring", 1.5, 1.25, 1, 0.5, 1.25),
	UNICORN("Unicorn Tail", 1, 1.5, 1.5, 0.5, 1),
	THESTRAL("Thestral Hair", 2, 2, 2, 2, 2),
	BASILISK("Basilisk Horn", 1.5, 0.5, 0.5, 1, 2),
	VEELA("Veela Hair", 0.5, 1.5, 1.5, 1, 1),
	WHITE_RIVER("White River Spine", 0.5, 1.5, 1.25, 1.25, 1);
	
	public String name;
	
	private double hex, charm, transfig, dada, da;
	
	private EnumWandCore(String name, double hex, double charm, double transfig, double dada, double da) {
		this.name = name;
		this.hex = hex;
		this.charm = charm;
		this.transfig = transfig;
		this.dada = dada;
		this.da = da;
	}

	public static EnumWandCore getRandom(){
		switch(new Random().nextInt(3)){
		case 0:
			return PHEONIX;
		case 1:
			return DRAGON;
		case 2:
			return UNICORN;
		default:
			return PHEONIX;
		}
	}
	
	public double getMultiplierForSpellType(EnumSpellType spellType){
		if(spellType == EnumSpellType.HEX){
			return this.hex;
		}else if(spellType == EnumSpellType.CHARM){
			return this.charm;
		}else if(spellType == EnumSpellType.TRANSFIGURATION){
			return this.transfig;
		}else if(spellType == EnumSpellType.DADA){
			return this.dada;
		}else if(spellType == EnumSpellType.DARK){
			return this.da;
		}
		return 1;
	}
}
