package com.builtbroken.woodenshears;

import com.builtbroken.woodenshears.content.WoodTypes;
import com.builtbroken.woodenshears.content.WoodenShearItem;
import net.minecraft.core.dispenser.ShearsDispenseItemBehavior;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.level.block.DispenserBlock;
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
        Arrays.stream(WoodTypes.values()).forEach(type -> {
            ShearsItem shears=new WoodenShearItem(type);
            event.getRegistry().register(shears);
            DispenserBlock.registerBehavior(shears,new ShearsDispenseItemBehavior());
        });

    }
}
