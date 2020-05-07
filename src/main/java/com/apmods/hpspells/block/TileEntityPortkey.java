package com.apmods.hpspells.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class TileEntityPortkey extends TileEntity{
	int prevx = 0;
	int prevy = 0;
	int prevz = 0;
	 int x = 0;
	 int y = 0;
	 int z = 0;
	Block b = Blocks.air;
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
	    super.readFromNBT(nbt);
	    this.x = nbt.getInteger("xcoord");
	    this.y = nbt.getInteger("ycoord");
	    this.z = nbt.getInteger("zcoord");
	    this.prevx = nbt.getInteger("prevxcoord");
	    this.prevy = nbt.getInteger("prevycoord");
	    this.prevz = nbt.getInteger("prevzcoord");
	    this.b = Block.getBlockById(nbt.getInteger("block"));
	}
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
	    super.writeToNBT(nbt);
	    nbt.setInteger("xcoord", x);
	    nbt.setInteger("ycoord", y);
	    nbt.setInteger("zcoord", z);
	    nbt.setInteger("prevxcoord", prevx);
	    nbt.setInteger("prevycoord", prevy);
	    nbt.setInteger("prevzcoord", prevz);
	    nbt.setInteger("block", Block.getIdFromBlock(b));
	}
	public void setPortkeyCoords(World world, int x1, int y1, int z1) {
		x = x1;
		y = y1;
		z = z1;
		world.notifyBlockChange(x, y, z, BlockManager.portkey);
		
	}
	
	/**
	 * 
	 * @param world The World
	 * @param player The Player that clicked the portkey
	 * @param px X Coord of Portkey before teleportation
	 * @param py Y Coord of Portkey before teleportation
	 * @param pz Z Coord of Portkey before teleportation
	 */
	public void teleport(World world, EntityPlayer player, int px, int py, int pz) {
		if(!world.getBlock(x, y, z).isOpaqueCube() && !world.getBlock(x, y + 1, z).isOpaqueCube()){
			prevx = (int) px;
			prevy = (int) py;
			prevz = (int) pz;
			AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(px - 2, py, pz - 2, px + 2, py + 1, pz + 2);
			player.setPositionAndUpdate(x, y, z);
			List<EntityPlayer> list = world.getEntitiesWithinAABB(EntityPlayer.class, aabb);
			for(EntityPlayer p : list){
				p.setPositionAndUpdate(x, y, z);
			}
			BlockPortkey po = (BlockPortkey) BlockManager.portkey;
			world.setBlock(x, y, z, po);
			TileEntityPortkey p = (TileEntityPortkey) worldObj.getTileEntity(x, y, z);
			   p.setPortkeyCoords(worldObj, prevx, prevy, prevz);
			   world.setBlock(px, py, pz, Blocks.air);
		}
		else{
        	player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Portkey Destination Obstructed!"));
        }
		
	}
	
	public void revertToBlock(World world, int x, int y, int z){
		world.setBlock(x, y, z, b);
	}
	
	
}
