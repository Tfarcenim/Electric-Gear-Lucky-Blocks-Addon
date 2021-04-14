package tfar.electricgearluckyblocksaddon;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

public class ElectricToolMaterial implements IItemTier {
    @Override
    public int getMaxUses() {
        return 2000;
    }

    @Override
    public float getEfficiency() {
        return 10;
    }

    @Override
    public float getAttackDamage() {
        return 4;
    }

    @Override
    public int getHarvestLevel() {
        return 4;
    }

    @Override
    public int getEnchantability() {
        return 25;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return null;
    }
}
