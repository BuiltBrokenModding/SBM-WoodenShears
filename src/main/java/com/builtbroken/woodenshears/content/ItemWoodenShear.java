package com.builtbroken.woodenshears.content;

import com.builtbroken.woodenshears.WoodenShears;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;

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
        this.setTextureName(WoodenShears.PREFIX + "shears");
    }

    @Override
    public int getMaxDamage()
    {
        return WoodenShears.MAX_DAMAGE;
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
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List subItems)
    {
        for (WoodTypes type : WoodTypes.values())
        {
            ItemStack stack = new ItemStack(itemIn);
            setType(stack, type);
            subItems.add(stack);
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return super.getUnlocalizedName() + "." + getType(stack).name;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister reg)
    {
        for (WoodTypes type : WoodTypes.values())
        {
            type.icon = reg.registerIcon(WoodenShears.PREFIX + "shears." + type.name);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(ItemStack stack, int pass)
    {
        return getType(stack).icon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderPasses(int metadata)
    {
        return 1;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses()
    {
        return true;
    }
}
