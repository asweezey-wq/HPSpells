package com.apmods.hpspells.entity;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class EntityAIWandmaker extends EntityAIBase
{
    private EntityWandmaker wandmaker;
    private static final String __OBFID = "CL_00001617";

    public EntityAIWandmaker(EntityWandmaker p_i1658_1_)
    {
        this.wandmaker = p_i1658_1_;
        this.setMutexBits(5);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (!this.wandmaker.isEntityAlive())
        {
            return false;
        }
        else if (this.wandmaker.isInWater())
        {
            return false;
        }
        else if (!this.wandmaker.onGround)
        {
            return false;
        }
        else if (this.wandmaker.velocityChanged)
        {
            return false;
        }
        else
        {
            EntityPlayer entityplayer = this.wandmaker.getCustomer();
            return this.wandmaker.getCustomer() != null;
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.wandmaker.getNavigator().clearPathEntity();
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        this.wandmaker.setCustomer((EntityPlayer)null);
    }
}