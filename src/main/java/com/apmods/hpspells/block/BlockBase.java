package com.apmods.hpspells.block;

import com.apmods.hpspells.main.HPSpells;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBase extends Block {

	protected BlockBase(String name) {
		super(Material.rock);
        this.setBlockName(name);
        GameRegistry.registerBlock(this, name);
        this.setBlockTextureName(HPSpells.MODID + ":" + name);
	}

}
