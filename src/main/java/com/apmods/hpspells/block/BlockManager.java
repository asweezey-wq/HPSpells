package com.apmods.hpspells.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class BlockManager {
	public static Block portkey, movingLightSource, lumosMaxima;
	
	public static void init(){
		portkey = new BlockPortkey("portkey");
		movingLightSource = new BlockMovingLightSource();
		lumosMaxima = new BlockLumosMaxima();
		GameRegistry.registerTileEntity(TileEntityPortkey.class, "portkey");
		GameRegistry.registerTileEntity(TileEntityMovingLightSource.class, "movingLightSource");
	}
}
