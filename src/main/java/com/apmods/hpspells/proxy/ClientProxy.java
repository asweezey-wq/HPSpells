package com.apmods.hpspells.proxy;

import java.io.IOException;

import com.apmods.hpspells.client.ModelCloak;
import com.apmods.hpspells.client.ModelPatronusBat;
import com.apmods.hpspells.client.ModelPatronusHorse;
import com.apmods.hpspells.client.ModelPatronusIronGolem;
import com.apmods.hpspells.client.ModelPatronusMagmaCube;
import com.apmods.hpspells.client.ModelPatronusOcelot;
import com.apmods.hpspells.client.ModelPatronusSheep;
import com.apmods.hpspells.client.ModelPatronusSkeleton;
import com.apmods.hpspells.client.ModelPatronusSquid;
import com.apmods.hpspells.client.ModelPatronusWither;
import com.apmods.hpspells.client.ModelPatronusWolf;
import com.apmods.hpspells.client.RenderBird;
import com.apmods.hpspells.client.RenderBroomstick;
import com.apmods.hpspells.client.RenderDarkWard;
import com.apmods.hpspells.client.RenderDeathEater;
import com.apmods.hpspells.client.RenderDementor;
import com.apmods.hpspells.client.RenderFumos;
import com.apmods.hpspells.client.RenderPatronus;
import com.apmods.hpspells.client.RenderSpell;
import com.apmods.hpspells.client.RenderWandmaker;
import com.apmods.hpspells.entity.EntityBird;
import com.apmods.hpspells.entity.EntityBroomstick;
import com.apmods.hpspells.entity.EntityDarkArtsWard;
import com.apmods.hpspells.entity.EntityDeathEater;
import com.apmods.hpspells.entity.EntityDementor;
import com.apmods.hpspells.entity.EntityFumos;
import com.apmods.hpspells.entity.EntityPatronus;
import com.apmods.hpspells.entity.EntitySpell;
import com.apmods.hpspells.entity.EntityWandmaker;
import com.apmods.hpspells.extendedplayer.SpellSkills;
import com.apmods.hpspells.gui.GuiImperio;
import com.apmods.hpspells.gui.GuiPortkey;
import com.apmods.hpspells.gui.GuiSpellSpeech;
import com.apmods.hpspells.item.ItemManager;
import com.apmods.hpspells.key.KeyHandlerWand;
import com.apmods.hpspells.key.SpellKeys;
import com.apmods.hpspells.main.HPSpells;
import com.apmods.hpspells.network.SyncSkillsPacket.SkillsMessage;
import com.apmods.hpspells.potion.PotionHandlerClient;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBlaze;
import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.model.ModelEnderman;
import net.minecraft.client.model.ModelGhast;
import net.minecraft.client.model.ModelPig;
import net.minecraft.client.model.ModelSilverfish;
import net.minecraft.client.model.ModelSnowMan;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.model.ModelVillager;
import net.minecraft.client.model.ModelWitch;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {

	public static ModelBase[] models = { new ModelChicken(), new ModelPatronusSheep(), new ModelPig(), new ModelCow(), new ModelPatronusSkeleton(), new ModelEnderman(), new ModelSpider(), new ModelCreeper(), new ModelPatronusBat(), new ModelPatronusIronGolem(), new ModelGhast(), new ModelBlaze(), new ModelPatronusWolf(), new ModelPatronusOcelot(), new ModelSilverfish(), new ModelPatronusMagmaCube(), new ModelVillager(0), new ModelWitch(0), new ModelSnowMan(), new ModelPatronusSquid(),
			new ModelPatronusWither(), new ModelPatronusHorse() };
	
	private static ModelCloak modelCloakChest = new ModelCloak(1.2f);
	private static ModelCloak modelCloak = new ModelCloak(0.5f);

	@Override
	public void init() {
		try {
			lsp = new LiveSpeechRecognizer(HPSpells.speechConfig);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ClientRegistry.registerKeyBinding(SpellKeys.spellkey);
		ClientRegistry.registerKeyBinding(SpellKeys.castKey);
		ClientRegistry.registerKeyBinding(SpellKeys.spellTreeKey);
		ClientRegistry.registerKeyBinding(SpellKeys.spellSpeechKey);
		FMLCommonHandler.instance().bus().register(new KeyHandlerWand());
		MinecraftForge.EVENT_BUS.register(new KeyHandlerWand());
		MinecraftForge.EVENT_BUS.register(new GuiSpellSpeech());
		FMLCommonHandler.instance().bus().register(new PotionHandlerClient());
		MinecraftForge.EVENT_BUS.register(new PotionHandlerClient());
		this.registerRenderers();
	}

	@Override
	public void registerRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(EntitySpell.class, new RenderSpell());
		RenderingRegistry.registerEntityRenderingHandler(EntityPatronus.class, new RenderPatronus(0));
		RenderingRegistry.registerEntityRenderingHandler(EntityDementor.class, new RenderDementor());
		RenderingRegistry.registerEntityRenderingHandler(EntityBird.class, new RenderBird());
		RenderingRegistry.registerEntityRenderingHandler(EntityDeathEater.class, new RenderDeathEater(new ModelBiped(), 0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityFumos.class, new RenderFumos());
		RenderingRegistry.registerEntityRenderingHandler(EntityDarkArtsWard.class, new RenderDarkWard(ItemManager.darkWard));
		RenderingRegistry.registerEntityRenderingHandler(EntityBroomstick.class, new RenderBroomstick());
		RenderingRegistry.registerEntityRenderingHandler(EntityWandmaker.class, new RenderWandmaker());
	}

	@Override
	public IMessage onMessage(SkillsMessage message, MessageContext ctx) {
		if (ctx.side.isClient()) {
			EntityPlayer player = Minecraft.getMinecraft().thePlayer;

			if (player != null) {
				int playerX = (int) player.posX;
				int playerY = (int) player.posY;
				int playerZ = (int) player.posZ;
				World world = player.worldObj;
				SpellSkills ext = SpellSkills.get(player);
				ext.loadNBTData(message.data);
			}

		}
		return null;
	}

	@Override
	public void imperio(EntityLivingBase entity, EntityPlayer player) {
		Minecraft mc = Minecraft.getMinecraft();
		mc.displayGuiScreen(new GuiImperio((EntityPlayer) entity));
	}

	@Override
	public void portus(MessageContext ctx) {
		Minecraft mc = Minecraft.getMinecraft();
		mc.displayGuiScreen(new GuiPortkey());

	}
	
	@Override
	public void portus(int x, int y, int z) {
		Minecraft mc = Minecraft.getMinecraft();
		GuiPortkey gui = new GuiPortkey();
		mc.displayGuiScreen(gui);
		gui.done.enabled = false;
		gui.textX.setText("" + x);
		gui.textY.setText("" + y);
		gui.textZ.setText("" + z);
	}
	
	@Override
	public ModelBiped getArmorModel(int i) {
		return new ModelCloak(1f);
	}
}
