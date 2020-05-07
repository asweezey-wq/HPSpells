package com.apmods.hpspells.item;

import com.apmods.hpspells.entity.EntityBroomstick;
import com.apmods.hpspells.entity.EnumBroomstick;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBroomstick extends ItemBase{

	public EnumBroomstick b;
	
	public ItemBroomstick(String name, EnumBroomstick b) {
		super(name);
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTabs.tabTransport);
		this.b = b;
	}
	
	@Override
	public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World world, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
		EntityBroomstick broomstick = new EntityBroomstick(world, b);
		broomstick.setPosition(p_77648_4_ + p_77648_8_, p_77648_5_ + p_77648_9_, p_77648_6_ + p_77648_10_);
		if(!world.isRemote){
			world.spawnEntityInWorld(broomstick);
		}
		p_77648_1_.stackSize--;
		return true;
	}
}
