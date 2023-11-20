package com.builtbroken.woodenshears.datagen;

import java.util.Arrays;

import com.builtbroken.woodenshears.WoodenShears;
import com.builtbroken.woodenshears.content.WoodTypes;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ItemModelGenerator extends ItemModelProvider {
    public ItemModelGenerator(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, WoodenShears.DOMAIN, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        final String parent = "item/generated";
        Arrays.stream(WoodTypes.values()).forEach(type -> {
            //Look up item, other mods may replace our items for things such as progression
            final String path = type.getItemRegistryName().getPath();
            singleTexture(path, mcLoc(parent), "layer0", modLoc(ITEM_FOLDER + "/" + path));
        });
    }

    @Override
    public String getName() {
        return "Wooden Shears Item Models";
    }
}
