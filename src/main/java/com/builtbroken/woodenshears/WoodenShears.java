package com.builtbroken.woodenshears;

import java.util.Arrays;

import com.builtbroken.woodenshears.content.WoodTypes;
import com.builtbroken.woodenshears.content.WoodenShearItem;

import net.minecraft.core.dispenser.ShearsDispenseItemBehavior;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab.TabVisibility;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.DispenserBlock;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.common.Mod.EventBusSubscriber.Bus;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.registries.ForgeRegistries;
import net.neoforged.neoforge.registries.ForgeRegistries.Keys;
import net.neoforged.neoforge.registries.RegisterEvent;

@Mod(WoodenShears.DOMAIN)
@Mod.EventBusSubscriber(bus = Bus.MOD)
public class WoodenShears
{
    public static final String DOMAIN = "woodenshears";
    public static final TagKey<Item> WSHEARS_TAG = ItemTags.create(new ResourceLocation(DOMAIN, "wshears"));

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
    public static void onCreativeModeTabBuildContents(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES)
        {
            ItemStack previous = new ItemStack(Items.SHEARS);

            for (WoodTypes type : WoodTypes.values())
            {
                Item item = ForgeRegistries.ITEMS.getValue(type.getItemRegistryName());

                if(!item.requiredFeatures().isSubsetOf(event.getFlags()))
                    continue;

                ItemStack stack = new ItemStack(item);
                event.getEntries().putAfter(previous, stack, TabVisibility.PARENT_AND_SEARCH_TABS);
                previous = stack;
            }
        }
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
