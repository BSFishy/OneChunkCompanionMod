package com.mttprvst13.onechunk.providers;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderGenerate;

public class OneChunkSurfaceChunkProvider extends ChunkProviderGenerate
{

    private World world;

    public OneChunkSurfaceChunkProvider(World world)
    {
        super(world, world.getSeed(), false);
        this.world = world;
    }

    @Override public Chunk loadChunk(int x, int z){ return this.provideChunk(x, z); }
    @Override public void populate(IChunkProvider par1IChunkProvider, int par2, int par3){
        super.populate(par1IChunkProvider, par2, par3);
    }

    @Override public Chunk provideChunk(int x, int z)
    {
        if(x != 0 || z != 0)
            return voidChunk(x, z);
        else
            return generateChunk(x, z);
        //return voidChunk(x, z);
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

    public String toString(){
        return "OneChunkSurfaceChunkProvider<World:" + world.getWorldInfo().getWorldName() + ">";
    }

}
