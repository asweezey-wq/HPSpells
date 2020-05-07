package com.apmods.hpspells.spell;

import java.util.Random;

import com.apmods.hpspells.entity.EntitySpell;
import com.apmods.hpspells.lib.SpellLib;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;

public class SpellApparate extends SpellBase implements ISpell {

	@Override
	public int getSpellIndex() {
		return 26;
	}

	@Override
	public String getName() {
		return "Apparate";
	}

	@Override
	public void doBlockEffect(Block blockHit, EntityLivingBase shootingEntity, EntitySpell spell, MovingObjectPosition mop) {
	}

	@Override
	public void doEntityEffect(EntityLivingBase entityHit, EntityLivingBase shootingEntity, EntitySpell spell) {
	}

	@Override
	public boolean doEffectOnCaster(EntityPlayer player, double spellMultiplier) {
		Random rand = new Random();
		World world = player.worldObj;
		for (int i = 0; i < 10; i++) {
			world.spawnParticle("cloud", player.posX - rand.nextFloat() + 0.6f, player.posY + rand.nextFloat() + rand.nextInt(2), player.posZ - rand.nextFloat() + 0.6f, 0, 0.3f, 0);
		}
		float f = 1.0F;
		float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
		float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
		double d = player.prevPosX + (player.posX - player.prevPosX) * f;
		double d1 = (player.prevPosY + (player.posY - player.prevPosY) * f + 1.6200000000000001D) - player.yOffset;
		double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * f;
		Vec3 vec3d = Vec3.createVectorHelper(d, d1, d2);
		float f3 = MathHelper.cos(-f2 * 0.01745329F - 3.141593F);
		float f4 = MathHelper.sin(-f2 * 0.01745329F - 3.141593F);
		float f5 = -MathHelper.cos(-f1 * 0.01745329F);
		float f6 = MathHelper.sin(-f1 * 0.01745329F);
		float f7 = f4 * f5;
		float f8 = f6;
		float f9 = f3 * f5;
		double d3 = 5000D;
		Vec3 vec3d1 = vec3d.createVectorHelper(f7 * d3, f8 * d3, f9 * d3);
		MovingObjectPosition movingobjectposition = world.rayTraceBlocks(vec3d, vec3d1, false);
		if (movingobjectposition != null) {
			if (movingobjectposition.typeOfHit == MovingObjectType.BLOCK) {
				int i = movingobjectposition.blockX;
				int j = movingobjectposition.blockY;
				int k = movingobjectposition.blockZ;
				if (world.isAirBlock(i, j + 1, k)) {
					j++;
				}
				if (!world.isRemote) {
					EnderTeleportEvent event1 = new EnderTeleportEvent(player, i, j, k, 5.0F);
					if (!MinecraftForge.EVENT_BUS.post(event1)) {
						if (player.isRiding()) {
							player.mountEntity((Entity) null);
						}
						player.setPositionAndUpdate(event1.targetX, event1.targetY, event1.targetZ);
						player.fallDistance = 0.0F;
						player.addPotionEffect(new PotionEffect(Potion.blindness.id, 50, 0, true));
					}
				}
			} else if (movingobjectposition.typeOfHit == MovingObjectType.ENTITY) {
				int i = (int) movingobjectposition.entityHit.posX;
				int j = (int) movingobjectposition.entityHit.posY;
				int k = (int) movingobjectposition.entityHit.posZ;
				if (!world.isRemote) {
					EnderTeleportEvent event1 = new EnderTeleportEvent(player, i, j, k, 5.0F);
					if (!MinecraftForge.EVENT_BUS.post(event1)) {
						if (player.isRiding()) {
							player.mountEntity((Entity) null);
						}
						player.setPositionAndUpdate(event1.targetX, event1.targetY, event1.targetZ);
						player.fallDistance = 0.0F;
					}
				}
			}
		}
		world.playSoundAtEntity(player, "random.pop", 4, 1);
		player.addPotionEffect(new PotionEffect(Potion.confusion.id, 60, 3));
		return true;
	}

	@Override
	public boolean isComplicated() {
		return true;
	}

	@Override
	public int getColor() {
		return 0;
	}

	@Override
	public EnumSpellType getSpellType() {
		return null;
	}

}
