package com.apmods.hpspells.main;

import java.util.ArrayList;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

public class recipe implements IRecipe
{
    private ItemStack field_92102_a;
    private static final String __OBFID = "CL_00000083";

    /**
     * Used to check if a recipe matches current crafting inventory
     */
    public boolean matches(InventoryCrafting p_77569_1_, World p_77569_2_)
    {
        this.field_92102_a = null;
        int paper = 0;
        int length = 0;
        int dyes = 0;
        int firewarkStars = 0;
        int diamondsGlowstone = 0;
        int shape = 0;

        for (int k1 = 0; k1 < p_77569_1_.getSizeInventory(); ++k1)
        {
            ItemStack itemstack = p_77569_1_.getStackInSlot(k1);

            if (itemstack != null)
            {
                if (itemstack.getItem() == Items.gunpowder)
                {
                    ++length;
                }
                else if (itemstack.getItem() == Items.firework_charge)
                {
                    ++firewarkStars;
                }
                else if (itemstack.getItem() == Items.dye)
                {
                    ++dyes;
                }
                else if (itemstack.getItem() == Items.paper)
                {
                    ++paper;
                }
                else if (itemstack.getItem() == Items.glowstone_dust)
                {
                    ++diamondsGlowstone;
                }
                else if (itemstack.getItem() == Items.diamond)
                {
                    ++diamondsGlowstone;
                }
                else if (itemstack.getItem() == Items.fire_charge)
                {
                    ++shape;
                }
                else if (itemstack.getItem() == Items.feather)
                {
                    ++shape;
                }
                else if (itemstack.getItem() == Items.gold_nugget)
                {
                    ++shape;
                }
                else
                {
                    if (itemstack.getItem() != Items.skull)
                    {
                        return false;
                    }

                    ++shape;
                }
            }
        }

        diamondsGlowstone += dyes + shape;
        //IF they are making a firework
        if (length <= 3 && paper <= 1)
        {
            NBTTagCompound nbttagcompound;
            NBTTagCompound nbttagcompound1;

            if (length >= 1 && paper == 1 && diamondsGlowstone == 0)
            {
                this.field_92102_a = new ItemStack(Items.fireworks);

                nbttagcompound = new NBTTagCompound();
                if (firewarkStars > 0)
                {
                    nbttagcompound1 = new NBTTagCompound();
                    NBTTagList nbttaglist = new NBTTagList();

                    for (int k2 = 0; k2 < p_77569_1_.getSizeInventory(); ++k2)
                    {
                        ItemStack itemstack3 = p_77569_1_.getStackInSlot(k2);

                        if (itemstack3 != null && itemstack3.getItem() == Items.firework_charge && itemstack3.hasTagCompound() && itemstack3.getTagCompound().hasKey("Explosion", 10))
                        {
                            nbttaglist.appendTag(itemstack3.getTagCompound().getCompoundTag("Explosion"));
                        }
                    }

                    nbttagcompound1.setTag("Explosions", nbttaglist);
                    nbttagcompound1.setByte("Flight", (byte)length);
                    nbttagcompound.setTag("Fireworks", nbttagcompound1);
                }
                this.field_92102_a.setTagCompound(nbttagcompound); //Forge BugFix: NPE Protection

                return true;
            }
            // IF they are making a Firework Star
            else if (length == 1 && paper == 0 && firewarkStars == 0 && dyes > 0 && shape <= 1)
            {
                this.field_92102_a = new ItemStack(Items.firework_charge);
                nbttagcompound = new NBTTagCompound();
                nbttagcompound1 = new NBTTagCompound();
                byte shape2 = 0;
                ArrayList arraylist = new ArrayList();

                for (int l1 = 0; l1 < p_77569_1_.getSizeInventory(); ++l1)
                {
                    ItemStack itemstack2 = p_77569_1_.getStackInSlot(l1);

                    if (itemstack2 != null)
                    {
                        if (itemstack2.getItem() == Items.dye)
                        {
                            arraylist.add(Integer.valueOf(ItemDye.field_150922_c[itemstack2.getItemDamage()]));
                        }
                        else if (itemstack2.getItem() == Items.glowstone_dust)
                        {
                            nbttagcompound1.setBoolean("Flicker", true);
                        }
                        else if (itemstack2.getItem() == Items.diamond)
                        {
                            nbttagcompound1.setBoolean("Trail", true);
                        }
                        else if (itemstack2.getItem() == Items.fire_charge)
                        {
                            shape2 = 1;
                        }
                        else if (itemstack2.getItem() == Items.feather)
                        {
                            shape2 = 4;
                        }
                        else if (itemstack2.getItem() == Items.gold_nugget)
                        {
                            shape2 = 2;
                        }
                        else if (itemstack2.getItem() == Items.skull)
                        {
                            shape2 = 3;
                        }
                    }
                }

                int[] aint1 = new int[arraylist.size()];

                for (int l2 = 0; l2 < aint1.length; ++l2)
                {
                    aint1[l2] = ((Integer)arraylist.get(l2)).intValue();
                }

                nbttagcompound1.setIntArray("Colors", aint1);
                nbttagcompound1.setByte("Type", shape2);
                nbttagcompound.setTag("Explosion", nbttagcompound1);
                this.field_92102_a.setTagCompound(nbttagcompound);
                return true;
            }
            else if (length == 0 && paper == 0 && firewarkStars == 1 && dyes > 0 && dyes == diamondsGlowstone)
            {
                ArrayList arraylist1 = new ArrayList();

                for (int i2 = 0; i2 < p_77569_1_.getSizeInventory(); ++i2)
                {
                    ItemStack itemstack1 = p_77569_1_.getStackInSlot(i2);

                    if (itemstack1 != null)
                    {
                        if (itemstack1.getItem() == Items.dye)
                        {
                            arraylist1.add(Integer.valueOf(ItemDye.field_150922_c[itemstack1.getItemDamage()]));
                        }
                        else if (itemstack1.getItem() == Items.firework_charge)
                        {
                            this.field_92102_a = itemstack1.copy();
                            this.field_92102_a.stackSize = 1;
                        }
                    }
                }

                int[] aint = new int[arraylist1.size()];

                for (int j2 = 0; j2 < aint.length; ++j2)
                {
                    aint[j2] = ((Integer)arraylist1.get(j2)).intValue();
                }

                if (this.field_92102_a != null && this.field_92102_a.hasTagCompound())
                {
                    NBTTagCompound nbttagcompound2 = this.field_92102_a.getTagCompound().getCompoundTag("Explosion");

                    if (nbttagcompound2 == null)
                    {
                        return false;
                    }
                    else
                    {
                        nbttagcompound2.setIntArray("FadeColors", aint);
                        return true;
                    }
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    /**
     * Returns an Item that is the result of this recipe
     */
    public ItemStack getCraftingResult(InventoryCrafting p_77572_1_)
    {
        return this.field_92102_a.copy();
    }

    /**
     * Returns the size of the recipe area
     */
    public int getRecipeSize()
    {
        return 10;
    }

    public ItemStack getRecipeOutput()
    {
        return this.field_92102_a;
    }
}