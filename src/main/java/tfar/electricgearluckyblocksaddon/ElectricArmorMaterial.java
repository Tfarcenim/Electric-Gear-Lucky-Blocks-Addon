package tfar.electricgearluckyblocksaddon;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public class ElectricArmorMaterial implements IArmorMaterial {

    private static final int[] damageReductionAmountArray = new int[]{3, 6, 8, 3};

    @Override
    public int getDurability(EquipmentSlotType slotIn) {
        return 300 * damageReductionAmountArray[slotIn.getIndex()];
    }

    @Override
    public int getDamageReductionAmount(EquipmentSlotType slotIn) {
        return damageReductionAmountArray[slotIn.getIndex()];
    }

    @Override
    public int getEnchantability() {
        return 25;
    }

    @Override
    public SoundEvent getSoundEvent() {
        return SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return null;
    }

    @Override
    public String getName() {
        return ElectricGearLuckyBlocksAddon.MODID + ":electric";
    }

    @Override
    public float getToughness() {
        return 4;
    }

    @Override
    public float getKnockbackResistance() {
        return 1;
    }
}
