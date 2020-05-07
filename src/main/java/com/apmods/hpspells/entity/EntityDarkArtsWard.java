package com.apmods.hpspells.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityDarkArtsWard extends Entity{

	
	public EntityDarkArtsWard(World p_i1582_1_) {
		super(p_i1582_1_);
		this.setSize(0.5f, 0.5f);
	}
		
	public boolean isEntityInvulnerable(){
		return true;
	}
	
	
	public AxisAlignedBB getBoundingBox(){
		return this.boundingBox;
	}
	@Override
	public AxisAlignedBB getCollisionBox(Entity p_70114_1_) {
		return this.boundingBox;
	}
	public boolean interactFirst(EntityPlayer player)
    {
		if(player.capabilities.isCreativeMode){
			this.setDead();
		}
        return true;
    }
	
	public boolean canBePushed(){
		return false;
	}
	
	public double getYOffset(){
		return 2;
	}
	
	@Override
	public boolean canBeCollidedWith() {
		return false;
	}

	@Override
	protected void entityInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource p_70097_1_, float p_70097_2_) {
		if(p_70097_1_.getSourceOfDamage() instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) p_70097_1_.getSourceOfDamage();
			if(player.capabilities.isCreativeMode){
				this.setDead();
			}
		}
		return true;
	}
}
