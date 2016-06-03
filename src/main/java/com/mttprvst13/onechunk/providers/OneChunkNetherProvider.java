package com.mttprvst13.onechunk.providers;

import com.mttprvst13.onechunk.OneChunk;
import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderHell;

public class OneChunkNetherProvider extends WorldProviderHell
{

    public IChunkProvider createChunkGenerator()
    {
        if(OneChunk.instance.getOverrideWorld(worldObj))
            return new OneChunkNetherChunkProvider(worldObj, worldObj.getSeed());
        return new ChunkProviderHell(worldObj, worldObj.getSeed());
    }

}
