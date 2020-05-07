package com.apmods.hpspells.command;

import com.apmods.hpspells.extendedplayer.SpellSkills;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class CommandLearnAll extends CommandBase{

	@Override
	public String getCommandName() {
		return "learnall";
	}

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) {
		return "/learnall";
	}

	@Override
	public void processCommand(ICommandSender p_71515_1_, String[] p_71515_2_) {
		SpellSkills ext = SpellSkills.get(getCommandSenderAsPlayer(p_71515_1_));
		ext.learnAll();
	}
	
	@Override
	public int getRequiredPermissionLevel() {
		return 2;
	}	
}
