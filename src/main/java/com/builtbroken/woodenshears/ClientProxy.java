package com.builtbroken.woodenshears;

import com.builtbroken.woodenshears.content.CustomMeshDefinition;
import com.builtbroken.woodenshears.content.WoodTypes;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraftforge.client.model.ModelLoader;

/**
 * Created by Dark on 7/25/2015.
 */
public class ClientProxy extends CommonProxy
{
    @Override
    public void doLoadModels()
    {
        super.doLoadModels();
        CustomMeshDefinition meshDefinition = new CustomMeshDefinition();
        ModelBakery.registerItemVariants(WoodenShears.itemShears, meshDefinition.defaultModelResourceLocation);
        for (WoodTypes type : WoodTypes.values())
        {
            ModelBakery.registerItemVariants(WoodenShears.itemShears, type.getModelResourceLocation());
        }
        ModelLoader.setCustomMeshDefinition(WoodenShears.itemShears, meshDefinition);
    }
}
