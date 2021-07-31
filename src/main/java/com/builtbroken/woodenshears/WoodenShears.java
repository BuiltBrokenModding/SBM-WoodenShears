package com.builtbroken.woodenshears;

import com.builtbroken.woodenshears.content.WoodTypes;
import com.builtbroken.woodenshears.content.WoodenShearItem;
import net.minecraft.core.dispenser.ShearsDispenseItemBehavior;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Arrays;

@Mod(WoodenShears.DOMAIN)
@Mod.EventBusSubscriber(bus = Bus.MOD)
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
        Arrays.stream(WoodTypes.values()).forEach(type -> event.getRegistry().register(new WoodenShearItem(type)));
    }

    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event)
    {
        //Common is invoked off main thread, queue to main thread
        event.enqueueWork(() -> Arrays.stream(WoodTypes.values()).forEach(type -> {
            //Look up item, other mods may replace our items for things such as progression
            final Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(DOMAIN, type.itemName));
            DispenserBlock.registerBehavior(item, new ShearsDispenseItemBehavior());
        }));
    }
}
