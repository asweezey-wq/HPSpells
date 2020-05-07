package com.apmods.hpspells.spell;

import com.apmods.hpspells.entity.EntitySpell;
import com.apmods.hpspells.lib.SpellLib;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.MovingObjectPosition;

public class SpellFeraVerto extends SpellBase implements ISpell {

	@Override
	public int getSpellIndex() {
		return 24;
	}

	@Override
	public String getName() {
		return "Fera Verto";
	}

	@Override
	public void doBlockEffect(Block blockHit, EntityLivingBase shootingEntity, EntitySpell spell, MovingObjectPosition mop) {
	}

	@Override
	public void doEntityEffect(EntityLivingBase entityHit, EntityLivingBase shootingEntity, EntitySpell spell) {
		if (!(entityHit instanceof EntityPlayer)) {
			entityHit.dropItem(Items.glass_bottle, 1);
			entityHit.setDead();
		}
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
		return 0xF0F0F0;
	}

	@Override
	public EnumSpellType getSpellType() {
		return EnumSpellType.TRANSFIGURATION;
	}

}
