package com.apmods.hpspells.network;

import com.apmods.hpspells.extendedplayer.SpellSkills;
import com.apmods.hpspells.network.SpeakingPacket.SpeakingMessage;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import scala.util.Random;

public class SpeakingPacket implements IMessageHandler<SpeakingMessage, IMessage>{

	@Override
	public IMessage onMessage(SpeakingMessage message, MessageContext ctx) {
		if (ctx.side.isServer()) {
			EntityPlayerMP player = ctx.getServerHandler().playerEntity;

			if (player != null) {
				int playerX = (int) player.posX;
				int playerY = (int) player.posY;
				int playerZ = (int) player.posZ;
				World world = player.worldObj;
				SpellSkills ext = SpellSkills.get(player);
				Random rand = new Random();

				if (!world.isRemote) {
					ext.setSpeaking(message.isSpeaking);
				}
				
			}
		}
		return null;
	}
	
	public static class SpeakingMessage implements IMessage{

		public boolean isSpeaking;
		
		public SpeakingMessage(){
			
		}
		
		public SpeakingMessage(boolean isSpeaking){
			this.isSpeaking = isSpeaking;
		}
		
		@Override
		public void fromBytes(ByteBuf buf) {
			isSpeaking = buf.readBoolean();
		}

		@Override
		public void toBytes(ByteBuf buf) {
			buf.writeBoolean(isSpeaking);
		}
		
	}
}
