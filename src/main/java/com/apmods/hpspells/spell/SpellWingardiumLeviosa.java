package com.apmods.hpspells.spell;

import com.apmods.hpspells.entity.EntitySpell;
import com.apmods.hpspells.extendedplayer.SpellSkills;
import com.apmods.hpspells.item.ItemWand;
import com.apmods.hpspells.lib.SpellLib;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;

public class SpellWingardiumLeviosa extends SpellBase implements ISpell{

	@Override
	public int getSpellIndex() {
		return 22;
	}

	@Override
	public String getName() {
		return "Wingardium Leviosa";
	}

	@Override
	public void doBlockEffect(Block blockHit, EntityLivingBase shootingEntity, EntitySpell spell, MovingObjectPosition mop) {
	}

	@Override
	public void doEntityEffect(EntityLivingBase entityHit, EntityLivingBase shootingEntity, EntitySpell spell) {
		if(shootingEntity instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) shootingEntity;
			SpellSkills ext = SpellSkills.get(player);
			if(player.getHeldItem().getItem() instanceof ItemWand){
				if(ext.getHeldEntity() == 0){
					ext.setHeldEntity(entityHit);
					player.getHeldItem().getTagCompound().setBoolean("wing lev", true);
				}else{
					ext.removeHeldEntity();
					player.getHeldItem().getTagCompound().setBoolean("wing lev", false);
				}
			}
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
		return 0xB6FF00;
	}

	@Override
	public EnumSpellType getSpellType() {
		return EnumSpellType.CHARM;
	}

}
