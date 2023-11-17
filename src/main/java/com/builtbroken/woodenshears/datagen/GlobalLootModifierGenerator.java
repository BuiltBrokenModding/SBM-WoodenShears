package com.builtbroken.woodenshears.datagen;

import com.builtbroken.woodenshears.LootModifierWoodenShears;
import com.builtbroken.woodenshears.WoodenShears;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;

public class GlobalLootModifierGenerator extends GlobalLootModifierProvider {
    public GlobalLootModifierGenerator(PackOutput output) {
        super(output, WoodenShears.DOMAIN);
    }

    @Override
    protected void start() {
        add("wooden_shears_harvest", new LootModifierWoodenShears(new LootItemCondition[] {
                MatchTool.toolMatches(ItemPredicate.Builder.item().of(WoodenShears.WSHEARS_TAG)).build()
        }));
    }
}
