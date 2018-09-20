package com.builtbroken.woodenshears;

import net.minecraftforge.common.config.Config;

@Config(modid=WoodenShears.DOMAIN, name="bbm/Wooden_Shears")
public class WoodenShearsConfig
{
    @Config.Name("durability_oak")
    @Config.Comment("Sets how many uses the oak shears have before breaking")
    @Config.RangeInt(min=10, max=1000)
    public static int OAK_DURABILITY = WoodenShears.DEFAULT_DURABILITY;

    @Config.Name("durability_spruce")
    @Config.Comment("Sets how many uses the spruce shears have before breaking")
    @Config.RangeInt(min=10, max=1000)
    public static int SPRUCE_DURABILITY = WoodenShears.DEFAULT_DURABILITY;

    @Config.Name("durability_birch")
    @Config.Comment("Sets how many uses the birch shears have before breaking")
    @Config.RangeInt(min=10, max=1000)
    public static int BIRCH_DURABILITY = WoodenShears.DEFAULT_DURABILITY;

    @Config.Name("durability_jungle")
    @Config.Comment("Sets how many uses the jungle shears hav before breaking")
    @Config.RangeInt(min=10, max=1000)
    public static int JUNGLE_DURABILITY = WoodenShears.DEFAULT_DURABILITY;

    @Config.Name("durability_acacia")
    @Config.Comment("Sets how many uses the acacia shears have before breaking")
    @Config.RangeInt(min=10, max=1000)
    public static int ACACIA_DURABILITY = WoodenShears.DEFAULT_DURABILITY;

    @Config.Name("durability_dark_oak")
    @Config.Comment("Sets how many uses the dark oak shears have before breaking")
    @Config.RangeInt(min=10, max=1000)
    public static int DARK_OAK_DURABILITY = WoodenShears.DEFAULT_DURABILITY;

    @Config.Name("durability_charred")
    @Config.Comment("Sets how many uses the charred shears have before breaking")
    @Config.RangeInt(min=10, max=1000)
    public static int CHARRED_DURABILITY = WoodenShears.DEFAULT_DURABILITY;
}
