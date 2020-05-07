package com.apmods.hpspells.network;

import com.apmods.hpspells.extendedplayer.SpellSkills;
import com.apmods.hpspells.network.CopyCoordsPacket.CopyCoordsMessage;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import scala.util.Random;

public class CopyCoordsPacket implements IMessageHandler<CopyCoordsMessage, IMessage>{

	@Override
	public IMessage onMessage(CopyCoordsMessage message, MessageContext ctx) {
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
					if(message.isCopying){
						ext.setPortkeyX(message.x);
						ext.setPortkeyY(message.y);
						ext.setPortkeyZ(message.z);
					}
				}
			}
		}
		return null;
	}

	public static class CopyCoordsMessage implements IMessage{
		
		public boolean isCopying;
		public int x, y, z;
		
		public CopyCoordsMessage() {
		}
		
		public CopyCoordsMessage(boolean isCopying, int x, int y, int z){
			this.isCopying = isCopying;
			this.x = x;
			this.y = y;
			this.z = z;
		}
		
		@Override
		public void fromBytes(ByteBuf buf) {
			this.isCopying = buf.readBoolean();
			this.x = buf.readInt();
			this.y = buf.readInt();
			this.z = buf.readInt();
		}

		@Override
		public void toBytes(ByteBuf buf) {
			buf.writeBoolean(isCopying);
			buf.writeInt(x);
			buf.writeInt(y);
			buf.writeInt(z);
		}
		
	}
}
