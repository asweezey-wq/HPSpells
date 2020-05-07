package com.apmods.hpspells.entity;

import com.apmods.hpspells.block.BlockManager;

import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityLumosMaxima extends Entity{

	public EntityLumosMaxima(World p_i1582_1_) {
		super(p_i1582_1_);
		this.setSize(0.1f, 0.1f);
	}

	@Override
	protected void entityInit() {
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		if(ticksExisted > 1200){
			this.worldObj.setBlock((int)this.posX, (int)this.posY, (int)this.posZ, Blocks.air);
			this.setDead();
		}else
		if(this.worldObj.getBlock((int)this.posX, (int)this.posY, (int)this.posZ) != BlockManager.lumosMaxima){
			if(this.worldObj.getBlock((int)this.posX, (int)this.posY, (int)this.posZ) == Blocks.air){
				this.worldObj.setBlock((int)this.posX, (int)this.posY, (int)this.posZ, BlockManager.lumosMaxima);
			}else {
				this.setDead();
			}
		}
	}
}
