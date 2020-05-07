package com.apmods.hpspells.entity;

import java.util.ArrayList;

import com.apmods.hpspells.main.HPSpells;

import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;

public class EntityManager {
	public static void init() {
		
		ArrayList<BiomeGenBase> biomeList = new ArrayList<BiomeGenBase>();
		for(BiomeGenBase b : BiomeGenBase.getBiomeGenArray()){
			if(b != null){
				biomeList.add(b);
			}
		}
		BiomeGenBase[] biomeArray_0 = new BiomeGenBase[biomeList.size()];
		BiomeGenBase[] allBiomes = biomeList.toArray(biomeArray_0);
		
		registerEntity(EntitySpell.class, "Spell");
		registerEntity(EntityPortkey.class, "Portkey");
		registerEntity(EntityPatronus.class, "Patronus");
		registerEntity(EntityDementor.class, "Dementor", 0x595959, 0x000000);
		registerEntity(EntityBird.class, "AvisBird", 0xddff00, 0x14c000);
		registerEntity(EntityDeathEater.class, "DeathEater", 0x000000, 0x000000);
		registerEntity(EntityFumos.class, "Fumos");
		registerEntity(EntityDarkArtsWard.class, "DarkWard");
		registerEntity(EntityBroomstick.class, "Broomstick");
		registerEntity(EntityLumosMaxima.class, "LumosMaxima");
		registerEntity(EntityDarkMark.class, "Dark Mark");
		registerEntity(EntityWandmaker.class, "Wandmaker", 0x354885, 0xb57b67);
		EntityRegistry.addSpawn(EntityDementor.class, 1, 2, 4, EnumCreatureType.monster, BiomeGenBase.swampland, BiomeGenBase.hell, BiomeGenBase.roofedForest, BiomeGenBase.coldTaiga, BiomeGenBase.coldTaigaHills);
		EntityRegistry.addSpawn(EntityDeathEater.class, 2, 1, 3, EnumCreatureType.monster, allBiomes);
		EntityRegistry.addSpawn(EntityBird.class, 2, 2, 4, EnumCreatureType.creature, allBiomes);
		EntityRegistry.addSpawn(EntityWandmaker.class, 1, 1, 1, EnumCreatureType.ambient, allBiomes);
	}

	public static void registerEntity(Class entityClass, String name) {
		int entityID = EntityRegistry.findGlobalUniqueEntityId();

		EntityRegistry.registerGlobalEntityID(entityClass, name, entityID);
		EntityRegistry.registerModEntity(entityClass, name, entityID, HPSpells.instance, 64, 1, true);
	}

	public static void registerEntity(Class entityClass, String name, int primary, int secondary) {
		int entityID = EntityRegistry.findGlobalUniqueEntityId();

		EntityRegistry.registerGlobalEntityID(entityClass, name, entityID);
		EntityRegistry.registerModEntity(entityClass, name, entityID, HPSpells.instance, 64, 1, true);
		EntityList.entityEggs.put(Integer.valueOf(entityID), new EntityList.EntityEggInfo(entityID, primary, secondary));
	}
}
