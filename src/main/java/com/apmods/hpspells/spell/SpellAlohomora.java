package com.apmods.hpspells.spell;

import com.apmods.hpspells.entity.EntitySpell;
import com.apmods.hpspells.lib.SpellLib;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;

public class SpellAlohomora extends SpellBase implements ISpell {

	@Override
	public int getSpellIndex() {
		return 28;
	}

	@Override
	public String getName() {
		return "Alohomora";
	}

	@Override
	public void doBlockEffect(Block blockHit, EntityLivingBase shootingEntity, EntitySpell spell, MovingObjectPosition mop) {
		if (blockHit instanceof BlockDoor) {
			int i1 = spell.func_150012_g(shootingEntity.worldObj, mop.blockX, mop.blockY, mop.blockZ);
			int j1 = i1 & 7;
			j1 ^= 4;
			if ((i1 & 8) == 0) {
				shootingEntity.worldObj.setBlockMetadataWithNotify(mop.blockX, mop.blockY, mop.blockZ, j1, 2);
				shootingEntity.worldObj.markBlockRangeForRenderUpdate(mop.blockX, mop.blockY, mop.blockZ, mop.blockX, mop.blockY, mop.blockZ);
			} else {
				shootingEntity.worldObj.setBlockMetadataWithNotify(mop.blockX, mop.blockY - 1, mop.blockZ, j1, 2);
				shootingEntity.worldObj.markBlockRangeForRenderUpdate(mop.blockX, mop.blockY - 1, mop.blockZ, mop.blockX, mop.blockY, mop.blockZ);
			}

			shootingEntity.worldObj.playAuxSFXAtEntity((EntityPlayer) spell.shootingEntity, 1003, mop.blockX, mop.blockY, mop.blockZ, 0);
		} else if (blockHit instanceof BlockTrapDoor) {
			int i1 = shootingEntity.worldObj.getBlockMetadata(mop.blockX, mop.blockY, mop.blockZ);
			shootingEntity.worldObj.setBlockMetadataWithNotify(mop.blockX, mop.blockY, mop.blockZ, i1 ^ 4, 2);
			shootingEntity.worldObj.playAuxSFXAtEntity((EntityPlayer) spell.shootingEntity, 1003, mop.blockX, mop.blockY, mop.blockZ, 0);
		}
	}

	@Override
	public void doEntityEffect(EntityLivingBase entityHit, EntityLivingBase shootingEntity, EntitySpell spell) {
	}

	/**
	 * @return False if this spell should shoot a projectile
	 */
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
		return 0xFFDA00;
	}

	@Override
	public EnumSpellType getSpellType() {
		return EnumSpellType.CHARM;
	}

}
