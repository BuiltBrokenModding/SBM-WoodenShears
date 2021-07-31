package com.builtbroken.woodenshears.content;
import net.minecraftforge.common.ForgeConfigSpec;

/**
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 9/20/2016.
 */
public enum WoodTypes
{
    /* 0 */OAK("oak", "oak"),
    /* 1 */ACACIA("acacia", "acacia"),
    /* 2 */BIRCH("birch", "birch"),
    /* 3 */JUNGLE("jungle", "jungle"),
    /* 4 */SPRUCE("spruce", "spruce"),
    /* 5 */BIG_OAK("big_oak", "dark_oak"),
    /* 6 */CHARRED("charred", "charred"),
    /* 7 */CRIMSON("crimson", "crimson"),
    /* 8 */WARPED("warped", "warped");

    /** Name to use for textures and localizations */
    public final String itemName;
    /** Prefix for config names */
    public final String configName;

    private ForgeConfigSpec.IntValue durability;

    WoodTypes(String name, String configName)
    {
        this.itemName = name;
        this.configName = configName;
    }

    public void genDurabilityConfig(ForgeConfigSpec.Builder builder) {
        durability = builder.defineInRange("durability_" + configName, getDurability(), 1, 10000);
    }

    public int getDurability() {
        return durability != null ? durability.get() : 50;
    }

    public String getItemRegistryName() {
        return "wshears_" + itemName;
    }
}
