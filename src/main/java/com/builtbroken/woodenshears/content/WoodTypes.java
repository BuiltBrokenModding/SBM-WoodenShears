package com.builtbroken.woodenshears.content;

import net.minecraft.init.Blocks;

/**
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 9/20/2016.
 */
public enum WoodTypes
{
    /* 0 */OAK("oak", Blocks.OAK_PLANKS.getRegistryName().toString()),
    /* 1 */ACACIA("acacia", Blocks.ACACIA_PLANKS.getRegistryName().toString()),
    /* 2 */BIRCH("birch", Blocks.BIRCH_PLANKS.getRegistryName().toString()),
    /* 3 */JUNGLE("jungle", Blocks.JUNGLE_PLANKS.getRegistryName().toString()),
    /* 4 */SPRUCE("spruce", Blocks.SPRUCE_PLANKS.getRegistryName().toString()),
    /* 5 */BIG_OAK("big_oak", Blocks.DARK_OAK_PLANKS.getRegistryName().toString()),
    /* 6 */CHARRED("charred");
    //TODO add support for other mods

    /** Name to use for textures and localizations */
    public final String name;

    public String blockID;

    WoodTypes(String name, String blockID)
    {
        this(name);
        this.blockID = blockID;
    }

    WoodTypes(String name)
    {
        this.name = name;
    }
}
