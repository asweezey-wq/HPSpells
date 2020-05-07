package com.apmods.hpspells.spell;

import java.util.Random;

import com.apmods.hpspells.entity.EntitySpell;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;

public class SpellOrchideous extends SpellBase implements ISpell {

	@Override
	public int getSpellIndex() {
		return 20;
	}

	@Override
	public String getName() {
		return "Orchideous";
	}

	@Override
	public void doBlockEffect(Block blockHit, EntityLivingBase shootingEntity, EntitySpell spell, MovingObjectPosition mop) {
	}

	@Override
	public void doEntityEffect(EntityLivingBase entityHit, EntityLivingBase shootingEntity, EntitySpell spell) {
	}

	@Override
	public boolean doEffectOnCaster(EntityPlayer player, double spellMultiplier) {
		Random rand = new Random();
		for (int i = 0; i < MathHelper.clamp_int(getSkillLevel(player, this), 1, 8); i++) {
			if(rand.nextInt(10) > 0){
				player.inventory.addItemStackToInventory(new ItemStack(Blocks.red_flower, 1, rand.nextInt(9)));
			}else{
				player.inventory.addItemStackToInventory(new ItemStack(Blocks.yellow_flower));
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
		return 0xFF00EA;
	}

	@Override
	public EnumSpellType getSpellType() {
		return EnumSpellType.TRANSFIGURATION;
	}

}
