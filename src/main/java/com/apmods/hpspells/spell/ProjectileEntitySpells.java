package com.apmods.hpspells.spell;
//package com.apmods.magicraft.spell;
//
//import java.util.Timer;
//
//import com.apmods.magicraft.entity.EntityBird;
//import com.apmods.magicraft.entity.EntityDementor;
//import com.apmods.magicraft.entity.EntitySpell;
//import com.apmods.magicraft.extendedplayer.MagiSkills;
//import com.apmods.magicraft.lib.SpellLib;
//import com.apmods.magicraft.main.MagiCraft;
//
//import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.EntityCreature;
//import net.minecraft.entity.EntityHanging;
//import net.minecraft.entity.EntityLiving;
//import net.minecraft.entity.EntityLivingBase;
//import net.minecraft.entity.ai.EntityAILookIdle;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.init.Blocks;
//import net.minecraft.init.Items;
//import net.minecraft.item.ItemStack;
//import net.minecraft.potion.Potion;
//import net.minecraft.potion.PotionEffect;
//import net.minecraft.util.MathHelper;
//
//public class ProjectileEntitySpells {
//	private EntitySpell spell;
//	private EntityLivingBase affectedEntity;
//	private EntityLivingBase shootingEntity;
//	private MagiSkills ext;
//
//	private int damage = 0;
//	private double knockback = 0;
//
//	public ProjectileEntitySpells(EntityLivingBase entityHit, EntityLivingBase shootingEntity, EntitySpell spell) {
//		this.spell = spell;
//		affectedEntity = entityHit;
//		this.shootingEntity = shootingEntity;
//		initExt();
//	}
//
//	private void initExt() {
//		if (shootingEntity != null && shootingEntity instanceof EntityPlayer) {
//			ext = MagiSkills.get((EntityPlayer) shootingEntity);
//		}
//	}
//
//	public int getSkillLevel(int spellOf) {
//		if (ext != null) {
//			return ext.getCurrentSkillLevel(spellOf);
//		} else {
//			return 2;
//		}
//	}
//
//	public void doDamageAndKnockback() {
//		float f4 = MathHelper.sqrt_double(spell.motionX * spell.motionX + spell.motionZ * spell.motionZ);
//		affectedEntity.attackEntityFrom(new SpellDamageSource(shootingEntity, spell.getSpell()), 1.0f * damage);
//		affectedEntity.addVelocity(spell.motionX * knockback * 0.6000000238418579D / f4, 0.4 + knockback / 10, spell.motionZ * knockback * 0.6000000238418579D / f4);
//	}
//
//	public void stupefy() {
//		int skill = getSkillLevel(SpellLib.getSpell("stupefy"));
//		affectedEntity.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 20 + skill * 3, 0));
//		damage = 4 + skill;
//		knockback = 0.3 + skill/10;
//	}
//
//	public void avadaKedavra() {
//		System.out.println("avada kedavra");
//		damage = 10000;
//		if (affectedEntity instanceof EntityDementor) {
//			affectedEntity.playSound(MagiCraft.MODID + ":" + "mob.dementor.death", 1, 1);
//			affectedEntity.setDead();
//
//		}
//		knockback = 0.2;
//	}
//
//	public void expelliarmus(EntitySpell spell) {
//		int skill = getSkillLevel(SpellLib.getSpell("expelliarmus"));
//		damage = 1 + skill / 2;
//		knockback = 0.2 + skill / 20;
//		ItemStack item = affectedEntity.getHeldItem();
//		if (item != null) {
//			affectedEntity.setCurrentItemOrArmor(0, null);
//			if (shootingEntity instanceof EntityPlayer && skill >= 4) {
//				((EntityPlayer) shootingEntity).inventory.addItemStackToInventory(item);
//			} else {
//				spell.dropItem(item.getItem(), item.stackSize);
//			}
//		}
//	}
//
//	public void expulso() {
//		int skill = getSkillLevel(SpellLib.getSpell("expulso"));
//		knockback = 1.4 + skill / 5;
//	}
//
//	public void confringo() {
//		int skill = getSkillLevel(SpellLib.getSpell("confringo"));
//		spell.worldObj.newExplosion(spell, spell.posX, spell.posY, spell.posZ, (float) (3.0f + skill / 4.5), true, true);
//	}
//
//	public void incendio() {
//		int skill = getSkillLevel(SpellLib.getSpell("incendio"));
//		affectedEntity.setFire(1 + skill);
//	}
//
//	public void aguamenti() {
//		affectedEntity.extinguish();
//	}
//
//	public void alarteAscendare() {
//		int skill = getSkillLevel(SpellLib.getSpell("alarte ascendare"));
//		affectedEntity.addVelocity(0, 1.2 + skill / 10, 0);
//	}
//
//	public void flipendo() {
//		int skill = getSkillLevel(SpellLib.getSpell("flipendo"));
//		knockback = 0.8 + skill / 5;
//	}
//
//	public void melofors() {
//		affectedEntity.setCurrentItemOrArmor(4, new ItemStack(Blocks.pumpkin));
//		if (affectedEntity instanceof EntityPlayer) {
//			((EntityPlayer) affectedEntity).inventory.markDirty();
//		}
//	}
//
//	public void obscuro() {
//		int skill = getSkillLevel(SpellLib.getSpell("obscuro"));
//		affectedEntity.addPotionEffect(new PotionEffect(Potion.blindness.id, 80 + skill * 10, 1));
//	}
//
//	public void petrificusTotalus() {
//		int skill = getSkillLevel(SpellLib.getSpell("petrificus totalus"));
//		affectedEntity.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 80 + skill * 10, 20));
//	}
//
//	public void feraVerto() {
//		if (!(affectedEntity instanceof EntityPlayer)) {
//			affectedEntity.dropItem(Items.glass_bottle, 1);
//			affectedEntity.setDead();
//		}
//	}
//
//	public void avifors() {
//		if (!(affectedEntity instanceof EntityPlayer)) {
//			affectedEntity.setDead();
//			EntityBird bat = new EntityBird(spell.worldObj);
//			bat.setPositionAndUpdate(affectedEntity.posX, affectedEntity.posY, affectedEntity.posZ);
//			spell.worldObj.spawnEntityInWorld(bat);
//		}
//	}
//
//	public void crucio() {
//		int skill = getSkillLevel(SpellLib.getSpell("crucio"));
//		affectedEntity.addPotionEffect(new PotionEffect(Potion.poison.id, 260 + 10 * skill, 20));
//	}
//
//	public void imperio() {
//		if (affectedEntity instanceof EntityPlayer && shootingEntity instanceof EntityPlayer) {
//			EntityPlayer playerHit = (EntityPlayer) affectedEntity;
//			FMLNetworkHandler.openGui((EntityPlayer) shootingEntity, MagiCraft.instance, 11, spell.worldObj, playerHit.getEntityId(), 0, 0);
//		}
//	}
//
//	public static void diffindo(Entity entity, EntityLivingBase shootingEntity) {
//		if (entity instanceof EntityHanging) {
//			EntityHanging entityHanging = (EntityHanging) entity;
//			entityHanging.onBroken(shootingEntity);
//			entityHanging.setDead();
//		}
//	}
//
//	public void bombarda() {
//		int skill = getSkillLevel(SpellLib.getSpell("bombarda"));
//		spell.worldObj.createExplosion(spell, spell.posX, spell.posY, spell.posZ, (float) (4.0f + skill / 4.5), true);
//	}
//
//	public void obliviate() {
//		if (affectedEntity instanceof EntityCreature) {
//			if(!affectedEntity.worldObj.isRemote){
//				EntityAILookIdle task = new EntityAILookIdle((EntityLiving) affectedEntity);
//				((EntityCreature)affectedEntity).targetTasks.addTask(0, task);
//				((EntityCreature) affectedEntity).setAttackTarget(null);
//				Timer t = new Timer();
//				t.schedule(new TimerTaskObliviate((EntityCreature) affectedEntity, task), 10000);
//			}
//		}
//	}
//}
