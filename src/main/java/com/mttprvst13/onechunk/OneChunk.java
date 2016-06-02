package com.mttprvst13.onechunk;

import com.mttprvst13.onechunk.providers.OneChunkNetherProvider;
import com.mttprvst13.onechunk.providers.OneChunkSurfaceProvider;
import com.mttprvst13.onechunk.world.OneChunkWorldType;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldType;
import net.minecraftforge.common.DimensionManager;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

@Mod(modid = OneChunk.MODID, version = OneChunk.VERSION)
public class OneChunk
{

    public static final String MODID = "onechunk";
    public static final String VERSION = "1.0";

    public static OneChunkWorldType worldType;
    private OneChunkSurfaceProvider surfaceProvider;
    private Map<String, OneChunkSurfaceProvider> generators = new HashMap();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        //GameRegistry.registerWorldGenerator(gen, 2);
        //generators.put("onechunk", new OneChunkSurfaceProvider())
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        FMLLog.info("One Chunk Companion mod Initialized");
        worldType = new OneChunkWorldType();
        //GameRegistry.registerWorldGenerator(gen, 0);
        Hashtable<Integer, Class<? extends WorldProvider>> providers = ReflectionHelper.getPrivateValue(DimensionManager.class, null, "providers");
        providers.put(-1, OneChunkNetherProvider.class);
        providers.put(0, OneChunkSurfaceProvider.class);
    }

    private int getNextWorldType()
    {
        for (int idx=0; idx< WorldType.worldTypes.length; idx++)
            if (WorldType.worldTypes[idx]==null)
                return idx;
        return -1;
    }
}
