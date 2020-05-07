package com.apmods.hpspells.spell;

import com.apmods.hpspells.entity.EntitySpell;
import com.apmods.hpspells.lib.SpellLib;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;

public class SpellMelofors extends SpellBase implements ISpell {

	@Override
	public int getSpellIndex() {
		return 17;
	}

	@Override
	public String getName() {
		return "Melofors";
	}

	@Override
	public void doBlockEffect(Block blockHit, EntityLivingBase shootingEntity, EntitySpell spell, MovingObjectPosition mop) {
	}

	@Override
	public void doEntityEffect(EntityLivingBase entityHit, EntityLivingBase shootingEntity, EntitySpell spell) {
		if(entityHit.getEquipmentInSlot(4) != null){
			spell.entityDropItem(entityHit.getEquipmentInSlot(4), 0);
		}
		entityHit.setCurrentItemOrArmor(4, new ItemStack(Blocks.pumpkin));
		if (entityHit instanceof EntityPlayer) {
			((EntityPlayer) entityHit).inventory.markDirty();
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
		return 0xFF9400;
	}

	@Override
	public EnumSpellType getSpellType() {
		return EnumSpellType.TRANSFIGURATION;
	}

}
