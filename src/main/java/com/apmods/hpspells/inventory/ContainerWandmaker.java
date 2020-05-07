package com.apmods.hpspells.inventory;

import java.util.Random;

import com.apmods.hpspells.entity.EntityWandmaker;
import com.apmods.hpspells.item.ItemManager;
import com.apmods.hpspells.item.ItemWand;
import com.apmods.hpspells.wand.EnumWandCore;
import com.apmods.hpspells.wand.EnumWandMaterial;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerMerchant;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ContainerWandmaker extends Container {
	
	private EntityWandmaker wandmaker;
	public InventoryWandmaker wandmakerInv;
	
	public int cost = 20;
	
	private static final int INV_START = InventoryWandmaker.INV_SIZE, INV_END = INV_START+26,
			HOTBAR_START = INV_END+1, HOTBAR_END = HOTBAR_START+8;

	public ContainerWandmaker(IInventory playerInventory, EntityWandmaker wandmaker) {
		this.wandmaker = wandmaker;
		this.wandmakerInv = new InventoryWandmaker(wandmaker);
		
		int j;
		int k;

		this.addSlotToContainer(new Slot(wandmakerInv, 0, 150, 20));
		this.addSlotToContainer(new SlotWandmakerResult(wandmakerInv, 1, 150, 50));

		for (j = 0; j < 3; ++j) {
			for (k = 0; k < 9; ++k) {
				this.addSlotToContainer(new Slot(playerInventory, k + j * 9 + 9, 8 + k * 18, 84 + j * 18));
			}
		}

		for (j = 0; j < 9; ++j) {
			this.addSlotToContainer(new Slot(playerInventory, j, 8 + j * 18, 142));
		}
	}

	public ItemStack transferStackInSlot(EntityPlayer player, int par2) {
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(par2);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			// Either armor slot or custom item slot was clicked
			if (par2 < 0) {
				// try to place in player inventory / action bar
				if (!this.mergeItemStack(itemstack1, INV_START, HOTBAR_END, true)) {
					return null;
				}

				slot.onSlotChange(itemstack1, itemstack);
			}
			// Item is in inventory / hotbar, try to place either in custom or
			// armor slots
			else {
				// item in player's inventory, but not in action bar
				if (par2 >= INV_START && par2 < HOTBAR_START) {
					// place in action bar
					if (!this.mergeItemStack(itemstack1, HOTBAR_START, HOTBAR_START + 1, false)) {
						return null;
					}
				}
				// item in action bar - place in player inventory
				else if (par2 >= HOTBAR_START && par2 < HOTBAR_END + 1) {
					if (!this.mergeItemStack(itemstack1, INV_START, INV_END + 1, false)) {
						return null;
					}
				}
			}

			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemstack.stackSize) {
				return null;
			}

			slot.onPickupFromSlot(player, itemstack1);
		}

		return itemstack;
	}

	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return true;
	}
	
	@Override
	public void onContainerClosed(EntityPlayer p_75134_1_) {
		super.onContainerClosed(p_75134_1_);
		if(this.getSlot(0).getHasStack()){
			p_75134_1_.dropPlayerItemWithRandomChoice(this.wandmakerInv.getStackInSlot(0), false);
		}
		wandmaker.setCustomer(null);
		if(wandmaker.getNumCores() == 0 || wandmaker.getNumWoods() == 0){
			wandmakerInv.wandmaker.setDead();
			return;
		}
	}
}
