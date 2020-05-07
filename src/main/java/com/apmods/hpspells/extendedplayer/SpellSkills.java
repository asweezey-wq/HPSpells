package com.apmods.hpspells.extendedplayer;

import java.util.Random;

import com.apmods.hpspells.client.RenderProtego;
import com.apmods.hpspells.lib.SpellLib;
import com.apmods.hpspells.network.HPNetwork;
import com.apmods.hpspells.network.SyncSkillsPacket;
import com.apmods.hpspells.proxy.CommonProxy;
import com.apmods.hpspells.spell.ISpell;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class SpellSkills implements IExtendedEntityProperties {
	/*
	 * Here I create a constant EXT_PROP_NAME for this class of properties. You
	 * need a unique name for every instance of IExtendedEntityProperties you
	 * make, and doing it at the top of each class as a constant makes it very
	 * easy to organize and avoid typos. It's easiest to keep the same constant
	 * name in every class, as it will be distinguished by the class name:
	 * ExtendedPlayer.EXT_PROP_NAME vs. ExtendedEntity.EXT_PROP_NAME
	 * 
	 * Note that a single entity can have multiple extended properties, so each
	 * property should have a unique name. Try to come up with something more
	 * unique than the tutorial example.
	 */
	public final static String MAGICSKILLS = "Magic Skill";

	// I always include the entity to which the properties belong for easy
	// access
	// It's final because we won't be changing which player it is
	private final EntityPlayer player;

	private int patronusID;

	private int[] knowledge = new int[SpellLib.names.length];
	private int overallSkill;
	private int[] skill = new int[SpellLib.names.length];
	private int[] skillLevel = new int[SpellLib.names.length];
	private boolean protego;
	private boolean isSpeaking;
	private int protegoCounter, castProtegoCounter;
	
	private int portkeyX, portkeyY, portkeyZ;
	
	private int heldEntityID;
	
	private float mouseSensitivity;
	
	private boolean isAscending;
	
	public RenderProtego renderProtego;

	/*
	 * The default constructor takes no arguments, but I put in the Entity so I
	 * can initialize the above variable 'player'
	 * 
	 * Also, it's best to initialize any other variables you may have added,
	 * just like in any constructor.
	 */
	public SpellSkills(EntityPlayer player) {
		this.player = player;
		this.protego = false;
		this.isSpeaking = false;
		this.protegoCounter = 280;
		this.castProtegoCounter = 400;
		this.portkeyX = 0;
		this.portkeyY = 0;
		this.portkeyZ = 0;
		this.isAscending = false;
		this.heldEntityID = 0;
		this.renderProtego = new RenderProtego();
		this.renderProtego.setRenderManager(RenderManager.instance);
		Random rand = new Random();
		this.patronusID = rand.nextInt(20);
		for (int i = 0; i < knowledge.length; i++) {
			knowledge[i] = 0;
		}
		for (int i = 0; i < skill.length; i++) {
			skill[i] = 0;
		}
		for (int i = 0; i < skillLevel.length; i++) {
			skillLevel[i] = 0;
		}
		this.overallSkill = 0;
		knowledge[SpellLib.getSpell("Flipendo").getSpellIndex()] = 1;
		knowledge[SpellLib.getSpell("Duro").getSpellIndex()] = 1;
		knowledge[SpellLib.getSpell("Multicolorfors").getSpellIndex()] = 1;
		knowledge[SpellLib.getSpell("Orchideous").getSpellIndex()] = 1;
		mouseSensitivity = 0;
	}

	/**
	 * Used to register these extended properties for the player during
	 * EntityConstructing event This method is for convenience only; it will
	 * make your code look nicer
	 */
	/**
	 * Returns ExtendedPlayer properties for player
	 */
	public static final SpellSkills get(EntityPlayer player) {
		return (SpellSkills) player.getExtendedProperties(MAGICSKILLS);
	}

	// Save any custom data that needs saving here
	@Override
	public void saveNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = new NBTTagCompound();
		properties.setInteger("patronus", this.patronusID);
		for (int i = 0; i < knowledge.length; i++) {
			properties.setInteger(SpellLib.names[i] + "_fizzle", knowledge[i]);
		}
		for (int i = 0; i < skill.length; i++) {
			properties.setInteger(SpellLib.names[i] + "_skill", skill[i]);
		}
		for (int i = 0; i < skillLevel.length; i++) {
			properties.setInteger(SpellLib.names[i] + "_level", skillLevel[i]);
		}
		properties.setInteger("overallSkill", this.overallSkill);
		properties.setBoolean("protego", protego);
		properties.setInteger("protegoCounter", protegoCounter);
		properties.setInteger("castProtegoCounter", castProtegoCounter);
		properties.setBoolean("isSpeaking", isSpeaking);
		properties.setInteger("portkeyX", portkeyX);
		properties.setInteger("portkeyY", portkeyY);
		properties.setInteger("portkeyZ", portkeyZ);
		properties.setBoolean("isAscending", isAscending);
		properties.setFloat("mouseSensitivity", mouseSensitivity);
		compound.setTag(MAGICSKILLS, properties);
	}

	// Load whatever data you saved
	@Override
	public void loadNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(MAGICSKILLS);
		this.patronusID = properties.getInteger("patronus");
		for (int i = 0; i < knowledge.length; i++) {
			knowledge[i] = properties.getInteger(SpellLib.names[i] + "_fizzle");
		}
		this.overallSkill = properties.getInteger("overallSkill");
		for (int i = 0; i < skill.length; i++) {
			skill[i] = properties.getInteger(SpellLib.names[i] + "_skill");
		}
		for (int i = 0; i < skillLevel.length; i++) {
			skillLevel[i] = properties.getInteger(SpellLib.names[i] + "_level");
		}
		this.protego = properties.getBoolean("protego");
		this.protegoCounter = properties.getInteger("protegoCounter");
		this.castProtegoCounter = properties.getInteger("castProtegoCounter");
		this.isSpeaking = properties.getBoolean("isSpeaking");
		this.portkeyX = properties.getInteger("portkeyX");
		this.portkeyY = properties.getInteger("portkeyY");
		this.portkeyZ = properties.getInteger("portkeyZ");
		this.isAscending = properties.getBoolean("isAscending");
		this.mouseSensitivity = properties.getFloat("mouseSensitivity");
	}

	@Override
	public void init(Entity entity, World world) {
	}

	/**
	 * Used to register these extended properties for the player during
	 * EntityConstructing event
	 */
	public static final void register(EntityPlayer player) {
		player.registerExtendedProperties(SpellSkills.MAGICSKILLS, new SpellSkills(player));
	}

	/**
	 * Makes it look nicer in the methods save/loadProxyData
	 */
	private static String getSaveKey(EntityPlayer player) {
		return player.getDisplayName() + ":" + MAGICSKILLS;
	}

	/**
	 * Does everything I did in onLivingDeathEvent and it's static, so you now
	 * only need to use the following in the above event:
	 * ExtendedPlayer.saveProxyData((EntityPlayer) event.entity));
	 */
	public static void saveProxyData(EntityPlayer player) {
		SpellSkills ext = SpellSkills.get(player);
		NBTTagCompound savedData = new NBTTagCompound();
		ext.removeHeldEntity();
		ext.saveNBTData(savedData);
		// Note that we made the CommonProxy method storeEntityData static,
		// so now we don't need an instance of CommonProxy to use it! Great!
		CommonProxy.storeEntityData(getSaveKey(player), savedData);
	}

	/**
	 * This cleans up the onEntityJoinWorld event by replacing most of the code
	 * with a single line: ExtendedPlayer.loadProxyData((EntityPlayer)
	 * event.entity));
	 */
	public static void loadProxyData(EntityPlayer player) {
		SpellSkills ext = get(player);
		NBTTagCompound savedData = CommonProxy.getEntityData(getSaveKey(player));

		if (savedData != null) {
			ext.loadNBTData(savedData);
		}
		// note we renamed 'syncExtendedProperties' to 'syncProperties' because
		// yay, it's shorter
		ext.syncProperties();
	}

	public void syncProperties() {
		if (player instanceof EntityPlayerMP) {
			HPNetwork.net.sendTo(new SyncSkillsPacket.SkillsMessage(player), (EntityPlayerMP) player);
		}
	}
	
	public void learnAll(){
		for(int i = 1; i < knowledge.length; i++){
			if(SpellLib.getSpell(SpellLib.names[i]).isComplicated()){
				knowledge[i] = 3;
			}else{
				knowledge[i] = 2;
			}
		}
		HPNetwork.net.sendTo(new SyncSkillsPacket.SkillsMessage(player), (EntityPlayerMP) player);
	}

	public int getPatronusID() {
		return this.patronusID;
	}

	public void addSkillLevel(int skillof) {
		this.skillLevel[skillof]++;
		if (skillLevel[skillof] >= 7) {
			skillLevel[skillof] = 6;
		}
		HPNetwork.net.sendTo(new SyncSkillsPacket.SkillsMessage(player), (EntityPlayerMP) player);
	}

	public void gainSkill(ISpell spell) {
		int index = spell.getSpellIndex();
		this.skill[index]++;
		this.overallSkill++;
		if (this.skill[index] % 35 == 0) {
			if (this.skillLevel[index] < 6) {
				this.addSkillLevel(index);
				player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GREEN + "[MagiCraft]" + EnumChatFormatting.RESET + " You leveled up your " + EnumChatFormatting.DARK_PURPLE + spell.getName() + EnumChatFormatting.RESET + " skill! " + "Current Level: " + EnumChatFormatting.DARK_PURPLE + this.getCurrentSkillLevel(spell)));
			}
		}
		HPNetwork.net.sendTo(new SyncSkillsPacket.SkillsMessage(player), (EntityPlayerMP) player);

	}

	public int getCurrentSkillLevel(ISpell spell) {

		return this.skillLevel[spell.getSpellIndex()];
	}

	public int getCurrentSkill(ISpell spell) {
		return this.skill[spell.getSpellIndex()];
	}

	public int getKnowledgeLevel(ISpell spell) {
		int j = this.knowledge[spell.getSpellIndex()];
		if(j > 2){
			if(!spell.isComplicated()){
				setKnowledgeLevel(spell, 2);
			}else if(j > 3){
				setKnowledgeLevel(spell, 3);
			}
		}
		return this.knowledge[spell.getSpellIndex()];
	}
	
	public void setKnowledgeLevel(ISpell spell, int level){
		this.knowledge[spell.getSpellIndex()] = level;
	}

	public void addKnowledgeLevel(ISpell spell) {

		if (!this.isKnowledgeMaxed(spell)) {
			// MagiNetwork.net.sendTo(new
			// SyncSkillsPacket.SkillsMessage(player), (EntityPlayerMP) player);
			this.knowledge[spell.getSpellIndex()]++;
		}
		HPNetwork.net.sendTo(new SyncSkillsPacket.SkillsMessage(player), (EntityPlayerMP) player);

	}

	public boolean isKnowledgeMaxed(ISpell spell) {
		if (spell.isComplicated()) {
			if (this.getKnowledgeLevel(spell) >= 3) {
				return true;
			}
		} else {
			if (this.getKnowledgeLevel(spell) >= 2) {
				return true;
			}
		}
		return false;
	}
	
	public boolean knowsSpell(ISpell spell){
		return getKnowledgeLevel(spell) > 0;
	}

	public void enableProtego(double multiplier) {
		this.protego = true;
		this.protegoCounter = (int) (280 * multiplier);
		HPNetwork.net.sendTo(new SyncSkillsPacket.SkillsMessage(player), (EntityPlayerMP) player);
	}

	public void disableProtego() {
		this.protego = false;
		HPNetwork.net.sendTo(new SyncSkillsPacket.SkillsMessage(player), (EntityPlayerMP) player);
	}

	public boolean hasProtego() {
		return this.protego;
	}

	public int getProtegoCounter() {
		return this.protegoCounter;
	}

	public void addProtegoCounter() {
		this.protegoCounter--;
		HPNetwork.net.sendTo(new SyncSkillsPacket.SkillsMessage(player), (EntityPlayerMP) player);
	}

	public void resetCastProtegoCounter() {
		this.castProtegoCounter = 400;
		HPNetwork.net.sendTo(new SyncSkillsPacket.SkillsMessage(player), (EntityPlayerMP) player);
	}

	public int getCastProtegoCounter() {
		return this.castProtegoCounter;
	}

	public void addCastProtegoCounter() {
		this.castProtegoCounter--;
		HPNetwork.net.sendTo(new SyncSkillsPacket.SkillsMessage(player), (EntityPlayerMP) player);
	}

	public boolean isSpeaking() {
		return isSpeaking;
	}

	public void setSpeaking(boolean isSpeaking) {
		this.isSpeaking = isSpeaking;
		HPNetwork.net.sendTo(new SyncSkillsPacket.SkillsMessage(player), (EntityPlayerMP) player);
	}

	public int getPortkeyX() {
		return portkeyX;
	}

	public void setPortkeyX(int portkeyX) {
		this.portkeyX = portkeyX;
	}

	public int getPortkeyY() {
		return portkeyY;
	}

	public void setPortkeyY(int portkeyY) {
		this.portkeyY = portkeyY;
	}

	public int getPortkeyZ() {
		return portkeyZ;
	}

	public void setPortkeyZ(int portkeyZ) {
		this.portkeyZ = portkeyZ;
	}

	public int getHeldEntity() {
		return heldEntityID;
	}

	public void setHeldEntity(Entity heldEntity) {
		this.heldEntityID = heldEntity.getEntityId();
	}
	
	public void removeHeldEntity(){
		this.heldEntityID = 0;
	}

	public boolean isAscending() {
		return isAscending;
	}

	public void setAscending(boolean isAscending) {
		this.isAscending = isAscending;
		HPNetwork.net.sendTo(new SyncSkillsPacket.SkillsMessage(player), (EntityPlayerMP) player);
	}
	
	public float getMouseSensitivity(){
		return mouseSensitivity;
	}
	
	public void setMouseSensitivity(float f){
		this.mouseSensitivity = f;
	}

}