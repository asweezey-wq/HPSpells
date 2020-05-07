package com.apmods.hpspells.spell;

import com.apmods.hpspells.entity.EntitySpell;
import com.apmods.hpspells.extendedplayer.SpellSkills;
import com.apmods.hpspells.lib.SpellLib;
import com.apmods.hpspells.main.HPSpells;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;

public class SpellProtego extends SpellBase implements ISpell {

	@Override
	public int getSpellIndex() {
		return 33;
	}

	@Override
	public String getName() {
		return "Protego";
	}

	@Override
	public void doBlockEffect(Block blockHit, EntityLivingBase shootingEntity, EntitySpell spell, MovingObjectPosition mop) {
	}

	@Override
	public void doEntityEffect(EntityLivingBase entityHit, EntityLivingBase shootingEntity, EntitySpell spell) {
	}

	@Override
	public boolean doEffectOnCaster(EntityPlayer player, double spellMultiplier) {
		SpellSkills ext = SpellSkills.get(player);
		if (ext.getCastProtegoCounter() <= 0) {
			if (!ext.hasProtego()) {
				ext.resetCastProtegoCounter();
				ext.enableProtego(spellMultiplier);
				player.worldObj.playSoundAtEntity(player, HPSpells.MODID + ":protego.hit", 1f, 3f);
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
