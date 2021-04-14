package tfar.electricgearluckyblocksaddon;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.event.RegistryEvent;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ModItems {

    public static final Item ELECTRIC_SWORD = new SwordItem(ElectricGearLuckyBlocksAddon.ELECTRIC_TOOL,1,-2.5f,new Item.Properties().group(ItemGroup.COMBAT));

    public static final Item ELECTRIC_HELMET = new ArmorItem(ElectricGearLuckyBlocksAddon.ELECTRIC, EquipmentSlotType.HEAD,new Item.Properties().group(ItemGroup.COMBAT));
    public static final Item ELECTRIC_CHESTPLATE = new ArmorItem(ElectricGearLuckyBlocksAddon.ELECTRIC, EquipmentSlotType.CHEST,new Item.Properties().group(ItemGroup.COMBAT));
    public static final Item ELECTRIC_LEGGINGS = new ArmorItem(ElectricGearLuckyBlocksAddon.ELECTRIC, EquipmentSlotType.LEGS,new Item.Properties().group(ItemGroup.COMBAT));
    public static final Item ELECTRIC_BOOTS = new ArmorItem(ElectricGearLuckyBlocksAddon.ELECTRIC, EquipmentSlotType.FEET,new Item.Properties().group(ItemGroup.COMBAT));

    public static final Item LIGHTNING_ROD = new BlockItem(ModBlocks.LIGHTNING_ROD,new Item.Properties().group(ItemGroup.DECORATIONS));

    private static List<Item> cache;

    public static void register(RegistryEvent.Register<Item> registry) {
        for (Field field : ModItems.class.getFields()) {
            try {
                Item item = (Item) field.get(null);
                String name = field.getName().toLowerCase(Locale.ROOT);
                item.setRegistryName(name);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        registry.getRegistry().registerAll(getItems().toArray(new Item[0]));
    }

    public static List<Item> getItems() {
        if (cache == null) {
            cache = Arrays.stream(ModItems.class.getFields()).map(field -> {
                try {
                    return field.get(null);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }).map(Item.class::cast).collect(Collectors.toList());
        }
        return cache;
    }

}
