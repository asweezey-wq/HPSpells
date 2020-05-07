package com.apmods.hpspells.network;

import java.util.List;

import com.apmods.hpspells.entity.EntityDarkArtsWard;
import com.apmods.hpspells.entity.EntitySpell;
import com.apmods.hpspells.extendedplayer.SpellSkills;
import com.apmods.hpspells.item.ItemManager;
import com.apmods.hpspells.item.ItemWand;
import com.apmods.hpspells.lib.SpellLib;
import com.apmods.hpspells.main.HPSpells;
import com.apmods.hpspells.network.CastSpellPacket.SpellMessage;
import com.apmods.hpspells.spell.EnumSpellType;
import com.apmods.hpspells.spell.ISpell;
import com.apmods.hpspells.wand.EnumWandCore;
import com.apmods.hpspells.wand.EnumWandMaterial;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import scala.util.Random;

public class CastSpellPacket implements IMessageHandler<SpellMessage, IMessage> {
	private int[] fizzle = { 1, 2, 2, 3, 3, 4, 5 };
	private int[] fizzlepractice = { 2, 2, 2, 1, 1, 1, 1 };

	@Override
	public IMessage onMessage(SpellMessage message, MessageContext ctx) {
		if (ctx.side.isServer()) {
			EntityPlayerMP player = ctx.getServerHandler().playerEntity;

			if (player != null) {
				int playerX = (int) player.posX;
				int playerY = (int) player.posY;
				int playerZ = (int) player.posZ;
				World world = player.worldObj;
				SpellSkills ext = SpellSkills.get(player);
				Random rand = new Random();

				if (!world.isRemote) {
					ItemStack current = player.getCurrentEquippedItem();
					// Make sure the current Item is a wand
					if (current != null && current.getItem() instanceof ItemWand) {
						ISpell s = message.spell;
						if (message.usedLastSpell == 0) {
							if(ext.getKnowledgeLevel(message.spell) <= 0){
								player.addChatComponentMessage(new ChatComponentText("You don't know " + message.spell.getName() + " yet!"));
								return null;
							}
							current.stackTagCompound.setString("last spell", message.spell.getName());
							return null;
						}
						// Get last spell cast
						ISpell spell = SpellLib.getSpell(current.getTagCompound().getString("last spell"));
						// Get the counter since the spell was last cast
						int counter = current.stackTagCompound.getInteger("last spell counter");
						int maxCounter = ((ItemWand) current.getItem()).getCooldownTime();
						if (counter == maxCounter && spell.getSpellIndex() > 0) {
							castSpell(player, spell, ext.getKnowledgeLevel(spell));
							current.getTagCompound().setInteger("last spell counter", 0);
						}
					}
				}

			}
		}
		return null;
	}

	public void enactSpell(EntityPlayer player, World world, ISpell spell) {
		if (spell.getSpellIndex() > 0) {
			Random rand = new Random();
			SpellSkills ext = SpellSkills.get(player);
			ItemStack is = player.getCurrentEquippedItem();
			is.stackTagCompound.setString("last spell", spell.getName());
			if(ext.getKnowledgeLevel(spell) <= 0){
				player.addChatComponentMessage(new ChatComponentText("You don't know this spell yet!"));
				return;
			}
			is.stackTagCompound.setInteger("last spell counter", 0);
			player.swingItem();
			ext.gainSkill(spell);
			double spellMultiplier = getSpellMultiplier(is, spell);
			if(!spell.doEffectOnCaster(player, spellMultiplier)){//If a projectile should be shot
				EntitySpell spelle = new EntitySpell(world, player, spell, spellMultiplier);
				spelle.setShootingEntity(player);
				if (spell.getSpellType() == EnumSpellType.DARK) {// Is Dark Arts
					if (HPSpells.isDarkMagicAllowed) {
						AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(player.posX - 8, player.posY - 5, player.posZ - 8, player.posX + 8, player.posY + 5, player.posZ + 8);
						List list = world.getEntitiesWithinAABB(EntityDarkArtsWard.class, aabb);
						if (list.isEmpty()) {// If there are no Dark Arts Ward
							// Make sure they have a Dark Heart
							if (player.inventory.hasItem(ItemManager.darkHeart) || player.capabilities.isCreativeMode) {
								player.addPotionEffect(new PotionEffect(Potion.weakness.id, 200, 1));
								if (rand.nextInt(10) == 0) {
									player.addPotionEffect(new PotionEffect(Potion.poison.id, 40, 3));
								}
								playSoundForSpell(spell, world, player);
								world.spawnEntityInWorld(spelle);
								if (!player.capabilities.isCreativeMode) {
									player.inventory.consumeInventoryItem(ItemManager.darkHeart);
								}
							} else {// If they have no Dark Heart
								player.addChatComponentMessage(new ChatComponentText("You need a Dark Heart to perform this curse!"));
							}
						} else {// If they are too close to a Dark Arts Ward
							player.addChatComponentMessage(new ChatComponentText("You are too near to a Dark Arts Ward!"));
						}
					} else {// If the config turned Dark Arts off
						player.addChatComponentMessage(new ChatComponentText("Dark Arts aren't allowed!"));
					}
				} else {// If the spell isn't Dark then just cast it
					playSoundForSpell(spell, world, player);
					world.spawnEntityInWorld(spelle);
				}

				ext.gainSkill(spell);
			}
		}
	}
	
	public void playSoundForSpell(ISpell spell, World world, EntityPlayer player){
		String s = "";
		switch(spell.getSpellType()){
		case CHARM:
			s = "fireworks.launch";
			break;
		case DADA:
			s = "fireworks.largeBlast";
			break;
		case DARK:
			s = "mob.enderdragon.growl";
			break;
		case HEX:
			s = "fireworks.blast";
			break;
		case TRANSFIGURATION:
			s = "fireworks.twinkle";
			break;
		default:
			break;
		}
		world.playSoundAtEntity(player, s, 1f, 1f);
	}

	public void castSpell(EntityPlayer player, ISpell spell, int knowledge) {
		Random rand = new Random();
		boolean doesntFizzle = false;
		int chance = 0;
		if(spell.isComplicated()){
			if(knowledge >= 3){
				doesntFizzle = true;
			}
			chance = 6 - ((knowledge - 1) * 3);
		}
		else{
			if(knowledge >= 2){
				doesntFizzle = true;
			}
			chance = 4;
			
		}
		if(player.getHeldItem() != null && player.getHeldItem().hasTagCompound()){
			ItemStack is = player.getHeldItem();
			chance *= ItemWand.wandMaterial(is).getFizzleRateMultiplier();
			chance = Math.min(chance, 9);
		}
		int i = rand.nextInt(10);
		if(doesntFizzle || i >= chance || spell.getName() == "Nox"){
			player.swingItem();
			enactSpell(player, player.worldObj, spell);
		}
		else{
			player.worldObj.playSoundAtEntity(player, "mob.silverfish.hit", 1f, 1f);
			player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED + "Fizzle"));
		}
	}
	
	public double getSpellMultiplier(ItemStack stack, ISpell spell){
		EnumWandMaterial wood = EnumWandMaterial.valueOf(stack.getTagCompound().getString("wood"));
		EnumWandCore core = EnumWandCore.valueOf(stack.getTagCompound().getString("wand core"));
		return wood.getMultiplierForSpellType(spell.getSpellType()) * core.getMultiplierForSpellType(spell.getSpellType());
	}

	public static class SpellMessage implements IMessage {
		private ISpell spell; // The index of the spell being used
		private byte usedLastSpell; // 0 if they typed in the spell, 1 if they
									// pressed "z"

		public SpellMessage() {
		}

		public SpellMessage(byte lastpsell, ISpell spell) {
			this.spell = spell;
			this.usedLastSpell = lastpsell;
		}

		@Override
		public void fromBytes(ByteBuf buf) {
			this.spell = SpellLib.getSpell(ByteBufUtils.readUTF8String(buf));
			this.usedLastSpell = buf.readByte();
		}

		@Override
		public void toBytes(ByteBuf buf) {
			ByteBufUtils.writeUTF8String(buf, spell.getName());
			buf.writeByte(usedLastSpell);
		}
	}
}
