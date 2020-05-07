package com.apmods.hpspells.block;

import java.util.Random;

import com.apmods.hpspells.main.HPSpells;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockPortkey extends BlockBase implements ITileEntityProvider{

	public BlockPortkey(String name) {
		super(name);
		this.setHardness(0.7f);
		this.setLightLevel(0.2f);
	}
	
	public boolean hasTileEntity(int metadata)
	{
	    return true;
	}
    public void breakBlock(World world, int x, int y, int z, Block p_149749_5_, int p_149749_6_)
    {
    	TileEntityPortkey p = (TileEntityPortkey)world.getTileEntity(x, y, z);
    	p.revertToBlock(world, x, y, z);
    	super.breakBlock(world, x, y, z, p_149749_5_, p_149749_6_);
    }
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return null;
    }
	@Override
	public TileEntity createTileEntity(World par1World, int metadata)
	{
	    return new TileEntityPortkey();
	}
	public void setPortkeyCoords(EntityPlayer player, World world, int x, int y, int z, int x1, int y1, int z1){
		if(!world.isRemote){
			TileEntityPortkey p = (TileEntityPortkey)world.getTileEntity(x, y, z);
			p.setPortkeyCoords(world, x1, y1, z1);
		}
	}
	public boolean onBlockActivated(World world, int par2, int par3, int par4, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
		if(!player.isSneaking()){
            if(!world.isRemote)
            {
                TileEntityPortkey t = (TileEntityPortkey) world.getTileEntity(par2, par3, par4);

                t.teleport(world, player, par2, par3, par4);
            }
		}else{
			if(!world.isRemote){
		        TileEntityPortkey t = (TileEntityPortkey) world.getTileEntity(par2, par3, par4);
		        HPSpells.proxy.portus(t.x, t.y, t.z);
			}
		}
		return true;
    }

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityPortkey();
	}
}
