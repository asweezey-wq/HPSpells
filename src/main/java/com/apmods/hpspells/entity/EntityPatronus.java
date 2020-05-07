package com.apmods.hpspells.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class EntityPatronus extends EntityCreature{
	public static Entity owner;
	public static int type;
	private int heightOffsetUpdateTime;
	private float heightOffset;
	public EntityPatronus(World p_i1594_1_, EntityPlayer player, int type) {
		super(p_i1594_1_);
		this.setSize(0.9f, 0.9f);
		this.owner = player;
		this.setAnimalIndex(type);
		this.tasks.addTask(0, new EntityAISwimming(this));
//		this.tasks.addTask(1, new EntityAIFollowOwner(this, 1.0, 10.0f, 2.0f));
        this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityDementor.class, 6.0F));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
	}
	public EntityPatronus(World p_i1594_1_) {
		super(p_i1594_1_);
	}
	protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(18, Integer.valueOf(0));
        this.dataWatcher.addObject(19, Integer.valueOf(0));
    }
	protected void fall(float f){
		
	}
	protected void updateFallState(double p_70064_1_, boolean p_70064_3_)
    {
		if(this.getAnimalIndex() != 10){
			super.updateFallState(p_70064_1_, p_70064_3_);
		}
    }
    public boolean isAIEnabled()
    {
        return true;
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(100.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
    }
    @SideOnly(Side.CLIENT)
    public int getBrightnessForRender(float par1)
    {
        return 15728880;
    }

    /**
     * Gets how bright this entity is.
     */
    public float getBrightness(float par1)
    {
        return 0.5f;
    }
    public boolean isEntityInvulnerable(){
    	return true;
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound p_70014_1_)
    {
        super.writeEntityToNBT(p_70014_1_);
        p_70014_1_.setInteger("animalIndex", this.getAnimalIndex());
        p_70014_1_.setInteger("life", this.getLife());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound nbt)
    {
        super.readEntityFromNBT(nbt);
        this.setAnimalIndex(nbt.getInteger("animalIndex"));
        this.setLife(nbt.getInteger("life"));
    }
    public int getAnimalIndex(){
    	return this.dataWatcher.getWatchableObjectInt(18);
    }
    private void setAnimalIndex(int i){
    	this.dataWatcher.updateObject(18, i);
    }
    public int getLife(){
    	return this.dataWatcher.getWatchableObjectInt(19);
    }
    private void setLife(int i){
    	this.dataWatcher.updateObject(19, i);
    }
	public void onLivingUpdate()
    {
		for (int k = 0; k < 2; ++k)
        {
            this.worldObj.spawnParticle("droplet", this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height - 0.25D, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, (this.rand.nextDouble() - 0.5D) * 2.0D, -this.rand.nextDouble(), (this.rand.nextDouble() - 0.5D) * 2.0D);
        }
		super.onLivingUpdate();
		this.setLife(this.getLife() + 1);
		if(this.getLife() >= 200){
			this.setDead();
				this.worldObj.updateLightByType(EnumSkyBlock.Block,
						(int) this.posX, (int) this.posY, (int) this.posZ);
			
		}
		if(this.getAnimalIndex() == 10){
			if(this.onGround){
				this.motionY += 0.5;
				
			}
			else if(motionY < 0.0){
				this.motionY *= 0.6f;
			}
		}

		addLight();
    }
	private void addLight() {
		this.worldObj.setLightValue(EnumSkyBlock.Block, (int) this.posX,
				(int) this.posY, (int) this.posZ, 6);
		this.worldObj.markBlockRangeForRenderUpdate((int) this.posX,
				(int) this.posY, (int) this.posX, 12, 12, 12);
		this.worldObj.markBlockForUpdate((int) this.posX, (int) this.posY,
				(int) this.posZ);
		this.worldObj.updateLightByType(EnumSkyBlock.Block, (int) this.posX,
				(int) this.posY + 1, (int) this.posZ);
		this.worldObj.updateLightByType(EnumSkyBlock.Block, (int) this.posX,
				(int) this.posY - 1, (int) this.posZ);
		this.worldObj.updateLightByType(EnumSkyBlock.Block,
				(int) this.posX + 1, (int) this.posY, (int) this.posZ);
		this.worldObj.updateLightByType(EnumSkyBlock.Block,
				(int) this.posX - 1, (int) this.posY, (int) this.posZ);
		this.worldObj.updateLightByType(EnumSkyBlock.Block, (int) this.posX,
				(int) this.posY, (int) this.posZ + 1);
		this.worldObj.updateLightByType(EnumSkyBlock.Block, (int) this.posX,
				(int) this.posY, (int) this.posZ - 1);
	}
//	@Override
//	public EntityAgeable createChild(EntityAgeable p_90011_1_) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	public EntityLivingBase getOwner()
    {
		return (EntityLivingBase) owner;
    }


}
