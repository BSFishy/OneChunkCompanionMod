package com.mttprvst13.onechunk.physicals.blocks.chunkgenerator;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import mcjty.lib.network.NetworkTools;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import io.netty.buffer.ByteBuf;

public class PacketChunkGenerator implements IMessage, IMessageHandler<PacketChunkGenerator, IMessage>
{

    private int x;
    private int y;
    private int z;

    private ItemStack item;

    @Override
    public void fromBytes(ByteBuf buf)
    {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();

        item = NetworkTools.readItemStack(buf);
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);

        NetworkTools.writeItemStack(buf, item);
    }

    public PacketChunkGenerator(int x, int y, int z, ItemStack item)
    {
        this.x = x;
        this.y = y;
        this.z = z;

        this.item = item;
    }

    @Override
    public IMessage onMessage(PacketChunkGenerator message, MessageContext ctx)
    {
        TileEntity te = ctx.getServerHandler().playerEntity.worldObj.getTileEntity(message.x, message.y, message.z);
        if(!(te instanceof ChunkGeneratorTE))
        {
            System.out.println("createPowerMonitotPacket: TileEntity is not a ChunkGeneratorTileEntity!");
            return null;
        }
        ChunkGeneratorTE chunkGeneratorBlockTileEntity = (ChunkGeneratorTE) te;
        //TODO: Possible do something here?
        return null;
    }
}
