package com.apmods.hpspells.spell;

import com.apmods.hpspells.entity.EntitySpell;
import com.apmods.hpspells.lib.SpellLib;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;

public class SpellBombarda extends SpellBase implements ISpell{

	@Override
	public int getSpellIndex() {
		return 37;
	}

	@Override
	public String getName() {
		return "Bombarda";
	}

	@Override
	public void doBlockEffect(Block blockHit, EntityLivingBase shootingEntity, EntitySpell spell, MovingObjectPosition mop) {
		int skill = getSkillLevel(shootingEntity, this);
		spell.worldObj.createExplosion(spell, spell.posX, spell.posY, spell.posZ, (float) ((4.0f + skill / 4.5) * spell.spellStrength), true);
	}

	@Override
	public void doEntityEffect(EntityLivingBase entityHit, EntityLivingBase shootingEntity, EntitySpell spell) {
		int skill = getSkillLevel(shootingEntity, this);
		spell.worldObj.createExplosion(spell, spell.posX, spell.posY, spell.posZ, (float) ((4.0f + skill / 4.5) * spell.spellStrength), true);
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
		return 0x434343;
	}

	@Override
	public EnumSpellType getSpellType() {
		return EnumSpellType.HEX;
	}

}
