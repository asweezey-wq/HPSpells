package com.apmods.hpspells.spell;

import java.util.Arrays;
import java.util.List;

import com.apmods.hpspells.entity.EntitySpell;
import com.apmods.hpspells.extendedplayer.SpellSkills;
import com.apmods.hpspells.lib.SpellLib;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;

public abstract class SpellBase implements ISpell{
	
	public double damage = 0;
	
	public double knockback;
	
	@Override
	public int getSpellIndex() {
		List<String> names = Arrays.asList(SpellLib.names);
		return names.indexOf(this.getName());
	}
	
	public int getSkillLevel(EntityLivingBase caster, ISpell spellOf) {
		if(caster != null && caster instanceof EntityPlayer){
			SpellSkills ext = SpellSkills.get((EntityPlayer) caster);
			if (ext != null) {
				return ext.getCurrentSkillLevel(spellOf);
			}
		}
		return 2;
	}
	
	public void doDamageAndKnockback(EntityLivingBase affectedEntity, EntityLivingBase shootingEntity, EntitySpell spell){
		float f4 = MathHelper.sqrt_double(spell.motionX * spell.motionX + spell.motionZ * spell.motionZ);
		if(damage > 0){
			affectedEntity.attackEntityFrom(new SpellDamageSource(shootingEntity, spell.getSpell()), 0.0f + (int)damage);
		}
		if(knockback > 0){
			affectedEntity.addVelocity(spell.motionX * knockback * 0.6000000238418579D / f4, 0.2 + knockback/10, spell.motionZ * knockback * 0.6000000238418579D / f4);
		}
//		if(affectedEntity instanceof EntityPlayerMP && !affectedEntity.worldObj.isRemote){
//			((EntityPlayerMP)affectedEntity).playerNetServerHandler.sendPacket(new S12PacketEntityVelocity(affectedEntity));
//		}
		
	}
}
