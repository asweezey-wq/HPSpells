package com.apmods.hpspells.spell;

import com.apmods.hpspells.entity.EntitySpell;
import com.apmods.hpspells.lib.SpellLib;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;

public class SpellNox extends SpellBase implements ISpell{

	@Override
	public int getSpellIndex() {
		// TODO Auto-generated method stub
		return 8;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Nox";
	}

	@Override
	public void doBlockEffect(Block blockHit, EntityLivingBase shootingEntity, EntitySpell spell, MovingObjectPosition mop) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doEntityEffect(EntityLivingBase entityHit, EntityLivingBase shootingEntity, EntitySpell spell) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean doEffectOnCaster(EntityPlayer player, double spellMultiplier) {
		player.getCurrentEquippedItem().stackTagCompound.setBoolean("lumos", false);
		return true;
	}

	@Override
	public boolean isComplicated() {
		// TODO Auto-generated method stub
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
