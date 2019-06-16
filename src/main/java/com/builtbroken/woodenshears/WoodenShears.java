package com.builtbroken.woodenshears;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.Logger;


@Mod(value = WoodenShears.DOMAIN)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class WoodenShears
{

    public WoodenShears(){
       // WoodenShearsConfig.bake();
    }

    public static final String DOMAIN = "woodenshears";

    public static Logger LOGGER;

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        IForgeRegistry<Item> registry = event.getRegistry();
        RegisterCompat.register(registry);
    }

    @SubscribeEvent
    public static void onConfigChanged(OnConfigChangedEvent event)
    {
        if(event.getModID().equals(DOMAIN))
            new ConfigChangedEvent.OnConfigChangedEvent(DOMAIN,DOMAIN, false, false);
    }
}
