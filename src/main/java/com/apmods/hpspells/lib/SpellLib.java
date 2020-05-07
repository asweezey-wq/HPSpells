package com.apmods.hpspells.lib;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.apmods.hpspells.spell.ISpell;

public class SpellLib {
	public static final int STUPEFY = 1, AVADA_KEDAVRA = 2, DURO = 3, EXPELLIARMUS = 4, EXPULSO = 5, CONFRINGO = 6, LUMOS = 7, NOX = 8, INCENDIO = 9, AGUAMENTI = 10, ALARTE_ASCENDARE = 11, DEFODIO = 12, DIFFINDO = 13, PATRONUS = 14, EPISKEY = 15, FLIPENDO = 16, MELOFORS = 17, OBSCURO = 18, HERBIVICUS = 19, ORCHIDEOUS = 20, PETRIFICUS_TOTALUS = 21, WINGARDIUM_LEVIOSA = 22, ACCIO = 23, FERA_VERTO = 24, MULTICOLORFORS = 25, APPARATE = 26, GLISSEO = 27, ALOHOMORA = 28, AVIS = 29, AVIFORS = 30,
			PORTUS = 31, IMPERIO = 32, PROTEGO = 33, CRUCIO = 34, FUMOS = 35, REFULGENS = 36;

	public static final String[] names = { "None", "Stupefy", "Avada Kedavra", "Duro", "Expelliarmus", "Expulso", "Confringo", "Lumos", "Nox", "Incendio", "Aguamenti", "Alarte Ascendare", "Defodio", "Diffindo", "Expecto Patronum", "Episkey", "Flipendo", "Melofors", "Obscuro", "Herbivicus", "Orchideous", "Petrificus Totalus", "Wingardium Leviosa", "Accio", "Fera Verto", "Multicolorfors", "Apparate", "Glisseo", "Alohomora", "Avis", "Avifors", "Portus", "Imperio", "Protego", "Crucio", "Fumos",
			"Refulgens", "Bombarda", "Depulso", "Obliviate", "Finite Incantatem", "Anapneo", "Homenum Revelio", "Ascendio", "Lumos Maxima", "Bestio Revelio", "Disillusio", "Colloportus", "Baubilious", "Evanesco", "Cistem Aperio", "Morsmordre"};
	public static final HashMap<String, ISpell> spells = new HashMap<String, ISpell>(names.length);

	public static void init() {
		List<String> list = Arrays.asList(names);
		for (int i = 0; i < list.size(); i++) {
			String s = list.get(i);
			String className = s.replaceAll("\\s", "");
			Class c;
			Object obj;
			try {
				c = Class.forName("com.apmods.hpspells.spell.Spell" + className);
				obj = c.newInstance();
				obj.getClass(); 
				if(obj instanceof ISpell){
					spells.put(list.get(i).toLowerCase(), (ISpell)obj);
				}
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}			
		}
	}

	public static ISpell getSpell(String text) {
		String name = text.trim().toLowerCase();
		return spells.get(name);
	}
}
