package com.apmods.hpspells.handler;

import com.apmods.hpspells.block.BlockManager;
import com.apmods.hpspells.extendedplayer.SpellSkills;
import com.apmods.hpspells.item.ItemWand;
import com.apmods.hpspells.lib.SpellLib;
import com.apmods.hpspells.main.HPSpells;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public class LumosHandler {
	int prevposX = 0;
	int prevposY = 0;
	int prevposZ = 0;

	@SubscribeEvent
	public void onTick(TickEvent.PlayerTickEvent event) {
		if(event.phase == TickEvent.Phase.START && !event.player.worldObj.isRemote){
			if (event.player.inventory.getCurrentItem() != null && event.player.inventory.getCurrentItem().getItem() instanceof ItemWand) {
				ItemStack wand = event.player.inventory.getCurrentItem();
				if (wand.stackTagCompound != null && wand.stackTagCompound.getBoolean("lumos")) {
					int blockX = MathHelper.floor_double(event.player.posX);
	                int blockY = MathHelper.floor_double(event.player.posY-0.2D - 
	                      event.player.getYOffset());
	                int blockZ = MathHelper.floor_double(event.player.posZ);
	                Vec3 lookVec = event.player.getLookVec();
	                // place light at head level
	                Block block = event.player.worldObj.getBlock(blockX, blockY + 1, blockZ);
	                if (block == Blocks.air)
	                {
	                    event.player.worldObj.setBlock(blockX, blockY + 1, blockZ, BlockManager.movingLightSource);
	                }
	                else if (event.player.worldObj.getBlock((int)(blockX + lookVec.xCoord), (int)(blockY + 1 + lookVec.yCoord), (int)(blockZ + lookVec.zCoord)) == Blocks.air)
	                {
	                    event.player.worldObj.setBlock((int)(blockX + lookVec.xCoord), (int)(blockY + 1 + lookVec.yCoord), (int)(blockZ + lookVec.zCoord), BlockManager.movingLightSource);
	                }
				}
			} 
		}
	}

	private void addLight(World world, EntityPlayer player) {
		SpellSkills ext = SpellSkills.get(player);
		world.setLightValue(EnumSkyBlock.Block, (int) player.posX, (int) player.posY, (int) player.posZ, 9 + ext.getCurrentSkillLevel(SpellLib.getSpell("lumos")));
		prevposX = (int) player.posX;
		prevposY = (int) player.posY;
		prevposZ = (int) player.posZ;
		world.markBlockRangeForRenderUpdate((int) player.posX, (int) player.posY, (int) player.posX, 12, 12, 12);
		world.markBlockForUpdate((int) player.posX, (int) player.posY, (int) player.posZ);

		world.updateLightByType(EnumSkyBlock.Block, (int) player.posX, (int) player.posY - 1, (int) player.posZ);
		world.updateLightByType(EnumSkyBlock.Block, (int) player.posX + 1, (int) player.posY - 1, (int) player.posZ + 1);
		world.updateLightByType(EnumSkyBlock.Block, (int) player.posX, (int) player.posY - 1, (int) player.posZ + 1);
		world.updateLightByType(EnumSkyBlock.Block, (int) player.posX - 1, (int) player.posY - 1, (int) player.posZ + 1);
		world.updateLightByType(EnumSkyBlock.Block, (int) player.posX + 1, (int) player.posY - 1, (int) player.posZ);
		world.updateLightByType(EnumSkyBlock.Block, (int) player.posX - 1, (int) player.posY - 1, (int) player.posZ);
		world.updateLightByType(EnumSkyBlock.Block, (int) player.posX + 1, (int) player.posY - 1, (int) player.posZ - 1);
		world.updateLightByType(EnumSkyBlock.Block, (int) player.posX, (int) player.posY - 1, (int) player.posZ - 1);
		world.updateLightByType(EnumSkyBlock.Block, (int) player.posX - 1, (int) player.posY - 1, (int) player.posZ - 1);

		world.updateLightByType(EnumSkyBlock.Block, (int) player.posX + 1, (int) player.posY + 1, (int) player.posZ + 1);
		world.updateLightByType(EnumSkyBlock.Block, (int) player.posX, (int) player.posY + 1, (int) player.posZ + 1);
		world.updateLightByType(EnumSkyBlock.Block, (int) player.posX - 1, (int) player.posY + 1, (int) player.posZ + 1);
		world.updateLightByType(EnumSkyBlock.Block, (int) player.posX + 1, (int) player.posY + 1, (int) player.posZ);
		world.updateLightByType(EnumSkyBlock.Block, (int) player.posX, (int) player.posY + 1, (int) player.posZ);
		world.updateLightByType(EnumSkyBlock.Block, (int) player.posX - 1, (int) player.posY + 1, (int) player.posZ);
		world.updateLightByType(EnumSkyBlock.Block, (int) player.posX + 1, (int) player.posY + 1, (int) player.posZ - 1);
		world.updateLightByType(EnumSkyBlock.Block, (int) player.posX, (int) player.posY + 1, (int) player.posZ - 1);
		world.updateLightByType(EnumSkyBlock.Block, (int) player.posX - 1, (int) player.posY + 1, (int) player.posZ - 1);

		world.updateLightByType(EnumSkyBlock.Block, (int) player.posX + 1, (int) player.posY, (int) player.posZ + 1);
		world.updateLightByType(EnumSkyBlock.Block, (int) player.posX, (int) player.posY, (int) player.posZ + 1);
		world.updateLightByType(EnumSkyBlock.Block, (int) player.posX - 1, (int) player.posY, (int) player.posZ + 1);
		world.updateLightByType(EnumSkyBlock.Block, (int) player.posX - 1, (int) player.posY, (int) player.posZ);
		world.updateLightByType(EnumSkyBlock.Block, (int) player.posX + 1, (int) player.posY, (int) player.posZ);
		world.updateLightByType(EnumSkyBlock.Block, (int) player.posX + 1, (int) player.posY, (int) player.posZ - 1);
		world.updateLightByType(EnumSkyBlock.Block, (int) player.posX, (int) player.posY, (int) player.posZ - 1);
		world.updateLightByType(EnumSkyBlock.Block, (int) player.posX - 1, (int) player.posY, (int) player.posZ - 1);

		// }
	}

	protected void fall(float p_70069_1_) {
	}

	public static void deleteLight(World world, EntityPlayer player) {
		world.setLightValue(EnumSkyBlock.Block, (int) player.posX, (int) player.posY, (int) player.posZ, 0);
		world.markBlockRangeForRenderUpdate((int) player.posX, (int) player.posY, (int) player.posX, 12, 12, 12);
		world.markBlockForUpdate((int) player.posX, (int) player.posY, (int) player.posZ);
		// for(int i = 1; i < 6; i++){
		// for(int j = 1; j < 6; j++){
		// for(int k = 1; k < 4; k++){
		// world.updateLightByType(EnumSkyBlock.Block, (int) player.posX - 3 +
		// j, (int) player.posY - 2 + k, (int) player.posZ - 3 + i);
		// }
		// }
		world.updateLightByType(EnumSkyBlock.Block, (int) player.posX, (int) player.posY - 1, (int) player.posZ);
		world.updateLightByType(EnumSkyBlock.Block, (int) player.posX + 1, (int) player.posY - 1, (int) player.posZ + 1);
		world.updateLightByType(EnumSkyBlock.Block, (int) player.posX, (int) player.posY - 1, (int) player.posZ + 1);
		world.updateLightByType(EnumSkyBlock.Block, (int) player.posX - 1, (int) player.posY - 1, (int) player.posZ + 1);
		world.updateLightByType(EnumSkyBlock.Block, (int) player.posX + 1, (int) player.posY - 1, (int) player.posZ);
		world.updateLightByType(EnumSkyBlock.Block, (int) player.posX - 1, (int) player.posY - 1, (int) player.posZ);
		world.updateLightByType(EnumSkyBlock.Block, (int) player.posX + 1, (int) player.posY - 1, (int) player.posZ - 1);
		world.updateLightByType(EnumSkyBlock.Block, (int) player.posX, (int) player.posY - 1, (int) player.posZ - 1);
		world.updateLightByType(EnumSkyBlock.Block, (int) player.posX - 1, (int) player.posY - 1, (int) player.posZ - 1);

		world.updateLightByType(EnumSkyBlock.Block, (int) player.posX + 1, (int) player.posY + 1, (int) player.posZ + 1);
		world.updateLightByType(EnumSkyBlock.Block, (int) player.posX, (int) player.posY + 1, (int) player.posZ + 1);
		world.updateLightByType(EnumSkyBlock.Block, (int) player.posX - 1, (int) player.posY + 1, (int) player.posZ + 1);
		world.updateLightByType(EnumSkyBlock.Block, (int) player.posX + 1, (int) player.posY + 1, (int) player.posZ);
		world.updateLightByType(EnumSkyBlock.Block, (int) player.posX, (int) player.posY + 1, (int) player.posZ);
		world.updateLightByType(EnumSkyBlock.Block, (int) player.posX - 1, (int) player.posY + 1, (int) player.posZ);
		world.updateLightByType(EnumSkyBlock.Block, (int) player.posX + 1, (int) player.posY + 1, (int) player.posZ - 1);
		world.updateLightByType(EnumSkyBlock.Block, (int) player.posX, (int) player.posY + 1, (int) player.posZ - 1);
		world.updateLightByType(EnumSkyBlock.Block, (int) player.posX - 1, (int) player.posY + 1, (int) player.posZ - 1);

		world.updateLightByType(EnumSkyBlock.Block, (int) player.posX + 1, (int) player.posY, (int) player.posZ + 1);
		world.updateLightByType(EnumSkyBlock.Block, (int) player.posX, (int) player.posY, (int) player.posZ + 1);
		world.updateLightByType(EnumSkyBlock.Block, (int) player.posX - 1, (int) player.posY, (int) player.posZ + 1);
		world.updateLightByType(EnumSkyBlock.Block, (int) player.posX - 1, (int) player.posY, (int) player.posZ);
		world.updateLightByType(EnumSkyBlock.Block, (int) player.posX + 1, (int) player.posY, (int) player.posZ);
		world.updateLightByType(EnumSkyBlock.Block, (int) player.posX + 1, (int) player.posY, (int) player.posZ - 1);
		world.updateLightByType(EnumSkyBlock.Block, (int) player.posX, (int) player.posY, (int) player.posZ - 1);
		world.updateLightByType(EnumSkyBlock.Block, (int) player.posX - 1, (int) player.posY, (int) player.posZ - 1);
	}
}
