package com.apmods.hpspells.spell;

import java.util.Random;

import com.apmods.hpspells.entity.EntitySpell;
import com.apmods.hpspells.lib.SpellLib;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.event.entity.player.BonemealEvent;

public class SpellHerbivicus extends SpellBase implements ISpell {

	@Override
	public int getSpellIndex() {
		return 19;
	}

	@Override
	public String getName() {
		return "Herbivicus";
	}

	@Override
	public void doBlockEffect(Block blockHit, EntityLivingBase shootingEntity, EntitySpell spell, MovingObjectPosition mop) {
		int skill = getSkillLevel(shootingEntity, this);
		Random rand = new Random();
		BonemealEvent event = new BonemealEvent((EntityPlayer) spell.shootingEntity, shootingEntity.worldObj, blockHit, (int) spell.posX, (int) spell.posY - 1, (int) spell.posZ);
		if (blockHit instanceof IGrowable) {
			IGrowable igrowable = (IGrowable) blockHit;

			if (igrowable.func_149851_a(shootingEntity.worldObj, (int) spell.posX, (int) spell.posY - 1, (int) spell.posZ, shootingEntity.worldObj.isRemote)) {
				if (!shootingEntity.worldObj.isRemote) {
					if (igrowable.func_149852_a(shootingEntity.worldObj, shootingEntity.worldObj.rand, (int) spell.posX, (int) spell.posY - 1, (int) spell.posZ)) {
						for (int i = 0; i < (4 + skill) * spell.spellStrength; i++) {
							igrowable.func_149853_b(shootingEntity.worldObj, shootingEntity.worldObj.rand, (int) spell.posX, (int) spell.posY - 1, (int) spell.posZ);
						}
					}

				}

			}
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
		return true;
	}

	

	

	@Override
	public int getColor() {
		return 0x06B700;
	}

	@Override
	public EnumSpellType getSpellType() {
		return EnumSpellType.TRANSFIGURATION;
	}

}
