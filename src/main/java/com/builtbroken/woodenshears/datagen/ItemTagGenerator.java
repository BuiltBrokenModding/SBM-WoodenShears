package com.builtbroken.woodenshears.datagen;

import java.util.Arrays;

import com.builtbroken.woodenshears.WoodenShears;
import com.builtbroken.woodenshears.content.WoodTypes;

import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemTagGenerator extends TagsProvider<Item> {
    protected ItemTagGenerator(DataGenerator dataGenerator, ExistingFileHelper existingFileHelper) {
        super(dataGenerator, Registry.ITEM, WoodenShears.DOMAIN, existingFileHelper);
    }

    @Override
    protected void addTags() {
        var wshearsTag = tag(WoodenShears.WSHEARS_TAG);
        Arrays.stream(WoodTypes.values()).forEach(type -> {
            //Look up item, other mods may replace our items for things such as progression
            final Item item = ForgeRegistries.ITEMS.getValue(type.getItemRegistryName());
            wshearsTag.add(item);
        });
        tag(Tags.Items.SHEARS).addTag(WoodenShears.WSHEARS_TAG);
    }

    @Override
    public String getName() {
        return "Wooden Shears Item Tags";
    }
}
