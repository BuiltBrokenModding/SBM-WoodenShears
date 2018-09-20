package com.builtbroken.woodenshears;

import java.io.File;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.builtbroken.woodenshears.content.ItemWoodenShear;
import com.builtbroken.woodenshears.content.WoodTypes;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

@Mod(modid = WoodenShears.DOMAIN, name = "Wooden Shears", version = "@MAJOR@.@MINOR@.@REVIS@.@BUILD@")
public class WoodenShears
{
    public static final String DOMAIN = "woodenshears";
    public static final String PREFIX = DOMAIN + ":";

    @SidedProxy(clientSide = "com.builtbroken.woodenshears.ClientProxy", serverSide = "com.builtbroken.woodenshears.CommonProxy")
    public static CommonProxy proxy;

    public static Logger LOGGER;

    public static Configuration config;

    public static final int DEFAULT_DURABILITY = 50;
    public static int OAK_DURABILITY = 50;
    public static int SPRUCE_DURABILITY = 50;
    public static int BIRCH_DURABILITY = 50;
    public static int JUNGLE_DURABILITY = 50;
    public static int ACACIA_DURABILITY = 50;
    public static int DARK_OAK_DURABILITY = 50;
    public static int CHARRED_DURABILITY = 50;

    public static ItemWoodenShear itemShears;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        LOGGER = LogManager.getLogger("WoodenShears");
        config = new Configuration(new File(event.getModConfigurationDirectory(), "bbm/Wooden_Shears.cfg"));
        config.load();
        OAK_DURABILITY = config.getInt("Oak_Durability", Configuration.CATEGORY_GENERAL, DEFAULT_DURABILITY, 10, 1000, "Sets how many uses the oak shears have before breaking");
        SPRUCE_DURABILITY = config.getInt("Spruce_Durability", Configuration.CATEGORY_GENERAL, DEFAULT_DURABILITY, 10, 1000, "Sets how many uses the spruce shears have before breaking");
        BIRCH_DURABILITY = config.getInt("Birch_Durability", Configuration.CATEGORY_GENERAL, DEFAULT_DURABILITY, 10, 1000, "Sets how many uses the birch shears have before breaking");
        JUNGLE_DURABILITY = config.getInt("Jungle_Durability", Configuration.CATEGORY_GENERAL, DEFAULT_DURABILITY, 10, 1000, "Sets how many uses the jungle shears hav before breaking");
        ACACIA_DURABILITY = config.getInt("Acacia_Durability", Configuration.CATEGORY_GENERAL, DEFAULT_DURABILITY, 10, 1000, "Sets how many uses the acacia shears have before breaking");
        DARK_OAK_DURABILITY = config.getInt("Dark_Oak_Durability", Configuration.CATEGORY_GENERAL, DEFAULT_DURABILITY, 10, 1000, "Sets how many uses the dark oak shears have before breaking");
        CHARRED_DURABILITY = config.getInt("Charred_Durability", Configuration.CATEGORY_GENERAL, DEFAULT_DURABILITY, 10, 1000, "Sets how many uses the charred shears have before breaking");

        itemShears = new ItemWoodenShear();
        GameRegistry.register(itemShears, itemShears.resourceLocation);

        proxy.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        for (WoodTypes type : WoodTypes.values())
        {
            type.registerRecipe();
        }
        //Support for ore dictionary planks that are not natively supported with custom textures
        List<ItemStack> items = OreDictionary.getOres("plankWood");
        for (ItemStack stack : items)
        {
            if (stack != null && stack.getItem() != Item.getItemFromBlock(Blocks.PLANKS))
            {
                //TODO ensure type is not already supported
                GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(WoodenShears.itemShears),
                        "w w",
                        " t ",
                        "s s",
                        'w', stack,
                        't', "treeSapling",
                        's', "stickWood"));
            }
        }
        proxy.postInit();
        config.save();
    }
}
