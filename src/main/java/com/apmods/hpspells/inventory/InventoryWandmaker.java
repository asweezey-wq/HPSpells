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
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class InventoryWandmaker implements IInventory {

	public EntityWandmaker wandmaker;
	
	public EnumWandMaterial selectedWood;
	public EnumWandCore selectedCore;
	
	private static final String NAME = "Wandmaker";
	private static final int INV_START = 0;
	public static final int INV_SIZE = 2;
	private ItemStack[] inventory = new ItemStack[INV_SIZE];

	public InventoryWandmaker(EntityWandmaker wandmaker) {
		this.wandmaker = wandmaker;
		selectedWood = wandmaker.getWood(0);
		selectedCore = wandmaker.getCore(0);
	}
	
	@Override
	public int getSizeInventory() {
		return INV_SIZE;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inventory[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		ItemStack stack = getStackInSlot(slot);
		if (stack != null) {
			if (stack.stackSize > amount) {
				stack = stack.splitStack(amount);
				if (stack.stackSize == 0) {
					setInventorySlotContents(slot, null);
				}
			} else {
				setInventorySlotContents(slot, null);
			}

			this.markDirty();
		}
		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		ItemStack stack = getStackInSlot(slot);
		if (stack != null) {
			setInventorySlotContents(slot, null);
		}
		return stack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemstack) {
		this.inventory[slot] = itemstack;

		if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()) {
			itemstack.stackSize = this.getInventoryStackLimit();
		}

		if(slot == 0){
			this.updateWand();
		}
	}

	@Override
	public String getInventoryName() {
		return NAME;
	}

	@Override
	public boolean hasCustomInventoryName() {
		return true;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void markDirty() {
		updateWand();
	}
	
	public void updateWand(){
		if(selectedCore != null && selectedWood != null && hasPayed()){
			ItemStack wand = new ItemStack(ItemManager.wand);
			wand.setTagCompound(new NBTTagCompound());
			ItemWand.setWandMaterial(wand, selectedWood);
			ItemWand.setWandCore(wand, selectedCore);
			Random rand = new Random();
			double[] lengths = {0, 0.25, 0.5, 0.75};
			wand.getTagCompound().setDouble("length", (rand.nextInt(7) + 8) + lengths[rand.nextInt(lengths.length)]);
			this.setInventorySlotContents(1, wand);
		}else{
			this.setInventorySlotContents(1, null);
		}
	}
	
	public boolean hasPayed(){
		return this.getStackInSlot(0) != null && this.getStackInSlot(0).getItem() == Items.emerald && this.getStackInSlot(0).stackSize >= 20;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
		return true;
	}

	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {
		wandmaker.setCustomer(null);
	}

	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
		return true;
	}

}
