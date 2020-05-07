package com.apmods.hpspells.spell;

import java.util.List;

import com.apmods.hpspells.entity.EntityBird;
import com.apmods.hpspells.entity.EntitySpell;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;

public class SpellOppugno extends SpellBase implements ISpell{

	@Override
	public int getSpellIndex() {
		return 42;
	}

	@Override
	public String getName() {
		return "Oppugno";
	}

	@Override
	public void doBlockEffect(Block blockHit, EntityLivingBase shootingEntity, EntitySpell spell, MovingObjectPosition mop) {
	}

	@Override
	public void doEntityEffect(EntityLivingBase entityHit, EntityLivingBase shootingEntity, EntitySpell spell) {
		AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(shootingEntity.posX - 20, shootingEntity.posY, shootingEntity.posZ - 20, shootingEntity.posX + 20, shootingEntity.posY + 20, shootingEntity.posZ + 20);
		List<EntityBird> list = shootingEntity.worldObj.getEntitiesWithinAABB(EntityBird.class, aabb);
		for(EntityBird e : list){
			
		}
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
	public int getColor() {
		return 0xB6FF00;
	}

	@Override
	public EnumSpellType getSpellType() {
		return EnumSpellType.HEX;
	}

}
