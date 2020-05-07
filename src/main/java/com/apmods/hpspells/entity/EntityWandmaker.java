package com.apmods.hpspells.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.apmods.hpspells.main.HPSpells;
import com.apmods.hpspells.wand.EnumWandCore;
import com.apmods.hpspells.wand.EnumWandMaterial;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.INpc;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIMoveIndoors;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

public class EntityWandmaker extends EntityCreature implements INpc {

	private ArrayList<EnumWandMaterial> woods;
	private ArrayList<EnumWandCore> cores;

	private EntityPlayer customer;

	public EntityWandmaker(World p_i1602_1_) {
		super(p_i1602_1_);
		this.setSize(0.6F, 1.8F);
		this.getNavigator().setAvoidsWater(true);

		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityZombie.class, 8.0F, 0.6D, 0.6D));
		this.tasks.addTask(1, new EntityAIWandmaker(this));
		this.tasks.addTask(2, new EntityAIMoveIndoors(this));
		this.tasks.addTask(3, new EntityAIRestrictOpenDoor(this));
		this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
		this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 0.6D));
		this.tasks.addTask(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
		this.tasks.addTask(9, new EntityAIWatchClosest2(this, EntityWandmaker.class, 5.0F, 0.02F));
		this.tasks.addTask(9, new EntityAIWander(this, 0.6D));
		this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));

		List<EnumWandMaterial> values = new ArrayList(Arrays.asList(EnumWandMaterial.values().clone()));
		values.remove(7);
		List<EnumWandCore> coreValues = new ArrayList(Arrays.asList(EnumWandCore.values().clone()));
		coreValues.remove(3);
		Random rand = new Random();
		int randWoods = rand.nextInt(3) + 4;
		int randCores = rand.nextInt(2) + 4;
		woods = new ArrayList<EnumWandMaterial>(randWoods);
		for (int i = 0; i < randWoods; i++) {
			int i1 = rand.nextInt(values.size());
			EnumWandMaterial wood = values.remove(i1);
			woods.add(wood);
		}
		updateWoods();
		cores = new ArrayList<EnumWandCore>(randCores);
		for (int i = 0; i < randCores; i++) {
			cores.add(coreValues.remove(rand.nextInt(coreValues.size())));
		}
		updateCores();
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5D);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(16, Integer.valueOf(0));
		this.dataWatcher.addObject(17, Integer.valueOf(0));
	}

	@Override
	public boolean getCanSpawnHere() {
		return super.getCanSpawnHere();
	}

	@Override
	public void readFromNBT(NBTTagCompound p_70020_1_) {
		super.readFromNBT(p_70020_1_);
		NBTTagList tagList = p_70020_1_.getTagList("Cores", Constants.NBT.TAG_STRING);
		NBTTagList tagList2 = p_70020_1_.getTagList("Woods", Constants.NBT.TAG_STRING);
		try {
			cores.clear();
			for (int i = 0; i < tagList.tagCount(); i++) {
				String name = tagList.getStringTagAt(i);
				cores.add(EnumWandCore.valueOf(name));
			}
			updateCores();
			woods.clear();
			for (int i = 0; i < tagList2.tagCount(); i++) {
				String name = tagList2.getStringTagAt(i);
				woods.add(EnumWandMaterial.valueOf(name));
			}
		} catch (ArrayIndexOutOfBoundsException err) {
			err.printStackTrace();
		}
		updateWoods();
	}

	@Override
	public void writeToNBT(NBTTagCompound p_70109_1_) {
		super.writeToNBT(p_70109_1_);
		NBTTagList tagList = new NBTTagList();
		for (int i = 0; i < cores.size(); i++) {
			String name = this.getCore(i).name();
			tagList.appendTag(new NBTTagString(name));
		}
		p_70109_1_.setTag("Cores", tagList);
		NBTTagList tagList2 = new NBTTagList();
		for (int i = 0; i < woods.size(); i++) {
			String name = this.getWood(i).name();
			tagList2.appendTag(new NBTTagString(name));
		}
		p_70109_1_.setTag("Woods", tagList2);
	}

	@Override
	protected boolean interact(EntityPlayer p_70085_1_) {
		if (this.customer == null) {
			this.customer = p_70085_1_;
			if (!worldObj.isRemote) {
				p_70085_1_.openGui(HPSpells.instance, 12, worldObj, this.getEntityId(), 0, 0);
			}
			return true;
		}
		return false;
	}

	public EntityPlayer getCustomer() {
		return customer;
	}

	public void setCustomer(EntityPlayer customer) {
		this.customer = customer;
	}

	public void setWood(int i, EnumWandMaterial wood) {
		this.woods.set(i, wood);
		int result = 0;
		for (int j = 0; j < woods.size(); j++) {
			int index = Arrays.asList(EnumWandMaterial.values()).indexOf(woods.get(j));
			result |= (index & 0xFF) << (j * 8);
		}
		this.dataWatcher.updateObject(16, result);
	}

	public void updateWoods() {
		int result = 0;
		for (int j = 0; j < woods.size(); j++) {
			int index = Arrays.asList(EnumWandMaterial.values()).indexOf(woods.get(j));
			result |= (index & 0xFF) << (j * 8);
		}
		this.dataWatcher.updateObject(16, result);
	}

	public void updateCores() {
		int result = 0;
		for (int j = 0; j < cores.size(); j++) {
			int index = Arrays.asList(EnumWandCore.values()).indexOf(cores.get(j));
			result |= (index & 0xFF) << (j * 8);
		}
		this.dataWatcher.updateObject(17, result);
	}

	public void setCore(int i, EnumWandCore core) {
		this.cores.set(i, core);
		int result = 0;
		for (int j = 0; j < cores.size(); j++) {
			int index = Arrays.asList(EnumWandCore.values()).indexOf(cores.get(j));
			result |= (index & 0xFF) << (j * 8);
		}
		this.dataWatcher.updateObject(17, result);
	}

	public EnumWandMaterial getWood(int i) {
		int result = this.dataWatcher.getWatchableObjectInt(16);
		int shifted = (result >> (i * 8)) & 0xFF;
		return EnumWandMaterial.values()[shifted];
	}

	public int getNumWoods() {
		return woods.size();
	}

	public EnumWandCore getCore(int i) {
		int result = this.dataWatcher.getWatchableObjectInt(17);
		int shifted = (result >> (i * 8)) & 0xFF;
		shifted = shifted % EnumWandCore.values().length;
		return EnumWandCore.values()[shifted];
	}

	public void removeCore(EnumWandCore core) {
		cores.remove(core);
		for (int j = 0; j < cores.size(); j++) {
			this.setCore(j, cores.get(j));
		}
	}

	public void removeWood(EnumWandMaterial wood) {
		woods.remove(wood);
		for (int j = 0; j < woods.size(); j++) {
			this.setWood(j, woods.get(j));
		}
	}

	public int getNumCores() {
		return cores.size();
	}
}
