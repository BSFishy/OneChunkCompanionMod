package com.mttprvst13.onechunk;

import com.mttprvst13.onechunk.providers.OneChunkNetherProvider;
import com.mttprvst13.onechunk.providers.OneChunkSurfaceProvider;
import com.mttprvst13.onechunk.world.OneChunkWorldType;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.common.Mod.Instance;
import mcjty.lib.base.ModBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import static net.minecraftforge.common.config.Configuration.CATEGORY_GENERAL;

@Mod(modid = OneChunk.MODID, version = OneChunk.VERSION, dependencies =
        "required-after:Forge@[" + OneChunk.MIN_FORGE_VER +
                ",);required-after:CoFHCore@[" + OneChunk.MIN_COFHCORE_VER +
                ",);required-after:McJtyLib@[" + OneChunk.MIN_MCJTYLIB_VER + ",)")
public class OneChunk implements ModBase
{

    @Instance("OneChunk")
    public static OneChunk instance;

    public static final String MODID = "onechunk";
    public static final String VERSION = "1.0";
    public static final String MIN_FORGE_VER = "10.13.4.1614";
    public static final String MIN_COFHCORE_VER = "3.1.2";
    public static final String MIN_MCJTYLIB_VER = "1.8.1";

    public static OneChunkWorldType worldType;
    private OneChunkSurfaceProvider surfaceProvider;
    private Map<String, OneChunkSurfaceProvider> generators = new HashMap();

    Configuration config;

    private boolean isOneChunkWorld = false;
    private boolean allowNewChunksGenerate = false;

    private static int modGuiIndex = 0;

    public static final int GUI_CHUNK_GENERATOR = modGuiIndex++;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();

        Property prop;

        prop = config.get(CATEGORY_GENERAL, "OverrideGeneration", isOneChunkWorld);
        prop.comment = "This will override the generation for any Minecraft world, no matter the generator type.\n" +
                "You might do default generator type, but the generator will always generate the One Chunk generation.";
        isOneChunkWorld = prop.getBoolean(isOneChunkWorld);

        prop = config.get(CATEGORY_GENERAL, "AllowNewChunksToBeGenerated", allowNewChunksGenerate);
        prop.comment = "This enables the block, and item that allows the player to generate new chunks.\n\n" +
                "NOTE: This will default to false if you have OverrideGeneration set to false. There is no need to.";
        allowNewChunksGenerate = isOneChunkWorld && prop.getBoolean(allowNewChunksGenerate);

        if(config.hasChanged())
            config.save();
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

    public boolean getOverrideWorld(World world)
    {
        return isOneChunkWorld || world.getWorldInfo().getTerrainType() == worldType;
    }

    public boolean getAllowNewChunks(World world)
    {
        return allowNewChunksGenerate;
    }

    @Override
    public String getModId() {
        return MODID;
    }

    @Override
    public void openManual(EntityPlayer entityPlayer, int i, String s) {

    }
}
