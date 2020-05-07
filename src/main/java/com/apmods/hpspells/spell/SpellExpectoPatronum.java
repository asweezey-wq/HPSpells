package com.apmods.hpspells.spell;

import com.apmods.hpspells.entity.EntityPatronus;
import com.apmods.hpspells.entity.EntitySpell;
import com.apmods.hpspells.extendedplayer.SpellSkills;
import com.apmods.hpspells.lib.SpellLib;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class SpellExpectoPatronum extends SpellBase implements ISpell {

	@Override
	public int getSpellIndex() {
		return 14;
	}

	@Override
	public String getName() {
		return "Expecto Patronum";
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
		SpellSkills ext = SpellSkills.get(player);
		EntityPatronus pat = new EntityPatronus(world, player, ext.getPatronusID());
		pat.setPositionAndUpdate(player.posX, player.posY + 2, player.posZ);
		world.spawnEntityInWorld(pat);
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
		return EnumSpellType.DADA;
	}

}
