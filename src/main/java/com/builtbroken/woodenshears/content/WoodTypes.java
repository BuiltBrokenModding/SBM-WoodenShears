package com.builtbroken.woodenshears.content;

import com.builtbroken.woodenshears.WoodenShears;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 9/20/2016.
 */
public enum WoodTypes
{
    OAK("oak", "minecraft:planks", 0),
    ACACIA("acacia", "minecraft:planks", 4),
    BIRCH("birch", "minecraft:planks", 2),
    JUNGLE("jungle", "minecraft:planks", 3),
    SPRUCE("spruce", "minecraft:planks", 1),
    BIG_OAK("big_oak", "minecraft:planks", 5),
    CHARRED("charred");
    //TODO add support for other mods

    /** Name to use for textures and unlocalizations */
    public final String name;

    public String blockID;
    public int blockMeta;

    @SideOnly(Side.CLIENT)
    public IIcon icon;

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

    public void registerRecipe()
    {
        if(blockID != null)
        {
            Block block = Block.getBlockFromName(blockID);
            if(block != null)
            {
                ItemStack stack = new ItemStack(WoodenShears.itemShears);
                WoodenShears.itemShears.setType(stack, this);
                GameRegistry.addRecipe(new ShapedOreRecipe(stack,
                        "w w",
                        " t ",
                        "s s",
                        'w', new ItemStack(block, 1, blockMeta),
                        't', "treeSapling",
                        's', "stickWood"));
            }
        }
    }
}
