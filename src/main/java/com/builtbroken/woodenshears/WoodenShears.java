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

import java.util.Arrays;

@Mod(WoodenShears.DOMAIN)
@Mod.EventBusSubscriber(bus=Bus.MOD)
public class WoodenShears
{
    public static final String DOMAIN = "woodenshears";

    public WoodenShears()
    {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, WoodenShearsConfig.CONFIG_SPEC);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        Arrays.stream(WoodTypes.values()).forEach(type ->  event.getRegistry().register(new ItemWoodenShear(type)));
    }
}
