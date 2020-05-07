package com.apmods.hpspells.spell;

import com.apmods.hpspells.entity.EntitySpell;
import com.apmods.hpspells.lib.SpellLib;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MovingObjectPosition;

public class SpellObscuro extends SpellBase implements ISpell {

	@Override
	public int getSpellIndex() {
		return 18;
	}

	@Override
	public String getName() {
		return "Obscuro";
	}

	@Override
	public void doBlockEffect(Block blockHit, EntityLivingBase shootingEntity, EntitySpell spell, MovingObjectPosition mop) {
	}

	@Override
	public void doEntityEffect(EntityLivingBase entityHit, EntityLivingBase shootingEntity, EntitySpell spell) {
		int skill = getSkillLevel(shootingEntity, this);
		entityHit.addPotionEffect(new PotionEffect(Potion.blindness.id, (int) ((80 + skill * 10) * spell.spellStrength), 1));
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
		return 0x000000;
	}

	@Override
	public EnumSpellType getSpellType() {
		return EnumSpellType.HEX;
	}

}
