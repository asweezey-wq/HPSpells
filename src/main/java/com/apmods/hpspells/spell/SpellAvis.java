package com.apmods.hpspells.spell;

import com.apmods.hpspells.entity.EntityBird;
import com.apmods.hpspells.entity.EntitySpell;
import com.apmods.hpspells.lib.SpellLib;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class SpellAvis extends SpellBase implements ISpell {

	@Override
	public int getSpellIndex() {
		return 29;
	}

	@Override
	public String getName() {
		return "Avis";
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
		
		for(int i = 0; i < 10 * spellMultiplier; i++){
			EntityBird bat = new EntityBird(world);
			bat.setPositionAndUpdate(player.posX, player.posY, player.posZ);
			world.spawnEntityInWorld(bat);
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
		return EnumSpellType.TRANSFIGURATION;
	}

}
