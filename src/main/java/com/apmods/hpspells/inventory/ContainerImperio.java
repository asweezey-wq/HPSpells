package com.apmods.hpspells.inventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.util.IIcon;

public class ContainerImperio extends Container{
	/** The crafting matrix inventory. */
    public InventoryCrafting craftMatrix = new InventoryCrafting(this, 2, 2);
    public IInventory craftResult = new InventoryCraftResult();
    /** Determines if inventory manipulation should be handled. */
    public boolean isLocalWorld;
    private final EntityPlayer thePlayer;
    private static final String __OBFID = "CL_00001754";

    public ContainerImperio(final InventoryPlayer p_i1819_1_, boolean p_i1819_2_, EntityPlayer p_i1819_3_)
    {
        this.isLocalWorld = p_i1819_2_;
        this.thePlayer = p_i1819_3_;
        this.addSlotToContainer(new SlotCrafting(p_i1819_1_.player, this.craftMatrix, this.craftResult, 0, 144, 36));
        int i;
        int j;

        for (i = 0; i < 2; ++i)
        {
            for (j = 0; j < 2; ++j)
            {
                this.addSlotToContainer(new Slot(this.craftMatrix, j + i * 2, 88 + j * 18, 26 + i * 18));
            }
        }

        for (i = 0; i < 4; ++i)
        {
            final int k = i;
            this.addSlotToContainer(new Slot(p_i1819_1_, p_i1819_1_.getSizeInventory() - 1 - i, 8, 8 + i * 18)
            {
                private static final String __OBFID = "CL_00001755";
                /**
                 * Returns the maximum stack size for a given slot (usually the same as getInventoryStackLimit(), but 1
                 * in the case of armor slots)
                 */
                public int getSlotStackLimit()
                {
                    return 1;
                }
                /**
                 * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
                 */
                public boolean isItemValid(ItemStack p_75214_1_)
                {
                    if (p_75214_1_ == null) return false;
                    return p_75214_1_.getItem().isValidArmor(p_75214_1_, k, thePlayer);
                }
                /**
                 * Returns the icon index on items.png that is used as background image of the slot.
                 */
                @SideOnly(Side.CLIENT)
                public IIcon getBackgroundIconIndex()
                {
                    return ItemArmor.func_94602_b(k);
                }
            });
        }

        for (i = 0; i < 3; ++i)
        {
            for (j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(p_i1819_1_, j + (i + 1) * 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(p_i1819_1_, i, 8 + i * 18, 142));
        }

        this.onCraftMatrixChanged(this.craftMatrix);
    }

    /**
     * Callback for when the crafting matrix is changed.
     */
    public void onCraftMatrixChanged(IInventory p_75130_1_)
    {
        this.craftResult.setInventorySlotContents(0, CraftingManager.getInstance().findMatchingRecipe(this.craftMatrix, this.thePlayer.worldObj));
    }

    /**
     * Called when the container is closed.
     */
    public void onContainerClosed(EntityPlayer p_75134_1_)
    {
        super.onContainerClosed(p_75134_1_);

        for (int i = 0; i < 4; ++i)
        {
            ItemStack itemstack = this.craftMatrix.getStackInSlotOnClosing(i);

            if (itemstack != null)
            {
                p_75134_1_.dropPlayerItemWithRandomChoice(itemstack, false);
            }
        }

        this.craftResult.setInventorySlotContents(0, (ItemStack)null);
    }

    public boolean canInteractWith(EntityPlayer p_75145_1_)
    {
        return true;
    }
    public ItemStack slotClick(int p_75144_1_, int p_75144_2_, int p_75144_3_, EntityPlayer p_75144_4_)
    {
//        ItemStack itemstack = null;
//        InventoryPlayer inventoryplayer = p_75144_4_.inventory;
//        int i1;
//        ItemStack itemstack3;
//
//        if (p_75144_3_ == 5)
//        {
//            int l = this.field_94536_g;
//            this.field_94536_g = func_94532_c(p_75144_2_);
//
//            if ((l != 1 || this.field_94536_g != 2) && l != this.field_94536_g)
//            {
//                this.func_94533_d();
//            }
//            else if (inventoryplayer.getItemStack() == null)
//            {
//                this.func_94533_d();
//            }
//            else if (this.field_94536_g == 0)
//            {
//                this.field_94535_f = func_94529_b(p_75144_2_);
//
//                if (func_94528_d(this.field_94535_f))
//                {
//                    this.field_94536_g = 1;
//                    this.field_94537_h.clear();
//                }
//                else
//                {
//                    this.func_94533_d();
//                }
//            }
//            else if (this.field_94536_g == 1)
//            {
//                Slot slot = (Slot)this.inventorySlots.get(p_75144_1_);
//
//                if (slot != null && func_94527_a(slot, inventoryplayer.getItemStack(), true) && slot.isItemValid(inventoryplayer.getItemStack()) && inventoryplayer.getItemStack().stackSize > this.field_94537_h.size() && this.canDragIntoSlot(slot))
//                {
//                    this.field_94537_h.add(slot);
//                }
//            }
//            else if (this.field_94536_g == 2)
//            {
//                if (!this.field_94537_h.isEmpty())
//                {
//                    itemstack3 = inventoryplayer.getItemStack().copy();
//                    i1 = inventoryplayer.getItemStack().stackSize;
//                    Iterator iterator = this.field_94537_h.iterator();
//
//                    while (iterator.hasNext())
//                    {
//                        Slot slot1 = (Slot)iterator.next();
//
//                        if (slot1 != null && func_94527_a(slot1, inventoryplayer.getItemStack(), true) && slot1.isItemValid(inventoryplayer.getItemStack()) && inventoryplayer.getItemStack().stackSize >= this.field_94537_h.size() && this.canDragIntoSlot(slot1))
//                        {
//                            ItemStack itemstack1 = itemstack3.copy();
//                            int j1 = slot1.getHasStack() ? slot1.getStack().stackSize : 0;
//                            func_94525_a(this.field_94537_h, this.field_94535_f, itemstack1, j1);
//
//                            if (itemstack1.stackSize > itemstack1.getMaxStackSize())
//                            {
//                                itemstack1.stackSize = itemstack1.getMaxStackSize();
//                            }
//
//                            if (itemstack1.stackSize > slot1.getSlotStackLimit())
//                            {
//                                itemstack1.stackSize = slot1.getSlotStackLimit();
//                            }
//
//                            i1 -= itemstack1.stackSize - j1;
//                            slot1.putStack(itemstack1);
//                        }
//                    }
//
//                    itemstack3.stackSize = i1;
//
//                    if (itemstack3.stackSize <= 0)
//                    {
//                        itemstack3 = null;
//                    }
//
//                    inventoryplayer.setItemStack(itemstack3);
//                }
//
//                this.func_94533_d();
//            }
//            else
//            {
//                this.func_94533_d();
//            }
//        }
//        else if (this.field_94536_g != 0)
//        {
//            this.func_94533_d();
//        }
//        else
//        {
//            Slot slot2;
//            int l1;
//            ItemStack itemstack5;
//
//            if ((p_75144_3_ == 0 || p_75144_3_ == 1) && (p_75144_2_ == 0 || p_75144_2_ == 1))
//            {
//                if (p_75144_1_ == -999)
//                {
//                    if (inventoryplayer.getItemStack() != null && p_75144_1_ == -999)
//                    {
//                        if (p_75144_2_ == 0)
//                        {
//                            p_75144_4_.dropPlayerItemWithRandomChoice(inventoryplayer.getItemStack(), true);
//                            inventoryplayer.setItemStack((ItemStack)null);
//                        }
//
//                        if (p_75144_2_ == 1)
//                        {
//                            p_75144_4_.dropPlayerItemWithRandomChoice(inventoryplayer.getItemStack().splitStack(1), true);
//
//                            if (inventoryplayer.getItemStack().stackSize == 0)
//                            {
//                                inventoryplayer.setItemStack((ItemStack)null);
//                            }
//                        }
//                    }
//                }
//                else if (p_75144_3_ == 1)
//                {
//                    if (p_75144_1_ < 0)
//                    {
//                        return null;
//                    }
//
//                    slot2 = (Slot)this.inventorySlots.get(p_75144_1_);
//
//                    if (slot2 != null && slot2.canTakeStack(p_75144_4_))
//                    {
//                        itemstack3 = this.transferStackInSlot(p_75144_4_, p_75144_1_);
//
//                        if (itemstack3 != null)
//                        {
//                            Item item = itemstack3.getItem();
//                            itemstack = itemstack3.copy();
//
//                            if (slot2.getStack() != null && slot2.getStack().getItem() == item)
//                            {
//                                this.retrySlotClick(p_75144_1_, p_75144_2_, true, p_75144_4_);
//                            }
//                        }
//                    }
//                }
//                else
//                {
//                    if (p_75144_1_ < 0)
//                    {
//                        return null;
//                    }
//
//                    slot2 = (Slot)this.inventorySlots.get(p_75144_1_);
//
//                    if (slot2 != null)
//                    {
//                        itemstack3 = slot2.getStack();
//                        ItemStack itemstack4 = inventoryplayer.getItemStack();
//
//                        if (itemstack3 != null)
//                        {
//                            itemstack = itemstack3.copy();
//                        }
//
//                        if (itemstack3 == null)
//                        {
//                            if (itemstack4 != null && slot2.isItemValid(itemstack4))
//                            {
//                                l1 = p_75144_2_ == 0 ? itemstack4.stackSize : 1;
//
//                                if (l1 > slot2.getSlotStackLimit())
//                                {
//                                    l1 = slot2.getSlotStackLimit();
//                                }
//
//                                if (itemstack4.stackSize >= l1)
//                                {
//                                    slot2.putStack(itemstack4.splitStack(l1));
//                                }
//
//                                if (itemstack4.stackSize == 0)
//                                {
//                                    inventoryplayer.setItemStack((ItemStack)null);
//                                }
//                            }
//                        }
//                        else if (slot2.canTakeStack(p_75144_4_))
//                        {
//                            if (itemstack4 == null)
//                            {
//                                l1 = p_75144_2_ == 0 ? itemstack3.stackSize : (itemstack3.stackSize + 1) / 2;
//                                itemstack5 = slot2.decrStackSize(l1);
//                                inventoryplayer.setItemStack(itemstack5);
//
//                                if (itemstack3.stackSize == 0)
//                                {
//                                    slot2.putStack((ItemStack)null);
//                                }
//
//                                slot2.onPickupFromSlot(p_75144_4_, inventoryplayer.getItemStack());
//                            }
//                            else if (slot2.isItemValid(itemstack4))
//                            {
//                                if (itemstack3.getItem() == itemstack4.getItem() && itemstack3.getItemDamage() == itemstack4.getItemDamage() && ItemStack.areItemStackTagsEqual(itemstack3, itemstack4))
//                                {
//                                    l1 = p_75144_2_ == 0 ? itemstack4.stackSize : 1;
//
//                                    if (l1 > slot2.getSlotStackLimit() - itemstack3.stackSize)
//                                    {
//                                        l1 = slot2.getSlotStackLimit() - itemstack3.stackSize;
//                                    }
//
//                                    if (l1 > itemstack4.getMaxStackSize() - itemstack3.stackSize)
//                                    {
//                                        l1 = itemstack4.getMaxStackSize() - itemstack3.stackSize;
//                                    }
//
//                                    itemstack4.splitStack(l1);
//
//                                    if (itemstack4.stackSize == 0)
//                                    {
//                                        inventoryplayer.setItemStack((ItemStack)null);
//                                    }
//
//                                    itemstack3.stackSize += l1;
//                                }
//                                else if (itemstack4.stackSize <= slot2.getSlotStackLimit())
//                                {
//                                    slot2.putStack(itemstack4);
//                                    inventoryplayer.setItemStack(itemstack3);
//                                }
//                            }
//                            else if (itemstack3.getItem() == itemstack4.getItem() && itemstack4.getMaxStackSize() > 1 && (!itemstack3.getHasSubtypes() || itemstack3.getItemDamage() == itemstack4.getItemDamage()) && ItemStack.areItemStackTagsEqual(itemstack3, itemstack4))
//                            {
//                                l1 = itemstack3.stackSize;
//
//                                if (l1 > 0 && l1 + itemstack4.stackSize <= itemstack4.getMaxStackSize())
//                                {
//                                    itemstack4.stackSize += l1;
//                                    itemstack3 = slot2.decrStackSize(l1);
//
//                                    if (itemstack3.stackSize == 0)
//                                    {
//                                        slot2.putStack((ItemStack)null);
//                                    }
//
//                                    slot2.onPickupFromSlot(p_75144_4_, inventoryplayer.getItemStack());
//                                }
//                            }
//                        }
//
//                        slot2.onSlotChanged();
//                    }
//                }
//            }
//            else if (p_75144_3_ == 2 && p_75144_2_ >= 0 && p_75144_2_ < 9)
//            {
//                slot2 = (Slot)this.inventorySlots.get(p_75144_1_);
//
//                if (slot2.canTakeStack(p_75144_4_))
//                {
//                    itemstack3 = inventoryplayer.getStackInSlot(p_75144_2_);
//                    boolean flag = itemstack3 == null || slot2.inventory == inventoryplayer && slot2.isItemValid(itemstack3);
//                    l1 = -1;
//
//                    if (!flag)
//                    {
//                        l1 = inventoryplayer.getFirstEmptyStack();
//                        flag |= l1 > -1;
//                    }
//
//                    if (slot2.getHasStack() && flag)
//                    {
//                        itemstack5 = slot2.getStack();
//                        inventoryplayer.setInventorySlotContents(p_75144_2_, itemstack5.copy());
//
//                        if ((slot2.inventory != inventoryplayer || !slot2.isItemValid(itemstack3)) && itemstack3 != null)
//                        {
//                            if (l1 > -1)
//                            {
//                                inventoryplayer.addItemStackToInventory(itemstack3);
//                                slot2.decrStackSize(itemstack5.stackSize);
//                                slot2.putStack((ItemStack)null);
//                                slot2.onPickupFromSlot(p_75144_4_, itemstack5);
//                            }
//                        }
//                        else
//                        {
//                            slot2.decrStackSize(itemstack5.stackSize);
//                            slot2.putStack(itemstack3);
//                            slot2.onPickupFromSlot(p_75144_4_, itemstack5);
//                        }
//                    }
//                    else if (!slot2.getHasStack() && itemstack3 != null && slot2.isItemValid(itemstack3))
//                    {
//                        inventoryplayer.setInventorySlotContents(p_75144_2_, (ItemStack)null);
//                        slot2.putStack(itemstack3);
//                    }
//                }
//            }
//            else if (p_75144_3_ == 3 && p_75144_4_.capabilities.isCreativeMode && inventoryplayer.getItemStack() == null && p_75144_1_ >= 0)
//            {
//                slot2 = (Slot)this.inventorySlots.get(p_75144_1_);
//
//                if (slot2 != null && slot2.getHasStack())
//                {
//                    itemstack3 = slot2.getStack().copy();
//                    itemstack3.stackSize = itemstack3.getMaxStackSize();
//                    inventoryplayer.setItemStack(itemstack3);
//                }
//            }
//            else if (p_75144_3_ == 4 && inventoryplayer.getItemStack() == null && p_75144_1_ >= 0)
//            {
//                slot2 = (Slot)this.inventorySlots.get(p_75144_1_);
//
//                if (slot2 != null && slot2.getHasStack() && slot2.canTakeStack(p_75144_4_))
//                {
//                    itemstack3 = slot2.decrStackSize(p_75144_2_ == 0 ? 1 : slot2.getStack().stackSize);
//                    slot2.onPickupFromSlot(p_75144_4_, itemstack3);
//                    p_75144_4_.dropPlayerItemWithRandomChoice(itemstack3, true);
//                }
//            }
//            else if (p_75144_3_ == 6 && p_75144_1_ >= 0)
//            {
//                slot2 = (Slot)this.inventorySlots.get(p_75144_1_);
//                itemstack3 = inventoryplayer.getItemStack();
//
//                if (itemstack3 != null && (slot2 == null || !slot2.getHasStack() || !slot2.canTakeStack(p_75144_4_)))
//                {
//                    i1 = p_75144_2_ == 0 ? 0 : this.inventorySlots.size() - 1;
//                    l1 = p_75144_2_ == 0 ? 1 : -1;
//
//                    for (int i2 = 0; i2 < 2; ++i2)
//                    {
//                        for (int j2 = i1; j2 >= 0 && j2 < this.inventorySlots.size() && itemstack3.stackSize < itemstack3.getMaxStackSize(); j2 += l1)
//                        {
//                            Slot slot3 = (Slot)this.inventorySlots.get(j2);
//
//                            if (slot3.getHasStack() && func_94527_a(slot3, itemstack3, true) && slot3.canTakeStack(p_75144_4_) && this.func_94530_a(itemstack3, slot3) && (i2 != 0 || slot3.getStack().stackSize != slot3.getStack().getMaxStackSize()))
//                            {
//                                int k1 = Math.min(itemstack3.getMaxStackSize() - itemstack3.stackSize, slot3.getStack().stackSize);
//                                ItemStack itemstack2 = slot3.decrStackSize(k1);
//                                itemstack3.stackSize += k1;
//
//                                if (itemstack2.stackSize <= 0)
//                                {
//                                    slot3.putStack((ItemStack)null);
//                                }
//
//                                slot3.onPickupFromSlot(p_75144_4_, itemstack2);
//                            }
//                        }
//                    }
//                }
//
//                this.detectAndSendChanges();
//            }
//        }
//
//        return itemstack;
    	return null;
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    public ItemStack transferStackInSlot(EntityPlayer p_82846_1_, int p_82846_2_)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(p_82846_2_);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (p_82846_2_ == 0)
            {
                if (!this.mergeItemStack(itemstack1, 9, 45, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (p_82846_2_ >= 1 && p_82846_2_ < 5)
            {
                if (!this.mergeItemStack(itemstack1, 9, 45, false))
                {
                    return null;
                }
            }
            else if (p_82846_2_ >= 5 && p_82846_2_ < 9)
            {
                if (!this.mergeItemStack(itemstack1, 9, 45, false))
                {
                    return null;
                }
            }
            else if (itemstack.getItem() instanceof ItemArmor && !((Slot)this.inventorySlots.get(5 + ((ItemArmor)itemstack.getItem()).armorType)).getHasStack())
            {
                int j = 5 + ((ItemArmor)itemstack.getItem()).armorType;

                if (!this.mergeItemStack(itemstack1, j, j + 1, false))
                {
                    return null;
                }
            }
            else if (p_82846_2_ >= 9 && p_82846_2_ < 36)
            {
                if (!this.mergeItemStack(itemstack1, 36, 45, false))
                {
                    return null;
                }
            }
            else if (p_82846_2_ >= 36 && p_82846_2_ < 45)
            {
                if (!this.mergeItemStack(itemstack1, 9, 36, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 9, 45, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(p_82846_1_, itemstack1);
        }

        return itemstack;
    }

    public boolean func_94530_a(ItemStack p_94530_1_, Slot p_94530_2_)
    {
        return p_94530_2_.inventory != this.craftResult && super.func_94530_a(p_94530_1_, p_94530_2_);
    }

}
