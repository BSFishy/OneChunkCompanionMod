package com.mttprvst13.onechunk.world;

//@SuppressWarnings("importnotfound")

import com.mttprvst13.onechunk.providers.OneChunkSurfaceChunkProvider;
import cpw.mods.fml.common.FMLLog;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.WorldChunkManager;

import java.util.List;
import java.util.Random;

public class OneChunkChunkManager extends WorldChunkManager
{
    private World world;
    public OneChunkSurfaceChunkProvider provider;

    public OneChunkChunkManager(World world)
    {
        this.world = world;
        provider = new OneChunkSurfaceChunkProvider(world);
    }

    public OneChunkChunkManager(World world, OneChunkSurfaceChunkProvider pro)
    {
        super(world);
        this.world = world;
        provider = pro;
    }

    @Override
    public ChunkPosition findBiomePosition(int x, int z, int range, List biomes, Random rand)
    {
        ChunkPosition ret = super.findBiomePosition(x, z, range, biomes, rand);
        if (x == 0 && z == 0 && !world.getWorldInfo().isInitialized())
        {
            if (ret == null)
            {
                ret = new ChunkPosition(0, 0, 0);
            }

            buildSpawn(world, ret.chunkPosX, world.provider.getAverageGroundLevel(), ret.chunkPosZ);
        }
        return ret;
    }

    private void buildSpawn(World world, int x, int y, int z)
    {
        FMLLog.info("[One Chunk Companion] Building chunk at: %d, %d, %d", x, y, z);
        //FMLLog.info(provider);
        OneChunkWorldGenerator gen = new OneChunkWorldGenerator(world.getChunkProvider());
        gen.generate(world, x, y, z);
    }
}
