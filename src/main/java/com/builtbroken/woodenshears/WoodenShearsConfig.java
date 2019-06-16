package com.builtbroken.woodenshears;


import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.Map;

public class WoodenShearsConfig
{
    public static Map<String,Integer> map = new HashMap<>();
    public static final ServerConfig SERVER;
    public static final ForgeConfigSpec SERVER_SPEC;

    static {
        final Pair<ServerConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ServerConfig::new);
        SERVER_SPEC = specPair.getRight();
        SERVER = specPair.getLeft();
    }

    public static class ServerConfig {

        public static ForgeConfigSpec.IntValue oak_durability;
        public static ForgeConfigSpec.IntValue spruce_durability;
        public static ForgeConfigSpec.IntValue birch_durability;
        public static ForgeConfigSpec.IntValue jungle_durability;
        public static ForgeConfigSpec.IntValue acacia_durability;
        public static ForgeConfigSpec.IntValue dark_oak_durability;
        public static ForgeConfigSpec.IntValue charred_durability;

        ServerConfig(ForgeConfigSpec.Builder builder) {
            builder.push("general");
            oak_durability = builder
                    .comment("Sets how many uses the oak shears have before breaking")
                    .translation("text.recipes.oak_shears.config.durability")
                    .defineInRange("offset",50,10,1000);
            spruce_durability = builder
                    .comment("Sets how many uses the spruce shears have before breaking")
                    .translation("text.recipes.spruce_shears.config.durability")
                    .defineInRange("offset",50,10,1000);
            birch_durability = builder
                    .comment("Sets how many uses the birch shears have before breaking")
                    .translation("text.recipes.birch_shears.config.durability")
                    .defineInRange("offset",50,10,1000);
            jungle_durability = builder
                    .comment("Sets how many uses the jungle shears have before breaking")
                    .translation("text.recipes.jungle_shears.config.durability")
                    .defineInRange("offset",50,10,1000);
            acacia_durability = builder
                    .comment("Sets how many uses the acacia shears have before breaking")
                    .translation("text.recipes.acacia_shears.config.durability")
                    .defineInRange("offset",50,10,1000);
            dark_oak_durability = builder
                    .comment("Sets how many uses the dark oak shears have before breaking")
                    .translation("text.recipes.dark_oak_shears.config.durability")
                    .defineInRange("offset",50,10,1000);
            charred_durability = builder
                    .comment("Sets how many uses the charred shears have before breaking")
                    .translation("text.recipes.acacia_shears.config.durability")
                    .defineInRange("charred",50,10,1000);
            builder.pop();
        }
    }


    public static void bake(){
        map.put("acacia",ServerConfig.acacia_durability.get());
        map.put("birch",ServerConfig.birch_durability.get());
        map.put("dark_oak",ServerConfig.dark_oak_durability.get());
        map.put("jungle",ServerConfig.jungle_durability.get());
        map.put("spruce",ServerConfig.spruce_durability.get());
        map.put("oak",ServerConfig.oak_durability.get());
        map.put("charred",ServerConfig.charred_durability.get());
    }
}
