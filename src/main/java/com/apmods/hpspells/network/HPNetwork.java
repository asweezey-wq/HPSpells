package com.apmods.hpspells.network;

import com.apmods.hpspells.main.HPSpells;
import com.apmods.hpspells.network.WandmakerPacket.WandmakerMessage;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class HPNetwork {
    public static SimpleNetworkWrapper net;
    public static int messages = 0;

    public static void init(){
        net = NetworkRegistry.INSTANCE.newSimpleChannel(HPSpells.MODID);
        registerMessage(CastSpellPacket.class, CastSpellPacket.SpellMessage.class);
        registerMessage(OpenWandGuiPacket.class, OpenWandGuiPacket.GuiMessage.class);
        registerMessageForClient(SyncSkillsPacket.class, SyncSkillsPacket.SkillsMessage.class);
        registerMessageForClient(PortkeyGuiPacket.class, PortkeyGuiPacket.GuiMessage.class);
        registerMessage(LevelUpKnowledgePacket.class, LevelUpKnowledgePacket.KnowledgeMessage.class);
        registerMessage(PortkeyPacket.class, PortkeyPacket.PortkeyMessage.class);
        registerMessage(BroomstickPacket.class, BroomstickPacket.BroomstickMessage.class);
        registerMessage(SpeakingPacket.class, SpeakingPacket.SpeakingMessage.class);
        registerMessage(CopyCoordsPacket.class, CopyCoordsPacket.CopyCoordsMessage.class);
        registerMessage(WandmakerPacket.class, WandmakerMessage.class);
    }

    private static void registerMessage(Class handler, Class message)
    {
        net.registerMessage(handler, message, messages, Side.CLIENT);
        net.registerMessage(handler, message, messages, Side.SERVER);
        messages++;
    }
    private static void registerMessageForServer(Class handler, Class message)
    {
        net.registerMessage(handler, message, messages, Side.SERVER);
        messages++;
    }
    private static void registerMessageForClient(Class handler, Class message)
    {
        net.registerMessage(handler, message, messages, Side.CLIENT);
        messages++;
    }
}
