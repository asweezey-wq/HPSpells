package com.apmods.hpspells.handler;

import com.apmods.hpspells.entity.EntityBroomstick;
import com.apmods.hpspells.extendedplayer.SpellSkills;
import com.apmods.hpspells.item.ItemManager;
import com.apmods.hpspells.main.HPSpells;
import com.apmods.hpspells.network.BroomstickPacket;
import com.apmods.hpspells.network.HPNetwork;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class SkillsHandler {
	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event) {
		if (event.entity instanceof EntityPlayer && SpellSkills.get((EntityPlayer) event.entity) == null) {
			SpellSkills.register((EntityPlayer) event.entity);
			EntityPlayer player = (EntityPlayer) event.entity;
		}
	}

	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event) {
		if (event.entity instanceof EntityPlayer && !event.entity.worldObj.isRemote) {
			SpellSkills.loadProxyData((EntityPlayer) event.entity);
			SpellSkills.get((EntityPlayer) event.entity).setSpeaking(false);
		}
	}
	
	private static final String NBT_KEY = "hpspells.firstjoin";

    @SubscribeEvent
    public void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {

        NBTTagCompound data = event.player.getEntityData();
        NBTTagCompound persistent;
        if (!data.hasKey(EntityPlayer.PERSISTED_NBT_TAG)) {
            data.setTag(EntityPlayer.PERSISTED_NBT_TAG, (persistent = new NBTTagCompound()));
        } else {
            persistent = data.getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
        }

        if (!persistent.hasKey(NBT_KEY)) {
            persistent.setBoolean(NBT_KEY, true);
            event.player.inventory.addItemStackToInventory(new ItemStack(ItemManager.wand));
        }
    }

	@SubscribeEvent
	public void onLivingDeath(LivingDeathEvent event) {
		// we only want to save data for players (most likely, anyway)
		if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer) {
			SpellSkills.saveProxyData((EntityPlayer) event.entity);
		}
	}

	@SubscribeEvent
	public void onTick(TickEvent.PlayerTickEvent evt) {
		if (evt.side.isServer()) {
			SpellSkills ext = SpellSkills.get(evt.player);
			if (ext.getCastProtegoCounter() != 0) {
				ext.addCastProtegoCounter();
			}
			if (ext.hasProtego()) {
				ext.addProtegoCounter();

				if (ext.getProtegoCounter() <= 0) {
					ext.disableProtego();
					evt.player.worldObj.playSoundAtEntity(evt.player, HPSpells.MODID + ":protego.hitReverse", 1f, 3f);
				}

			}
			
		}
		else{
			if(evt.player.ridingEntity != null && evt.player.ridingEntity instanceof EntityBroomstick){
				EntityBroomstick broomstick = (EntityBroomstick) evt.player.ridingEntity;
				if(!broomstick.isYLocked() && Minecraft.getMinecraft().gameSettings.keyBindJump.getIsKeyPressed()){
					HPNetwork.net.sendToServer(new BroomstickPacket.BroomstickMessage(true));
				}else if(broomstick.isYLocked() && !Minecraft.getMinecraft().gameSettings.keyBindJump.getIsKeyPressed()){
					HPNetwork.net.sendToServer(new BroomstickPacket.BroomstickMessage(false));
				}
			}
		}
	}
}
