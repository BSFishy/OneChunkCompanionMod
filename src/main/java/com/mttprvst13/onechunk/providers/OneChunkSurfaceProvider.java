package com.mttprvst13.onechunk.providers;

import com.mttprvst13.onechunk.OneChunk;
import com.mttprvst13.onechunk.world.OneChunkChunkManager;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.chunk.IChunkProvider;

public class OneChunkSurfaceProvider extends WorldProviderSurface
{

    private OneChunkSurfaceChunkProvider provider;

    public OneChunkSurfaceProvider()
    {

    }

    public OneChunkSurfaceProvider(OneChunkSurfaceChunkProvider pro)
    {
        provider = pro;
    }

    @Override
    public boolean canCoordinateBeSpawn(int x, int z)
    {
        if(OneChunk.instance.getOverrideWorld(worldObj))
        {
            if (0 <= x && x < 16 && 0 <= z && z < 16)
                return true;
            else
                return false;
        }
        else
            return super.canCoordinateBeSpawn(x, z);
    }

    @Override
    public ChunkCoordinates getRandomizedSpawnPoint(){
        if(OneChunk.instance.getOverrideWorld(worldObj))
        {
            ChunkCoordinates pos = new ChunkCoordinates(7, 0, 7);
            pos.posY = worldObj.getTopSolidOrLiquidBlock(pos.posX, pos.posZ);
            return pos;
        }
        else
            return super.getRandomizedSpawnPoint();
    }

    @Override
    public void registerWorldChunkManager()
    {
        if(OneChunk.instance.getOverrideWorld(worldObj))
            worldChunkMgr = new OneChunkChunkManager(worldObj, provider);
        else
            worldChunkMgr = terrainType.getChunkManager(worldObj);
    }

    @Override
    public IChunkProvider createChunkGenerator()
    {
        if(OneChunk.instance.getOverrideWorld(worldObj))
            return new OneChunkSurfaceChunkProvider(worldObj);
        else
            return terrainType.getChunkGenerator(worldObj, field_82913_c);
    }

}
