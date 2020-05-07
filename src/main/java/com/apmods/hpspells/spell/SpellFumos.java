package com.apmods.hpspells.spell;

import com.apmods.hpspells.entity.EntityFumos;
import com.apmods.hpspells.entity.EntitySpell;
import com.apmods.hpspells.lib.SpellLib;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class SpellFumos extends SpellBase implements ISpell{

	@Override
	public int getSpellIndex() {
		return 35;
	}

	@Override
	public String getName() {
		return "Fumos";
	}

	@Override
	public void doBlockEffect(Block blockHit, EntityLivingBase shootingEntity, EntitySpell spell, MovingObjectPosition mop) {
	}

	@Override
	public void doEntityEffect(EntityLivingBase entityHit, EntityLivingBase shootingEntity, EntitySpell spell) {
	}

	@Override
	public boolean doEffectOnCaster(EntityPlayer player, double spellMultiplier) {
		World world = player.worldObj;
		EntityFumos f = new EntityFumos(world, spellMultiplier);
		if(!world.isRemote){
			f.setPositionAndUpdate(player.posX, player.posY + 1, player.posZ);
			world.spawnEntityInWorld(f);
		}
		return true;
	}

	@Override
	public boolean isComplicated() {
		return false;
	}

	

	

	@Override
	public int getColor() {
		return 0;
	}

	@Override
	public EnumSpellType getSpellType() {
		return EnumSpellType.CHARM;
	}

}
