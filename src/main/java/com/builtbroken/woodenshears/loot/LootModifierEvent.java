package com.builtbroken.woodenshears.loot;

import com.builtbroken.woodenshears.WoodenShears;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Created by Dark(DarkGuardsman, Robert) on 5/10/2020.
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = WoodenShears.DOMAIN)
public class LootModifierEvent
{
    @SubscribeEvent
    public static void onRegisterModifierSerializers(final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event)
    {
        event.getRegistry().register(new LootModifierWoodenShearsSerializer().setRegistryName(WoodenShears.DOMAIN, "wooden_shears_harvest"));
    }
}
