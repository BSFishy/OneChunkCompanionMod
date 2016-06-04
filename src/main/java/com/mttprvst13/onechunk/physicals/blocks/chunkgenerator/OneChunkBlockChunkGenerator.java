package com.mttprvst13.onechunk.physicals.blocks.chunkgenerator;

import com.mttprvst13.onechunk.OneChunk;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mcjty.lib.container.GenericBlock;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class OneChunkBlockChunkGenerator extends GenericBlock {

    private String frontName;

    public OneChunkBlockChunkGenerator(String blockName, String frontName, Class<? extends TileEntity> tileEntityClass) {
        super(OneChunk.instance, Material.iron, tileEntityClass, true);
        setBlockName(blockName);
        this.frontName = frontName;
        setCreativeTab(CreativeTabs.tabMisc);
    }

    @Override
    public String getIdentifyingIconName() {
        return frontName;
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

    @Override
    @SideOnly(Side.CLIENT)
    public GuiContainer createClientGui(EntityPlayer player, TileEntity tileEntity)
    {
        ChunkGeneratorTE chunkGeneratorBlockTileEntity = (ChunkGeneratorTE) tileEntity;
        ChunkGeneratorContainer chunkGeneratorContainer = new ChunkGeneratorContainer(player, chunkGeneratorBlockTileEntity);
        return new ChunkGeneratorGui(chunkGeneratorBlockTileEntity, chunkGeneratorContainer);
    }

    @Override
    public Container createServerContainer(EntityPlayer entityPlayer, TileEntity tileEntity) {
        return new ChunkGeneratorContainer(entityPlayer, (ChunkGeneratorTE) tileEntity);
    }
}
