package com.apmods.hpspells.item;

import java.util.List;

import com.apmods.hpspells.entity.EntityDarkArtsWard;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class ItemDarkWardDisabler extends ItemBase{

	public ItemDarkWardDisabler(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	public ItemStack onItemRightClick(ItemStack p_77659_1_, World world, EntityPlayer player)
    {
		if(player.capabilities.isCreativeMode){
			AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(player.posX - 1, player.posY - 1, player.posZ - 1, player.posX + 1, player.posY + 1, player.posZ + 1);
			List l = world.getEntitiesWithinAABB(EntityDarkArtsWard.class, aabb);
			for(int i = 0; i < l.size(); i++){
				EntityDarkArtsWard e = (EntityDarkArtsWard)l.get(i);
				e.setDead();
			}
		}
        return p_77659_1_;
    }
}
