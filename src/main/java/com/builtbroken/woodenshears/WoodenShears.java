package com.builtbroken.woodenshears;

import com.builtbroken.woodenshears.content.ItemWoodenShear;

import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

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

        ResourceLocation shears = new ResourceLocation(DOMAIN, "wshears");
        itemShears = new ItemWoodenShear(shears);
        GameRegistry.register(itemShears, shears);
        if (event.getSide().isClient()) {
            ModelLoader.setCustomModelResourceLocation(itemShears, 0, new ModelResourceLocation(shears, "inventory"));
        }

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
        GameRegistry.addShapedRecipe(new ItemStack(itemShears), "w w", " t ", "s s", 'w', new ItemStack(Blocks.PLANKS), 't', Blocks.SAPLING, 's', Items.STICK);
        proxy.postInit();
        config.save();
    }
}
