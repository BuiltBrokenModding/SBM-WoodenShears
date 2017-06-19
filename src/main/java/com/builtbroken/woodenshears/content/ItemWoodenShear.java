package com.builtbroken.woodenshears.content;

import com.builtbroken.woodenshears.WoodenShears;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

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
    }

    @Override
    public int getMaxDamage(ItemStack stack)
    {
        return WoodenShears.MAX_DAMAGE;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems)
    {
        for (WoodTypes type : WoodTypes.values())
        {
            ItemStack stack = new ItemStack(itemIn);
            setType(stack, type);
            subItems.add(stack);
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
    public String getUnlocalizedName(ItemStack stack)
    {
        return super.getUnlocalizedName() + "." + getType(stack).name;
    }
}
