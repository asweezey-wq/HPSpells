package com.apmods.hpspells.spell;

import com.apmods.hpspells.entity.EntityLumosMaxima;
import com.apmods.hpspells.entity.EntitySpell;
import com.apmods.hpspells.lib.SpellLib;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;

public class SpellLumosMaxima extends SpellBase implements ISpell{

	@Override
	public int getSpellIndex() {
		return 44;
	}

	@Override
	public String getName() {
		return "Lumos Maxima";
	}

	@Override
	public void doBlockEffect(Block blockHit, EntityLivingBase shootingEntity, EntitySpell spell, MovingObjectPosition mop) {
	}

	@Override
	public void doEntityEffect(EntityLivingBase entityHit, EntityLivingBase shootingEntity, EntitySpell spell) {
	}

	@Override
	public boolean doEffectOnCaster(EntityPlayer player, double spellMultiplier) {
		EntityLumosMaxima lumos = new EntityLumosMaxima(player.worldObj);
		lumos.setPosition(player.posX, player.posY + 1, player.posZ);
		if(!player.worldObj.isRemote){
			player.worldObj.spawnEntityInWorld(lumos);
		}
		return true;
	}

	@Override
	public boolean isComplicated() {
		return true;
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
