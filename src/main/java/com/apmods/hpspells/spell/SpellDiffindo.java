package com.apmods.hpspells.spell;

import java.util.Random;

import com.apmods.hpspells.entity.EntitySpell;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;

public class SpellDiffindo extends SpellBase implements ISpell {

	@Override
	public int getSpellIndex() {
		return 13;
	}

	@Override
	public String getName() {
		return "Diffindo";
	}

	@Override
	public void doBlockEffect(Block blockHit, EntityLivingBase shootingEntity, EntitySpell spell, MovingObjectPosition mop) {
	}

	@Override
	public void doEntityEffect(EntityLivingBase entityHit, EntityLivingBase shootingEntity, EntitySpell spell) {
		Random rand = new Random();
		int i = rand.nextInt(4) + 1;
		if (entityHit.getEquipmentInSlot(i) != null) {
			spell.entityDropItem(entityHit.getEquipmentInSlot(i), 0);
			entityHit.setCurrentItemOrArmor(i, null);
		}else if(entityHit instanceof EntitySheep){
			EntitySheep sheep = (EntitySheep) entityHit;
			for(ItemStack stack : sheep.onSheared(null, entityHit.worldObj, 0, 0, 0, 0)){
				sheep.entityDropItem(stack, 0f);
			}
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

	

	public void doEffect(EntityHanging entity, EntityLivingBase shootingEntity) {
		entity.onBroken(shootingEntity);
		entity.setDead();
	}

	@Override
	public int getColor() {
		return 0xB375DF;
	}

	@Override
	public EnumSpellType getSpellType() {
		return EnumSpellType.HEX;
	}

}
