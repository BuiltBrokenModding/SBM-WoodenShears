package com.builtbroken.woodenshears.loot;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameterSets;
import net.minecraft.world.storage.loot.LootParameters;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
import net.minecraftforge.common.loot.LootModifier;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Points loot tables for woodenshears to that of iron shears
 * <p>
 * Created by Dark(DarkGuardsman, Robert) on 5/10/2020.
 */
public class LootModifierWoodenShears extends LootModifier
{
    public LootModifierWoodenShears(ILootCondition[] conditionsIn)
    {
        super(conditionsIn);
    }

    @Nonnull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context)
    {
        final BlockState blockState = context.get(LootParameters.BLOCK_STATE);
        if (blockState != null)
        {
            return lootTable(context, blockState).generate(createContext(context));
        }
        return generatedLoot;
    }

    private LootTable lootTable(LootContext context, BlockState blockState)
    {
        return context.getWorld().getServer().getLootTableManager().getLootTableFromLocation(blockState.getBlock().getLootTable());
    }

    private LootContext createContext(LootContext context)
    {
        return new LootContext.Builder(context)
                .withParameter(LootParameters.TOOL, new ItemStack(Items.SHEARS))
                .build(LootParameterSets.BLOCK);
    }
}
