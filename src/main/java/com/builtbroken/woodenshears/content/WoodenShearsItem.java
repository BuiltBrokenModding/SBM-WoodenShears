package com.builtbroken.woodenshears.content;

import com.builtbroken.woodenshears.WoodenShearsConfig;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;

/**
 * Created by Dark on 8/11/2015.
 */
public class WoodenShearsItem extends ShearsItem
{
    public final String wood_type;
    //TODO add icons and recipes for other wood types
    public WoodenShearsItem(Properties properties,String wood_type)
    {
        super(properties);
        this.wood_type = wood_type;
    }

    @Override
    public int getItemStackLimit(ItemStack stack) {
        return 1;
    }
}
