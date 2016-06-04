package com.mttprvst13.onechunk.providers;

import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderHell;
import net.minecraft.world.gen.MapGenBase;

import java.lang.reflect.Field;

public class OneChunkNetherChunkProvider extends ChunkProviderHell implements ChunkProvider
{

    private World world;
    private Field genWorldF;

    public OneChunkNetherChunkProvider(World world, long seed) {
        super(world, seed);
        this.world = world;
        genWorldF = ReflectionHelper.findField(MapGenBase.class, "field_75039_c", "worldObj");
        genWorldF.setAccessible(true);
    }

    @Override public Chunk loadChunk(int x, int z) { return provideChunk(x, z); }

    @Override
    public void populate(IChunkProvider provider, int x, int z)
    {
        super.populate(provider, x, z);
    }

    @Override
    public Chunk provideChunk(int x, int z)
    {
        if(x != 0 || z != 0)
            return voidChunk(x, z);
        else
            return super.provideChunk(x, z);
    }

    private Chunk voidChunk(int x, int z){
        Chunk ret = new Chunk(world, new Block[8 * 16 * 16 * 16], x, z);
        BiomeGenBase[] biomes = world.getWorldChunkManager().loadBlockGeneratorData(null, x * 16, z * 16, 16, 16);
        byte[] ids = ret.getBiomeArray();

        for(int i = 0; i < ids.length; ++i){
            ids[i] = (byte) biomes[i].biomeID;
        }

        ret.generateSkylightMap();
        return ret;
    }

    public Chunk generateChunk(int x, int z)
    {
        return super.provideChunk(x, z);
    }
}
