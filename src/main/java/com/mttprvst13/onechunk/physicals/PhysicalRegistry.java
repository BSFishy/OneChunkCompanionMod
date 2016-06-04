package com.mttprvst13.onechunk.physicals;

import com.mttprvst13.onechunk.OneChunk;
import com.mttprvst13.onechunk.physicals.blocks.IOneChunkBlock;
import com.mttprvst13.onechunk.physicals.blocks.chunkgenerator.SetupChunkGenerator;
import com.mttprvst13.onechunk.physicals.items.OneChunkItem;
import com.mttprvst13.onechunk.physicals.items.OneChunkItemChunkSelector;
import cpw.mods.fml.common.registry.GameRegistry;

public class PhysicalRegistry
{

    private static int itemIndex = 0;
    private static int blockIndex = 0;
    public static int CHUNKSELECTORINDEX = itemIndex++;
    public static int CHUNKGENERATORINDEX = blockIndex++;

    private static OneChunkItem[] items = new OneChunkItem[itemIndex+1];
    private static IOneChunkBlock[] blocks = new IOneChunkBlock[blockIndex+1];
    public void register(){

        if(!OneChunk.instance.getAllowNewChunks())
            return;

        items[CHUNKSELECTORINDEX] = new OneChunkItemChunkSelector();
        blocks[CHUNKGENERATORINDEX] = new SetupChunkGenerator();

        for(OneChunkItem item : items)
        {
            GameRegistry.registerItem(item, item.getUnlocalizedName());
        }
        for(IOneChunkBlock block : blocks)
        {
            block.setupBlock();
            block.setupCrafting();
        }
    }

}
