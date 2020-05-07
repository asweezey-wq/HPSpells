package com.apmods.hpspells.spell;

import com.apmods.hpspells.entity.EntitySpell;
import com.apmods.hpspells.lib.SpellLib;
import com.apmods.hpspells.main.HPSpells;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MovingObjectPosition;

public class SpellPetrificusTotalus extends SpellBase implements ISpell {

	@Override
	public int getSpellIndex() {
		return 21;
	}

	@Override
	public String getName() {
		return "Petrificus Totalus";
	}

	@Override
	public void doBlockEffect(Block blockHit, EntityLivingBase shootingEntity, EntitySpell spell, MovingObjectPosition mop) {
	}

	@Override
	public void doEntityEffect(EntityLivingBase entityHit, EntityLivingBase shootingEntity, EntitySpell spell) {
		int skill = getSkillLevel(shootingEntity, this);
		entityHit.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, (int) ((120 + skill * 10) * spell.spellStrength), 20));
		entityHit.addPotionEffect(new PotionEffect(HPSpells.petrified.id, (int) ((120 + skill * 10) * spell.spellStrength), 20));
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
		return 0x4272C9;
	}

	@Override
	public EnumSpellType getSpellType() {
		return EnumSpellType.HEX;
	}

}
