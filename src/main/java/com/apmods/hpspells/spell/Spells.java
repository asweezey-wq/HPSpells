package com.apmods.hpspells.spell;
//package com.apmods.magicraft.spell;
//
//import java.util.List;
//import java.util.Random;
//
//import com.apmods.magicraft.entity.EntityBird;
//import com.apmods.magicraft.entity.EntityFumos;
//import com.apmods.magicraft.entity.EntityPatronus;
//import com.apmods.magicraft.extendedplayer.MagiSkills;
//import com.apmods.magicraft.handler.LumosHandler;
//import com.apmods.magicraft.lib.SpellLib;
//import com.apmods.magicraft.network.MagiNetwork;
//import com.apmods.magicraft.network.PortkeyGuiPacket;
//
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.item.EntityFireworkRocket;
//import net.minecraft.entity.item.EntityItem;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.entity.player.EntityPlayerMP;
//import net.minecraft.init.Blocks;
//import net.minecraft.init.Items;
//import net.minecraft.item.ItemArmor;
//import net.minecraft.item.ItemArmor.ArmorMaterial;
//import net.minecraft.item.ItemDye;
//import net.minecraft.item.ItemStack;
//import net.minecraft.nbt.NBTTagCompound;
//import net.minecraft.nbt.NBTTagList;
//import net.minecraft.potion.Potion;
//import net.minecraft.potion.PotionEffect;
//import net.minecraft.util.AxisAlignedBB;
//import net.minecraft.util.ChatComponentText;
//import net.minecraft.util.EnumChatFormatting;
//import net.minecraft.util.MathHelper;
//import net.minecraft.util.MovingObjectPosition;
//import net.minecraft.util.MovingObjectPosition.MovingObjectType;
//import net.minecraft.util.Vec3;
//import net.minecraft.world.World;
//import net.minecraftforge.common.MinecraftForge;
//import net.minecraftforge.event.entity.living.EnderTeleportEvent;
//
//public class Spells {
//	private EntityPlayer player;
//	private World world;
//	private ItemStack is;
//	private MagiSkills ext;
//	private Random rand = new Random();
//
//	public Spells(EntityPlayer player) {
//		this.player = player;
//		world = player.worldObj;
//		is = player.getCurrentEquippedItem();
//		ext = MagiSkills.get(player);
//	}
//
//	public void lumos() {
//		is.stackTagCompound.setBoolean("lumos", true);
//	}
//
//	public void nox() {
//		LumosHandler.deleteLight(world, player);
//		is.stackTagCompound.setBoolean("lumos", false);
//	}
//
//	public void expectoPatronum() {
//		EntityPatronus pat = new EntityPatronus(world, player, ext.getPatronusID());
//		pat.setPositionAndUpdate(player.posX, player.posY + 2, player.posZ);
//		world.spawnEntityInWorld(pat);
//	}
//
//	public void episkey() {
//		int skill = getSkillLevel(SpellLib.getSpell("episkey"));
//		if (player.getHealth() > 14 - skill) {
//			player.heal(2 + skill);
//		}
//	}
//
//	public void wingardiumLeviosa() {
//		is.stackTagCompound.setBoolean("wing lev", true);
//	}
//
//	public void multicolorfors() {
//		int[] colors = { 0x000000, 0xffffff, 0xfff000, 0x00ff00, 0x0000ff, 0xff0000, 0xff00ff, 0x00ffff, 0xff8800, 0x5500ff };
//		for (int i = 0; i < 4; i++) {
//			if (player.inventory.armorInventory[i] != null) {
//				ItemArmor item = (ItemArmor) player.inventory.armorInventory[i].getItem();
//				if (item.getArmorMaterial() == ArmorMaterial.CLOTH) {
//					item.func_82813_b(player.inventory.armorInventory[i], colors[rand.nextInt(colors.length)]);
//				}
//			}
//		}
//	}
//
//	public void orchideous() {
//		for (int i = 0; i < Math.max(1, getSkillLevel(SpellLib.getSpell("orchideous"))); i++) {
//			switch (rand.nextInt(2)) {
//			case 0:
//				player.inventory.addItemStackToInventory(new ItemStack(Blocks.red_flower, 1, rand.nextInt(9)));
//				break;
//			case 1:
//				player.inventory.addItemStackToInventory(new ItemStack(Blocks.yellow_flower));
//				break;
//			}
//		}
//
//	}
//
//	public void accio() {
//		int skill = getSkillLevel(SpellLib.getSpell("accio"));
//		int reach = 9 + skill;
//		List<EntityItem> items = world.getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getBoundingBox(player.posX - reach, player.posY, player.posZ - reach, player.posX + reach, player.posY + reach / 2, player.posZ + reach));
//		for (int i = 0; i < items.size(); i++) {
//			player.inventory.addItemStackToInventory(items.get(i).getEntityItem());
//			items.get(i).setDead();
//		}
//	}
//
//	public void apparate() {
//		for (int i = 0; i < 10; i++) {
//			world.spawnParticle("cloud", player.posX - rand.nextFloat() + 0.6f, player.posY + rand.nextFloat() + rand.nextInt(2), player.posZ - rand.nextFloat() + 0.6f, 0, 0.3f, 0);
//		}
//		float f = 1.0F;
//		float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
//		float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
//		double d = player.prevPosX + (player.posX - player.prevPosX) * f;
//		double d1 = (player.prevPosY + (player.posY - player.prevPosY) * f + 1.6200000000000001D) - player.yOffset;
//		double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * f;
//		Vec3 vec3d = Vec3.createVectorHelper(d, d1, d2);
//		float f3 = MathHelper.cos(-f2 * 0.01745329F - 3.141593F);
//		float f4 = MathHelper.sin(-f2 * 0.01745329F - 3.141593F);
//		float f5 = -MathHelper.cos(-f1 * 0.01745329F);
//		float f6 = MathHelper.sin(-f1 * 0.01745329F);
//		float f7 = f4 * f5;
//		float f8 = f6;
//		float f9 = f3 * f5;
//		double d3 = 5000D;
//		Vec3 vec3d1 = vec3d.createVectorHelper(f7 * d3, f8 * d3, f9 * d3);
//		MovingObjectPosition movingobjectposition = world.rayTraceBlocks(vec3d, vec3d1, false);
//		if (movingobjectposition != null) {
//			if (movingobjectposition.typeOfHit == MovingObjectType.BLOCK) {
//				int i = movingobjectposition.blockX;
//				int j = movingobjectposition.blockY;
//				int k = movingobjectposition.blockZ;
//				if(world.isAirBlock(i, j + 1, k)){
//					j++;
//				}
//				if (!world.isRemote) {
//					EnderTeleportEvent event1 = new EnderTeleportEvent(player, i, j, k, 5.0F);
//					if (!MinecraftForge.EVENT_BUS.post(event1)) {
//						if (player.isRiding()) {
//							player.mountEntity((Entity) null);
//						}
//						player.setPositionAndUpdate(event1.targetX, event1.targetY, event1.targetZ);
//						player.fallDistance = 0.0F;
//						player.addPotionEffect(new PotionEffect(Potion.blindness.id, 50, 0, true));
//					}
//				}
//			} else if (movingobjectposition.typeOfHit == MovingObjectType.ENTITY) {
//				int i = (int) movingobjectposition.entityHit.posX;
//				int j = (int) movingobjectposition.entityHit.posY;
//				int k = (int) movingobjectposition.entityHit.posZ;
//				if (!world.isRemote) {
//					EnderTeleportEvent event1 = new EnderTeleportEvent(player, i, j, k, 5.0F);
//					if (!MinecraftForge.EVENT_BUS.post(event1)) {
//						if (player.isRiding()) {
//							player.mountEntity((Entity) null);
//						}
//						player.setPositionAndUpdate(event1.targetX, event1.targetY, event1.targetZ);
//						player.fallDistance = 0.0F;
//					}
//				}
//			}
//		}
//		world.playSoundAtEntity(player, "random.pop", 4, 1);
//		player.addPotionEffect(new PotionEffect(Potion.confusion.id, 60, 3));
//	}
//
//	public void avis() {
//		EntityBird bat = new EntityBird(world);
//		bat.setPositionAndUpdate(player.posX, player.posY, player.posZ);
//		world.spawnEntityInWorld(bat);
//	}
//
//	public void portus() {
//		MagiNetwork.net.sendTo(new PortkeyGuiPacket.GuiMessage(), (EntityPlayerMP) player);
//	}
//
//	public void protego() {
//		if (ext.getCastProtegoCounter() <= 0) {
//			if (!ext.hasProtego()) {
//				ext.resetCastProtegoCounter();
//				ext.enableProtego();
//				player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.AQUA + "Protego!"));
//			}
//		}
//	}
//
//	public void fumos() {
//		EntityFumos f = new EntityFumos(world);
//		f.setPositionAndUpdate(player.posX, player.posY + 1, player.posZ);
//		world.spawnEntityInWorld(f);
//	}
//
//	public void refulgens() {
//		ItemStack is1 = new ItemStack(Items.fireworks);
//
//		ItemStack is2 = new ItemStack(Items.firework_charge);
//		NBTTagCompound nbt1 = new NBTTagCompound();
//		NBTTagCompound nbt2 = new NBTTagCompound();
//		nbt2.setBoolean("Flicker", rand.nextBoolean());
//		nbt2.setBoolean("Trail", rand.nextBoolean());
//		nbt2.setByte("Type", (byte) rand.nextInt(5));
//		int[] aint1 = new int[rand.nextInt(3) + 1];
//		for (int i = 0; i < aint1.length; i++) {
//			aint1[i] = ItemDye.field_150922_c[rand.nextInt(ItemDye.field_150922_c.length)];
//		}
//		int[] aint = new int[rand.nextInt(2) + 1];
//		for (int i = 0; i < aint.length; i++) {
//			aint[i] = ItemDye.field_150922_c[rand.nextInt(ItemDye.field_150922_c.length)];
//		}
//		nbt2.setIntArray("Colors", aint1);
//		nbt2.setIntArray("FadeColors", aint);
//		nbt1.setTag("Explosion", nbt2);
//		is2.setTagCompound(nbt1);
//
//		NBTTagCompound nbt3 = new NBTTagCompound();
//		nbt3.setByte("Flight", (byte) 1);
//		NBTTagList nbtlist = new NBTTagList();
//		nbtlist.appendTag(is2.getTagCompound().getCompoundTag("Explosion"));
//		nbt3.setTag("Explosions", nbtlist);
//		NBTTagCompound nbt4 = new NBTTagCompound();
//		nbt4.setTag("Fireworks", nbt3);
//		is1.setTagCompound(nbt4);
//
//		NBTTagCompound nbttagcompound1 = is1.getTagCompound().getTagList("Explosions", 10).getCompoundTagAt(0);
//		byte b0 = nbttagcompound1.getByte("Type");
//		boolean flag3 = nbttagcompound1.getBoolean("Trail");
//		boolean flag2 = nbttagcompound1.getBoolean("Flicker");
//		int[] aint0 = nbttagcompound1.getIntArray("Colors");
//		int[] aint01 = nbttagcompound1.getIntArray("FadeColors");
//		System.out.println(aint0.length + " " + aint01.length);
//		if (!world.isRemote) {
//			world.spawnEntityInWorld(new EntityFireworkRocket(world, player.posX, player.posY, player.posZ, is1));
//		}
//	}
//	
//	public void depulso(){
//		for(int i = 0; i < player.inventory.getSizeInventory(); i++){
//			ItemStack stackInSlot = player.inventory.getStackInSlot(i);
//			if(stackInSlot != null){
//				player.dropItem(stackInSlot.getItem(), -1);
//			}
//		}
//	}
//	
//	public void ascendio(){
//		player.addVelocity(0, 1.2, 0);
//	}
//
//	private int getSkillLevel(int spellOf) {
//		if (ext != null) {
//			return ext.getCurrentSkillLevel(spellOf);
//		} else {
//			return 2;
//		}
//	}
//}
