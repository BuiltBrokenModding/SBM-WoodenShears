package com.builtbroken.woodenshears.datagen;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

import com.builtbroken.woodenshears.WoodenShears;
import com.builtbroken.woodenshears.content.WoodTypes;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ItemTagGenerator extends TagsProvider<Item> {
    protected ItemTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, Registries.ITEM, lookupProvider, WoodenShears.DOMAIN, existingFileHelper);
    }

    @Override
    protected void addTags(Provider provider) {
        var wshearsTag = tag(WoodenShears.WSHEARS_TAG);
        Arrays.stream(WoodTypes.values()).forEach(type -> {
            //Look up item, other mods may replace our items for things such as progression
            final var item = ResourceKey.create(Registries.ITEM, type.getItemRegistryName());
            wshearsTag.add(item);
        });
        tag(Tags.Items.SHEARS).addTag(WoodenShears.WSHEARS_TAG);
    }

    @Override
    public String getName() {
        return "Wooden Shears Item Tags";
    }
}
