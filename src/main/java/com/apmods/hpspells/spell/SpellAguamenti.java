package com.apmods.hpspells.spell;

import com.apmods.hpspells.entity.EntitySpell;
import com.apmods.hpspells.lib.SpellLib;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.MovingObjectPosition;

public class SpellAguamenti extends SpellBase implements ISpell{

	@Override
	public int getSpellIndex() {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Aguamenti";
	}

	@Override
	public void doBlockEffect(Block blockHit, EntityLivingBase shootingEntity, EntitySpell spell, MovingObjectPosition mop) {
		if (mop.sideHit == 1) {
			shootingEntity.worldObj.setBlock(mop.blockX, mop.blockY + 1, mop.blockZ, Blocks.flowing_water, 0, 3);
		} else if (mop.sideHit == 0) {
			shootingEntity.worldObj.setBlock(mop.blockX, mop.blockY - 1, mop.blockZ, Blocks.flowing_water, 0, 3);
		} else if (mop.sideHit == 2) {
			shootingEntity.worldObj.setBlock(mop.blockX + 1, mop.blockY, mop.blockZ, Blocks.flowing_water, 0, 3);
		} else if (mop.sideHit == 4) {
			shootingEntity.worldObj.setBlock(mop.blockX - 1, mop.blockY, mop.blockZ, Blocks.flowing_water, 0, 3);
		} else if (mop.sideHit == 3) {
			shootingEntity.worldObj.setBlock(mop.blockX, mop.blockY, mop.blockZ + 1, Blocks.flowing_water, 0, 3);
		} else if (mop.sideHit == 5) {
			shootingEntity.worldObj.setBlock(mop.blockX, mop.blockY, mop.blockZ - 1, Blocks.flowing_water, 0, 3);
		}
	}

	@Override
	public void doEntityEffect(EntityLivingBase entityHit, EntityLivingBase shootingEntity, EntitySpell spell) {
		entityHit.extinguish();
	}

	@Override
	public boolean doEffectOnCaster(EntityPlayer player, double spellMultiplier) {
		return false;
	}

	@Override
	public boolean isComplicated() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getColor() {
		return 0x006AFF;
	}

	@Override
	public EnumSpellType getSpellType() {
		return EnumSpellType.CHARM;
	}

}
