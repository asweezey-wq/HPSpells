package com.apmods.hpspells.spell;

import com.apmods.hpspells.entity.EntitySpell;
import com.apmods.hpspells.lib.SpellLib;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.MovingObjectPosition;

public class SpellDefodio extends SpellBase implements ISpell {

	@Override
	public int getSpellIndex() {
		return 12;
	}

	@Override
	public String getName() {
		return "Defodio";
	}

	@Override
	public void doBlockEffect(Block blockHit, EntityLivingBase shootingEntity, EntitySpell spell, MovingObjectPosition mop) {
		if (blockHit != Blocks.bedrock) {
			shootingEntity.worldObj.setBlock(mop.blockX, mop.blockY, mop.blockZ, Blocks.air);
			blockHit.dropBlockAsItem(shootingEntity.worldObj, (int) spell.posX, (int) spell.posY, (int) spell.posZ, shootingEntity.worldObj.getBlockMetadata((int) spell.posX, (int) spell.posY, (int) spell.posZ), 0);
		}
	}

	@Override
	public void doEntityEffect(EntityLivingBase entityHit, EntityLivingBase shootingEntity, EntitySpell spell) {
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
		return 0x787878;
	}

	@Override
	public EnumSpellType getSpellType() {
		return EnumSpellType.CHARM;
	}

}
