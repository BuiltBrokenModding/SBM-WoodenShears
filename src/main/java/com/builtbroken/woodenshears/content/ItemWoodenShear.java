package com.builtbroken.woodenshears.content;

import com.builtbroken.woodenshears.WoodenShears;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;

/**
 * Extended version of the shears featuring wood types
 *
 * Created by Dark on 8/11/2015.
 */
public class ItemWoodenShear extends ShearsItem
{
    /** Wood type of the shear */
    public final WoodTypes woodType;

    public ItemWoodenShear(WoodTypes woodType)
    {
        super(new Item.Properties()
                .group(ItemGroup.TOOLS)
                .maxStackSize(1)
                .defaultMaxDamage(woodType.getDurability()));
        this.setRegistryName(WoodenShears.DOMAIN, "wshears_" + woodType.name);
        this.woodType = woodType;
    }

    @Override
    public int getMaxDamage(ItemStack stack)
    {
        return woodType.getDurability();
    }
}
