package com.apmods.hpspells.spell;

import com.apmods.hpspells.entity.EntitySpell;
import com.apmods.hpspells.lib.SpellLib;
import com.apmods.hpspells.network.HPNetwork;
import com.apmods.hpspells.network.PortkeyGuiPacket;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.MovingObjectPosition;

public class SpellPortus extends SpellBase implements ISpell {

	@Override
	public int getSpellIndex() {
		return 31;
	}

	@Override
	public String getName() {
		return "Portus";
	}

	@Override
	public void doBlockEffect(Block blockHit, EntityLivingBase shootingEntity, EntitySpell spell, MovingObjectPosition mop) {
	}

	@Override
	public void doEntityEffect(EntityLivingBase entityHit, EntityLivingBase shootingEntity, EntitySpell spell) {
	}

	@Override
	public boolean doEffectOnCaster(EntityPlayer player, double spellMultiplier) {
		HPNetwork.net.sendTo(new PortkeyGuiPacket.GuiMessage(), (EntityPlayerMP) player);
		return true;
	}

	@Override
	public boolean isComplicated() {
		return false;
	}

	@Override
	public int getColor() {
		return 0x0000FF;
	}

	@Override
	public EnumSpellType getSpellType() {
		return null;
	}

}
