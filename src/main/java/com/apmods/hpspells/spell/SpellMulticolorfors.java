package com.apmods.hpspells.spell;

import java.util.Random;

import com.apmods.hpspells.entity.EntitySpell;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.util.MovingObjectPosition;

public class SpellMulticolorfors extends SpellBase implements ISpell {

	@Override
	public int getSpellIndex() {
		return 25;
	}

	@Override
	public String getName() {
		return "Multicolorfors";
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
		int[] colors = { 0x000000, 0xffffff, 0xfff000, 0x00ff00, 0x0000ff, 0xff0000, 0xff00ff, 0x00ffff, 0xff8800, 0x5500ff };
		for (int i = 0; i < 4; i++) {
			if (player.inventory.armorInventory[i] != null) {
				ItemArmor item = (ItemArmor) player.inventory.armorInventory[i].getItem();
				if (item.getArmorMaterial() == ArmorMaterial.CLOTH) {
					item.func_82813_b(player.inventory.armorInventory[i], colors[rand.nextInt(colors.length)]);
				}
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
		return EnumSpellType.TRANSFIGURATION;
	}

}
