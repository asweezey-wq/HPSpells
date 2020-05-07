package com.apmods.hpspells.item;

import java.util.List;

import com.apmods.hpspells.extendedplayer.SpellSkills;
import com.apmods.hpspells.lib.SpellLib;
import com.apmods.hpspells.spell.ISpell;
import com.apmods.hpspells.wand.EnumWandCore;
import com.apmods.hpspells.wand.EnumWandMaterial;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemElderWand extends ItemWand {

	private Entity _target;
	private boolean _flag;

	public ItemElderWand(String name) {
		super(name, 10);
		this.setMaxStackSize(1);
	}
	
	@Override
	public void onUpdate(ItemStack is, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
		super.onUpdate(is, world, entity, p_77663_4_, p_77663_5_);
		if(is.hasTagCompound()){
			if(!is.getTagCompound().getString("wood").equals(EnumWandMaterial.ELDER.name())){
				is.getTagCompound().setString("wood", EnumWandMaterial.ELDER.name());
			}
			if(!is.getTagCompound().getString("wand core").equals(EnumWandCore.THESTRAL.name())){
				is.getTagCompound().setString("wand core", EnumWandCore.THESTRAL.name());
			}
		}
	}
}
