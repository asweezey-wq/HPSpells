package com.apmods.hpspells.spell;

import com.apmods.hpspells.entity.EntitySpell;
import com.apmods.hpspells.lib.SpellLib;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MovingObjectPosition;

public class SpellStupefy extends SpellBase implements ISpell{
	
	@Override
	public int getSpellIndex() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Stupefy";
	}

	@Override
	public void doBlockEffect(Block blockHit, EntityLivingBase shootingEntity, EntitySpell spell, MovingObjectPosition mop) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doEntityEffect(EntityLivingBase entityHit, EntityLivingBase shootingEntity, EntitySpell spell) {
		int skill = getSkillLevel(shootingEntity, this);
		entityHit.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, (int) ((30 + (skill * 5)) * spell.spellStrength), 0));
		this.damage = (int) ((4 + skill) * spell.spellStrength);
		this.knockback = (0.3 + skill/10) * spell.spellStrength;
	}

	@Override
	public boolean doEffectOnCaster(EntityPlayer player, double spellMultiplier) {
		return false;
	}

	@Override
	public boolean isComplicated() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public int getColor() {
		return 0xFF0000;
	}

	@Override
	public EnumSpellType getSpellType() {
		return EnumSpellType.DADA;
	}

}
