package com.apmods.hpspells.spell;

import com.apmods.hpspells.entity.EntityBird;
import com.apmods.hpspells.entity.EntitySpell;
import com.apmods.hpspells.lib.SpellLib;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;

public class SpellAvifors extends SpellBase implements ISpell {

	@Override
	public int getSpellIndex() {
		return 30;
	}

	@Override
	public String getName() {
		return "Avifors";
	}

	@Override
	public void doBlockEffect(Block blockHit, EntityLivingBase shootingEntity, EntitySpell spell, MovingObjectPosition mop) {
	}

	@Override
	public void doEntityEffect(EntityLivingBase entityHit, EntityLivingBase shootingEntity, EntitySpell spell) {
		if (!(entityHit instanceof EntityPlayer)) {
			entityHit.setDead();
			EntityBird bat = new EntityBird(spell.worldObj);
			bat.setPositionAndUpdate(entityHit.posX, entityHit.posY, entityHit.posZ);
			spell.worldObj.spawnEntityInWorld(bat);
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
		return 0xB6FF00;
	}

	@Override
	public EnumSpellType getSpellType() {
		return EnumSpellType.TRANSFIGURATION;
	}

}
