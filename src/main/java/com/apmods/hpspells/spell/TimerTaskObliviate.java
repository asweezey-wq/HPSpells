package com.apmods.hpspells.spell;

import java.util.TimerTask;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAILookIdle;

public class TimerTaskObliviate extends TimerTask{

	public EntityCreature affectedEntity;
	public EntityAILookIdle task;
	
	public TimerTaskObliviate (EntityCreature entity, EntityAILookIdle task){
		affectedEntity = entity;
		this.task = task;
	}
	
	@Override
	public void run() {
		affectedEntity.targetTasks.removeTask(task);
		this.cancel();
	}

}
