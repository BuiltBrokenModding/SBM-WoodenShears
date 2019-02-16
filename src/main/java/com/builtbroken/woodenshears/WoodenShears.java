package com.builtbroken.woodenshears;

import com.builtbroken.woodenshears.content.ItemWoodenShear;
import com.builtbroken.woodenshears.content.WoodTypes;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig;

@Mod(WoodenShears.DOMAIN)
@Mod.EventBusSubscriber(bus=Bus.MOD)
public class WoodenShears
{
    public static final String DOMAIN = "woodenshears";
    public static final String PREFIX = DOMAIN + ":";

    public static ItemWoodenShear itemShearsOak;
    public static ItemWoodenShear itemShearsSpruce;
    public static ItemWoodenShear itemShearsBirch;
    public static ItemWoodenShear itemShearsJungle;
    public static ItemWoodenShear itemShearsAcacia;
    public static ItemWoodenShear itemShearsDarkOak;
    public static ItemWoodenShear itemShearsCharred;

    public WoodenShears()
    {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, WoodenShearsConfig.CONFIG_SPEC);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().register(itemShearsOak = new ItemWoodenShear(WoodTypes.OAK));
        event.getRegistry().register(itemShearsSpruce = new ItemWoodenShear(WoodTypes.SPRUCE));
        event.getRegistry().register(itemShearsBirch = new ItemWoodenShear(WoodTypes.BIRCH));
        event.getRegistry().register(itemShearsJungle = new ItemWoodenShear(WoodTypes.JUNGLE));
        event.getRegistry().register(itemShearsAcacia = new ItemWoodenShear(WoodTypes.ACACIA));
        event.getRegistry().register(itemShearsDarkOak = new ItemWoodenShear(WoodTypes.BIG_OAK));
        event.getRegistry().register(itemShearsCharred = new ItemWoodenShear(WoodTypes.CHARRED));
    }
}
