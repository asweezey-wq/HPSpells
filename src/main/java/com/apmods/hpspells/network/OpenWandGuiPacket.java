package com.apmods.hpspells.network;

import com.apmods.hpspells.main.HPSpells;
import com.apmods.hpspells.network.OpenWandGuiPacket.GuiMessage;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class OpenWandGuiPacket implements IMessageHandler<GuiMessage,IMessage>
{

    @Override
    public IMessage onMessage(GuiMessage message, MessageContext ctx)
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
                FMLNetworkHandler.openGui(player, HPSpells.instance, 20, world, playerX, playerY, playerZ);
            }
            
        }
        return null;
    }

    public static class GuiMessage implements IMessage
    {

        public GuiMessage()
        {
        }

		@Override
		public void fromBytes(ByteBuf buf) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void toBytes(ByteBuf buf) {
			// TODO Auto-generated method stub
			
		}

        
    }
}
