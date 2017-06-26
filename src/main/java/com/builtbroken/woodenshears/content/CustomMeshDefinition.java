package com.builtbroken.woodenshears.content;

import com.builtbroken.woodenshears.WoodenShears;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;

/**
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 6/19/2017.
 */
public class CustomMeshDefinition implements ItemMeshDefinition
{
    public final ModelResourceLocation defaultModelResourceLocation;

    public CustomMeshDefinition()
    {
        defaultModelResourceLocation = new ModelResourceLocation(WoodenShears.itemShears.getRegistryName(), "inventory");
    }

    @Override
    public ModelResourceLocation getModelLocation(ItemStack stack)
    {
        if(stack != null && stack.getItem() instanceof ItemWoodenShear)
        {
            return WoodenShears.itemShears.getType(stack).getModelResourceLocation();
        }
        return defaultModelResourceLocation;
    }
}
