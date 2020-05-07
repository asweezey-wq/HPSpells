package com.apmods.hpspells.spell;

import java.util.List;

import com.apmods.hpspells.entity.EntitySpell;
import com.apmods.hpspells.item.ItemManager;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;

public class SpellDisillusio extends SpellBase implements ISpell {

	@Override
	public int getSpellIndex() {
		return 46;
	}

	@Override
	public String getName() {
		return "Disillusio";
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
		List list = player.worldObj.getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getBoundingBox(player.posX - 1, player.posY, player.posZ - 1, player.posX + 1, player.posY + 1, player.posZ + 1));
		if (skill >= 3) {
			for (Object o : list) {
				if (o instanceof EntityItem) {
					EntityItem item = (EntityItem) o;
					if (item.getEntityItem() != null && item.getEntityItem().getItem() == ItemManager.cloak) {
						ItemStack cloak = item.getEntityItem();
						ItemStack invisCloak = new ItemStack(ItemManager.invisCloak, cloak.stackSize);
						invisCloak.setTagCompound(cloak.stackTagCompound);
						item.setEntityItemStack(invisCloak);
						return true;
					}
				}
			}
		}
		player.addPotionEffect(new PotionEffect(Potion.invisibility.id, (int) ((300 + skill * 50) * spellMultiplier), 0, true));
		return true;
	}

	@Override
	public boolean isComplicated() {
		return true;
	}

	

	@Override
	public EnumSpellType getSpellType() {
		return EnumSpellType.CHARM;
	}

	@Override
	public int getColor() {
		return 0x666666;
	}

}
