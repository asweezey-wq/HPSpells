package com.apmods.hpspells.spell;

import java.util.Timer;

import com.apmods.hpspells.entity.EntitySpell;
import com.apmods.hpspells.lib.SpellLib;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MovingObjectPosition;

public class SpellObliviate extends SpellBase implements ISpell{

	@Override
	public int getSpellIndex() {
		return 39;
	}

	@Override
	public String getName() {
		return "Obliviate";
	}

	@Override
	public void doBlockEffect(Block blockHit, EntityLivingBase shootingEntity, EntitySpell spell, MovingObjectPosition mop) {
	}

	@Override
	public void doEntityEffect(EntityLivingBase entityHit, EntityLivingBase shootingEntity, EntitySpell spell) {
		if (entityHit instanceof EntityCreature) {
			if(!entityHit.worldObj.isRemote){
				EntityAILookIdle task = new EntityAILookIdle((EntityLiving) entityHit);
				((EntityCreature)entityHit).targetTasks.addTask(0, task);
				((EntityCreature) entityHit).setAttackTarget(null);
				Timer t = new Timer();
				t.schedule(new TimerTaskObliviate((EntityCreature) entityHit, task), (long) (8000 * spell.spellStrength) );
			}
		}else if(entityHit instanceof EntityPlayer){
			if(!entityHit.worldObj.isRemote){
				entityHit.rotationYawHead = -entityHit.rotationYawHead;
				entityHit.addPotionEffect(new PotionEffect(Potion.confusion.id, 60, 1));
			}
		}
	}

	@Override
	public boolean doEffectOnCaster(EntityPlayer player, double spellMultiplier) {
		return false;
	}

	@Override
	public boolean isComplicated() {
		return false;
	}

	

	

	@Override
	public int getColor() {
		return 0xF67CFE;
	}

	@Override
	public EnumSpellType getSpellType() {
		return EnumSpellType.HEX;
	}

}
