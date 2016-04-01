package com.builtbroken.woodenshears;

import com.builtbroken.woodenshears.content.ItemWoodenShear;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

/**
 * Created by Dark on 7/25/2015.
 */
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

    public static Item itemShears;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        LOGGER = LogManager.getLogger("WoodenShears");
        config = new Configuration(new File(event.getModConfigurationDirectory(), "bbm/Wooden_Shears.cfg"));
        config.load();
        MAX_DAMAGE = config.getInt("Max_Durability", Configuration.CATEGORY_GENERAL, MAX_DAMAGE, 10, 1000, "Sets how many uses the tool has before breaking");

        itemShears = new ItemWoodenShear();
        GameRegistry.registerItem(itemShears, "wsShears");

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
        GameRegistry.addShapedRecipe(new ItemStack(itemShears), "w w", " t ", "s s", 'w', new ItemStack(Blocks.planks), 't', Blocks.sapling, 's', Items.stick);
        proxy.postInit();
        config.save();
    }
}
