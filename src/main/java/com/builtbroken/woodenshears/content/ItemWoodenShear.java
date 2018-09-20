package com.builtbroken.woodenshears.content;

import com.builtbroken.woodenshears.WoodenShears;
import com.builtbroken.woodenshears.WoodenShearsConfig;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Dark on 8/11/2015.
 */
public class ItemWoodenShear extends ItemShears
{
    //TODO add icons and recipes for other wood types
    public ItemWoodenShear()
    {
        this.setRegistryName(WoodenShears.DOMAIN, "wshears");
        this.setMaxStackSize(1);
        this.setMaxDamage(WoodenShearsConfig.DEFAULT_DURABILITY);
        this.setCreativeTab(CreativeTabs.TOOLS);
        this.setTranslationKey(WoodenShears.PREFIX + "shears");
    }

    @Override
    public int getMaxDamage(ItemStack stack)
    {
        WoodTypes type = getType(stack);

        switch(type)
        {
            case OAK: return WoodenShearsConfig.OAK_DURABILITY;
            case SPRUCE: return WoodenShearsConfig.SPRUCE_DURABILITY;
            case BIRCH: return WoodenShearsConfig.BIRCH_DURABILITY;
            case JUNGLE: return WoodenShearsConfig.JUNGLE_DURABILITY;
            case ACACIA: return WoodenShearsConfig.ACACIA_DURABILITY;
            case BIG_OAK: return WoodenShearsConfig.DARK_OAK_DURABILITY;
            case CHARRED: return WoodenShearsConfig.CHARRED_DURABILITY;
        }

        return WoodenShearsConfig.DEFAULT_DURABILITY;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if (tab == CreativeTabs.TOOLS)
        {
            for (WoodTypes type : WoodTypes.values())
            {
                ItemStack stack = new ItemStack(this);
                setType(stack, type);
                items.add(stack);
            }
        }
    }

    /**
     * Type of the shear, used for Item Icon mainly
     *
     * @param stack - this item
     * @return byte representing the icon to use
     */
    public WoodTypes getType(ItemStack stack)
    {
        if (stack.getTagCompound() != null && stack.getTagCompound().hasKey("mType"))
        {
            byte b = stack.getTagCompound().getByte("mType");
            if (b > 0 && b < WoodTypes.values().length)
            {
                return WoodTypes.values()[b];
            }
        }
        return WoodTypes.OAK;
    }

    /**
     * Sets the type of the item
     *
     * @param stack - this item
     * @param type  type representing the preferred icon to use
     */
    public void setType(ItemStack stack, WoodTypes type)
    {
        if (stack.getTagCompound() == null)
        {
            stack.setTagCompound(new NBTTagCompound());
        }
        stack.getTagCompound().setByte("mType", (byte) type.ordinal());
    }

    @Override
    public String getTranslationKey(ItemStack stack)
    {
        return super.getTranslationKey() + "." + getType(stack).name;
    }
}
