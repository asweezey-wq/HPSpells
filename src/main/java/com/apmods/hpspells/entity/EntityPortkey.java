package com.apmods.hpspells.entity;

import java.util.Random;

import com.apmods.hpspells.block.BlockManager;
import com.apmods.hpspells.block.BlockPortkey;
import com.apmods.hpspells.block.TileEntityPortkey;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class EntityPortkey extends EntityThrowable implements IProjectile{

   private int arrowShake;
//   public List<Block> difBlocks = new ArrayList<Block>();
   Entity shootingEntity;
   public int x;
   public int y;
   public int z;
   public EntityPortkey(World par1World) {
       super(par1World);
       this.setThrowableHeading(motionX, motionY, motionZ, 4.5f, 1F);
   }
   public EntityPortkey(World par1World, EntityLivingBase player, int x, int y, int z) {
       super(par1World, player);
       this.setCoord(1, x);
       this.setCoord(2, y);
       this.setCoord(3, z);
       this.shootingEntity = player;
       this.setThrowableHeading(motionX, motionY, motionZ, 4.5f, 1F);
   }
   public EntityPortkey(World par1World, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase, float par4, float par5)
   {
       super(par1World);
       this.renderDistanceWeight = 10.0D;
       this.shootingEntity = par2EntityLivingBase;

       this.posY = par2EntityLivingBase.posY + (double)par2EntityLivingBase.getEyeHeight() - 0.10000000149011612D;
       double d0 = par3EntityLivingBase.posX - par2EntityLivingBase.posX;
       double d1 = par3EntityLivingBase.boundingBox.minY + (double)(par3EntityLivingBase.height / 3.0F) - this.posY;
       double d2 = par3EntityLivingBase.posZ - par2EntityLivingBase.posZ;
       double d3 = (double)MathHelper.sqrt_double(d0 * d0 + d2 * d2);

       if (d3 >= 1.0E-7D)
       {
           float f2 = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
           float f3 = (float)(-(Math.atan2(d1, d3) * 180.0D / Math.PI));
           double d4 = d0 / d3;
           double d5 = d2 / d3;
           this.setLocationAndAngles(par2EntityLivingBase.posX + d4, this.posY, par2EntityLivingBase.posZ + d5, f2, f3);
           this.yOffset = 0.0F;
           float f4 = (float)d3 * 0.2F;
           this.setThrowableHeading(d0, d1 + (double)f4, d2, par4, par5);
       }
   }

   @Override
   protected void entityInit() {
	   this.dataWatcher.addObject(20, Integer.valueOf(0));
	   this.dataWatcher.addObject(21, Integer.valueOf(0));
	   this.dataWatcher.addObject(22, Integer.valueOf(0));
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound nbt) {
	   super.readEntityFromNBT(nbt);
	   this.setCoord(1, nbt.getInteger("tx"));
	   this.setCoord(2, nbt.getInteger("ty"));
	   this.setCoord(3, nbt.getInteger("tx"));
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound nbt) {
	   super.writeEntityToNBT(nbt);
	   nbt.setFloat("tx", this.getCoord(1));
	   nbt.setFloat("ty", this.getCoord(2));
	   nbt.setFloat("tz", this.getCoord(3));
   }

   @Override
   protected void onImpact(MovingObjectPosition mop) {
	   Block block = this.worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ);
	   Material mat = this.worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ).getMaterial();
	   BlockPortkey portkey = (BlockPortkey) BlockManager.portkey;
	   this.worldObj.setBlock(mop.blockX, mop.blockY + 1, mop.blockZ, portkey);
	   TileEntityPortkey p = (TileEntityPortkey) worldObj.getTileEntity(mop.blockX, mop.blockY + 1, mop.blockZ);
	   p.setPortkeyCoords(worldObj, this.getCoord(1), this.getCoord(2) - 1, this.getCoord(3));
	   this.setDead();
   }
	   
   public int func_150012_g(IBlockAccess p_150012_1_, int p_150012_2_, int p_150012_3_, int p_150012_4_)
   {
       int l = p_150012_1_.getBlockMetadata(p_150012_2_, p_150012_3_, p_150012_4_);
       boolean flag = (l & 8) != 0;
       int i1;
       int j1;

       if (flag)
       {
           i1 = p_150012_1_.getBlockMetadata(p_150012_2_, p_150012_3_ - 1, p_150012_4_);
           j1 = l;
       }
       else
       {
           i1 = l;
           j1 = p_150012_1_.getBlockMetadata(p_150012_2_, p_150012_3_ + 1, p_150012_4_);
       }

       boolean flag1 = (j1 & 1) != 0;
       return i1 & 7 | (flag ? 8 : 0) | (flag1 ? 16 : 0);
   }
   
   @Override
   public float getGravityVelocity(){
	   return 0;
   }
   @SideOnly(Side.CLIENT)
   public int getBrightnessForRender(float par1)
   {
       return 15728880;
   }

   /**
    * Gets how bright this entity is.
    */
   public float getBrightness(float par1)
   {
       return 1.0F;
   }
	public void onUpdate(){
		super.onUpdate();
//		if(this.getSpell() == SpellLib.LUMOS){
//			this.addLight();
//			if (this.isDead) {
//				this.worldObj.updateLightByType(EnumSkyBlock.Block,
//						(int) this.posX, (int) this.posY, (int) this.posZ);
//			}
//		}
		Random rand = new Random();
		if(rand.nextInt(2) == 1){
		this.worldObj.spawnParticle("enchantmenttable", this.posX + rand.nextFloat()- 0.6f, this.posY , this.posZ + rand.nextFloat() - 0.6f, rand.nextFloat() - 0.5f, 0, rand.nextFloat() - 0.5f);
		}
		else{
			this.worldObj.spawnParticle("enchantmenttable", this.posX - rand.nextFloat() + 0.6f, this.posY, this.posZ - rand.nextFloat() + 0.6f, rand.nextFloat() - 0.5f, 0, rand.nextFloat() - 0.5f);
		}
	}
	
	public void onEntityUpdate(){
		super.onEntityUpdate();
	}
	public int getCoord(int i){
		return this.dataWatcher.getWatchableObjectInt(19 + i);
	}
	private void setCoord(int i, int coord){
		this.dataWatcher.updateObject(19 + i, Integer.valueOf(coord));
	}
	public void setShootingEntity(EntityPlayer entity){
		this.shootingEntity = entity;
	}
	
	

}