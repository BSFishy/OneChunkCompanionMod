package com.mttprvst13.onechunk.providers;

import net.minecraft.world.chunk.Chunk;

public interface ChunkProvider {

    public Chunk generateChunk(int x, int z);

}
