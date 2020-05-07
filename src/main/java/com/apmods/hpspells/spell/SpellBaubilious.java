package com.apmods.hpspells.spell;

import com.apmods.hpspells.entity.EntitySpell;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;

public class SpellBaubilious extends SpellBase implements ISpell {

	@Override
	public String getName() {
		return "Baubilious";
	}

	@Override
	public void doBlockEffect(Block blockHit, EntityLivingBase shootingEntity, EntitySpell spell, MovingObjectPosition mop) {
		shootingEntity.worldObj.addWeatherEffect(new EntityLightningBolt(shootingEntity.worldObj, mop.blockX, mop.blockY, mop.blockZ));
	}

	@Override
	public void doEntityEffect(EntityLivingBase entityHit, EntityLivingBase shootingEntity, EntitySpell spell) {
		EntityLightningBolt lightning = new EntityLightningBolt(shootingEntity.worldObj, entityHit.posX, entityHit.posY, entityHit.posZ);
		shootingEntity.worldObj.addWeatherEffect(lightning);
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
	public EnumSpellType getSpellType() {
		return EnumSpellType.HEX;
	}

	@Override
	public int getColor() {
		return 0;
	}

}
