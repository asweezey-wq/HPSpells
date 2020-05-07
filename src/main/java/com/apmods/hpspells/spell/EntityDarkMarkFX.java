package com.apmods.hpspells.spell;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class EntityDarkMarkFX extends EntityFX {
	private int fireworkAge;
	private final EffectRenderer theEffectRenderer;
	private NBTTagList fireworkExplosions;
	boolean twinkle;
	private int isSparking = 0;
	private static final String __OBFID = "CL_00000906";

	public EntityDarkMarkFX(World p_i1208_1_, double p_i1208_2_, double p_i1208_4_, double p_i1208_6_, double p_i1208_8_, double p_i1208_10_, double p_i1208_12_, EffectRenderer p_i1208_14_) {
		super(p_i1208_1_, p_i1208_2_, p_i1208_4_, p_i1208_6_, 0.0D, 0.0D, 0.0D);
		this.motionX = p_i1208_8_;
		this.motionY = p_i1208_10_;
		this.motionZ = p_i1208_12_;
		this.theEffectRenderer = p_i1208_14_;
		this.particleMaxAge = 8;

		// if (p_i1208_15_ != null)
		// {
		// this.fireworkExplosions = p_i1208_15_.getTagList("Explosions", 10);
		//
		// if (this.fireworkExplosions.tagCount() == 0)
		// {
		// this.fireworkExplosions = null;
		// }
		// else
		// {
		// this.particleMaxAge = this.fireworkExplosions.tagCount() * 2 - 1;
		//
		// for (int i = 0; i < this.fireworkExplosions.tagCount(); ++i)
		// {
		// NBTTagCompound nbttagcompound1 =
		// this.fireworkExplosions.getCompoundTagAt(i);
		//
		// if (nbttagcompound1.getBoolean("Flicker"))
		// {
		// this.twinkle = true;
		// this.particleMaxAge += 15;
		// break;
		// }
		// }
		// }
		// }
	}

	public void renderParticle(Tessellator p_70539_1_, float p_70539_2_, float p_70539_3_, float p_70539_4_, float p_70539_5_, float p_70539_6_, float p_70539_7_) {
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	public void onUpdate() {
		boolean flag;

		if (this.fireworkAge == 0) {
			int k = this.fireworkAge / 2;
			int[] aint = new int[] { 0x00ff00 };
			int[] aint1 = new int[] { 0x009900 };
//			this.createShaped(0.5d, new double[][]{{0, 0}, {0, 1}, {1, 1}}, aint, aint1, false, false, true);
			this.createShaped(0.5d, new double[][] { { 0, 0 }, { 0, 1 }, { -1, 2 }, { -1, 3 }, { 0, 4 }, { 1.5, 4 }, { 2.5, 3 }, { 2.5, 2 }, { 1.5, 1 }, {0, 1}, { 1.5, 1 }, { 1.5, 0 }, { 0, 0 }}, aint, aint1, false, false, true);
			this.createShaped(0.5d, new double[][] { {-0.5, 2}, {-0.5, 3}, {0.5, 3}, {0.5, 2}, {-0.5, 2}}, aint, aint1, false, false , true);
			this.createShaped(0.5d, new double[][] { {1, 2}, {1, 3}, {2, 3}, {2, 2}, {1, 2}}, aint, aint1, false, false , true);
			this.createShaped(0.5d, new double[][]{{0.5, 1.5}, {0.75, 2}, {1, 1.5}, {0.5, 1.5}}, aint,  aint1,  false, false, true);
			this.createShaped(0.5d, new double[][]{{0.2, 1}, {0.2, 0}}, aint,  aint1,  false, false, true);
			this.createShaped(0.5d, new double[][]{{0.4, 1}, {0.4, 0}}, aint,  aint1,  false, false, true);
			this.createShaped(0.5d, new double[][]{{0.6, 1}, {0.6, 0}}, aint,  aint1,  false, false, true);
			this.createShaped(0.5d, new double[][]{{0.8, 1}, {0.8, 0}}, aint,  aint1,  false, false, true);
			this.createShaped(0.5d, new double[][]{{1, 1}, {1, 0}}, aint,  aint1,  false, false, true);
			this.createShaped(0.5d, new double[][]{{1.2, 1}, {1.2, 0}}, aint,  aint1,  false, false, true);
//			this.createBallWithOffset(0.5, 0.25, -1, 1, 0, aint, aint1, false, false);
			int j = aint[0];
			float f = (float) ((j & 16711680) >> 16) / 255.0F;
			float f1 = (float) ((j & 65280) >> 8) / 255.0F;
			float f2 = (float) ((j & 255) >> 0) / 255.0F;
			EntityDarkMarkOverlayFX entityfireworkoverlayfx = new EntityDarkMarkOverlayFX(this.worldObj, this.posX, this.posY, this.posZ);
			entityfireworkoverlayfx.setRBGColorF(f, f1, f2);
			this.theEffectRenderer.addEffect(entityfireworkoverlayfx);
			this.fireworkAge = 1;
			this.isSparking = 100;
		}else if(isSparking > 0 && this.fireworkAge > 7 && this.fireworkAge % 3 == 0){
			isSparking--;
			for(int i = 0; i < 10; i++){
				this.createParticle(this.rand.nextDouble() + this.posX + 1, this.posY, this.posZ + rand.nextDouble(), (rand.nextDouble() - 0.5) * 2, 2, (this.rand.nextDouble() - 0.5) * 2, new int[]{0x000000}, new int[]{0x000000}, true, false);
			}
			if(isSparking == 0){
				this.setDead();
			}
		}
		this.fireworkAge++;

	}

	private boolean func_92037_i() {
		Minecraft minecraft = Minecraft.getMinecraft();
		return minecraft == null || minecraft.renderViewEntity == null || minecraft.renderViewEntity.getDistanceSq(this.posX, this.posY, this.posZ) >= 256.0D;
	}

	/**
	 * Creates a single particle. Args: x, y, z, x velocity, y velocity, z
	 * velocity, colours, fade colours, whether to trail, whether to twinkle
	 */
	private void createParticle(double p_92034_1_, double p_92034_3_, double p_92034_5_, double p_92034_7_, double p_92034_9_, double p_92034_11_, int[] p_92034_13_, int[] p_92034_14_, boolean p_92034_15_, boolean p_92034_16_) {
		EntityDarkMarkSparkFX entityfireworksparkfx = new EntityDarkMarkSparkFX(this.worldObj, p_92034_1_, p_92034_3_, p_92034_5_, p_92034_7_, p_92034_9_, p_92034_11_, this.theEffectRenderer);
		entityfireworksparkfx.setTrail(p_92034_15_);
		entityfireworksparkfx.setTwinkle(p_92034_16_);
		int i = this.rand.nextInt(p_92034_13_.length);
		entityfireworksparkfx.setColour(p_92034_13_[i]);

		if (p_92034_14_ != null && p_92034_14_.length > 0) {
			entityfireworksparkfx.setFadeColour(p_92034_14_[this.rand.nextInt(p_92034_14_.length)]);
		}
		entityfireworksparkfx.setParticleMaxAge(200);
		this.theEffectRenderer.addEffect(entityfireworksparkfx);
	}

	/**
	 * Creates a small ball or large ball type explosion. Args: particle speed,
	 * size, colours, fade colours, whether to trail, whether to flicker
	 */
	private void createBall(double p_92035_1_, double p_92035_3_, int[] p_92035_4_, int[] p_92035_5_, boolean p_92035_6_, boolean p_92035_7_) {
		double d1 = this.posX;
		double d2 = this.posY;
		double d3 = this.posZ;

		for (double j = -p_92035_3_; j <= p_92035_3_; j += 0.1) {
			for (double k = -p_92035_3_; k <= p_92035_3_; k += 0.1) {
				for (double l = -p_92035_3_; l <= p_92035_3_; l += 0.1) {
					double d4 = (double) k + (this.rand.nextDouble() - this.rand.nextDouble()) * 0.5D;
					double d5 = (double) j + (this.rand.nextDouble() - this.rand.nextDouble()) * 0.5D;
					double d6 = (double) l + (this.rand.nextDouble() - this.rand.nextDouble()) * 0.5D;
					double d7 = (double) MathHelper.sqrt_double(d4 * d4 + d5 * d5 + d6 * d6) / p_92035_1_ + this.rand.nextGaussian() * 0.05D;
					this.createParticle(d1, d2, d3, d4 / d7, d5 / d7, d6 / d7, p_92035_4_, p_92035_5_, p_92035_6_, p_92035_7_);

					if (j != -p_92035_3_ && j != p_92035_3_ && k != -p_92035_3_ && k != p_92035_3_) {
						l += p_92035_3_ * 2 - 1;
					}
				}
			}
		}
	}
	
	private void createBallWithOffset(double p_92035_1_, double p_92035_3_, double posX, double posY, double posZ, int[] p_92035_4_, int[] p_92035_5_, boolean p_92035_6_, boolean p_92035_7_) {
		double d1 = this.posX;
		double d2 = this.posY;
		double d3 = this.posZ;

		for (double j = -p_92035_3_; j <= p_92035_3_; j += 0.1) {
			for (double k = -p_92035_3_; k <= p_92035_3_; k += 0.1) {
				for (double l = -p_92035_3_; l <= p_92035_3_; l += 0.1) {
					double d4 = (double) k + (this.rand.nextDouble() - this.rand.nextDouble()) * 0.5D;
					double d5 = (double) j + (this.rand.nextDouble() - this.rand.nextDouble()) * 0.5D;
					double d6 = (double) l + (this.rand.nextDouble() - this.rand.nextDouble()) * 0.5D;
					double d7 = (double) MathHelper.sqrt_double(d4 * d4 + d5 * d5 + d6 * d6) / p_92035_1_ + this.rand.nextGaussian() * 0.05D;
					this.createParticle(d1, d2, d3, d4 / d7 + posX, d5 / d7 + posY, d6 / d7 + posZ, p_92035_4_, p_92035_5_, p_92035_6_, p_92035_7_);

					if (j != -p_92035_3_ && j != p_92035_3_ && k != -p_92035_3_ && k != p_92035_3_) {
						l += p_92035_3_ * 2 - 1;
					}
				}
			}
		}
	}

	/**
	 * Creates a creeper-shaped or star-shaped explosion. Args: particle speed,
	 * shape, colours, fade colours, whether to trail, whether to flicker,
	 * unknown
	 */
	private void createShaped(double p_92038_1_, double[][] p_92038_3_, int[] p_92038_4_, int[] p_92038_5_, boolean p_92038_6_, boolean p_92038_7_, boolean p_92038_8_) {
		double d1 = p_92038_3_[0][0];
		double d2 = p_92038_3_[0][1];
		d1 -= 1;
		this.createParticle(this.posX, this.posY, this.posZ, d1 * p_92038_1_, d2 * p_92038_1_, 0.0D, p_92038_4_, p_92038_5_, p_92038_6_, p_92038_7_);
		float f = (float) Math.PI;
		double d3 = p_92038_8_ ? 0.034D : 0.34D;

		for (int i = 0; i < 3; ++i) {
			double d4 = (double) f + (double) ((float) i * (float) Math.PI) * d3;
			double d5 = d1;
			double d6 = d2;

			for (int j = 1; j < p_92038_3_.length; ++j) {
				double d7 = p_92038_3_[j][0];// X Coord
				double d8 = p_92038_3_[j][1];// Y coord
				d7 -= 1;
				for (double d9 = 0.25D; d9 <= 1.0D; d9 += 0.05D) {
					double d10 = (d5 + (d7 - d5) * d9) * p_92038_1_;
					double d11 = (d6 + (d8 - d6) * d9) * p_92038_1_;
					double d12 = d10 * Math.sin(d4);
					d10 *= Math.cos(d4);
					this.createParticle(this.posX, this.posY, this.posZ, d10, d11, d12, p_92038_4_, p_92038_5_, p_92038_6_, p_92038_7_);
				}

				d5 = d7;
				d6 = d8;
			}
		}
	}

	/**
	 * Creates a burst type explosion. Args: colours, fade colours, whether to
	 * trail, whether to flicker
	 */
	private void createBurst(int[] p_92036_1_, int[] p_92036_2_, boolean p_92036_3_, boolean p_92036_4_) {
		double d0 = this.rand.nextGaussian() * 0.05D;
		double d1 = this.rand.nextGaussian() * 0.05D;

		for (int i = 0; i < 70; ++i) {
			double d2 = this.motionX * 0.5D + this.rand.nextGaussian() * 0.15D + d0;
			double d3 = this.motionZ * 0.5D + this.rand.nextGaussian() * 0.15D + d1;
			double d4 = this.motionY * 0.5D + this.rand.nextDouble() * 0.5D;
			this.createParticle(this.posX, this.posY, this.posZ, d2, d4, d3, p_92036_1_, p_92036_2_, p_92036_3_, p_92036_4_);
		}
	}

	public int getFXLayer() {
		return 0;
	}
}