package com.apmods.hpspells.spell;

import com.apmods.hpspells.entity.EntitySpell;
import com.apmods.hpspells.lib.SpellLib;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;

public class SpellExpulso extends SpellBase implements ISpell{

	@Override
	public int getSpellIndex() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Expulso";
	}

	@Override
	public void doBlockEffect(Block blockHit, EntityLivingBase shootingEntity, EntitySpell spell, MovingObjectPosition mop) {
		AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(mop.blockX - 4, mop.blockY, mop.blockZ - 4, mop.blockX + 4, mop.blockY + 2, mop.blockZ + 4);
		for (Object o : shootingEntity.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, aabb)) {
			if (o instanceof EntityLivingBase) {
				EntityLivingBase entity = (EntityLivingBase) o;
				double x = entity.posX - mop.blockX;
				double y = 0.9;
				double z = entity.posZ - mop.blockZ;
				entity.addVelocity((x / 3) * spell.spellStrength, 0.7, (z / 3) * spell.spellStrength);
			}
		}
		
	}

	@Override
	public void doEntityEffect(EntityLivingBase entityHit, EntityLivingBase shootingEntity, EntitySpell spell) {
		int skill = getSkillLevel(shootingEntity, this);
		knockback = (1.4 + skill / 5) * spell.spellStrength;
		
	}

	@Override
	public boolean doEffectOnCaster(EntityPlayer player, double spellMultiplier) {
		return false;
	}

	@Override
	public boolean isComplicated() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public int getColor() {
		return 0x00D7FF;
	}

	@Override
	public EnumSpellType getSpellType() {
		return EnumSpellType.HEX;
	}

}
