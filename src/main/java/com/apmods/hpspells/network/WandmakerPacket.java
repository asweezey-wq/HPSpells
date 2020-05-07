package com.apmods.hpspells.network;

import com.apmods.hpspells.extendedplayer.SpellSkills;
import com.apmods.hpspells.inventory.ContainerWandmaker;
import com.apmods.hpspells.network.WandmakerPacket.WandmakerMessage;
import com.apmods.hpspells.wand.EnumWandCore;
import com.apmods.hpspells.wand.EnumWandMaterial;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import scala.util.Random;

public class WandmakerPacket implements IMessageHandler<WandmakerMessage, IMessage>{

	@Override
	public IMessage onMessage(WandmakerMessage message, MessageContext ctx) {
		if (ctx.side.isServer()) {
			EntityPlayerMP player = ctx.getServerHandler().playerEntity;

			if (player != null) {
				int playerX = (int) player.posX;
				int playerY = (int) player.posY;
				int playerZ = (int) player.posZ;
				World world = player.worldObj;
				if(player.openContainer instanceof ContainerWandmaker){
					ContainerWandmaker container = (ContainerWandmaker) player.openContainer;
					EnumWandCore[] values = EnumWandCore.values();
					container.wandmakerInv.selectedCore = EnumWandCore.values()[message.core];
					container.wandmakerInv.selectedWood = EnumWandMaterial.values()[message.wood];
					container.wandmakerInv.updateWand();
				}
			}
		}
		return null;
	}
	
	public static class WandmakerMessage implements IMessage {

		private byte wood;
		private byte core;
		
		public WandmakerMessage() {
		}
		
		public WandmakerMessage(byte wood, byte core){
			this.wood = wood;
			this.core = core;
		}
		
		@Override
		public void fromBytes(ByteBuf buf) {
			this.wood = buf.readByte();
			this.core = buf.readByte();
		}

		@Override
		public void toBytes(ByteBuf buf) {
			buf.writeByte(this.wood);
			buf.writeByte(this.core);
		}
		
	}

}
