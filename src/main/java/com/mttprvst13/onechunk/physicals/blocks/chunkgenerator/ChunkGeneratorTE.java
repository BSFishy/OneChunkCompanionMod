package com.mttprvst13.onechunk.physicals.blocks.chunkgenerator;

import com.mttprvst13.onechunk.physicals.items.OneChunkItemChunkSelector;
import mcjty.lib.container.InventoryHelper;
import mcjty.lib.entity.GenericEnergyReceiverTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;

public class ChunkGeneratorTE extends GenericEnergyReceiverTileEntity implements ISidedInventory {

    public InventoryHelper inventoryHelper = new InventoryHelper(this, ChunkGeneratorContainer.factory, 1);

    public ChunkGeneratorTE()
    {
        super(ChunkGeneratorConfig.MAXENERGY, ChunkGeneratorConfig.RECIEVEPERTICK);
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int i) {
        return new int[0];
    }

    @Override
    public boolean canInsertItem(int i, ItemStack itemStack, int i1) {
        return false;
    }

    @Override
    public boolean canExtractItem(int i, ItemStack itemStack, int i1) {
        return false;
    }

    @Override
    public int getSizeInventory() {
        return inventoryHelper.getCount();
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return inventoryHelper.getStackInSlot(i);
    }

    @Override
    public ItemStack decrStackSize(int i, int i1) {
        return inventoryHelper.decrStackSize(i, i1);
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemStack) {
        inventoryHelper.setInventorySlotContents(getInventoryStackLimit(), i, itemStack);
    }

    @Override
    public String getInventoryName() {
        return "Chunk Generator";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityPlayer) {
        return canPlayerAccess(entityPlayer);
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemStack) {
        return itemStack.getItem() instanceof OneChunkItemChunkSelector;
    }
}
