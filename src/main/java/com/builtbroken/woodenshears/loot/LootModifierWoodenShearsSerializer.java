package com.builtbroken.woodenshears.loot;

import com.google.gson.JsonObject;
import net.minecraft.util.ResourceLocation;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;

/**
 * Created by Dark(DarkGuardsman, Robert) on 5/10/2020.
 */
public class LootModifierWoodenShearsSerializer extends GlobalLootModifierSerializer<LootModifierWoodenShears>
{
    @Override
    public LootModifierWoodenShears read(ResourceLocation location, JsonObject object, ILootCondition[] conditions)
    {
        return new LootModifierWoodenShears(conditions);
    }

    @Override
    public JsonObject write(LootModifierWoodenShears instance)
    {
        return null;
    }

}
