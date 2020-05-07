package com.apmods.hpspells.spell;

import java.util.Random;

import com.apmods.hpspells.entity.EntitySpell;
import com.apmods.hpspells.lib.SpellLib;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class SpellRefulgens extends SpellBase implements ISpell{

	@Override
	public int getSpellIndex() {
		return 36;
	}

	@Override
	public String getName() {
		return "Refulgens";
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
		ItemStack is1 = new ItemStack(Items.fireworks);

		ItemStack is2 = new ItemStack(Items.firework_charge);
		NBTTagCompound nbt1 = new NBTTagCompound();
		NBTTagCompound nbt2 = new NBTTagCompound();
		nbt2.setBoolean("Flicker", rand.nextBoolean());
		nbt2.setBoolean("Trail", rand.nextBoolean());
		nbt2.setByte("Type", (byte) rand.nextInt(5));
		int[] aint1 = new int[rand.nextInt(3) + 1];
		for (int i = 0; i < aint1.length; i++) {
			aint1[i] = ItemDye.field_150922_c[rand.nextInt(ItemDye.field_150922_c.length)];
		}
		int[] aint = new int[rand.nextInt(2) + 1];
		for (int i = 0; i < aint.length; i++) {
			aint[i] = ItemDye.field_150922_c[rand.nextInt(ItemDye.field_150922_c.length)];
		}
		nbt2.setIntArray("Colors", aint1);
		nbt2.setIntArray("FadeColors", aint);
		nbt1.setTag("Explosion", nbt2);
		is2.setTagCompound(nbt1);

		NBTTagCompound nbt3 = new NBTTagCompound();
		nbt3.setByte("Flight", (byte) 1);
		NBTTagList nbtlist = new NBTTagList();
		nbtlist.appendTag(is2.getTagCompound().getCompoundTag("Explosion"));
		nbt3.setTag("Explosions", nbtlist);
		NBTTagCompound nbt4 = new NBTTagCompound();
		nbt4.setTag("Fireworks", nbt3);
		is1.setTagCompound(nbt4);

		NBTTagCompound nbttagcompound1 = is1.getTagCompound().getTagList("Explosions", 10).getCompoundTagAt(0);
		byte b0 = nbttagcompound1.getByte("Type");
		boolean flag3 = nbttagcompound1.getBoolean("Trail");
		boolean flag2 = nbttagcompound1.getBoolean("Flicker");
		int[] aint0 = nbttagcompound1.getIntArray("Colors");
		int[] aint01 = nbttagcompound1.getIntArray("FadeColors");
		if (!world.isRemote) {
			world.spawnEntityInWorld(new EntityFireworkRocket(world, player.posX, player.posY, player.posZ, is1));
		}
		return true;
	}

	@Override
	public boolean isComplicated() {
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
