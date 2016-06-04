package com.mttprvst13.onechunk.physicals;

import com.mttprvst13.onechunk.physicals.items.OneChunkItem;
import com.mttprvst13.onechunk.physicals.items.OneChunkItemChunkSelector;
import cpw.mods.fml.common.registry.GameRegistry;

public class PhysicalRegistry
{

    private static int itemIndex = 0;
    private static int CHUNKSELECTORINDEX = itemIndex++;

    private static OneChunkItem[] items = new OneChunkItem[itemIndex+1];

    public void register(){
        items[CHUNKSELECTORINDEX] = new OneChunkItemChunkSelector();

        for(OneChunkItem item : items)
        {
            GameRegistry.registerItem(item, item.getUnlocalizedName());
        }
    }

}
