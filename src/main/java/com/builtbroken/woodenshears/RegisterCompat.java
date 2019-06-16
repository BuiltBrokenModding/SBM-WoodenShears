package com.builtbroken.woodenshears;

import com.builtbroken.woodenshears.content.WoodenShearsItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.HashSet;
import java.util.Set;

public class RegisterCompat {

  private static final Set<String> woodtypes = new HashSet<>();
  private static IForgeRegistry<Item> registry;

  public static void register(IForgeRegistry<Item> registry){
    RegisterCompat.registry = registry;

    registerVanilla();
    for (String wood : woodtypes){
      Item.Properties properties = new Item.Properties().
              group(ItemGroup.TOOLS).maxDamage(WoodenShearsConfig.map.getOrDefault(wood, 50));
      registerItem(new WoodenShearsItem(properties,wood),wood+"_shears");
    }
  }

  private static void registerVanilla() {
    woodtypes.add("acacia");
    woodtypes.add("birch");
    woodtypes.add("dark_oak");
    woodtypes.add("jungle");
    woodtypes.add("oak");
    woodtypes.add("spruce");
    woodtypes.add("charred");
  }

  private static void registerItem(Item item, String name) {
    item.setRegistryName(name);
    registry.register(item);
  }
}
