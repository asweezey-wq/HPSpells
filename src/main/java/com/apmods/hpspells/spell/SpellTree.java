package com.apmods.hpspells.spell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.apmods.hpspells.lib.SpellLib;

public class SpellTree {
	public static List spellTree = new ArrayList<List>();
	public static List blockSpells = new ArrayList<Object>();
	public static List offensiveSpells = new ArrayList();
	public static List conjurationSpells = new ArrayList();
	public static List transfigSpells = new ArrayList();
	public static List darkArts = new ArrayList();
	static{
		loadSpells();
	}
	
	public static void loadSpells(){
		spellTree.clear();
		
		offensiveSpells.clear();
			offensiveSpells.add(SpellLib.getSpell("Flipendo"));
			List offensiveSpells2 = new ArrayList<Object>();
				offensiveSpells2.add(SpellLib.getSpell("Expulso"));
				offensiveSpells2.add(SpellLib.getSpell("Petrificus Totalus"));
				offensiveSpells2.add(SpellLib.getSpell("Alarte Ascendare"));
				offensiveSpells2.add(SpellLib.getSpell("Evanesco"));
			offensiveSpells.add(offensiveSpells2);
			offensiveSpells.add(SpellLib.getSpell("Stupefy"));
			offensiveSpells.add(SpellLib.getSpell("Expelliarmus"));
			offensiveSpells.add(SpellLib.getSpell("Obscuro"));
			List offensiveSpells3 = new ArrayList<Object>();
				offensiveSpells3.add(SpellLib.getSpell("Diffindo"));
				offensiveSpells3.add(SpellLib.getSpell("Baubilious"));
				offensiveSpells3.add(SpellLib.getSpell("Obliviate"));
			offensiveSpells.add(offensiveSpells3);
			offensiveSpells.add(SpellLib.getSpell("Bombarda"));
		
		conjurationSpells.clear();
			conjurationSpells.add(SpellLib.getSpell("Orchideous"));
			List conjurationSpells2 = new ArrayList<Object>();
				conjurationSpells2.add(SpellLib.getSpell("Fumos"));
				conjurationSpells2.add(SpellLib.getSpell("Disillusio"));
				conjurationSpells2.add(SpellLib.getSpell("Refulgens"));
				conjurationSpells2.add(SpellLib.getSpell("Depulso"));
			conjurationSpells.add(conjurationSpells2);
			conjurationSpells.add(SpellLib.getSpell("Herbivicus"));
			List conjurationSpells3 = new ArrayList();
				conjurationSpells3.add(SpellLib.getSpell("Accio"));
				conjurationSpells3.add(SpellLib.getSpell("Ascendio"));
				conjurationSpells3.add(SpellLib.getSpell("Wingardium Leviosa"));
			conjurationSpells.add(conjurationSpells3);
			conjurationSpells.add(SpellLib.getSpell("Episkey"));
			conjurationSpells.add(SpellLib.getSpell("Bestio Revelio"));
			conjurationSpells.add(SpellLib.getSpell("Homenum Revelio"));
		
		transfigSpells.clear();
			transfigSpells.add(SpellLib.getSpell("Multicolorfors"));
			List transfigSpells2 = new ArrayList<Object>();
				transfigSpells2.add(SpellLib.getSpell("Avis"));
				transfigSpells2.add(SpellLib.getSpell("Avifors"));
				transfigSpells2.add(SpellLib.getSpell("Finite Incantatem"));
				transfigSpells2.add(SpellLib.getSpell("Protego"));
			transfigSpells.add(transfigSpells2);
			transfigSpells.add(SpellLib.getSpell("Melofors"));
			List transfigSpells3 = new ArrayList();
			transfigSpells3.add(SpellLib.getSpell("Anapneo"));
			transfigSpells3.add(SpellLib.getSpell("Fera Verto"));
			transfigSpells.add(transfigSpells3);			
			transfigSpells.add(SpellLib.getSpell("Lumos"));
			transfigSpells.add(SpellLib.getSpell("Lumos Maxima"));
		
		blockSpells.clear();
			blockSpells.add(SpellLib.getSpell("Duro"));
			List blockSpells2 = new ArrayList<Object>();
				blockSpells2.add(SpellLib.getSpell("Aguamenti"));
				blockSpells2.add(SpellLib.getSpell("Incendio"));
				List blockSpells3 = new ArrayList();
					blockSpells3.add(SpellLib.getSpell("Portus"));
					blockSpells3.add(SpellLib.getSpell("Apparate"));
				blockSpells2.add(blockSpells3);
				blockSpells2.add(SpellLib.getSpell("Expecto Patronum"));
				blockSpells2.add(SpellLib.getSpell("Cistem Aperio"));
			blockSpells.add(blockSpells2);
			blockSpells.add(SpellLib.getSpell("Defodio"));
			blockSpells.add(SpellLib.getSpell("Glisseo"));
			blockSpells.add(SpellLib.getSpell("Alohomora"));
			blockSpells.add(SpellLib.getSpell("Colloportus"));
			
		darkArts.clear();
		darkArts.add(SpellLib.getSpell("Morsmordre"));
		darkArts.add(SpellLib.getSpell("Confringo"));
		darkArts.add(SpellLib.getSpell("Imperio"));
		darkArts.add(SpellLib.getSpell("Crucio"));
		darkArts.add(SpellLib.getSpell("Avada Kedavra"));
		
		spellTree.add(offensiveSpells);
		spellTree.add(blockSpells);
		spellTree.add(transfigSpells);
		spellTree.add(conjurationSpells);
		spellTree.add(darkArts);
		
		System.out.println("[HP Spells] Spells in spell tree: " + countSpells(spellTree));
		List<ISpell> spellsCopy = new ArrayList<ISpell>();
		spellsCopy.addAll(SpellLib.spells.values());
		spellsCopy.remove(SpellLib.getSpell("None"));
		spellsCopy.remove(SpellLib.getSpell("Nox"));
		spellsCopy.removeAll(getSpells(spellTree));
		System.out.println("[HP Spells] Spells missing (" + (SpellLib.spells.size() - countSpells(spellTree) - 2) + ") : " + spellsCopy);
	}
	
	public static int indexOfWithoutLists(Object item, List list){
		List list2 = new ArrayList(list);
		for(Object o : list){
			if(item instanceof ISpell){
				if(!(o instanceof ISpell)){
					list2.remove(o);
				}
			}else if(item instanceof List){
				if(!(o instanceof ISpell) && !o.equals(item)){
					list2.remove(o);
				}
			}
		}
		return list2.indexOf(item);
	}
		
	public static int indexOfList(List list, List superList){
		List list2 = new ArrayList(superList);
		for(Object o : superList){
			if(!(o instanceof List)){
				list2.remove(o);
			}
		}
		int i = list2.size() - list2.indexOf(list);
		return i;
	}
	
	public static int countLists(List list){
		int i = 0;
		for(Object o : list){
			if(o instanceof List){
				i += countLists((List) o);
				i++;
			}
		}
		return i;
	}
	
	public static int countSpells(List list){
		return getSpells(list).size();
	}
	
	public static List<ISpell> getSpells(List list){
		List spells = new ArrayList<ISpell>();
		for(Object o : list){
			if(o instanceof ISpell){
				spells.add(o);
			}else if(o instanceof List){
				spells.addAll(getSpells((List) o));
			}
		}
		return spells;
	}
	
	public static int nestedIndexOf(List list, List superList){
		if(superList.indexOf(list) > -1){
			return superList.indexOf(list);
		}else{
			int count = -1;
			for(Object o : superList){
				if(o instanceof List){
					int j = nestedIndexOf(list, (List) o);
					count++;
					if(j - countLists((List) o) > -1){
						return count + j;
					}else{
						count += (j + 1);	
					}
				}
			}
			return count;
		}
	}
	
	public static List listWithoutLists(List list){
		List list2 = new ArrayList(list);
		for(Object o : list){
			if(!(o instanceof ISpell)){
				list2.remove(o);
			}
		}
		return list2;
	}
}
