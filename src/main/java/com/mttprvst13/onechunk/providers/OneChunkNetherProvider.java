package com.mttprvst13.onechunk.providers;

import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.chunk.IChunkProvider;

public class OneChunkNetherProvider extends WorldProviderHell
{

    public IChunkProvider createChunkGenerator()
    {
        return new OneChunkNetherChunkProvider(worldObj, worldObj.getSeed());
    }

}
