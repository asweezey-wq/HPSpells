package com.apmods.hpspells.spell;

import com.apmods.hpspells.entity.EntityDarkMark;
import com.apmods.hpspells.entity.EntitySpell;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;

public class SpellMorsmordre extends SpellBase implements ISpell {

	@Override
	public String getName() {
		return "Morsmordre";
	}

	@Override
	public void doBlockEffect(Block blockHit, EntityLivingBase shootingEntity, EntitySpell spell, MovingObjectPosition mop) {
	}

	@Override
	public void doEntityEffect(EntityLivingBase entityHit, EntityLivingBase shootingEntity, EntitySpell spell) {
	}

	@Override
	public boolean doEffectOnCaster(EntityPlayer player, double spellMultiplier) {
		if (!player.worldObj.isRemote) {
			EntityDarkMark darkMark = new EntityDarkMark(player.worldObj, player.posX, player.posY, player.posZ);
			darkMark.setVelocity(player.getLookVec().xCoord, player.getLookVec().yCoord, player.getLookVec().zCoord);
			player.worldObj.spawnEntityInWorld(darkMark);
		}
		return true;
	}

	@Override
	public boolean isComplicated() {
		return false;
	}

	@Override
	public EnumSpellType getSpellType() {
		return EnumSpellType.DARK;
	}

	@Override
	public int getColor() {
		return 0;
	}

}
