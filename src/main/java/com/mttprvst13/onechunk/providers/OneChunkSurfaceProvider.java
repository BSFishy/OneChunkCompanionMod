package com.mttprvst13.onechunk.providers;

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
        if(0 <= x && x < 16 && 0 <= z && z < 16)
            return true;
        else
            return false;
    }

    @Override
    public ChunkCoordinates getRandomizedSpawnPoint(){
        /*ChunkCoordinates pos = new ChunkCoordinates(worldObj.getSpawnPoint());
        pos.posY = worldObj.getTopSolidOrLiquidBlock(pos.posX, pos.posZ);*/
        ChunkCoordinates pos = new ChunkCoordinates(7, 0, 7);
        pos.posY = worldObj.getTopSolidOrLiquidBlock(pos.posX, pos.posZ);
        return pos;
    }

    @Override
    public void registerWorldChunkManager()
    {
        worldChunkMgr = new OneChunkChunkManager(worldObj, provider);
    }

    @Override
    public IChunkProvider createChunkGenerator()
    {
        //return provider;
        return new OneChunkSurfaceChunkProvider(worldObj);
    }

}
