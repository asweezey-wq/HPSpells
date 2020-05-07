package com.apmods.hpspells.potion;

import org.lwjgl.opengl.GL11;

import com.apmods.hpspells.extendedplayer.SpellSkills;
import com.apmods.hpspells.item.ItemInvisibilityCloak;
import com.apmods.hpspells.main.HPSpells;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.RenderWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;

public class PotionHandler {
	
	@SubscribeEvent
	public void targetEvent(LivingSetAttackTargetEvent event){
		if(event.target != null && event.target instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) event.target;
			if(player.getCurrentArmor(2) != null && player.getCurrentArmor(2).getItem() instanceof ItemInvisibilityCloak){
				if(event.entityLiving instanceof EntityCreature){
					((EntityCreature)event.entityLiving).setAttackTarget(null);
				}
			}
		}
	}
	
	@SubscribeEvent
	public void attackEvent(LivingAttackEvent event){
		if(event.entityLiving != null && event.entityLiving instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			if(player.getCurrentArmor(2) != null && player.getCurrentArmor(2).getItem() instanceof ItemInvisibilityCloak){
				if(event.source.getSourceOfDamage() !=  null){
					if(event.source.getSourceOfDamage() instanceof EntityCreature){
						((EntityCreature)event.source.getSourceOfDamage()).setAttackTarget(null);
						event.setCanceled(true);
					}
				}
			}
		}
	}
}
