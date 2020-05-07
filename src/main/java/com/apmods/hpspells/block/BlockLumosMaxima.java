package com.apmods.hpspells.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockLumosMaxima extends Block{

	protected BlockLumosMaxima() {
		super(Material.air);
		setTickRandomly(false);
        setLightLevel(1.0F);
        this.setBlockName("lumosmaxima");
        GameRegistry.registerBlock(this, "lumosmaxima");
        setBlockBounds(0.5F, 0.5F, 0.5F, 0.5F, 0.5F, 0.5F);
	}
	
	 @Override
	    public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
	    	return null;
	    }

	    @Override
	    public boolean isOpaqueCube()
	    {
	        return false;
	    }

	    @Override
	    public boolean canPlaceBlockAt(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_) {
	    	return true;
	    }

	    @Override
	    public void onBlockAdded(World p_149726_1_, int p_149726_2_, int p_149726_3_, int p_149726_4_) {
	    	return;
	    }
	    
	    @Override
	    public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_) {
	    	return;
	    }

	    @Override
	    public void onFallenUpon(World p_149746_1_, int p_149746_2_, int p_149746_3_, int p_149746_4_, Entity p_149746_5_, float p_149746_6_) {
	    	return;
	    }

}
