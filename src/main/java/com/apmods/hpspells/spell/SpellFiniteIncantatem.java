package com.apmods.hpspells.spell;

import java.util.Collection;

import com.apmods.hpspells.entity.EntitySpell;
import com.apmods.hpspells.lib.SpellLib;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MovingObjectPosition;

public class SpellFiniteIncantatem extends SpellBase implements ISpell{

	@Override
	public int getSpellIndex() {
		return 40;
	}

	@Override
	public String getName() {
		return "Finite Incantatem";
	}

	@Override
	public void doBlockEffect(Block blockHit, EntityLivingBase shootingEntity, EntitySpell spell, MovingObjectPosition mop) {
	}

	@Override
	public void doEntityEffect(EntityLivingBase entityHit, EntityLivingBase shootingEntity, EntitySpell spell) {
	}

	@Override
	public boolean doEffectOnCaster(EntityPlayer player, double spellMultiplier) {
		Collection<PotionEffect> c = (Collection<PotionEffect>) player.getActivePotionEffects();
		while(c.size() > 0){
			PotionEffect pe = (PotionEffect) c.toArray()[0];
			Potion p = Potion.potionTypes[pe.getPotionID()];
			if(p.isBadEffect()){
				player.removePotionEffect(pe.getPotionID());
			}
		}
		return true;
	}

	@Override
	public boolean isComplicated() {
		return false;
	}

	

	

	@Override
	public int getColor() {
		return 0;
	}

	@Override
	public EnumSpellType getSpellType() {
		return EnumSpellType.DADA;
	}

}
