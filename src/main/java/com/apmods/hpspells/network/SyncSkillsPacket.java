package com.apmods.hpspells.network;

import com.apmods.hpspells.extendedplayer.SpellSkills;
import com.apmods.hpspells.main.HPSpells;
import com.apmods.hpspells.network.SyncSkillsPacket.SkillsMessage;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class SyncSkillsPacket implements IMessageHandler<SkillsMessage,IMessage>
{

    @Override
    public IMessage onMessage(SkillsMessage message, MessageContext ctx)
    {
        return HPSpells.proxy.onMessage(message, ctx);
    }

    public static class SkillsMessage implements IMessage
    {
    	public NBTTagCompound data;

        public SkillsMessage()
        {
        }
        public SkillsMessage(EntityPlayer player)
        {
        	SpellSkills ext = SpellSkills.get(player);
        	this.data = new NBTTagCompound();
        	ext.saveNBTData(data);
        }

		@Override
		public void fromBytes(ByteBuf buf) {
			this.data = ByteBufUtils.readTag(buf);
		}

		@Override
		public void toBytes(ByteBuf buf) {
			ByteBufUtils.writeTag(buf, data);
		}

        
    }
}
