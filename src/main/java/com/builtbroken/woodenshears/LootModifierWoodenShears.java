package com.builtbroken.woodenshears;

import java.util.function.Supplier;

import javax.annotation.Nonnull;

import com.builtbroken.woodenshears.content.WoodenShearItem;
import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParam;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;

/**
 * Points loot tables for woodenshears to that of iron shears
 * <p>
 * Created by Dark(DarkGuardsman, Robert) on 5/10/2020.
 */
public class LootModifierWoodenShears extends LootModifier
{
    public static final Supplier<Codec<LootModifierWoodenShears>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.create(instance -> codecStart(instance).apply(instance, LootModifierWoodenShears::new)));

    public LootModifierWoodenShears(LootItemCondition[] conditions)
    {
        super(conditions);
    }

    @Nonnull
    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context)
    {
        final BlockState blockState = context.getParamOrNull(LootContextParams.BLOCK_STATE);
        final Vec3 origin = context.getParamOrNull(LootContextParams.ORIGIN);
        final ItemStack itemStack = context.getParamOrNull(LootContextParams.TOOL);
        if (blockState != null && origin != null && itemStack.getItem() instanceof WoodenShearItem)
        {
            return lootTable(context, blockState).getRandomItems(
                    new LootParams.Builder(context.getLevel())
                    .withParameter(LootContextParams.TOOL, new ItemStack(Items.SHEARS))
                    .withParameter(LootContextParams.ORIGIN, origin)
                    .withParameter(LootContextParams.BLOCK_STATE, blockState)
                    .create(LootContextParamSets.BLOCK)
            );
        }
        return generatedLoot;
    }

    private LootTable lootTable(LootContext context, BlockState blockState)
    {
        return context.getLevel().getServer().getLootData().getLootTable(blockState.getBlock().getLootTable());
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}
