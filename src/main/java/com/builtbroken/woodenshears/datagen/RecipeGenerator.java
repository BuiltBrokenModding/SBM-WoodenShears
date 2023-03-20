package com.builtbroken.woodenshears.datagen;

import java.util.Arrays;
import java.util.function.Consumer;

import com.builtbroken.woodenshears.content.WoodTypes;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.ForgeRegistries;

public class RecipeGenerator extends RecipeProvider {
    public RecipeGenerator(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected final void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        Arrays.stream(WoodTypes.values()).forEach(type -> {
            if (type.planksBlock == null)
                return;
            //Look up item, other mods may replace our items for things such as progression
            final Item item = ForgeRegistries.ITEMS.getValue(type.getItemRegistryName());
            ShapedRecipeBuilder.shaped(item, 1)
            .group("securitycraft:wshears")
            .pattern("w w")
            .pattern(" t ")
            .pattern("s s")
            .define('w', type.planksBlock)
            .define('t', ItemTags.SAPLINGS)
            .define('s', Items.STICK)
            .unlockedBy("has_block", has(ItemTags.PLANKS))
            .save(consumer);
        });
    }

    @Override
    public String getName() {
        return "Wooden Shears Recipes";
    }
}
