package com.apmods.hpspells.item;

import com.apmods.hpspells.entity.EntityDarkArtsWard;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemDarkWard extends ItemBase{

	public ItemDarkWard(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
    	EntityDarkArtsWard darkWard = new EntityDarkArtsWard(world);
    	darkWard.setPosition(x, y + 2, z);
    	if(!world.isRemote){
    		world.spawnEntityInWorld(darkWard);
    	}
        return true;
    }

}
