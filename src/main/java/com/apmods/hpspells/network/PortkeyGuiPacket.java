package com.apmods.hpspells.network;

import com.apmods.hpspells.main.HPSpells;
import com.apmods.hpspells.network.PortkeyGuiPacket.GuiMessage;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class PortkeyGuiPacket implements IMessageHandler<GuiMessage, IMessage>{

	@Override
    public IMessage onMessage(GuiMessage message, MessageContext ctx)
    {
        if(ctx.side.isClient())
        {
        	HPSpells.proxy.portus(ctx);
            
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
		}

		@Override
		public void toBytes(ByteBuf buf) {
			
		}

        
    }

}
