package com.builtbroken.woodenshears;

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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.List;

@Mod(modid = WoodenShears.DOMAIN, name = "Wooden Shears", version = "@MAJOR@.@MINOR@.@REVIS@.@BUILD@")
public class WoodenShears
{
    public static final String DOMAIN = "woodenshears";
    public static final String PREFIX = DOMAIN + ":";

    @SidedProxy(clientSide = "com.builtbroken.woodenshears.ClientProxy", serverSide = "com.builtbroken.woodenshears.CommonProxy")
    public static CommonProxy proxy;

    public static Logger LOGGER;

    public static Configuration config;

    public static int MAX_DAMAGE = 50;

    public static ItemWoodenShear itemShears;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        LOGGER = LogManager.getLogger("WoodenShears");
        config = new Configuration(new File(event.getModConfigurationDirectory(), "bbm/Wooden_Shears.cfg"));
        config.load();
        MAX_DAMAGE = config.getInt("Max_Durability", Configuration.CATEGORY_GENERAL, MAX_DAMAGE, 10, 1000, "Sets how many uses the tool has before breaking");


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
                        't', Blocks.SAPLING,
                        's', "stickWood"));
            }
        }
        proxy.postInit();
        config.save();
    }
}
