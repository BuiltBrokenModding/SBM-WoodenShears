package com.builtbroken.woodenshears.content;

import java.util.List;

import com.builtbroken.woodenshears.WoodenShears;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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
        this.setMaxStackSize(1);
        this.setMaxDamage(WoodenShears.DEFAULT_DURABILITY);
        this.setCreativeTab(CreativeTabs.tabTools);
        this.setUnlocalizedName(WoodenShears.PREFIX + "shears");
    }

    @Override
    public ModelResourceLocation getModel(ItemStack stack, EntityPlayer player, int useRemaining)
    {
        return CustomMeshDefinition.instance.getModelLocation(stack);
    }

    @Override
    public int getMaxDamage(ItemStack stack)
    {
        WoodTypes type = getType(stack);

        switch(type)
        {
            case OAK: return WoodenShears.OAK_DURABILITY;
            case SPRUCE: return WoodenShears.SPRUCE_DURABILITY;
            case BIRCH: return WoodenShears.BIRCH_DURABILITY;
            case JUNGLE: return WoodenShears.JUNGLE_DURABILITY;
            case ACACIA: return WoodenShears.ACACIA_DURABILITY;
            case BIG_OAK: return WoodenShears.DARK_OAK_DURABILITY;
            case CHARRED: return WoodenShears.CHARRED_DURABILITY;
        }

        return WoodenShears.DEFAULT_DURABILITY;
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
