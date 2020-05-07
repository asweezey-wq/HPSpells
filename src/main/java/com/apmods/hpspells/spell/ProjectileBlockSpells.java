package com.apmods.hpspells.spell;
//package com.apmods.magicraft.spell;
//
//import java.util.Random;
//
//import com.apmods.magicraft.entity.EntitySpell;
//import com.apmods.magicraft.extendedplayer.MagiSkills;
//import com.apmods.magicraft.lib.SpellLib;
//
//import net.minecraft.block.Block;
//import net.minecraft.block.BlockDoor;
//import net.minecraft.block.BlockTrapDoor;
//import net.minecraft.block.IGrowable;
//import net.minecraft.block.material.Material;
//import net.minecraft.entity.EntityLivingBase;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.init.Blocks;
//import net.minecraft.util.AxisAlignedBB;
//import net.minecraft.util.MovingObjectPosition;
//import net.minecraftforge.event.entity.player.BonemealEvent;
//
//public class ProjectileBlockSpells {
//	private EntitySpell spell;
//	private Block blockHit;
//	private MovingObjectPosition mop;
//	private EntityLivingBase shootingEntity;
//	private MagiSkills ext;
//
//	public ProjectileBlockSpells(Block blockHit, EntityLivingBase shootingEntity, EntitySpell spell, MovingObjectPosition mop) {
//		this.mop = mop;
//		this.blockHit = blockHit;
//		this.shootingEntity = shootingEntity;
//		initExt();
//		this.spell = spell;
//	}
//
//	private void initExt() {
//		if (shootingEntity != null && shootingEntity instanceof EntityPlayer) {
//			ext = MagiSkills.get((EntityPlayer) shootingEntity);
//		}
//	}
//
//	private int getSkillLevel(int spellOf) {
//		if (ext != null) {
//			return ext.getCurrentSkillLevel(spellOf);
//		} else {
//			return 2;
//		}
//	}
//
//	public void duro() {
//		shootingEntity.worldObj.setBlock(mop.blockX, mop.blockY, mop.blockZ, Blocks.stone);
//	}
//
//	public void expulso() {
//		AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(mop.blockX - 4, mop.blockY, mop.blockZ - 4, mop.blockX + 4, mop.blockY + 2, mop.blockZ + 4);
//		for (Object o : shootingEntity.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, aabb)) {
//			if (o instanceof EntityLivingBase) {
//				EntityLivingBase entity = (EntityLivingBase) o;
//				double x = entity.posX - mop.blockX;
//				double y = 0.9;
//				double z = entity.posZ - mop.blockZ;
//				entity.addVelocity(x / 3, 0.7, z / 3);
//			}
//		}
//	}
//
//	public void confringo() {
//		shootingEntity.worldObj.newExplosion(spell, spell.posX, spell.posY, spell.posZ, 3.0f, true, true);
//	}
//
//	public void incendio() {
//		int x = getCoordsWithOffset(mop.sideHit)[0];
//		int y = getCoordsWithOffset(mop.sideHit)[1];
//		int z = getCoordsWithOffset(mop.sideHit)[2];
//		System.out.println("" + x + " " + y + " " + z);
//		if (shootingEntity.worldObj.isAirBlock(x, y, z)) {
//			shootingEntity.worldObj.setBlock(x, y, z, Blocks.fire);
//		}
//		Random rand = new Random();
//		for (int xOff = -1; xOff < 2; xOff++) {
//			for (int zOff = -1; zOff < 2; zOff++) {
//				if (rand.nextInt(3) == 0) {
//					if (shootingEntity.worldObj.isAirBlock(x + xOff, y, z + zOff)) {
//						shootingEntity.worldObj.setBlock(x + xOff, y, z + zOff, Blocks.fire);
//					}
//				}
//			}
//		}
//	}
//
//	public void aguamenti() {
//		if (mop.sideHit == 1) {
//			shootingEntity.worldObj.setBlock(mop.blockX, mop.blockY + 1, mop.blockZ, Blocks.flowing_water, 0, 3);
//		} else if (mop.sideHit == 0) {
//			shootingEntity.worldObj.setBlock(mop.blockX, mop.blockY - 1, mop.blockZ, Blocks.flowing_water, 0, 3);
//		} else if (mop.sideHit == 2) {
//			shootingEntity.worldObj.setBlock(mop.blockX + 1, mop.blockY, mop.blockZ, Blocks.flowing_water, 0, 3);
//		} else if (mop.sideHit == 4) {
//			shootingEntity.worldObj.setBlock(mop.blockX - 1, mop.blockY, mop.blockZ, Blocks.flowing_water, 0, 3);
//		} else if (mop.sideHit == 3) {
//			shootingEntity.worldObj.setBlock(mop.blockX, mop.blockY, mop.blockZ + 1, Blocks.flowing_water, 0, 3);
//		} else if (mop.sideHit == 5) {
//			shootingEntity.worldObj.setBlock(mop.blockX, mop.blockY, mop.blockZ - 1, Blocks.flowing_water, 0, 3);
//		}
//	}
//
//	public void defodio() {
//		if (blockHit != Blocks.bedrock) {
//			shootingEntity.worldObj.setBlock(mop.blockX, mop.blockY, mop.blockZ, Blocks.air);
//			blockHit.dropBlockAsItem(shootingEntity.worldObj, (int) spell.posX, (int) spell.posY, (int) spell.posZ, shootingEntity.worldObj.getBlockMetadata((int) spell.posX, (int) spell.posY, (int) spell.posZ), 0);
//		}
//	}
//
//	public void herbivicus() {
//		int skill = getSkillLevel(SpellLib.getSpell("herbivicus"));
//		Random rand = new Random();
//		BonemealEvent event = new BonemealEvent((EntityPlayer) spell.shootingEntity, shootingEntity.worldObj, blockHit, (int) spell.posX, (int) spell.posY - 1, (int) spell.posZ);
//		if (blockHit instanceof IGrowable) {
//			IGrowable igrowable = (IGrowable) blockHit;
//
//			if (igrowable.func_149851_a(shootingEntity.worldObj, (int) spell.posX, (int) spell.posY - 1, (int) spell.posZ, shootingEntity.worldObj.isRemote)) {
//				if (!shootingEntity.worldObj.isRemote) {
//					if (igrowable.func_149852_a(shootingEntity.worldObj, shootingEntity.worldObj.rand, (int) spell.posX, (int) spell.posY - 1, (int) spell.posZ)) {
//						for (int i = 0; i < 4 + skill; i++) {
//							igrowable.func_149853_b(shootingEntity.worldObj, shootingEntity.worldObj.rand, (int) spell.posX, (int) spell.posY - 1, (int) spell.posZ);
//						}
//					}
//
//				}
//
//			}
//		}
//	}
//
//	public void glisseo() {
//		Material mat = blockHit.getMaterial();
//		if (mat == Material.lava) {
//			shootingEntity.worldObj.setBlock(mop.blockX, mop.blockY, mop.blockZ, Blocks.obsidian);
//		}
//		if (mat == Material.water) {
//			shootingEntity.worldObj.setBlock(mop.blockX, mop.blockY, mop.blockZ, Blocks.ice);
//		}
//	}
//
//	public void alohomora() {
//		if (blockHit instanceof BlockDoor) {
//			int i1 = spell.func_150012_g(shootingEntity.worldObj, mop.blockX, mop.blockY, mop.blockZ);
//			int j1 = i1 & 7;
//			j1 ^= 4;
//			if ((i1 & 8) == 0) {
//				shootingEntity.worldObj.setBlockMetadataWithNotify(mop.blockX, mop.blockY, mop.blockZ, j1, 2);
//				shootingEntity.worldObj.markBlockRangeForRenderUpdate(mop.blockX, mop.blockY, mop.blockZ, mop.blockX, mop.blockY, mop.blockZ);
//			} else {
//				shootingEntity.worldObj.setBlockMetadataWithNotify(mop.blockX, mop.blockY - 1, mop.blockZ, j1, 2);
//				shootingEntity.worldObj.markBlockRangeForRenderUpdate(mop.blockX, mop.blockY - 1, mop.blockZ, mop.blockX, mop.blockY, mop.blockZ);
//			}
//
//			shootingEntity.worldObj.playAuxSFXAtEntity((EntityPlayer) spell.shootingEntity, 1003, mop.blockX, mop.blockY, mop.blockZ, 0);
//		} else if (blockHit instanceof BlockTrapDoor) {
//			int i1 = shootingEntity.worldObj.getBlockMetadata(mop.blockX, mop.blockY, mop.blockZ);
//			shootingEntity.worldObj.setBlockMetadataWithNotify(mop.blockX, mop.blockY, mop.blockZ, i1 ^ 4, 2);
//			shootingEntity.worldObj.playAuxSFXAtEntity((EntityPlayer) spell.shootingEntity, 1003, mop.blockX, mop.blockY, mop.blockZ, 0);
//		}
//	}
//
//	public void bombarda() {
//		int skill = getSkillLevel(SpellLib.getSpell("bombarda"));
//		spell.worldObj.createExplosion(spell, spell.posX, spell.posY, spell.posZ, (float) (4.0f + skill / 4.5), true);
//	}
//
//	public int[] getCoordsWithOffset(int sideHit) {
//		int x = mop.blockX, y = mop.blockY, z = mop.blockZ;
//		switch (sideHit) {
//		case 0:
//			y--;
//			break;
//		case 1:
//			y++;
//			break;
//		case 2:
//			z--;
//			break;
//		case 3:
//			z++;
//			break;
//		case 4:
//			x--;
//			break;
//		case 5:
//			x++;
//			break;
//		}
//		return new int[] { x, y, z };
//	}
//}
