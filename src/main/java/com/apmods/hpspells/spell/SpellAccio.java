package com.apmods.hpspells.spell;

import java.util.List;

import com.apmods.hpspells.entity.EntitySpell;
import com.apmods.hpspells.lib.SpellLib;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;

public class SpellAccio extends SpellBase implements ISpell {

	@Override
	public int getSpellIndex() {
		return 23;
	}

	@Override
	public String getName() {
		return "Accio";
	}

	@Override
	public void doBlockEffect(Block blockHit, EntityLivingBase shootingEntity, EntitySpell spell, MovingObjectPosition mop) {
	}

	@Override
	public void doEntityEffect(EntityLivingBase entityHit, EntityLivingBase shootingEntity, EntitySpell spell) {
	}

	@Override
	public boolean doEffectOnCaster(EntityPlayer player, double spellMultiplier) {
		int skill = getSkillLevel(player, this);
		int reach = (int) ((9 + skill) * spellMultiplier);
		List<EntityItem> items = player.worldObj.getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getBoundingBox(player.posX - reach, player.posY, player.posZ - reach, player.posX + reach, player.posY + reach / 2, player.posZ + reach));
		for (int i = 0; i < items.size(); i++) {
			player.inventory.addItemStackToInventory(items.get(i).getEntityItem());
			items.get(i).setDead();
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
		return EnumSpellType.CHARM;
	}

}
