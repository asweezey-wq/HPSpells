package com.apmods.hpspells.block;

import com.apmods.hpspells.item.ItemWand;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityMovingLightSource extends TileEntity{
	public EntityPlayer thePlayer;
    
    public TileEntityMovingLightSource()
    {
        // after constructing the tile entity instance, remember to call 
        // the setPlayer() method.

    }
    
    /**
     * This controls whether the tile entity gets replaced whenever the block state 
     * is changed. Normally only want this when block actually is replaced.
     */
    @Override
    public boolean shouldRefresh(Block oldBlock, Block newBlock, int oldMeta, int newMeta, World world, int x, int y, int z) {
    	return oldBlock != newBlock;
    }

    @Override
    public void updateEntity()
    {
    	
        // check if player has moved away from the tile entity
        EntityPlayer thePlayer = worldObj.getClosestPlayer(
              this.xCoord + 0.5D, 
              this.yCoord + 0.5D, 
              this.zCoord + 0.5D, 
              2.0D);
        if (thePlayer == null)
        {
            if (worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord) == 
                  BlockManager.movingLightSource)
            {
                worldObj.setBlockToAir(this.xCoord, this.yCoord, this.zCoord);
            }
        }else if(thePlayer.getHeldItem() == null || !(thePlayer.getHeldItem().getItem() instanceof ItemWand)){
        	 if (worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord) == 
                     BlockManager.movingLightSource)
               {
                   worldObj.setBlockToAir(this.xCoord, this.yCoord, this.zCoord);
               }
        }else if(!(thePlayer.getHeldItem().getTagCompound().getBoolean("lumos"))){
        	if (worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord) == 
                    BlockManager.movingLightSource)
              {
                  worldObj.setBlockToAir(this.xCoord, this.yCoord, this.zCoord);
              }
        }
    }  
    
    public void setPlayer(EntityPlayer parPlayer)
    {
        thePlayer = parPlayer;
    }
}
