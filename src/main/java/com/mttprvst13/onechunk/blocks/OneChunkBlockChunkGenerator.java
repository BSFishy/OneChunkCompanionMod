package com.mttprvst13.onechunk.blocks;

import com.mttprvst13.onechunk.OneChunk;
import mcjty.lib.container.GenericBlock;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class OneChunkBlockChunkGenerator extends GenericBlock {

    public OneChunkBlockChunkGenerator(Material material, Class<? extends TileEntity> tileEntityClass, boolean isContainer) {
        super(OneChunk.instance, material, tileEntityClass, isContainer);
    }

    @Override
    public int getGuiID() {
        return OneChunk.GUI_CHUNK_GENERATOR;
    }

    @Override public String getSideIconName() {       return "chunkCreatorSide"; }
    @Override public String getTopIconName() {        return "chunkCreatorTB"; }
    @Override public String getBottomIconName() {     return "chunkCreatorTB"; }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return null;
    }
}
