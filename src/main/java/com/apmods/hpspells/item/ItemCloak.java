package com.apmods.hpspells.item;

import com.apmods.hpspells.client.ModelCloak;
import com.apmods.hpspells.main.HPSpells;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemCloak extends ItemArmor{
	
	public ItemCloak(String name, int p_i45325_2_, int p_i45325_3_) {
		super(ArmorMaterial.CHAIN, p_i45325_2_, p_i45325_3_);
		setUnlocalizedName(name);
		this.setTextureName(HPSpells.MODID + ":" + getUnlocalizedName().substring(5));
		this.setCreativeTab(CreativeTabs.tabCombat);
		this.setMaxStackSize(1);
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		return HPSpells.MODID + ":textures/armor/cloakTexture.png";
	}
	
	public Item register(){
    	GameRegistry.registerItem(this, getUnlocalizedName().substring(5));
    	return this;
    }
	
	@Override
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack stack, int armorSlot) {
		if (stack != null) {
			if (stack.getItem() instanceof ItemArmor) {

				ModelBiped armorModel = HPSpells.proxy.getArmorModel(0);
				
				armorModel.bipedHeadwear.showModel = false;
				armorModel.bipedBody.showModel = armorSlot == 1;
				armorModel.bipedRightArm.showModel = armorSlot == 1;
				armorModel.bipedLeftArm.showModel = armorSlot == 1;
				armorModel.isSneak = entityLiving.isSneaking();
				armorModel.isRiding = entityLiving.isRiding();
				armorModel.isChild = entityLiving.isChild();
				armorModel.heldItemRight = entityLiving.getHeldItem() != null ? 1 :0;
				if(entityLiving instanceof EntityPlayer){
					armorModel.aimedBow =((EntityPlayer)entityLiving).getItemInUseDuration() > 2;
				}
				
				if(armorModel instanceof ModelCloak){
					ModelCloak cloak = (ModelCloak) armorModel;
					cloak.cape.rotateAngleX = (float) ((Math.PI/4) * entityLiving.limbSwingAmount);
				}

				return armorModel;
			}
		}
		return null;
	}
}
