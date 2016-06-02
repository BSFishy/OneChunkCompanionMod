package com.mttprvst13.onechunk.world;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

public class OneChunkWorldGenerator
{

    private IChunkProvider provider;

    public OneChunkWorldGenerator(IChunkProvider pro)
    {
        provider = pro;
    }

    public void generate(World world, int x, int y, int z)
    {
        if(world.provider.dimensionId == 0)
            buildWorld(world, x, y, z);
    }

    private void buildWorld(World world, int x, int y, int z)
    {
        //FMLLog.info(provider.toString());
        provider.provideChunk(x, z);
    }
}
