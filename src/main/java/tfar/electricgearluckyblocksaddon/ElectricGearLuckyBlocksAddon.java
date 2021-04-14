package tfar.electricgearluckyblocksaddon;

import net.minecraft.block.Block;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ElectricGearLuckyBlocksAddon.MODID)
public class ElectricGearLuckyBlocksAddon
{
    // Directly reference a log4j logger.

    public static final String MODID = "electricgearluckyblocksaddon";

    public static final ITag<Item> ELECTRIC_TAG = ItemTags.makeWrapperTag(new ResourceLocation(MODID,"electric").toString());

    public static final IArmorMaterial ELECTRIC = new ElectricArmorMaterial();
    public static final IItemTier ELECTRIC_TOOL = new ElectricToolMaterial();

    public ElectricGearLuckyBlocksAddon() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addGenericListener(Item.class,ModItems::register);
        bus.addGenericListener(Block.class,ModBlocks::register);
        // Register the setup method for modloading
        bus.addListener(this::setup);
        // Register the doClientStuff method for modloading
        bus.addListener(this::doClientStuff);
        MinecraftForge.EVENT_BUS.addListener(this::toss);
    }

    private void toss(ItemTossEvent event) {
        ItemEntity itemEntity = event.getEntityItem();
        ItemStack stack = itemEntity.getItem();
        if (stack.getItem().isIn(ELECTRIC_TAG)) {
            itemEntity.setInvulnerable(true);
        }
    }

    private void setup(final FMLCommonSetupEvent event) {
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
    }
}
