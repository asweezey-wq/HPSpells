package com.apmods.hpspells.spell;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;

public class SpellDamageSource extends DamageSource{

	private ISpell spell;
	private EntityLivingBase attackingEntity;
	
	public SpellDamageSource(EntityLivingBase attackingEntity, ISpell spell) {
		super("spell");
		this.spell = spell;
		this.attackingEntity = attackingEntity;
	}
	
	@Override
	public Entity getSourceOfDamage() {
		return attackingEntity;
	}

	@Override
	public Entity getEntity() {
		return attackingEntity;
	}
	
	public ISpell getSpell() {
		return spell;
	}

	public void setSpell(ISpell spell) {
		this.spell = spell;
	}
	
	public EntityLivingBase getAttackingEntity() {
		return attackingEntity;
	}

	public void setAttackingEntity(EntityLivingBase attackingEntity) {
		this.attackingEntity = attackingEntity;
	}
	
	public IChatComponent func_151519_b(EntityLivingBase p_151519_1_)
    {
		String s = "death.attack.spell";
		return p_151519_1_ != null && spell.getName() != "None" ? new ChatComponentTranslation(s, new Object[]{p_151519_1_.func_145748_c_(), attackingEntity.func_145748_c_(), spell.getName()}) : new ChatComponentTranslation(s, new Object[]{p_151519_1_.func_145748_c_(), attackingEntity.func_145748_c_()});
    }

}
