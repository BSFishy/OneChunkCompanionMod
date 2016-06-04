package com.mttprvst13.onechunk.physicals.blocks.chunkgenerator;

import com.mttprvst13.onechunk.OneChunk;
import com.mttprvst13.onechunk.providers.ChunkProvider;
import com.mttprvst13.onechunk.providers.OneChunkSurfaceChunkProvider;
import mcjty.lib.container.GenericGuiContainer;
import mcjty.lib.gui.events.ButtonEvent;
import mcjty.lib.gui.layout.PositionalLayout;
import mcjty.lib.gui.widgets.Button;
import mcjty.lib.gui.widgets.EnergyBar;
import mcjty.lib.gui.widgets.Widget;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;

public class ChunkGeneratorGui extends GenericGuiContainer<ChunkGeneratorTE>
{

    public static final int CHUNK_GENERATOR_WIDTH = 256;
    public static final int CHUNK_GENERATOR_HEIGHT = 224;

    private EnergyBar energyBar;
    private Button generateButton;

    public ChunkGeneratorGui(ChunkGeneratorTE chunkGeneratorBlockTileEntity, ChunkGeneratorContainer container)
    {
        super(OneChunk.instance, network, chunkGeneratorBlockTileEntity, container, OneChunk.GUI_CHUNK_GENERATOR, "chunkgenerator");
        chunkGeneratorBlockTileEntity.setCurrentRF(chunkGeneratorBlockTileEntity.getEnergyStored(ForgeDirection.DOWN));

        xSize = CHUNK_GENERATOR_WIDTH;
        ySize = CHUNK_GENERATOR_WIDTH;
    }

    @Override
    public void initGui()
    {
        super.initGui();

        // Energy bar
        int maxEnergyStored = tileEntity.getMaxEnergyStored(ForgeDirection.DOWN);
        energyBar = new EnergyBar(mc, this).setVertical().setMaxValue(maxEnergyStored).setLayoutHint(new PositionalLayout.PositionalHint(12, 141, 10, 76)).setShowText(false);
        energyBar.setValue(tileEntity.getCurrentRF());

        generateButton = new Button(mc, this).
                setText("Generate").
                setTooltips("Press to generate", "a new chunk at a", "specific position").
                addButtonEvent(new ButtonEvent() {
                    @Override
                    public void buttonClicked(Widget widget) {
                        ItemStack i = tileEntity.getStackInSlot(0);
                        ChunkProvider p;
                        if(i.stackTagCompound.getInteger("Dimension") == 0) p = (OneChunkSurfaceChunkProvider) OneChunk.instance.worldType.getChunkGenerator(tileEntity.getWorldObj(), "");
                        p.generateChunk(i.stackTagCompound.getInteger("PositionX"), i.stackTagCompound.getInteger("PositionZ"));
                        //TODO: Finish up this, and add nether generator
                    }
                });
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float v, int i, int i1) {
        drawWindow();
        int currentRF = tileEntity.getCurrentRF();
        energyBar.setValue(currentRF);
    }
}
