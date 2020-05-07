package com.apmods.hpspells.gui;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.apmods.hpspells.extendedplayer.SpellSkills;
import com.apmods.hpspells.item.ItemWand;
import com.apmods.hpspells.main.HPSpells;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

public class GuiSpellSpeech extends Gui{
	
	public ResourceLocation texture = new ResourceLocation(HPSpells.MODID + ":textures/gui/microphone.png");
	
	public int lastFlux = 0;
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void renderExperienceBar(RenderGameOverlayEvent.Post event){
		Minecraft mc = Minecraft.getMinecraft();
		if (event.type != ElementType.EXPERIENCE) {
			return;
		}
		SpellSkills ext = SpellSkills.get(mc.thePlayer);
		if(ext == null){
			return;
		}
		boolean isSpeaking = ext.isSpeaking();
		GL11.glPushMatrix();
		GL11.glColor4f(1, 1, 1, isSpeaking ? 1 : 0);
		ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
	    ResourceLocation armoredCreeperTextures = new ResourceLocation("textures/entity/creeper/creeper_armor.png");
		mc.getTextureManager().bindTexture(texture);
		drawTexturedModalRect(16, sr.getScaledHeight() - 48, 0, 0, 32, 32);
		Random rand = new Random();
		if(mc.theWorld.getWorldTime() % 2 == 0){
			lastFlux = rand.nextInt(4);
		}
		drawTexturedModalRect(28, sr.getScaledHeight() - 48 + 7 -lastFlux, 32, 0, 8, lastFlux + 12);
		if(ext.hasProtego() && mc.gameSettings.thirdPersonView == 0){
//			GL11.glPushMatrix();
			mc.getTextureManager().bindTexture(armoredCreeperTextures);
			float f1 = (float)mc.thePlayer.ticksExisted + event.partialTicks;
            float f2 = f1 * 0.01F;
            float f3 = f1 * 0.01F;
            GL11.glEnable(GL11.GL_BLEND);
            float f4 = 0.5F;
            GL11.glColor4f(f4, f4, f4, 1.0F);
            GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
			drawTexturedModalRect(0, 0, 0, 0, sr.getScaledWidth(), sr.getScaledHeight());
			GL11.glDisable(GL11.GL_BLEND);
//            GL11.glPopMatrix();

		}
		GL11.glColor4f(1, 1, 1, 1);
		GL11.glPopMatrix();
		
		if(mc.inGameHasFocus){
			if(mc.thePlayer.getHeldItem() != null){
				Item item = mc.thePlayer.getHeldItem().getItem();
				if(item instanceof ItemWand && mc.thePlayer.getHeldItem().hasTagCompound()){
					drawString(mc.fontRenderer, mc.thePlayer.getHeldItem().getTagCompound().getString("last spell"), 8, sr.getScaledHeight()-12, 0xffffff);
				}
			}
			
		}
		
		
	}
}
