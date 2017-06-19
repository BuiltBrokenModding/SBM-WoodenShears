package com.builtbroken.woodenshears;

import com.builtbroken.woodenshears.content.CustomMeshDefinition;
import com.builtbroken.woodenshears.content.WoodTypes;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraftforge.client.model.ModelLoader;

/**
 * Created by Dark on 7/25/2015.
 */
public class ClientProxy extends CommonProxy
{
    @Override
    public void preInit()
    {
        super.preInit();
        for (WoodTypes type : WoodTypes.values())
        {
            ModelBakery.registerItemVariants(WoodenShears.itemShears, type.getModelResourceLocation());
        }
        ModelLoader.setCustomMeshDefinition(WoodenShears.itemShears, CustomMeshDefinition.instance);
        //ModelLoader.setCustomModelResourceLocation(WoodenShears.itemShears, 0, CustomMeshDefinition.instance.defaultModelResourceLocation);
    }
}
