package com.builtbroken.woodenshears.content;

import com.builtbroken.woodenshears.WoodenShears;
import com.builtbroken.woodenshears.WoodenShearsConfig;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;

/**
 * Created by Dark on 8/11/2015.
 */
public class ItemWoodenShear extends ItemShears
{
    private WoodTypes type;

    //TODO add icons and recipes for other wood types
    public ItemWoodenShear(WoodTypes type)
    {
        super(new Item.Properties().group(ItemGroup.TOOLS).maxStackSize(1).defaultMaxDamage(WoodenShearsConfig.DEFAULT_DURABILITY));
        this.setRegistryName(WoodenShears.DOMAIN, "wshears_" + type.name);
        this.type = type;
    }

    @Override
    public int getMaxDamage(ItemStack stack)
    {
        switch(((ItemWoodenShear)stack.getItem()).getType())
        {
            case OAK: return WoodenShearsConfig.CONFIG.oakDurability.get();
            case SPRUCE: return WoodenShearsConfig.CONFIG.spruceDurability.get();
            case BIRCH: return WoodenShearsConfig.CONFIG.birchDurability.get();
            case JUNGLE: return WoodenShearsConfig.CONFIG.jungleDurability.get();
            case ACACIA: return WoodenShearsConfig.CONFIG.acaciaDurability.get();
            case BIG_OAK: return WoodenShearsConfig.CONFIG.darkOakDurability.get();
            case CHARRED: return WoodenShearsConfig.CONFIG.charredDurability.get();
        }

        return WoodenShearsConfig.DEFAULT_DURABILITY;
    }

    /**
     * Type of the shear, used for damage mainly
     *
     * @param stack - this item
     * @return byte representing the type of this shear
     */
    public WoodTypes getType()
    {
        return type;
    }
}
