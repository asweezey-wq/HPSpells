package com.apmods.hpspells.spell;

import java.util.Random;

import com.apmods.hpspells.entity.EntitySpell;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;

public class SpellEvanesco extends SpellBase implements ISpell {

	@Override
	public String getName() {
		return "Evanesco";
	}

	@Override
	public void doBlockEffect(Block blockHit, EntityLivingBase shootingEntity, EntitySpell spell, MovingObjectPosition mop) {
	}

	@Override
	public void doEntityEffect(EntityLivingBase entityHit, EntityLivingBase shootingEntity, EntitySpell spell) {
		if(entityHit instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)entityHit;
			player.setPositionAndUpdate(player.getBedLocation(player.dimension).posX, player.getBedLocation(player.dimension).posY, player.getBedLocation(player.dimension).posZ);
		}else{
			boolean flag = teleportRandomly(entityHit, 200);
			while(!flag){
				flag = teleportRandomly(entityHit, 200);
			}
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
	public EnumSpellType getSpellType() {
		return EnumSpellType.HEX;
	}

	@Override
	public int getColor() {
		return 0;
	}
	
	protected boolean teleportRandomly(Entity entityIn, double distance) {
		Random rand = new Random();
		double d0 = entityIn.posX + (rand.nextDouble() - 0.5D) * distance;
		double d1 = entityIn.posY + (double) (rand.nextInt((int) distance) - 32);
		double d2 = entityIn.posZ + (rand.nextDouble() - 0.5D) * distance;

		return teleportTo(entityIn, d0, d1, d2);
	}

	protected boolean teleportTo(Entity entityIn, double p_70825_1_, double p_70825_3_, double p_70825_5_) {
		Random rand = new Random();
		double d3 = entityIn.posX;
		double d4 = entityIn.posY;
		double d5 = entityIn.posZ;
		entityIn.posX = p_70825_1_;
		entityIn.posY = p_70825_3_;
		entityIn.posZ = p_70825_5_;
		boolean flag = false;
		int i = MathHelper.floor_double(entityIn.posX);
		int j = MathHelper.floor_double(entityIn.posY);
		int k = MathHelper.floor_double(entityIn.posZ);

		if (entityIn.worldObj.blockExists(i, j, k)) {
			boolean flag1 = false;

			while (!flag1 && j > 0) {
				Block block = entityIn.worldObj.getBlock(i, j - 1, k);

				if (block.getMaterial().blocksMovement()) {
					flag1 = true;
				} else {
					--entityIn.posY;
					--j;
				}
			}

			if (flag1) {
				entityIn.setPosition(entityIn.posX, entityIn.posY, entityIn.posZ);

				if (entityIn.worldObj.getCollidingBoundingBoxes(entityIn, entityIn.boundingBox).isEmpty() && !entityIn.worldObj.isAnyLiquid(entityIn.boundingBox)) {
					flag = true;
				}
			}
		}

		if (!flag) {
			entityIn.setPosition(d3, d4, d5);
			for (int i1 = 0; i1 < 100; i1++) {
				entityIn.worldObj.spawnParticle("largesmoke", entityIn.posX + (rand.nextDouble() - 0.5d), entityIn.posY + rand.nextInt(3) - 1 + (rand.nextDouble() - 0.5f), entityIn.posZ + (rand.nextDouble() - 0.5f), (rand.nextDouble() - 0.5d) * 0.2d, 0.1d, (rand.nextDouble() - 0.5d) * 0.2d);
			}
			return false;
		} else {
			short short1 = 128;

			for (int l = 0; l < short1; ++l) {
				double d6 = (double) l / ((double) short1 - 1.0D);
				float f = (rand.nextFloat() - 0.5F) * 0.2F;
				float f1 = (rand.nextFloat() - 0.5F) * 0.2F;
				float f2 = (rand.nextFloat() - 0.5F) * 0.2F;
				double d7 = d3 + (entityIn.posX - d3) * d6 + (rand.nextDouble() - 0.5D) * (double) entityIn.width * 2.0D;
				double d8 = d4 + (entityIn.posY - d4) * d6 + rand.nextDouble() * (double) entityIn.height;
				double d9 = d5 + (entityIn.posZ - d5) * d6 + (rand.nextDouble() - 0.5D) * (double) entityIn.width * 2.0D;
				entityIn.worldObj.spawnParticle("largesmoke", d3, d4, d5, (double) f, (double) f1, (double) f2);

			}

			return true;
		}
	}

}
