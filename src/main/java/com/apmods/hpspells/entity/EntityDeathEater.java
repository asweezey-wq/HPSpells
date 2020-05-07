package com.apmods.hpspells.entity;

import java.util.Random;

import com.apmods.hpspells.item.ItemManager;
import com.apmods.hpspells.item.ItemWand;
import com.apmods.hpspells.lib.SpellLib;
import com.apmods.hpspells.main.HPSpells;
import com.apmods.hpspells.spell.ISpell;
import com.apmods.hpspells.spell.SpellDamageSource;
import com.apmods.hpspells.wand.EnumWandCore;
import com.apmods.hpspells.wand.EnumWandMaterial;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;

public class EntityDeathEater extends EntityMob implements IRangedAttackMob {

	public EntityAIBase aiArrowAttack = new EntityAIArrowAttack(this, 1.0D, 30, 60, 15.0f);
	public EntityAIBase aiAttack = new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.2d, false);

	public EntityDeathEater(World p_i1738_1_) {
		super(p_i1738_1_);
		this.setCanPickUpLoot(true);
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, aiArrowAttack);
		this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));

	}

	String[] spellscast = { "Avada Kedavra", "Expelliarmus", "Expulso", "Incendio", "Obscuro", "Petrificus Totalus", "Stupefy", "Stupefy", "Stupefy" };

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase p_82196_1_, float p_82196_2_) {
		Random rand = new Random();
		int i = rand.nextInt(spellscast.length);
		if (i == 0) {
			if (rand.nextInt(3) != 0) {
				i = rand.nextInt(spellscast.length);
			}
		}
		ISpell spell = SpellLib.getSpell(spellscast[i]);
		EntitySpell entitySpell = new EntitySpell(this.worldObj, this, p_82196_1_, 4.5f, spell, getSpellMultiplier(this.getHeldItem(), spell));
		worldObj.spawnEntityInWorld(entitySpell);
		playSoundForSpell(spell, worldObj);
		if (p_82196_1_ instanceof EntityPlayer) {
			((EntityPlayer) p_82196_1_).addChatComponentMessage(new ChatComponentText("Death Eater: " + spell.getName() + "!"));
		}

	}

	public boolean shouldCastProtego(EntityLivingBase caster, ISpell spell) {
		if (new Random().nextInt(10) == 0 && spell.getName() != "Avada Kedavra") {
			if (caster instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) caster;
				player.addChatComponentMessage(new ChatComponentText("Death Eater: Protego!"));
				worldObj.playSoundAtEntity(this, HPSpells.MODID + ":protego.hit", 1f, 1f);
				return true;
			}

		}
		return false;

	}

	public void playSoundForSpell(ISpell spell, World world) {
		String s = "";
		switch (spell.getSpellType()) {
		case CHARM:
			s = "fireworks.launch";
			break;
		case DADA:
			s = "fireworks.largeBlast";
			break;
		case DARK:
			s = "mob.enderdragon.growl";
			break;
		case HEX:
			s = "fireworks.blast";
			break;
		case TRANSFIGURATION:
			s = "fireworks.twinkle";
			break;
		default:
			break;
		}
		world.playSoundAtEntity(this, s, 1f, 1f);
	}

	protected void addRandomArmor() {
		super.addRandomArmor();
		ItemStack wand = new ItemStack(ItemManager.wand);
		wand.setTagCompound(new NBTTagCompound());
		wand.getTagCompound().setString("wood", EnumWandMaterial.getRandom().name());
		wand.getTagCompound().setString("wand core", EnumWandCore.getRandom().name());
		this.setCurrentItemOrArmor(0, wand);
	}

	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEAD;
	}

	public IEntityLivingData onSpawnWithEgg(IEntityLivingData p_110161_1_) {
		this.addRandomArmor();
		return p_110161_1_;
	}

	public boolean attackEntityAsMob(Entity p_70652_1_) {
		return super.attackEntityAsMob(p_70652_1_);
	}

	public boolean isAIEnabled() {
		return true;
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
	}

	protected void entityInit() {
		super.entityInit();
	}

	public void onLivingUpdate() {
		Random rand = new Random();
		if (rand.nextInt(240) == 0) {
			if (!worldObj.isRemote) {
				if (this.getAttackTarget() == null) {
					for (int i = 0; i < 100; i++) {
						this.worldObj.spawnParticle("largesmoke", this.posX + (rand.nextDouble() - 0.5d), this.posY + rand.nextInt(3) - 1 + (rand.nextDouble() - 0.5f), this.posZ + (rand.nextDouble() - 0.5f), rand.nextDouble() - 0.5d, 0.1d, rand.nextDouble() - 0.5d);
					}
					double prevX = this.posX;
					double prevY = this.posY;
					double prevZ = this.posZ;
					boolean flag = this.teleportRandomly();
					while (!flag) {
						flag = this.teleportRandomly();
					}
					for (int i1 = 0; i1 < 100; i1++) {
						for (int i = 0; i < 5; i++) {
							this.worldObj.spawnParticle("largesmoke", this.posX + ((prevX - this.posX) * (i1 / 100)), this.posY + ((prevY - this.posY) * (i1 / 100)), this.posZ + ((prevZ - this.posZ) * (i1 / 100)), 0, 0, 0);
						}
					}
				}
			}
			for (int i = 0; i < 100; i++) {
				this.worldObj.spawnParticle("largesmoke", this.posX + (rand.nextDouble() - 0.5d), this.posY + rand.nextInt(3) - 1 + (rand.nextDouble() - 0.5f), this.posZ + (rand.nextDouble() - 0.5f), (rand.nextDouble() - 0.5d) * 0.2d, 0.1d, (rand.nextDouble() - 0.5d) * 0.2d);
			}
		}
		super.onLivingUpdate();
	}

	protected boolean teleportRandomly() {
		double d0 = this.posX + (this.rand.nextDouble() - 0.5D) * 64.0D;
		double d1 = this.posY + (double) (this.rand.nextInt(64) - 32);
		double d2 = this.posZ + (this.rand.nextDouble() - 0.5D) * 64.0D;

		return this.teleportTo(d0, d1, d2);
	}

	protected boolean teleportTo(double p_70825_1_, double p_70825_3_, double p_70825_5_) {
		double d3 = this.posX;
		double d4 = this.posY;
		double d5 = this.posZ;
		this.posX = p_70825_1_;
		this.posY = p_70825_3_;
		this.posZ = p_70825_5_;
		boolean flag = false;
		int i = MathHelper.floor_double(this.posX);
		int j = MathHelper.floor_double(this.posY);
		int k = MathHelper.floor_double(this.posZ);

		if (this.worldObj.blockExists(i, j, k)) {
			boolean flag1 = false;

			while (!flag1 && j > 0) {
				Block block = this.worldObj.getBlock(i, j - 1, k);

				if (block.getMaterial().blocksMovement()) {
					flag1 = true;
				} else {
					--this.posY;
					--j;
				}
			}

			if (flag1) {
				this.setPosition(this.posX, this.posY, this.posZ);
				if (this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox)) {
					flag = true;
				}
			}
		}

		if (!flag) {
			this.setPosition(d3, d4, d5);
			for (int i1 = 0; i1 < 100; i1++) {
				this.worldObj.spawnParticle("largesmoke", this.posX + (rand.nextDouble() - 0.5d), this.posY + rand.nextInt(3) - 1 + (rand.nextDouble() - 0.5f), this.posZ + (rand.nextDouble() - 0.5f), (rand.nextDouble() - 0.5d) * 0.2d, 0.1d, (rand.nextDouble() - 0.5d) * 0.2d);
			}
			return false;
		} else {
			short short1 = 128;

			for (int l = 0; l < short1; ++l) {
				double d6 = (double) l / ((double) short1 - 1.0D);
				float f = (this.rand.nextFloat() - 0.5F) * 0.2F;
				float f1 = (this.rand.nextFloat() - 0.5F) * 0.2F;
				float f2 = (this.rand.nextFloat() - 0.5F) * 0.2F;
				double d7 = d3 + (this.posX - d3) * d6 + (this.rand.nextDouble() - 0.5D) * (double) this.width * 2.0D;
				double d8 = d4 + (this.posY - d4) * d6 + this.rand.nextDouble() * (double) this.height;
				double d9 = d5 + (this.posZ - d5) * d6 + (this.rand.nextDouble() - 0.5D) * (double) this.width * 2.0D;
				this.worldObj.spawnParticle("largesmoke", d3, d4, d5, (double) f, (double) f1, (double) f2);
			}

			return true;
		}
	}

	@Override
	public void setCurrentItemOrArmor(int p_70062_1_, ItemStack p_70062_2_) {
		super.setCurrentItemOrArmor(p_70062_1_, p_70062_2_);
		if (p_70062_1_ == 0 && !worldObj.isRemote) {
			this.setCombatTask();
		}
	}

	public void setCombatTask() {
		if (this.getHeldItem() == null) {
			this.tasks.removeTask(aiArrowAttack);
			this.tasks.removeTask(aiAttack);
			this.tasks.addTask(2, aiAttack);
			return;
		} else if (this.getHeldItem().getItem() instanceof ItemWand) {
			this.tasks.removeTask(aiAttack);
			this.tasks.removeTask(aiArrowAttack);
			this.tasks.addTask(2, aiArrowAttack);
			return;
		}
	}

	public double getSpellMultiplier(ItemStack stack, ISpell spell) {
		EnumWandMaterial wood = EnumWandMaterial.valueOf(stack.getTagCompound().getString("wood"));
		EnumWandCore core = EnumWandCore.valueOf(stack.getTagCompound().getString("wand core"));
		return wood.getMultiplierForSpellType(spell.getSpellType()) * core.getMultiplierForSpellType(spell.getSpellType());
	}

}
