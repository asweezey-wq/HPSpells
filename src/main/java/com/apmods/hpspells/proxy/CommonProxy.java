package com.apmods.hpspells.proxy;

import java.util.HashMap;
import java.util.Map;

import com.apmods.hpspells.handler.LumosHandler;
import com.apmods.hpspells.handler.SkillsHandler;
import com.apmods.hpspells.network.SyncSkillsPacket.SkillsMessage;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {
	private static final Map<String, NBTTagCompound> extendedEntityData = new HashMap<String, NBTTagCompound>();
	
	public LiveSpeechRecognizer lsp;
	
	public void init(){
		this.registerRenderers();
		FMLCommonHandler.instance().bus().register(new LumosHandler());
		MinecraftForge.EVENT_BUS.register(new SkillsHandler());
	}
	public void registerRenderers(){
		
	}
	/**
	* Adds an entity's custom data to the map for temporary storage
	* @param compound An NBT Tag Compound that stores the IExtendedEntityProperties data only
	*/
	public static void storeEntityData(String name, NBTTagCompound compound)
	{
		extendedEntityData.put(name, compound);
	}

	/**
	* Removes the compound from the map and returns the NBT tag stored for name or null if none exists
	*/
	public static NBTTagCompound getEntityData(String name)
	{
		return extendedEntityData.remove(name);
	}
	public EntityPlayer getPlayerEntity(MessageContext ctx) {
		return ctx.getServerHandler().playerEntity;
	}
	public IMessage onMessage(SkillsMessage message, MessageContext ctx)
    {
		return null;
    }
	public void imperio(EntityLivingBase entity, EntityPlayer player){
	}
	public void portus(MessageContext ctx){
		
	}
	public void portus(int x, int y, int z) {
	}
	
	public ModelBiped getArmorModel(int i){
		return null;
	}
}
