package com.apmods.hpspells.spell;

import com.apmods.hpspells.entity.EntityDementor;
import com.apmods.hpspells.entity.EntitySpell;
import com.apmods.hpspells.lib.SpellLib;
import com.apmods.hpspells.main.HPSpells;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;

public class SpellAvadaKedavra extends SpellBase implements ISpell {

	@Override
	public int getSpellIndex() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Avada Kedavra";
	}

	@Override
	public void doBlockEffect(Block blockHit, EntityLivingBase entityHit, EntitySpell spell, MovingObjectPosition mop) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doEntityEffect(EntityLivingBase shootingEntity, EntityLivingBase entityHit, EntitySpell spell) {
		damage = (int) (10000 * spell.spellStrength);
		if (entityHit instanceof EntityDementor) {
			entityHit.playSound(HPSpells.MODID + ":" + "mob.dementor.death", 1, 1);
			entityHit.setDead();

		}
		knockback = 0.2;

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
		return 0x00FF00;
	}

	@Override
	public EnumSpellType getSpellType() {
		return EnumSpellType.DARK;
	}

}
