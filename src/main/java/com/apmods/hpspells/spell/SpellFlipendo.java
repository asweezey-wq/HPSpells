package com.apmods.hpspells.spell;

import com.apmods.hpspells.entity.EntitySpell;
import com.apmods.hpspells.main.HPSpells;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MovingObjectPosition;

public class SpellFlipendo extends SpellBase implements ISpell {
	
	@Override
	public int getSpellIndex() {
		return 16;
	}

	@Override
	public String getName() {
		return "Flipendo";
	}

	@Override
	public void doBlockEffect(Block blockHit, EntityLivingBase shootingEntity, EntitySpell spell, MovingObjectPosition mop) {
	}

	@Override
	public void doEntityEffect(EntityLivingBase entityHit, EntityLivingBase shootingEntity, EntitySpell spell) {
		int skill = getSkillLevel(shootingEntity, this);
		damage = 3;
		knockback = (0.4 + skill / 5) * spell.spellStrength;
		entityHit.addPotionEffect(new PotionEffect(HPSpells.flipped.id, 10));
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
		return 0x00D7FF;
	}

	@Override
	public EnumSpellType getSpellType() {
		return EnumSpellType.HEX;
	}

}
