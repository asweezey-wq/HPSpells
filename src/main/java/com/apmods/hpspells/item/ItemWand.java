package com.apmods.hpspells.item;

import java.util.List;
import java.util.Random;

import org.lwjgl.input.Keyboard;

import com.apmods.hpspells.extendedplayer.SpellSkills;
import com.apmods.hpspells.lib.SpellLib;
import com.apmods.hpspells.lib.WandLib;
import com.apmods.hpspells.spell.EnumSpellType;
import com.apmods.hpspells.spell.ISpell;
import com.apmods.hpspells.wand.EnumWandCore;
import com.apmods.hpspells.wand.EnumWandMaterial;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemWand extends ItemBase {

	private boolean _flag;

	private int cooldownTime;

	public ItemWand(String name, int cooldownTime) {
		super(name);
		this.setMaxStackSize(1);
		this.cooldownTime = cooldownTime;
	}

	@Override
	public void onUpdate(ItemStack is, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
		if (is.stackTagCompound == null) {
			Random rand = new Random();
			double[] lengths = { 0.25, 0.5, 0.75 };
			is.stackTagCompound = new NBTTagCompound();
			is.stackTagCompound.setDouble("length", (rand.nextInt(7) + 8) + lengths[rand.nextInt(lengths.length)]);
			is.stackTagCompound.setString("wood", EnumWandMaterial.getRandom().name());
			is.stackTagCompound.setString("wand core", EnumWandCore.getRandom().name());
			is.stackTagCompound.setString("last spell", "None");
			is.stackTagCompound.setInteger("last spell counter", 0);
			is.stackTagCompound.setBoolean("lumos", false);
			is.stackTagCompound.setBoolean("wing lev", false);
			is.stackTagCompound.setBoolean("cistemAperio", false);
		} else {
			if (is.stackTagCompound.getInteger("last spell counter") < cooldownTime) {
				is.stackTagCompound.setInteger("last spell counter", is.stackTagCompound.getInteger("last spell counter") + 1);
			}
		}

		if (is.getTagCompound().getBoolean("wing lev")) {
			if (entity instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) entity;
				SpellSkills ext = SpellSkills.get(player);
				if (ext.getHeldEntity() != 0) {
					Entity entityHeld = world.getEntityByID(ext.getHeldEntity());
					if (entityHeld != null) {
						Vec3 vec = player.getLookVec();
						entityHeld.motionX = 0;
						entityHeld.motionY = 0;
						entityHeld.motionZ = 0;
						entityHeld.fallDistance = 0;
						int multiplier = 10;
						entityHeld.setPosition(player.posX + vec.xCoord * multiplier, player.posY + (vec.yCoord * multiplier) + 1, player.posZ + vec.zCoord * multiplier);
					} else {
						ext.removeHeldEntity();
					}
				}
			}
		}
		if (entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entity;
			SpellSkills ext = SpellSkills.get(player);
			if(is.getTagCompound().getBoolean("cistemAperio")){
				if(player.openContainer == null || player.openContainer == player.inventoryContainer){
					is.getTagCompound().setBoolean("cistemAperio", false);
				}
			}
			if (ext.isAscending()) {
				entity.motionY += 0.075;
				entity.motionY = Math.min(entity.motionY, 0.4);
				entity.velocityChanged = true;
			}
		}
	}

	@Override
	public void onCreated(ItemStack is, World p_77622_2_, EntityPlayer p_77622_3_) {
		is.stackTagCompound = new NBTTagCompound();
		is.stackTagCompound.setInteger("length", 0);
		is.stackTagCompound.setString("wand core", "PHEONIX");
		is.stackTagCompound.setString("wood", "OAK");
		is.stackTagCompound.setString("last spell", "None");
		is.stackTagCompound.setInteger("last spell counter", 0);
		is.stackTagCompound.setBoolean("lumos", false);
		is.stackTagCompound.setBoolean("wing lev", false);
		is.stackTagCompound.setBoolean("cistemAperio", false);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack is, EntityPlayer p_77624_2_, List list, boolean p_77624_4_) {
		SpellSkills ext = SpellSkills.get(p_77624_2_);
		if (is.stackTagCompound != null) {
			list.add(EnumChatFormatting.DARK_PURPLE + "Wood: " + wandMaterial(is).name + " Wood");
			list.add(EnumChatFormatting.DARK_PURPLE + "Length: " + is.stackTagCompound.getDouble("length") + " inches");
			list.add(EnumChatFormatting.DARK_PURPLE + "Core: " + wandCore(is).name);
			if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
				list.add("-Charms: " + (int)(getSpellMultiplier(is, EnumSpellType.CHARM) * 100) + "%");
				list.add("-Hexes: " + (int)(getSpellMultiplier(is, EnumSpellType.HEX) * 100) + "%");
				list.add("-Transfiguration: " + (int)(getSpellMultiplier(is, EnumSpellType.TRANSFIGURATION) * 100) + "%");
				list.add("-DADA: " + (int)(getSpellMultiplier(is, EnumSpellType.DADA) * 100) + "%");
				list.add("-Dark Arts: " + (int)(getSpellMultiplier(is, EnumSpellType.DARK) * 100) + "%");
				double fizzleRate = (wandMaterial(is).getFizzleRateMultiplier());
				list.add("-Ease of Learning: " + (fizzleRate > 1 ? "Hard" : (fizzleRate == 1 ? "Normal" : "Easy")));
			} else {
				list.add(EnumChatFormatting.WHITE + "Press " + EnumChatFormatting.YELLOW + "Shift " + EnumChatFormatting.WHITE + "to see effectiveness");
			}
//			String s = is.stackTagCompound.getString("last spell");
//			ISpell spell = SpellLib.getSpell(s);
//			list.add(EnumChatFormatting.DARK_PURPLE + "Current Spell: " + spell.getName());
//			list.add(EnumChatFormatting.DARK_PURPLE + "Current Spell Skill: " + ext.getCurrentSkill(spell));
//			list.add(EnumChatFormatting.DARK_PURPLE + "Current Spell Skill Level: " + ext.getCurrentSkillLevel(spell));
		}
	}

	public int getCooldownTime() {
		return cooldownTime;
	}

	@Override
	public int getColorFromItemStack(ItemStack stack, int p_82790_2_) {
		if (stack.hasTagCompound()) {
			return wandMaterial(stack).color;
		} else {
			return 0x000000;
		}
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		if (stack.hasTagCompound()) {
			return wandMaterial(stack).special;
		}
		return false;
	}

	public static EnumWandMaterial wandMaterial(ItemStack stack) {
		return EnumWandMaterial.valueOf(stack.getTagCompound().getString("wood"));
	}

	public static EnumWandCore wandCore(ItemStack stack) {
		return EnumWandCore.valueOf(stack.getTagCompound().getString("wand core"));
	}
	
	public static void setWandMaterial(ItemStack stack, EnumWandMaterial mat){
		stack.getTagCompound().setString("wood", mat.name());
	}
	
	public static void setWandCore(ItemStack stack, EnumWandCore core){
		stack.getTagCompound().setString("wand core", core.name());
	}
	
	public double getSpellMultiplier(ItemStack stack, EnumSpellType type){
		EnumWandMaterial wood = EnumWandMaterial.valueOf(stack.getTagCompound().getString("wood"));
		EnumWandCore core = EnumWandCore.valueOf(stack.getTagCompound().getString("wand core"));
		return wood.getMultiplierForSpellType(type) * core.getMultiplierForSpellType(type);
	}

}
