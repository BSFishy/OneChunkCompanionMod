package com.mttprvst13.onechunk.physicals.blocks.chunkgenerator;

import com.mttprvst13.onechunk.physicals.blocks.IOneChunkBlock;
import cpw.mods.fml.common.registry.GameRegistry;
import mcjty.lib.container.GenericItemBlock;

public class SetupChunkGenerator implements IOneChunkBlock
{

    public OneChunkBlockChunkGenerator chunkGenerator;

    public void setupBlock()
    {
        chunkGenerator = new OneChunkBlockChunkGenerator("chunkgenerator", "chunkgenerator", ChunkGeneratorTileEntity.class);
        GameRegistry.registerBlock(chunkGenerator, GenericItemBlock.class, "chunkgenerator");
        GameRegistry.registerTileEntity(ChunkGeneratorTileEntity.class, "ChunkGeneratorTileEntity");
    }

    public void setupCrafting()
    {
        //TODO:Add the crafting recipe for this
    }
}
