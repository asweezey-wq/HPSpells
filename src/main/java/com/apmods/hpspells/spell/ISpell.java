package com.apmods.hpspells.spell;

import com.apmods.hpspells.entity.EntitySpell;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;

public interface ISpell {
		
	public int getSpellIndex();
	
	public String getName();
	
	/**
	 * Enacts this spell's effect when it hits a block
	 */
	public void doBlockEffect(Block blockHit, EntityLivingBase shootingEntity, EntitySpell spell, MovingObjectPosition mop);
	
	/**
	 * Enacts this spell's effect when it hits an entity
	 */
	public void doEntityEffect(EntityLivingBase entityHit, EntityLivingBase shootingEntity, EntitySpell spell);
	
	/**
	 * Enacts this spell's effect on the caster when cast
	 * @return False if the spell should shoot a projectile
	 */
	public boolean doEffectOnCaster(EntityPlayer player, double spellMultiplier);
	
	public boolean isComplicated();
	
	public EnumSpellType getSpellType();
	
	/**
	 * @return The color of the spell (used for rendering)
	 */
	public int getColor();
	
}
