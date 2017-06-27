package com.builtbroken.woodenshears.content;

import com.builtbroken.woodenshears.WoodenShears;
import net.minecraft.block.BlockPlanks;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;

/**
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 9/20/2016.
 */
public enum WoodTypes
{
    /* 0 */OAK("oak", "minecraft:planks", BlockPlanks.EnumType.OAK.ordinal()),
    /* 1 */ACACIA("acacia", "minecraft:planks", BlockPlanks.EnumType.ACACIA.ordinal()),
    /* 2 */BIRCH("birch", "minecraft:planks", BlockPlanks.EnumType.BIRCH.ordinal()),
    /* 3 */JUNGLE("jungle", "minecraft:planks", BlockPlanks.EnumType.JUNGLE.ordinal()),
    /* 4 */SPRUCE("spruce", "minecraft:planks", BlockPlanks.EnumType.SPRUCE.ordinal()),
    /* 5 */BIG_OAK("big_oak", "minecraft:planks", BlockPlanks.EnumType.DARK_OAK.ordinal()),
    /* 6 */CHARRED("charred");
    //TODO add support for other mods

    /** Name to use for textures and unlocalizations */
    public final String name;

    public String blockID;
    public int blockMeta;

    private ModelResourceLocation modelResourceLocation;

    WoodTypes(String name, String blockID, int meta)
    {
        this(name);
        this.blockID = blockID;
        this.blockMeta = meta;
    }

    WoodTypes(String name)
    {
        this.name = name;
    }

    /** Model for this type */
    public ModelResourceLocation getModelResourceLocation()
    {
        if (modelResourceLocation == null)
        {
            modelResourceLocation = new ModelResourceLocation(WoodenShears.itemShears.getRegistryName() + "_" + name, "inventory");
        }
        return modelResourceLocation;
    }
}
