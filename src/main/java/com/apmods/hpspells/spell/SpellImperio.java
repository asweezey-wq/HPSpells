package com.apmods.hpspells.spell;

import com.apmods.hpspells.entity.EntitySpell;
import com.apmods.hpspells.lib.SpellLib;
import com.apmods.hpspells.main.HPSpells;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;

public class SpellImperio extends SpellBase implements ISpell {

	@Override
	public int getSpellIndex() {
		return 32;
	}

	@Override
	public String getName() {
		return "Imperio";
	}

	@Override
	public void doBlockEffect(Block blockHit, EntityLivingBase shootingEntity, EntitySpell spell, MovingObjectPosition mop) {
	}

	@Override
	public void doEntityEffect(EntityLivingBase entityHit, EntityLivingBase shootingEntity, EntitySpell spell) {
		if (entityHit instanceof EntityPlayer && shootingEntity instanceof EntityPlayer) {
			EntityPlayer playerHit = (EntityPlayer) entityHit;
			FMLNetworkHandler.openGui((EntityPlayer) shootingEntity, HPSpells.instance, 11, spell.worldObj, playerHit.getEntityId(), 0, 0);
		}
	}

	@Override
	public boolean doEffectOnCaster(EntityPlayer player, double spellMultiplier) {
		return false;
	}

	@Override
	public boolean isComplicated() {
		return true;
	}

	@Override
	public int getColor() {
		return 0xB7A800;
	}

	@Override
	public EnumSpellType getSpellType() {
		return EnumSpellType.DARK;
	}

}
