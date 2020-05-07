package com.apmods.hpspells.network;

import com.apmods.hpspells.extendedplayer.SpellSkills;
import com.apmods.hpspells.item.ItemManager;
import com.apmods.hpspells.lib.SpellLib;
import com.apmods.hpspells.network.LevelUpKnowledgePacket.KnowledgeMessage;
import com.apmods.hpspells.spell.EnumSpellType;
import com.apmods.hpspells.spell.ISpell;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class LevelUpKnowledgePacket implements IMessageHandler<KnowledgeMessage,IMessage>
{

    @Override
    public IMessage onMessage(KnowledgeMessage message, MessageContext ctx)
    {
        if(ctx.side.isServer())
        {
        	EntityPlayerMP player = ctx.getServerHandler().playerEntity;

            if (player != null)
            {
                int playerX = (int) player.posX;
                int playerY = (int) player.posY;
                int playerZ = (int) player.posZ;
                World world = player.worldObj;
                SpellSkills ext = SpellSkills.get(player);
                if(message.spell.getName() != "None" && !ext.isKnowledgeMaxed(message.spell)){
                	int extraExpCost = 0;
                	boolean flag = message.spell.getSpellType() == EnumSpellType.DARK;
                	if((player.experienceLevel >= 10 + extraExpCost && (!flag || player.inventory.hasItem(ItemManager.darkArts))) || player.capabilities.isCreativeMode){
	                	ext.addKnowledgeLevel(message.spell);
	                	if(message.spell == SpellLib.getSpell("Lumos")){
	                		ext.addKnowledgeLevel(SpellLib.getSpell("Nox"));
	                	}
	                	if(!player.capabilities.isCreativeMode){
	                		player.addExperienceLevel(-10 - extraExpCost);
	                	}
	                	HPNetwork.net.sendTo(new SyncSkillsPacket.SkillsMessage(player), player);
	                	player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GREEN + "[HP Spells]" + EnumChatFormatting.RESET + " You leveled up your " + EnumChatFormatting.DARK_PURPLE + message.spell.getName() + EnumChatFormatting.RESET + " knowledge to level " + ext.getKnowledgeLevel(message.spell)));
                	}
                	else{
	                	player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED + "[HP Spells] Not Enough Experience! You need " + (10 + extraExpCost) + " levels!"));
                	}
                }
            }
            
        }
        return null;
    }

    public static class KnowledgeMessage implements IMessage
    {
    	public ISpell spell;

        public KnowledgeMessage()
        {
        }
        public KnowledgeMessage(ISpell spell)
        {
        	this.spell = spell;
        }

		@Override
		public void fromBytes(ByteBuf buf) {
			this.spell = SpellLib.getSpell(ByteBufUtils.readUTF8String(buf));
			
		}

		@Override
		public void toBytes(ByteBuf buf) {
			ByteBufUtils.writeUTF8String(buf, spell.getName());
			
		}

        
    }
}
