package com.apmods.hpspells.spell;

import com.apmods.hpspells.entity.EntitySpell;
import com.apmods.hpspells.lib.SpellLib;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;

public class SpellEpiskey extends SpellBase implements ISpell {

	@Override
	public int getSpellIndex() {
		return 15;
	}

	@Override
	public String getName() {
		return "Episkey";
	}

	@Override
	public void doBlockEffect(Block blockHit, EntityLivingBase shootingEntity, EntitySpell spell, MovingObjectPosition mop) {
	}

	@Override
	public void doEntityEffect(EntityLivingBase entityHit, EntityLivingBase shootingEntity, EntitySpell spell) {
	}

	@Override
	public boolean doEffectOnCaster(EntityPlayer player, double spellMultiplier) {
		int skill = getSkillLevel(player, this);
		if (player.getHealth() > 4) {
			player.heal((float) ((2 + skill) * spellMultiplier));
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
		return EnumSpellType.CHARM;
	}

}
