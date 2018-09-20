package com.builtbroken.woodenshears;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.builtbroken.woodenshears.content.ItemWoodenShear;
import com.builtbroken.woodenshears.content.WoodTypes;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

@Mod(modid = WoodenShears.DOMAIN, name = "Wooden Shears", version = "@MAJOR@.@MINOR@.@REVIS@.@BUILD@")
@Mod.EventBusSubscriber
public class WoodenShears
{
    public static final String DOMAIN = "woodenshears";
    public static final String PREFIX = DOMAIN + ":";

    @SidedProxy(clientSide = "com.builtbroken.woodenshears.ClientProxy", serverSide = "com.builtbroken.woodenshears.CommonProxy")
    public static CommonProxy proxy;

    public static Logger LOGGER;

    public static final int DEFAULT_DURABILITY = 50;

    public static ItemWoodenShear itemShears;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        LOGGER = LogManager.getLogger("WoodenShears");
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
    }

    @SubscribeEvent
    public static void onConfigChanged(OnConfigChangedEvent event)
    {
        if(event.getModID().equals(DOMAIN))
            ConfigManager.sync(DOMAIN, Config.Type.INSTANCE);
    }
}
