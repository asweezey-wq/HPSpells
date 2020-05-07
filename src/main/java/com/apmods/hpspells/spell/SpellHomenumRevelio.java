package com.apmods.hpspells.spell;

import java.util.List;

import com.apmods.hpspells.entity.EntitySpell;
import com.apmods.hpspells.lib.SpellLib;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;

public class SpellHomenumRevelio extends SpellBase implements ISpell{

	@Override
	public int getSpellIndex() {
		return 42;
	}

	@Override
	public String getName() {
		return "Homenum Revelio";
	}

	@Override
	public void doBlockEffect(Block blockHit, EntityLivingBase shootingEntity, EntitySpell spell, MovingObjectPosition mop) {
	}

	@Override
	public void doEntityEffect(EntityLivingBase entityHit, EntityLivingBase shootingEntity, EntitySpell spell) {
	}

	@Override
	public boolean doEffectOnCaster(EntityPlayer player, double spellMultiplier) {
		int distance = (int) (64 * spellMultiplier);
		AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(player.posX - distance, player.posY - distance, player.posZ - distance, player.posX + distance, player.posY + distance, player.posZ + distance);
		List<EntityPlayer> entities = player.worldObj.getEntitiesWithinAABB(EntityPlayer.class, aabb);
		entities.remove(player);
		if(entities.isEmpty()){
			player.addChatComponentMessage(new ChatComponentText("No Players Detected"));
			return true;
		}
		for(EntityPlayer p : entities){
			if(p != player){
				String s = p.getDisplayName();
				int i = (int) Math.sqrt(player.getDistanceSqToEntity(p));
				double east = p.posX - player.posX;
				double south = p.posZ - player.posZ;
				boolean shouldBeTwoDirs = false;
				double greaterNum = Math.max(Math.abs(east), Math.abs(south));
				double lesserNum = Math.min(Math.abs(east), Math.abs(south));
				if(lesserNum/greaterNum >= 0.5){
					shouldBeTwoDirs = true;
				}
				String s1 = "";
				String s2 = "";
				if(greaterNum == Math.abs(east)){
					if(east >= 0){
						s1 = "East";
					}else{
						s1 = "West";
					}
				}else{
					if(south >= 0){
						s1 = "South";
					}else{
						s1 = "North";
					}
				}
				if(shouldBeTwoDirs){
					if(lesserNum == Math.abs(east)){
						if(east >= 0){
							s2 = " East";
						}else{
							s2 = " West";
						}
					}else{
						if(south >= 0){
							s2 = "South";
						}else{
							s2 = "North";
						}
					}
				}
				String s3 = (s2.equals("North") || s2.equals("South")) ? s2 + " " + s1 : s1 + s2;
				String s4 = p.posY - player.posY < 0 ? "Down" : (p.posY - player.posY > 0 ? "Up" : "");
				String s5 = !s4.equals("") ? " and " + (int)Math.abs(p.posY - player.posY) + " Blocks " + s4 : "";
				player.addChatComponentMessage(new ChatComponentText("Revealed " + EnumChatFormatting.DARK_PURPLE + s + EnumChatFormatting.RESET + " " + i + " Blocks " + s3 + s5));
			}
		}
		player.addChatComponentMessage(new ChatComponentText("End Homenum Revelio"));
		return true;
	}

	@Override
	public boolean isComplicated() {
		return true;
	}


	@Override
	public int getColor() {
		return 0;
	}

	@Override
	public EnumSpellType getSpellType() {
		return EnumSpellType.DADA;
	}

}
