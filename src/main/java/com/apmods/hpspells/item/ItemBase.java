package com.apmods.hpspells.item;

import java.util.List;

import com.apmods.hpspells.main.HPSpells;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemBase extends Item{
	String tooltip;
	public ItemBase(String name){
		super();
		setUnlocalizedName(name);
		this.setTextureName(HPSpells.MODID + ":" + getUnlocalizedName().substring(5));
		this.setCreativeTab(CreativeTabs.tabCombat);
		
	}
    public ItemBase register(){
    	GameRegistry.registerItem(this, getUnlocalizedName().substring(5));
    	return this;
    }
    public void setTex(){
    	this.setTextureName(HPSpells.MODID + ":" + this.getUnlocalizedName().substring(5));
    }
    public Item setItemTooltip(String s){
    	tooltip = s;
    	return this;
    }
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List list, boolean p_77624_4_) {
    	if(tooltip != null){
    		list.add(tooltip);
    	}
    }
}
