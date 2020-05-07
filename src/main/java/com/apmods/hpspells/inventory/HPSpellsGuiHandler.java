package com.apmods.hpspells.inventory;

import com.apmods.hpspells.entity.EntityWandmaker;
import com.apmods.hpspells.gui.GuiImperio;
import com.apmods.hpspells.gui.GuiWandmaker;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class HPSpellsGuiHandler implements IGuiHandler{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		if(ID == 11){
			EntityPlayer playerHit = (EntityPlayer) world.getEntityByID(x);
			return new ContainerImperio(playerHit.inventory, !world.isRemote, playerHit);
		}else if(ID == 12){
			EntityWandmaker wandmaker = (EntityWandmaker) world.getEntityByID(x);
			return new ContainerWandmaker(player.inventory, wandmaker);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		if(ID == 11){
			EntityPlayer playerHit = (EntityPlayer) world.getEntityByID(x);
			return new GuiImperio(playerHit);
		}else if(ID == 12){
			EntityWandmaker wandmaker = (EntityWandmaker) world.getEntityByID(x);
			return new GuiWandmaker(player.inventory, wandmaker);
		}
		else{
			return null;
		}
	}
}
