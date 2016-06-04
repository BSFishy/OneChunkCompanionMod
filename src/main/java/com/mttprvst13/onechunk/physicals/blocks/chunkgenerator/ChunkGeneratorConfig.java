package com.mttprvst13.onechunk.physicals.blocks.chunkgenerator;

import net.minecraftforge.common.config.Configuration;

public class ChunkGeneratorConfig
{

    public static final String CATEGORY_CHUNKGEN = "ChunkGenerator";
    public static int MAXENERGY = 50000;
    public static int RFUSED = 25000;
    public static int RECIEVEPERTICK = 500;

    public static void init(Configuration config)
    {
        MAXENERGY = config.get(CATEGORY_CHUNKGEN, "MaxStoredRF", MAXENERGY, "The maximum amount of RF the chunk generator can hold").getInt();
        RFUSED = config.get(CATEGORY_CHUNKGEN, "RFUsed", RFUSED, "The amount of RF used per chunk generation").getInt();
        RECIEVEPERTICK = config.get(CATEGORY_CHUNKGEN, "InputRFPerTick", RECIEVEPERTICK, "The amount of RF the machine will recieve per tick").getInt();
    }

}
