package com.builtbroken.woodenshears;

import com.builtbroken.woodenshears.content.WoodTypes;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;

public class WoodenShearsConfig
{
    public static final ForgeConfigSpec CONFIG_SPEC;
    public static final WoodenShearsConfig CONFIG;

    static
    {
        Pair<WoodenShearsConfig,ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(WoodenShearsConfig::new);

        CONFIG_SPEC = specPair.getRight();
        CONFIG = specPair.getLeft();
    }

    public WoodenShearsConfig(ForgeConfigSpec.Builder builder)
    {
        //Durability Settings
        builder.comment("Durability settings").push("durability");
        Arrays.stream(WoodTypes.values()).forEach(type -> type.genDurabilityConfig(builder));
        builder.pop();
    }
}
