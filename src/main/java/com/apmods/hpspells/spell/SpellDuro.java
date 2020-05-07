package com.apmods.hpspells.spell;

import com.apmods.hpspells.entity.EntitySpell;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.MovingObjectPosition;

public class SpellDuro extends SpellBase implements ISpell {

	@Override
	public int getSpellIndex() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Duro";
	}

	@Override
	public void doBlockEffect(Block blockHit, EntityLivingBase shootingEntity, EntitySpell spell, MovingObjectPosition mop) {
		shootingEntity.worldObj.setBlock(mop.blockX, mop.blockY, mop.blockZ, Blocks.stone);
	}

	@Override
	public void doEntityEffect(EntityLivingBase entityHit, EntityLivingBase shootingEntity, EntitySpell spell) {
		// TODO Auto-generated method stub

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
		return 0x787878;
	}

	@Override
	public EnumSpellType getSpellType() {
		return EnumSpellType.TRANSFIGURATION;
	}

}
