package com.apmods.hpspells.spell;

import com.apmods.hpspells.entity.EntitySpell;
import com.apmods.hpspells.lib.SpellLib;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;

public class SpellExpelliarmus extends SpellBase implements ISpell{

	@Override
	public int getSpellIndex() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Expelliarmus";
	}

	@Override
	public void doBlockEffect(Block blockHit, EntityLivingBase shootingEntity, EntitySpell spell, MovingObjectPosition mop) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doEntityEffect(EntityLivingBase entityHit, EntityLivingBase shootingEntity, EntitySpell spell) {
		int skill = getSkillLevel(shootingEntity, this);
		damage = (1 + skill / 2) * spell.spellStrength;
		knockback = (0.2 + skill / 20) * spell.spellStrength;
		ItemStack item = entityHit.getHeldItem();
		if (item != null) {
			spell.entityDropItem(item, 0);
			entityHit.setCurrentItemOrArmor(0, null);
			
		}
		
	}

	@Override
	public boolean doEffectOnCaster(EntityPlayer player, double spellMultiplier) {
		return false;
	}

	@Override
	public boolean isComplicated() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public int getColor() {
		return 0xFFCB00;
	}

	@Override
	public EnumSpellType getSpellType() {
		return EnumSpellType.DADA;
	}

}
