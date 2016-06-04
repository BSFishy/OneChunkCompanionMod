package com.mttprvst13.onechunk.physicals.blocks.chunkgenerator;

import com.mttprvst13.onechunk.physicals.items.OneChunkItemChunkSelector;
import mcjty.lib.container.ContainerFactory;
import mcjty.lib.container.GenericContainer;
import mcjty.lib.container.SlotDefinition;
import mcjty.lib.container.SlotType;
import net.minecraft.entity.player.EntityPlayer;

public class ChunkGeneratorContainer extends GenericContainer
{

    public static final String CONTAINER_INVENTORY = "container";
    private final ChunkGeneratorTE chunkGeneratorTE;

    public static final ContainerFactory factory = new ContainerFactory()
    {
        @Override
        protected void setup()
        {
            addSlot(new SlotDefinition(SlotType.SLOT_SPECIFICITEM, OneChunkItemChunkSelector.class), CONTAINER_INVENTORY, 0, 31, 13);
            layoutPlayerInventorySlots(85, 142);
        }
    };

    public ChunkGeneratorContainer(EntityPlayer player, ChunkGeneratorTE containerInvenroty)
    {
        super(factory);
        chunkGeneratorTE = containerInvenroty;
        addInventory(CONTAINER_INVENTORY, containerInvenroty);
        addInventory(ContainerFactory.CONTAINER_PLAYER, player.inventory);
        generateSlots();
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer)
    {
        return false;
    }
}
