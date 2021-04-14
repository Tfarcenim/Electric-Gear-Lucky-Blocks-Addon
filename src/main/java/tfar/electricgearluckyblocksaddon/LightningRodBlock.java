package tfar.electricgearluckyblocksaddon;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.List;
import java.util.Random;

public class LightningRodBlock extends Block {
    public LightningRodBlock(Properties properties) {
        super(properties);
    }

    private static final Random rand = new Random();

    public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        worldIn.getPendingBlockTicks().scheduleTick(pos, this, 20);
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        boolean didSomething = false;
        int r = 2;
        List<ItemEntity> nearbyItems = worldIn.getEntitiesWithinAABB(ItemEntity.class,new AxisAlignedBB(pos.add(-r,-r,-r),pos.add(r,r,r)));
        for (ItemEntity itemEntity : nearbyItems) {
            ItemStack stack = itemEntity.getItem();
            if (stack.getItem().isIn(ElectricGearLuckyBlocksAddon.ELECTRIC_TAG) && !stack.isEnchanted()) {
                didSomething = true;
                List<EnchantmentData> list = getEnchantmentList(stack, 50);
                for (EnchantmentData enchantmentdata : list) {
                    stack.addEnchantment(enchantmentdata.enchantment, enchantmentdata.enchantmentLevel);
                }
            }
        }
        if (didSomething) {
            spawnLightning(worldIn,pos,false);
        }
        worldIn.getPendingBlockTicks().scheduleTick(pos, this, 20);
    }

    private static void spawnLightning(ServerWorld world,BlockPos pos,boolean effectOnly) {
        LightningBoltEntity lightningboltentity = EntityType.LIGHTNING_BOLT.create(world);
        lightningboltentity.moveForced(Vector3d.copyCenteredHorizontally(pos));
        lightningboltentity.setEffectOnly(effectOnly);
        world.addEntity(lightningboltentity);
    }

    private static List<EnchantmentData> getEnchantmentList(ItemStack stack, int level) {
        List<EnchantmentData> list = EnchantmentHelper.buildEnchantmentList(rand, stack, level, false);
        if (stack.getItem() == Items.BOOK && list.size() > 1) {
            list.remove(rand.nextInt(list.size()));
        }
        return list;
    }

}
