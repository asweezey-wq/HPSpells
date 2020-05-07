package com.apmods.hpspells.entity;

import java.util.Random;

import com.apmods.hpspells.extendedplayer.SpellSkills;
import com.apmods.hpspells.lib.SpellLib;
import com.apmods.hpspells.main.HPSpells;
import com.apmods.hpspells.spell.ISpell;
import com.apmods.hpspells.spell.SpellBase;
import com.apmods.hpspells.spell.SpellDiffindo;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class EntitySpell extends EntityThrowable implements IProjectile {

	public double spellStrength = 1;
	// public List<Block> difBlocks = new ArrayList<Block>();
	public EntityLivingBase shootingEntity;

	public EntitySpell(World par1World) {
		super(par1World);
		this.setThrowableHeading(motionX, motionY, motionZ, 4.5f, 1F);
	}

	public EntitySpell(World par1World, EntityLivingBase player, ISpell spell, double spellStrength) {
		super(par1World, player);
		this.setSpell(spell);
		float speed = 4.5f;
		this.shootingEntity = player;
		this.spellStrength = spellStrength;
		this.setThrowableHeading(motionX, motionY, motionZ, speed, 1F);
	}

	public EntitySpell(World par1World, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase, float par4, ISpell par5, double spellStrength) {
		super(par1World);
		this.renderDistanceWeight = 10.0D;
		this.shootingEntity = par2EntityLivingBase;
		this.setSpell(par5);
		this.spellStrength = spellStrength;
		this.posY = par2EntityLivingBase.posY + par2EntityLivingBase.height / 2;
		double d0 = par3EntityLivingBase.posX - par2EntityLivingBase.posX;
		double d1 = par3EntityLivingBase.boundingBox.minY + par3EntityLivingBase.height / 3.0F - this.posY;
		double d2 = par3EntityLivingBase.posZ - par2EntityLivingBase.posZ;
		double d3 = MathHelper.sqrt_double(d0 * d0 + d2 * d2);

		if (d3 >= 1.0E-7D) {
			float f2 = (float) (Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
			float f3 = (float) (-(Math.atan2(d1, d3) * 180.0D / Math.PI));
			double d4 = d0 / d3;
			double d5 = d2 / d3;
			this.setLocationAndAngles(par2EntityLivingBase.posX + d4, this.posY, par2EntityLivingBase.posZ + d5, f2, f3);
			this.yOffset = 0.0F;
			float f4 = (float) d3 * 0.2F;
			this.setThrowableHeading(d0, d1 + f4, d2, par4, 1f);
		}
	}

	@Override
	protected void entityInit() {
		// Spell
		this.dataWatcher.addObject(20, "None");

	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		this.setSpell(SpellLib.getSpell(nbt.getString("spell")));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setString("spell", this.getSpell().getName());
	}

	@Override
	protected void onImpact(MovingObjectPosition mop) {
		Block block = this.worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ);
		spellEffect(mop);
		if (mop.entityHit != null && mop.entityHit == shootingEntity.ridingEntity) {
		} else{
			this.setDead();
		}
	}

	private void spellEffect(MovingObjectPosition mop) {
		Block block = this.worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ);
		Material mat = this.worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ).getMaterial();
		if (mop.entityHit != null) {// If an entity was hit
			if (mop.entityHit instanceof EntityLivingBase) {

				EntityLivingBase entity = (EntityLivingBase) mop.entityHit;
				ISpell spell = this.getSpell();
				if (spell.getName() == "Imperio") {
					spell.doEntityEffect(entity, shootingEntity, this);
					return;
				}
				if (!worldObj.isRemote) {
					// Check to see if the player hit is casting protego
					if (entity instanceof EntityPlayer) {
						EntityPlayer playerHit = (EntityPlayer) entity;
						SpellSkills ext1 = SpellSkills.get(playerHit);
						if (ext1.hasProtego()) {
							if (spell.getName() != "Avada Kedavra") {
								worldObj.playSoundAtEntity(playerHit, HPSpells.MODID + ":protego.hit", 1, 1);
								for (int i = 0; i < 20; i++) {
									worldObj.spawnParticle("happyVillager", this.posX, this.posY, this.posZ, worldObj.rand.nextDouble() - 0.5, worldObj.rand.nextDouble() - 0.5, worldObj.rand.nextDouble() - 0.5);
								}
								return;
							}
						}
					}
					if (mop.entityHit instanceof EntityDeathEater) {
						if (((EntityDeathEater) mop.entityHit).shouldCastProtego(shootingEntity, spell)) {
							return;
						}
					}
					spell.doEntityEffect(entity, shootingEntity, this);
					entity.velocityChanged = true;
					((SpellBase) spell).doDamageAndKnockback(entity, shootingEntity, this);
				}

			} else if (this.getSpell() instanceof SpellDiffindo && mop.entityHit instanceof EntityHanging) {
				((SpellDiffindo) this.getSpell()).doEffect((EntityHanging) mop.entityHit, shootingEntity);
			}
		} else if (worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ).getMaterial() == Material.water) {// If
																											// no
																											// entity
																											// was
																											// hit
			if (this.getSpell().getName() == "Glisseo") {
				this.getSpell().doBlockEffect(block, shootingEntity, this, mop);
			}
		} else if (block != Blocks.air) {// If a block was hit
			ISpell spell = this.getSpell();
			if (!worldObj.isRemote && shootingEntity != null) {
				spell.doBlockEffect(block, shootingEntity, this, mop);
			}
		}
	}

	public int func_150012_g(IBlockAccess p_150012_1_, int p_150012_2_, int p_150012_3_, int p_150012_4_) {
		int l = p_150012_1_.getBlockMetadata(p_150012_2_, p_150012_3_, p_150012_4_);
		boolean flag = (l & 8) != 0;
		int i1;
		int j1;

		if (flag) {
			i1 = p_150012_1_.getBlockMetadata(p_150012_2_, p_150012_3_ - 1, p_150012_4_);
			j1 = l;
		} else {
			i1 = l;
			j1 = p_150012_1_.getBlockMetadata(p_150012_2_, p_150012_3_ + 1, p_150012_4_);
		}

		boolean flag1 = (j1 & 1) != 0;
		return i1 & 7 | (flag ? 8 : 0) | (flag1 ? 16 : 0);
	}

	@Override
	public float getGravityVelocity() {
		return 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBrightnessForRender(float par1) {
		return 15728880;
	}

	/**
	 * Gets how bright this entity is.
	 */
	@Override
	public float getBrightness(float par1) {
		return 1.0F;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		Random rand = new Random();
		for (int i = 0; i < 5; i++) {
			this.worldObj.spawnParticle("enchantmenttable", this.posX + (rand.nextFloat() - 0.5f)/2, this.posY + (rand.nextFloat() - 0.5f)/2, this.posZ + (rand.nextFloat() - 0.5f)/2, rand.nextFloat() - 0.5f, 0, rand.nextFloat() - 0.5f);
		}
	}

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
	}

	public ISpell getSpell() {
		return SpellLib.getSpell(this.dataWatcher.getWatchableObjectString(20));
	}

	private void setSpell(ISpell spell) {
		this.dataWatcher.updateObject(20, spell.getName());
	}

	public void setShootingEntity(EntityPlayer entity) {
		this.shootingEntity = entity;
	}

}
