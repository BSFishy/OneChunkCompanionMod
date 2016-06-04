package com.mttprvst13.onechunk.physicals.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import java.util.List;

public class OneChunkItemChunkSelector extends OneChunkItem {

    private boolean glowing = false;

    public OneChunkItemChunkSelector()
    {
        setMaxStackSize(1);
        setCreativeTab(CreativeTabs.tabMisc);
        setUnlocalizedName(getUnlocalizedName());
        setTextureName("onechunk:ChunkSelector");
    }

    public String getUnlocalizedName()
    {
        return "chunkSelector";
    }

    @Override
    public boolean hasEffect(ItemStack item)
    {
        return glowing;
    }

    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        if(player.dimension == 1)
            return false;
        if(stack.stackTagCompound == null)
        {
            stack.stackTagCompound = new NBTTagCompound();
        }
        stack.stackTagCompound.setInteger("PositionX", player.worldObj.getChunkFromBlockCoords(player.playerLocation.posX, player.playerLocation.posZ).xPosition);
        stack.stackTagCompound.setInteger("PositionZ", player.worldObj.getChunkFromBlockCoords(player.playerLocation.posX, player.playerLocation.posZ).zPosition);
        stack.stackTagCompound.setInteger("Dimension", player.dimension);

        glowing = true;
        //player.chat
        return false;
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
    {
        if(stack.stackTagCompound == null)
        {
            return;
        }
        list.add(EnumChatFormatting.GREEN + "Chunk X: " + stack.stackTagCompound.getInteger("PositionX"));
        list.add(EnumChatFormatting.GREEN + "Chunk Z: " + stack.stackTagCompound.getInteger("PositionZ"));
        String dim = "";
        switch(stack.stackTagCompound.getInteger("Dimension"))
        {
            case -1:
                dim = "Nether";
                break;
            case 0:
                dim = "Overworld";
                break;
            case 1:
                dim = "End";
        }
        list.add(EnumChatFormatting.GREEN + "Dimension: " + dim);
    }
}
