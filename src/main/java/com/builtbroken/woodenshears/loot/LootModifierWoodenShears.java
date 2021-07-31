package com.builtbroken.woodenshears.loot;

import com.builtbroken.woodenshears.content.WoodenShearItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
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
    public LootModifierWoodenShears(LootItemCondition[] conditionsIn)
    {
        super(conditionsIn);
    }

    @Nonnull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context)
    {
        final BlockState blockState = context.getParamOrNull(LootContextParams.BLOCK_STATE);
        final ItemStack itemStack = context.getParamOrNull(LootContextParams.TOOL);
        if (blockState != null && itemStack.getItem() instanceof WoodenShearItem)
        {
            return lootTable(context, blockState).getRandomItems(createContext(context));
        }
        return generatedLoot;
    }

    private LootTable lootTable(LootContext context, BlockState blockState)
    {
        return context.getLootTable(blockState.getBlock().getLootTable());
    }

    private LootContext createContext(LootContext context)
    {
        return new LootContext.Builder(context)
                .withParameter(LootContextParams.TOOL, new ItemStack(Items.SHEARS))
                .create(LootContextParamSets.BLOCK);
    }

}
