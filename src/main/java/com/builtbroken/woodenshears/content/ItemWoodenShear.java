package com.builtbroken.woodenshears.content;

import com.builtbroken.woodenshears.WoodenShears;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by Dark on 8/11/2015.
 */
public class ItemWoodenShear extends ItemShears
{
    //TODO add icons and recipes for other wood types
    public ItemWoodenShear()
    {
        this.setMaxStackSize(1);
        this.setMaxDamage(WoodenShears.MAX_DAMAGE);
        this.setCreativeTab(CreativeTabs.tabTools);
        this.setUnlocalizedName(WoodenShears.PREFIX + "shears");
        this.setTextureName(WoodenShears.PREFIX + "shears");
    }

    @Override
    public int getMaxDamage()
    {
        return WoodenShears.MAX_DAMAGE;
    }

    /**
     * Type of the shear, used for Item Icon mainly
     * @param stack - this item
     * @return byte representing the icon to use
     */
    public byte getType(ItemStack stack)
    {
        if (stack.getTagCompound() != null && stack.getTagCompound().hasKey("mType"))
        {
            return stack.getTagCompound().getByte("mType");
        }
        return 0;
    }

    /**
     * Sets the type of the item
     * @param stack - this item
     * @param type type representing the preferred icon to use
     */
    public void setType(ItemStack stack, byte type)
    {
        if (stack.getTagCompound() == null)
            stack.setTagCompound(new NBTTagCompound());
        stack.getTagCompound().setByte("mType", type);
    }
}
