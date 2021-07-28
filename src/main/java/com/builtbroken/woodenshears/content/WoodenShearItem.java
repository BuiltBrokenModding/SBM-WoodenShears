package com.builtbroken.woodenshears.content;

import com.builtbroken.woodenshears.WoodenShears;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;

/**
 * Extended version of the shears featuring wood types
 *
 * Created by Dark on 8/11/2015.
 */
public class WoodenShearItem extends ShearsItem
{
    /** Wood type of the shear */
    public final WoodTypes woodType;

    public WoodenShearItem(WoodTypes woodType)
    {
        super(new Item.Properties()
                .tab(CreativeModeTab.TAB_TOOLS)
                .stacksTo(1)
                .defaultDurability(woodType.getDurability()));
        this.setRegistryName(WoodenShears.DOMAIN, "wshears_" + woodType.name);
        this.woodType = woodType;
    }

    @Override
    public int getMaxDamage(ItemStack stack)
    {
        return woodType.getDurability();
    }
}
