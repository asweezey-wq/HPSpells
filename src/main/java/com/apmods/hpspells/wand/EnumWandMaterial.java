package com.apmods.hpspells.wand;

import java.util.Random;

import com.apmods.hpspells.spell.EnumSpellType;

public enum EnumWandMaterial {
	//Hex, Charm, Transfig, DADA, DA, Fizzling
	OAK("Oak", 0x823306, false, 1, 1, 2, 1.5, 1, 0.5),
	BIRCH("Birch", 0xccb588, false, 1, 2, 1, 1, 1, 1),
	ASH("Ash", 0x522e1d, false, 1, 1, 1.5, 1, 1.5, 1),
	ROWAN("Rowan", 0xa1854a, false, 1.5, 1.5, 1, 1, 1, 1),
	CHERRY("Cherry", 0x6d2517, false, 1.25, 1.25, 1.25, 1.25, 0.5, 1.25),
	PINE("Pine", 0x3d270b, false, 1.25, 1.25, 1.25, 1.25, 1.25, 1.5),
	YEW("Yew", 0xe1d0a3, false, 1, 1, 2, 1, 1.5, 1),
	ELDER("Elder", 0xcecabd, true, 2, 2, 2, 2, 2, 0.25);
	
	public String name;
	
	public int color;
	
	public boolean special;
	
	private double hex, charm, transfig, dada, da, fizzle;
	
	private EnumWandMaterial(String name, int color, boolean special, double hex, double charm, double transfig, double dada, double da, double fizzle) {
		this.name = name;
		this.color = color;
		this.special = special;
		this.hex = hex;
		this.charm = charm;
		this.transfig = transfig;
		this.dada = dada;
		this.da = da;
		this.fizzle = fizzle;
	}

	public static EnumWandMaterial getRandom(){
		switch(new Random().nextInt(7)){
		case 0:
			return OAK;
		case 1:
			return BIRCH;
		case 2:
			return ROWAN;
		case 3:
			return ASH;
		case 4: 
			return PINE;
		case 5:
			return CHERRY;
		case 6:
			return YEW;
		default:
			return OAK;
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
	
	public double getFizzleRateMultiplier(){
		return this.fizzle;
	}
}
