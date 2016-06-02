package com.mttprvst13.onechunk.world;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;

public class OneChunkWorldType extends WorldType
{

    private OneChunkChunkManager manager;
    private OneChunkChunkProvider provider;

    public OneChunkWorldType()
    {
        super("onechunk");
    }

    @Override
    public WorldChunkManager getChunkManager(World world)
    {
        if(manager == null && provider == null)
        {
            provider = new OneChunkChunkProvider(world);
            manager = new OneChunkChunkManager(world, provider);
        }
        else if(manager == null)
        {
            manager = new OneChunkChunkManager(world, provider);
        }

        return manager;
    }

    @Override
    public IChunkProvider getChunkGenerator(World world, String generatorOptions)
    {
        if(provider == null)
        {
            provider = new OneChunkChunkProvider(world);
            manager = new OneChunkChunkManager(world, provider);
        }
        else if(manager == null)
        {
            manager = new OneChunkChunkManager(world, provider);
        }

        return provider;
    }

    @Override
    public int getSpawnFuzz(){
        return 1;
    }

}
