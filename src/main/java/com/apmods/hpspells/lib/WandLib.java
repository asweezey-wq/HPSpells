package com.apmods.hpspells.lib;

public class WandLib {
	public static final int PHOENIX_FEATHER = 1, DRAGON_HEARTSTRING = 2, UNICORN_TAIL_HAIR = 3;
	public static final int OAK = 1, BIRCH = 2, SPRUCE = 3, ASH = 4, VINE = 5, HAWTHORN = 6, CHERRY = 7, YEW = 8, WALNUT = 9;
	public static String getCoreName(int core){
		switch(core){
		case PHOENIX_FEATHER:
			return "Phoenix Feather";
		case DRAGON_HEARTSTRING:
			return "Dragon Heartstring";
		case UNICORN_TAIL_HAIR:
			return "Unicorn Tail Hair";
			default:
				return "Unknown";
		}
	}
	public static String getWoodName(int wood){
		switch(wood){
		case OAK:
			return "Oak";
		case BIRCH:
			return "Birch";
		case SPRUCE:
			return "Spruce";
		case ASH:
			return "Ash";
		case VINE:
			return "Vine";	
		case HAWTHORN:
			return "Hawthorn";
		case CHERRY:
			return "Cherry";
		case YEW:
			return "Yew";
		case WALNUT:
			return "Walnut";
		default:
			return "Unknown";
		}
	}
}
