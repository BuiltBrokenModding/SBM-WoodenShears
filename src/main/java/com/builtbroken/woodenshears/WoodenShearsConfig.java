package com.builtbroken.woodenshears;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class WoodenShearsConfig
{
    public static final ForgeConfigSpec CONFIG_SPEC;
    public static final WoodenShearsConfig CONFIG;

    public static final int DEFAULT_DURABILITY = 50;
    public final IntValue oakDurability;
    public final IntValue spruceDurability;
    public final IntValue birchDurability;
    public final IntValue jungleDurability;
    public final IntValue acaciaDurability;
    public final IntValue darkOakDurability;
    public final IntValue charredDurability;

    static
    {
        Pair<WoodenShearsConfig,ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(WoodenShearsConfig::new);

        CONFIG_SPEC = specPair.getRight();
        CONFIG = specPair.getLeft();
    }

    public WoodenShearsConfig(ForgeConfigSpec.Builder builder)
    {
        builder.comment("Durability settings")
        .push("durability");
        oakDurability = builder
                .comment("Sets how many uses the oak shears have before breaking")
                .defineInRange("durability_oak", DEFAULT_DURABILITY, 10, 1000);
        spruceDurability = builder
                .comment("Sets how many uses the spruce shears have before breaking")
                .defineInRange("durability_spruce", DEFAULT_DURABILITY, 10, 1000);
        birchDurability = builder
                .comment("Sets how many uses the birch shears have before breaking")
                .defineInRange("durability_birch", DEFAULT_DURABILITY, 10, 1000);
        jungleDurability = builder
                .comment("Sets how many uses the jungle shears hav before breaking")
                .defineInRange("durability_jungle", DEFAULT_DURABILITY, 10, 1000);
        acaciaDurability = builder
                .comment("Sets how many uses the acacia shears have before breaking")
                .defineInRange("durability_acacia", DEFAULT_DURABILITY, 10, 1000);
        darkOakDurability = builder
                .comment("Sets how many uses the dark oak shears have before breaking")
                .defineInRange("durability_dark_oak", DEFAULT_DURABILITY, 10, 1000);
        charredDurability = builder
                .comment("Sets how many uses the charred shears have before breaking")
                .defineInRange("durability_charred", DEFAULT_DURABILITY, 10, 1000);
        builder.pop();
    }
}
