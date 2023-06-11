package com.builtbroken.woodenshears.content;
import com.builtbroken.woodenshears.WoodenShears;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.ForgeConfigSpec;

/**
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 9/20/2016.
 */
public enum WoodTypes
{
    /* 0 */OAK("oak", "oak", Blocks.OAK_PLANKS),
    /* 1 */ACACIA("acacia", "acacia", Blocks.ACACIA_PLANKS),
    /* 2 */BIRCH("birch", "birch", Blocks.BIRCH_PLANKS),
    /* 3 */JUNGLE("jungle", "jungle", Blocks.JUNGLE_PLANKS),
    /* 4 */SPRUCE("spruce", "spruce", Blocks.SPRUCE_PLANKS),
    /* 5 */BIG_OAK("big_oak", "dark_oak", Blocks.DARK_OAK_PLANKS),
    /* 6 */CHARRED("charred", "charred", null),
    /* 7 */CRIMSON("crimson", "crimson", Blocks.CRIMSON_PLANKS),
    /* 8 */WARPED("warped", "warped", Blocks.WARPED_PLANKS),
    /* 9 */MANGROVE("mangrove", "mangrove", Blocks.MANGROVE_PLANKS),
    /* 10 */BAMBOO("bamboo", "bamboo", Blocks.BAMBOO_PLANKS),
    /* 11 */CHERRY("cherry", "cherry", Blocks.CHERRY_PLANKS);

    /** Name to use for textures and localizations */
    public final String itemName;
    /** Prefix for config names */
    public final String configName;
    /** Planks for recipe generation */
    public final Block planksBlock;
    /** Required feature flags for experimental blocks (example: bamboo shear for 1.20 experimental pack) */
    public final FeatureFlag[] requiredFeatures;

    private ForgeConfigSpec.IntValue durability;

    WoodTypes(String name, String configName, Block planksBlock)
    {
        this(name, configName, planksBlock, FeatureFlags.VANILLA);
    }

    WoodTypes(String name, String configName, Block planksBlock, FeatureFlag... requiredFeatures)
    {
        this.itemName = name;
        this.configName = configName;
        this.planksBlock = planksBlock;
        this.requiredFeatures = requiredFeatures;
    }

    public void genDurabilityConfig(ForgeConfigSpec.Builder builder) {
        durability = builder.defineInRange("durability_" + configName, getDurability(), 1, 10000);
    }

    public int getDurability() {
        return durability != null ? durability.get() : 50;
    }

    public ResourceLocation getItemRegistryName() {
        return new ResourceLocation(WoodenShears.DOMAIN, "wshears_" + itemName);
    }
}
