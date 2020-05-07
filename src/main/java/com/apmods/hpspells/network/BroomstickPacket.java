package com.apmods.hpspells.network;

import com.apmods.hpspells.entity.EntityBroomstick;
import com.apmods.hpspells.extendedplayer.SpellSkills;
import com.apmods.hpspells.network.BroomstickPacket.BroomstickMessage;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import scala.util.Random;

public class BroomstickPacket implements IMessageHandler<BroomstickMessage, IMessage>{

	@Override
	public IMessage onMessage(BroomstickMessage message, MessageContext ctx) {
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
					if(player.ridingEntity instanceof EntityBroomstick){
						EntityBroomstick broomstick = (EntityBroomstick) player.ridingEntity;
						broomstick.setLockY(message.lockY);
					}
				}
			}
		}
		return null;
	}
	
	public static class BroomstickMessage implements IMessage{

		public boolean lockY;
		
		public BroomstickMessage() {
			
		}
		
		public BroomstickMessage(boolean lockY) {
			this.lockY = lockY;
		}
		
		@Override
		public void fromBytes(ByteBuf buf) {
			this.lockY = buf.readBoolean();
		}

		@Override
		public void toBytes(ByteBuf buf) {
			buf.writeBoolean(lockY);
		}
		
	}

}
