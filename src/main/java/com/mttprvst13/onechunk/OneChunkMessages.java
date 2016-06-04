package com.mttprvst13.onechunk;

import com.mttprvst13.onechunk.physicals.blocks.chunkgenerator.PacketChunkGenerator;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import mcjty.lib.network.PacketHandler;

public class OneChunkMessages
{

    public static SimpleNetworkWrapper INSTANCE;

    public static void registerNetworkMessages(SimpleNetworkWrapper net)
    {
        INSTANCE = net;

        net.registerMessage(PacketChunkGenerator.class, PacketChunkGenerator.class, PacketHandler.nextID(), Side.SERVER);
    }

}
