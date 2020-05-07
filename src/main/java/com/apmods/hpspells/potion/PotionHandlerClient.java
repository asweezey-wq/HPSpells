package com.apmods.hpspells.potion;

import org.lwjgl.opengl.GL11;

import com.apmods.hpspells.extendedplayer.SpellSkills;
import com.apmods.hpspells.item.ItemInvisibilityCloak;
import com.apmods.hpspells.main.HPSpells;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class PotionHandlerClient {
	@SubscribeEvent
	public void mouseEvent(MouseEvent event) {
		SpellSkills ext = SpellSkills.get(Minecraft.getMinecraft().thePlayer);
		float f = Minecraft.getMinecraft().gameSettings.mouseSensitivity;
		if (f != -1f / 3f && f != ext.getMouseSensitivity()) {
			ext.setMouseSensitivity(f);
		}
		if (Minecraft.getMinecraft().thePlayer.getActivePotionEffect(HPSpells.petrified) != null) {
			if (Minecraft.getMinecraft().gameSettings.mouseSensitivity != -1f / 3f) {
				ext.setMouseSensitivity(Minecraft.getMinecraft().gameSettings.mouseSensitivity);
				Minecraft.getMinecraft().gameSettings.mouseSensitivity = -1f / 3f;
			}
		} else if (Minecraft.getMinecraft().gameSettings.mouseSensitivity != SpellSkills.get(Minecraft.getMinecraft().thePlayer).getMouseSensitivity()) {
			Minecraft.getMinecraft().gameSettings.mouseSensitivity = SpellSkills.get(Minecraft.getMinecraft().thePlayer).getMouseSensitivity();
		}
	}

	@SubscribeEvent
	public void renderPlayerEvent(RenderPlayerEvent.Pre event) {
		if (event.entityPlayer.getCurrentArmor(2) != null && event.entityPlayer.getCurrentArmor(2).getItem() instanceof ItemInvisibilityCloak) {
			event.setCanceled(true);
			return;
		}
		SpellSkills s = SpellSkills.get(event.entityPlayer);
		if (s.hasProtego()) {
			s.renderProtego.doRender(event.entityPlayer, event.entityPlayer.posX, event.entityPlayer.posY, event.entityPlayer.posZ, 0, event.partialRenderTick);
		}
	}
	
	@SubscribeEvent
	public void renderLivingEvent(RenderLivingEvent.Pre event){
		if(event.entity.getActivePotionEffect(HPSpells.flipped) != null){
			GL11.glPushMatrix();
			GL11.glRotatef(180, 0, 0, 1);
		}
	}
	
	@SubscribeEvent
	public void renderLivingEvent(RenderLivingEvent.Post event){
		if(event.entity.getActivePotionEffect(HPSpells.flipped) != null){
			GL11.glPopMatrix();
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void renderHandEvent(RenderHandEvent event) {
		if (Minecraft.getMinecraft().thePlayer.getCurrentArmor(2) != null && Minecraft.getMinecraft().thePlayer.getCurrentArmor(2).getItem() instanceof ItemInvisibilityCloak) {
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(1, 1);
			GL11.glPopMatrix();
		}
	}

	@SubscribeEvent
	public void deathEvent(LivingDeathEvent event) {
		if (event.entityLiving instanceof EntityPlayer) {
			if (Minecraft.getMinecraft().gameSettings.mouseSensitivity == -1f / 3f) {
				Minecraft.getMinecraft().gameSettings.mouseSensitivity = SpellSkills.get(Minecraft.getMinecraft().thePlayer).getMouseSensitivity();
			}
		}
	}
}
