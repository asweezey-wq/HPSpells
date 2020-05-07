package com.apmods.hpspells.spell;

import com.apmods.hpspells.entity.EntitySpell;
import com.apmods.hpspells.item.ItemWand;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockContainer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;

public class SpellCistemAperio extends SpellBase implements ISpell {

	@Override
	public String getName() {
		return "Cistem Aperio";
	}

	@Override
	public void doBlockEffect(Block blockHit, EntityLivingBase shootingEntity, EntitySpell spell, MovingObjectPosition mop) {
		if (shootingEntity instanceof EntityPlayer && blockHit instanceof BlockChest) {
			EntityPlayer player = (EntityPlayer) shootingEntity;
			if (player.getHeldItem() != null && player.getHeldItem().getItem() instanceof ItemWand) {
				player.displayGUIChest(((BlockChest)blockHit).func_149951_m(player.worldObj, mop.blockX, mop.blockY, mop.blockZ));
				player.getHeldItem().getTagCompound().setBoolean("cistemAperio", true);
			}
		}
	}

	@Override
	public void doEntityEffect(EntityLivingBase entityHit, EntityLivingBase shootingEntity, EntitySpell spell) {
	}

	@Override
	public boolean doEffectOnCaster(EntityPlayer player, double spellMultiplier) {
		return false;
	}

	@Override
	public boolean isComplicated() {
		return false;
	}

	@Override
	public EnumSpellType getSpellType() {
		return EnumSpellType.CHARM;
	}

	@Override
	public int getColor() {
		return 0xdddd00;
	}

}
