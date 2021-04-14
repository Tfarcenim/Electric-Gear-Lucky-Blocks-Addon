package tfar.electricgearluckyblocksaddon;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.event.RegistryEvent;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ModBlocks {

    public static final Block LIGHTNING_ROD = new LightningRodBlock(AbstractBlock.Properties.create(Material.ANVIL).hardnessAndResistance(1,6));

    private static List<Block> cache;

    public static void register(RegistryEvent.Register<Block> registry) {
        for (Field field : ModBlocks.class.getFields()) {
            try {
                Block item = (Block) field.get(null);
                String name = field.getName().toLowerCase(Locale.ROOT);
                item.setRegistryName(name);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        registry.getRegistry().registerAll(getBlocks().toArray(new Block[0]));
    }

    public static List<Block> getBlocks() {
        if (cache == null) {
            cache = Arrays.stream(ModBlocks.class.getFields()).map(field -> {
                try {
                    return field.get(null);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }).map(Block.class::cast).collect(Collectors.toList());
        }
        return cache;
    }
}
