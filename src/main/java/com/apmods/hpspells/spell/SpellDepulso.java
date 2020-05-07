package com.apmods.hpspells.spell;

import com.apmods.hpspells.entity.EntitySpell;
import com.apmods.hpspells.lib.SpellLib;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;

public class SpellDepulso extends SpellBase implements ISpell{

	@Override
	public int getSpellIndex() {
		return 38;
	}

	@Override
	public String getName() {
		return "Depulso";
	}

	@Override
	public void doBlockEffect(Block blockHit, EntityLivingBase shootingEntity, EntitySpell spell, MovingObjectPosition mop) {
	}

	@Override
	public void doEntityEffect(EntityLivingBase entityHit, EntityLivingBase shootingEntity, EntitySpell spell) {
	}

	@Override
	public boolean doEffectOnCaster(EntityPlayer player, double spellMultiplier) {
		int i;

        for (i = 0; i < player.inventory.mainInventory.length; ++i)
        {
            if (player.inventory.mainInventory[i] != null)
            {
            	if(!player.inventory.mainInventory[i].equals(player.getHeldItem())){
            		player.inventory.player.func_146097_a(player.inventory.mainInventory[i], true, false);
                	player.inventory.mainInventory[i] = null;
            	}
            }
        }

        for (i = 0; i < player.inventory.armorInventory.length; ++i)
        {
            if (player.inventory.armorInventory[i] != null)
            {
                player.inventory.player.func_146097_a(player.inventory.armorInventory[i], true, false);
                player.inventory.armorInventory[i] = null;
            }
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
