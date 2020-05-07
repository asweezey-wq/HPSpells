package com.apmods.hpspells.entity;

import com.apmods.hpspells.main.HPSpells;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityDementor extends EntityMob{
	private float heightOffset = 0.5F;
    /** ticks until heightOffset is randomized */
    private int heightOffsetUpdateTime;

	public EntityDementor(World p_i1587_1_) {
		super(p_i1587_1_);
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIAvoidEntity(this, EntityPatronus.class, 6.0F, 1.0D, 1.2D));
		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.2D, false));
		this.tasks.addTask(4, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(6, new EntityAILookIdle(this));

		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.setSize(0.6F, 1.8F);
	}
	protected void fall(float p_70069_1_) {}
	
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.2D);
	}
	public boolean isEntityInvulnerable(){
		return true;
	}
	public boolean attackEntityAsMob(Entity p_70652_1_)
    {
        if (super.attackEntityAsMob(p_70652_1_))
        {
            ((EntityLivingBase)p_70652_1_).addPotionEffect(new PotionEffect(Potion.wither.id, 60));
            return true;
        }
        else
        {
            return false;
        }
    }
    protected boolean isAIEnabled(){
    	return true;
    }
    
    public void playLivingSound()
    {
        this.playSound(HPSpells.MODID + ":" + "mob.dementor.breathe", 1, 1);
    }
    
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
    {
    	if (this.getHealth() <= 0.0F)
        {
        	this.playSound(HPSpells.MODID + ":" + "mob.dementor.death", this.getSoundVolume(), this.getSoundPitch());
        }
        else
        {
        	this.playSound(HPSpells.MODID + ":" + "mob.dementor.hit", this.getSoundVolume(), this.getSoundPitch());
        }
    	return super.attackEntityFrom(par1DamageSource, par2);
    }
    /**
     * Moves the entity based on the specified heading.  Args: strafe, forward
     */
    public void onLivingUpdate()
    {
        if (!this.worldObj.isRemote)
        {
            --this.heightOffsetUpdateTime;

            if (this.heightOffsetUpdateTime <= 0)
            {
                this.heightOffsetUpdateTime = 100;
                this.heightOffset = 0.5F + (float)this.rand.nextGaussian() * 3.0F;
            }

            if (this.getEntityToAttack() != null && this.getEntityToAttack().posY + (double)this.getEntityToAttack().getEyeHeight() > this.posY + (double)this.getEyeHeight() + (double)this.heightOffset)
            {
                this.motionY += (0.30000001192092896D - this.motionY) * 0.30000001192092896D;
            }
        }

        if (this.rand.nextInt(24) == 0)
        {
        }

        if (!this.onGround && this.motionY < 0.0D)
        {
            this.motionY *= 0.6D;
        }

        for (int i = 0; i < 2; ++i)
        {
            this.worldObj.spawnParticle("largesmoke", this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, 0.0D, 0.0D, 0.0D);
        }

        super.onLivingUpdate();
    }

    /**
     * returns true if this entity is by a ladder, false otherwise
     */
    public boolean isOnLadder()
    {
        return false;
    }

}
