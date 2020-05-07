package com.apmods.hpspells.inventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.apmods.hpspells.wand.EnumWandCore;
import com.apmods.hpspells.wand.EnumWandMaterial;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotWandmakerResult extends Slot{

	public InventoryWandmaker wandmakerInv;
	
	public SlotWandmakerResult(InventoryWandmaker p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_) {
		super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
		wandmakerInv = p_i1824_1_;
	}
	
	@Override
	public boolean isItemValid(ItemStack p_75214_1_) {
		return false;
	}
	
	@Override
	public void onPickupFromSlot(EntityPlayer p_82870_1_, ItemStack p_82870_2_) {
		super.onPickupFromSlot(p_82870_1_, p_82870_2_);
		if(wandmakerInv.hasPayed()){
			wandmakerInv.decrStackSize(0, 20);
			wandmakerInv.wandmaker.removeCore(wandmakerInv.selectedCore);
			wandmakerInv.wandmaker.removeWood(wandmakerInv.selectedWood);
			wandmakerInv.selectedCore = wandmakerInv.wandmaker.getCore(0);
			wandmakerInv.selectedWood = wandmakerInv.wandmaker.getWood(0);
		}
	}
}
