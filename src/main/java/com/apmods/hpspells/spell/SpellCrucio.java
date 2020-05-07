package com.apmods.hpspells.spell;

import com.apmods.hpspells.entity.EntitySpell;
import com.apmods.hpspells.lib.SpellLib;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MovingObjectPosition;

public class SpellCrucio extends SpellBase implements ISpell {

	@Override
	public int getSpellIndex() {
		return 34;
	}

	@Override
	public String getName() {
		return "Crucio";
	}

	@Override
	public void doBlockEffect(Block blockHit, EntityLivingBase shootingEntity, EntitySpell spell, MovingObjectPosition mop) {
	}

	@Override
	public void doEntityEffect(EntityLivingBase entityHit, EntityLivingBase shootingEntity, EntitySpell spell) {
		int skill = getSkillLevel(shootingEntity, this);
		entityHit.addPotionEffect(new PotionEffect(Potion.poison.id, (int) ((200 + 10 * skill) * spell.spellStrength), 20));
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
		return 0x780000;
	}

	@Override
	public EnumSpellType getSpellType() {
		return EnumSpellType.DARK;
	}

}
