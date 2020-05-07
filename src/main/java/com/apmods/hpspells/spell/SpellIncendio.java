package com.apmods.hpspells.spell;

import java.util.Random;

import com.apmods.hpspells.entity.EntitySpell;
import com.apmods.hpspells.lib.SpellLib;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.MovingObjectPosition;

public class SpellIncendio extends SpellBase implements ISpell{

	@Override
	public int getSpellIndex() {
		// TODO Auto-generated method stub
		return 9;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Incendio";
	}

	@Override
	public void doBlockEffect(Block blockHit, EntityLivingBase shootingEntity, EntitySpell spell, MovingObjectPosition mop) {
		int x = getCoordsWithOffset(mop.sideHit, mop)[0];
		int y = getCoordsWithOffset(mop.sideHit, mop)[1];
		int z = getCoordsWithOffset(mop.sideHit, mop)[2];
		if (shootingEntity.worldObj.isAirBlock(x, y, z)) {
			shootingEntity.worldObj.setBlock(x, y, z, Blocks.fire);
		}
		Random rand = new Random();
		for (int xOff = -1; xOff < 2; xOff++) {
			for (int zOff = -1; zOff < 2; zOff++) {
				if (rand.nextInt(3) == 0) {
					if (shootingEntity.worldObj.isAirBlock(x + xOff, y, z + zOff)) {
						shootingEntity.worldObj.setBlock(x + xOff, y, z + zOff, Blocks.fire);
					}
				}
			}
		}
		
	}

	@Override
	public void doEntityEffect(EntityLivingBase entityHit, EntityLivingBase shootingEntity, EntitySpell spell) {
		int skill = getSkillLevel(shootingEntity, this);
		entityHit.setFire((int) ((1 + skill) * spell.spellStrength));
		
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

	

	public int[] getCoordsWithOffset(int sideHit, MovingObjectPosition mop) {
		int x = mop.blockX, y = mop.blockY, z = mop.blockZ;
		switch (sideHit) {
		case 0:
			y--;
			break;
		case 1:
			y++;
			break;
		case 2:
			z--;
			break;
		case 3:
			z++;
			break;
		case 4:
			x--;
			break;
		case 5:
			x++;
			break;
		}
		return new int[] { x, y, z };
	}

	@Override
	public int getColor() {
		return 0xFF5D00;
	}

	@Override
	public EnumSpellType getSpellType() {
		return EnumSpellType.CHARM;
	}
}
