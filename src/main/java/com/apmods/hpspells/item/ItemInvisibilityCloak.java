package com.apmods.hpspells.item;

import com.apmods.hpspells.main.HPSpells;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class ItemInvisibilityCloak extends ItemCloak{
	
	public ItemInvisibilityCloak(String name, int p_i45325_2_, int p_i45325_3_) {
		super(name, p_i45325_2_, p_i45325_3_);
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(player.posX - 64, player.posY - 64, player.posZ - 64, player.posX + 64, player.posY + 64, player.posZ + 64);
		for(Object o : world.getEntitiesWithinAABB(EntityCreature.class, aabb)){
			if(o instanceof EntityCreature){
				EntityCreature entity = (EntityCreature) o;
				if(entity.getAttackTarget() == player){
					entity.setAttackTarget(null);
				}
			}
		}
	}
	
	
}
