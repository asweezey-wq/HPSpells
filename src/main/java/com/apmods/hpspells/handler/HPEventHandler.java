package com.apmods.hpspells.handler;

import java.util.Random;

import com.apmods.hpspells.item.ItemManager;
import com.apmods.hpspells.item.ItemWand;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerOpenContainerEvent;

public class HPEventHandler {
	@SubscribeEvent
	public void livingDropsEvent(LivingDropsEvent evt){
		Random rand = new Random();
		if(evt.entityLiving instanceof EntityCreeper){
			if(evt.source.getSourceOfDamage() != null && evt.source.getSourceOfDamage() instanceof EntityPlayer){
				EntityPlayer player = (EntityPlayer) evt.source.getSourceOfDamage();
				if(rand.nextInt(15 - (evt.lootingLevel * 3)) == 0){
					evt.entityLiving.dropItem(ItemManager.darkHeart, 1);
				}
			}
		}
	}
	
	@SubscribeEvent
	public void canInteractEvent(PlayerOpenContainerEvent event){
		if(event.entityPlayer.getHeldItem() != null && event.entityPlayer.getHeldItem().getItem() instanceof ItemWand){
			if(event.entityPlayer.getHeldItem().hasTagCompound() && event.entityPlayer.getHeldItem().stackTagCompound.getBoolean("cistemAperio")){
				event.setResult(Result.ALLOW);
			}
		}
	}
}
