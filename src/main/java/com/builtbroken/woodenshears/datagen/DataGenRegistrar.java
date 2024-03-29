package com.builtbroken.woodenshears.datagen;

import java.util.Collections;

import com.builtbroken.woodenshears.WoodenShears;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = WoodenShears.DOMAIN, bus = Bus.MOD)
public class DataGenRegistrar {
    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = new ExistingFileHelper(Collections.EMPTY_LIST, Collections.EMPTY_SET, false, null, null);

        generator.addProvider(event.includeServer(), new GlobalLootModifierGenerator(output));
        generator.addProvider(event.includeClient(), new ItemModelGenerator(output, existingFileHelper));
        generator.addProvider(event.includeServer(), new ItemTagGenerator(output, event.getLookupProvider(), existingFileHelper));
        generator.addProvider(event.includeServer(), new RecipeGenerator(output));
    }
}
