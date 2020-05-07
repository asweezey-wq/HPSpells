package com.apmods.hpspells.spell;

import com.apmods.hpspells.entity.EntitySpell;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemDoor;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class SpellColloportus extends SpellBase implements ISpell{

	@Override
	public String getName() {
		return "Colloportus";
	}

	@Override
	public void doBlockEffect(Block blockHit, EntityLivingBase shootingEntity, EntitySpell spell, MovingObjectPosition mop) {
		if(blockHit == Blocks.wooden_door){
			World world = shootingEntity.worldObj;
			ItemDoor.placeDoorBlock(world, mop.blockX, mop.blockY, mop.blockZ, world.getBlockMetadata(mop.blockX, mop.blockY, mop.blockZ), Blocks.iron_door);
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
		return EnumSpellType.TRANSFIGURATION;
	}

	@Override
	public int getColor() {
		return 0;
	}

}
