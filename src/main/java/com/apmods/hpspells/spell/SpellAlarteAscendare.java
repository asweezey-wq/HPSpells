package com.apmods.hpspells.spell;

import com.apmods.hpspells.entity.EntitySpell;
import com.apmods.hpspells.lib.SpellLib;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;

public class SpellAlarteAscendare extends SpellBase implements ISpell {

	@Override
	public int getSpellIndex() {
		return 11;
	}

	@Override
	public String getName() {
		return "Alarte Ascendare";
	}

	@Override
	public void doBlockEffect(Block blockHit, EntityLivingBase shootingEntity, EntitySpell spell, MovingObjectPosition mop) {
	}

	@Override
	public void doEntityEffect(EntityLivingBase entityHit, EntityLivingBase shootingEntity, EntitySpell spell) {
		int skill = getSkillLevel(shootingEntity, this);
		entityHit.addVelocity(0, (1.2 + skill/10) * spell.spellStrength, 0);
	}

	@Override
	public boolean doEffectOnCaster(EntityPlayer player, double spellMultiplier) {
		return false;
	}

	@Override
	public boolean isComplicated() {
		return true;
	}

	@Override
	public int getColor() {
		return 0x00FFC8;
	}

	@Override
	public EnumSpellType getSpellType() {
		return EnumSpellType.HEX;
	}
}