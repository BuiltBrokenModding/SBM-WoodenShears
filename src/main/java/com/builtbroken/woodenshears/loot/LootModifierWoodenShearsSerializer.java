package com.builtbroken.woodenshears.loot;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;

/**
 * Created by Dark(DarkGuardsman, Robert) on 5/10/2020.
 */
public class LootModifierWoodenShearsSerializer extends GlobalLootModifierSerializer<LootModifierWoodenShears>
{
    @Override
    public LootModifierWoodenShears read(ResourceLocation location, JsonObject object, LootItemCondition[] conditions)
    {
        return new LootModifierWoodenShears(conditions);
    }

    @Override
    public JsonObject write(LootModifierWoodenShears instance)
    {
        return null;
    }

}
