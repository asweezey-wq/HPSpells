package com.apmods.hpspells.network;

import com.apmods.hpspells.entity.EntityPortkey;
import com.apmods.hpspells.extendedplayer.SpellSkills;
import com.apmods.hpspells.network.PortkeyPacket.PortkeyMessage;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class PortkeyPacket implements IMessageHandler<PortkeyMessage,IMessage>
{

    @Override
    public IMessage onMessage(PortkeyMessage message, MessageContext ctx)
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
                if(!world.getBlock(message.x, message.y, message.z).isOpaqueCube() && !world.getBlock(message.x, message.y + 1, message.z).isOpaqueCube()){
                	if(message.y < 256){
	                	if(!world.isRemote){
	                		world.spawnEntityInWorld(new EntityPortkey(world, player, message.x, message.y, message.z));
	                	}
                	}
                }
                else{
                	player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Portkey Destination Obstructed!"));
                }
            }
            
        }
        return null;
    }

    public static class PortkeyMessage implements IMessage
    {
    	private int x;
    	private int y;
    	private int z;

        public PortkeyMessage()
        {
        }
        public PortkeyMessage(int x, int y, int z)
        {
        	this.x = x;
        	this.y = y;
        	this.z = z;
        }

		@Override
		public void fromBytes(ByteBuf buf) {
			this.x = buf.readInt();
			this.y = buf.readInt();
			this.z = buf.readInt();
		}

		@Override
		public void toBytes(ByteBuf buf) {
			buf.writeInt(x);
			buf.writeInt(y);
			buf.writeInt(z);
			
		}

        
    }
}
