package com.apmods.hpspells.spell;

import com.apmods.hpspells.entity.EntitySpell;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;

public class SpellConfringo extends SpellBase implements ISpell{

	@Override
	public int getSpellIndex() {
		// TODO Auto-generated method stub
		return 6;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Confringo";
	}

	@Override
	public void doBlockEffect(Block blockHit, EntityLivingBase shootingEntity, EntitySpell spell, MovingObjectPosition mop) {
		int skill = getSkillLevel(shootingEntity, this);
		spell.worldObj.newExplosion(spell, spell.posX, spell.posY, spell.posZ, (float) ((3.0f + skill / 4.5) * spell.spellStrength), true, true);
		
	}

	@Override
	public void doEntityEffect(EntityLivingBase entityHit, EntityLivingBase shootingEntity, EntitySpell spell) {
		int skill = getSkillLevel(shootingEntity, this);
		spell.worldObj.newExplosion(spell, spell.posX, spell.posY, spell.posZ, (float) ((3.0f + skill / 4.5) * spell.spellStrength), true, true);
	}

	@Override
	public boolean doEffectOnCaster(EntityPlayer player, double spellMultiplier) {
		return false;
	}

	@Override
	public boolean isComplicated() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public int getColor() {
		return 0xFF7700;
	}

	@Override
	public EnumSpellType getSpellType() {
		return EnumSpellType.DARK;
	}

}
