package com.builtbroken.woodenshears;

import java.util.Arrays;

import com.builtbroken.woodenshears.content.WoodTypes;
import com.builtbroken.woodenshears.content.WoodenShearItem;

import net.minecraft.core.dispenser.ShearsDispenseItemBehavior;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistries.Keys;
import net.minecraftforge.registries.RegisterEvent;

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
    public static void registerItems(RegisterEvent event)
    {
        if (event.getRegistryKey() == Keys.ITEMS)
            event.register(Keys.ITEMS, helper -> Arrays.stream(WoodTypes.values()).forEach(type -> helper.register(type.getItemRegistryName(), new WoodenShearItem(type))));
        else if (event.getRegistryKey() == Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS)
            event.register(Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, helper -> helper.register("wooden_shears_harvest", LootModifierWoodenShears.CODEC.get()));
    }

    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event)
    {
        //Common is invoked off main thread, queue to main thread
        event.enqueueWork(() -> Arrays.stream(WoodTypes.values()).forEach(type -> {
            //Look up item, other mods may replace our items for things such as progression
            final Item item = ForgeRegistries.ITEMS.getValue(type.getItemRegistryName());
            DispenserBlock.registerBehavior(item, new ShearsDispenseItemBehavior());
        }));
    }
}
