package com.apmods.hpspells.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityFumos extends EntityLiving{

	private double smokeRate = 1;
	
	public EntityFumos(World world){
		super(world);
	}
	
	public EntityFumos(World p_i1582_1_, double smokeRate) {
		super(p_i1582_1_);
		this.smokeRate = smokeRate;
	}
	
	public boolean isEntityInvulnerable(){
		return true;
	}
	
	public void onLivingUpdate(){
		if(this.ticksExisted < 100){
			for(int i = 0; i < 20 * smokeRate; i++){
				worldObj.spawnParticle("largesmoke", this.posX + ((-0.5 + rand.nextDouble()) * 2), this.posY + ((-0.5 + rand.nextDouble()) * 2), this.posZ + ((-0.5 + rand.nextDouble()) * 2), (rand.nextDouble() - 0.5), (rand.nextDouble() - 0.5) * 0.5, (rand.nextDouble() - 0.5));
			}
		}
		else{
			this.setDead();
		}
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound p_70037_1_) {
		super.readEntityFromNBT(p_70037_1_);
		
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound p_70014_1_) {
		super.writeEntityToNBT(p_70014_1_);
		
	}

}
