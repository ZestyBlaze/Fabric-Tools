package dev.zestyblaze.fabtools.block;

import dev.zestyblaze.fabtools.block.entity.MelterBlockEntity;
import dev.zestyblaze.fabtools.gui.MelterGui;
import dev.zestyblaze.fabtools.mixin.BucketItemAccessor;
import eu.pb4.polymer.core.api.block.SimplePolymerBlock;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class MelterBlock extends SimplePolymerBlock implements EntityBlock {
    public MelterBlock(Properties properties) {
        super(properties, Blocks.CHISELED_STONE_BRICKS);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (itemStack.getItem() instanceof BucketItem bucketItem && ((BucketItemAccessor)bucketItem).getContent() == Fluids.LAVA) {
            Storage<FluidVariant> storage = FluidStorage.SIDED.find(level, blockPos, blockHitResult.getDirection());
            if (storage != null) {
                try (Transaction transaction = Transaction.openOuter()) {
                    long inserted = storage.insert(FluidVariant.of(Fluids.LAVA), FluidConstants.BUCKET, transaction);
                    if (inserted == FluidConstants.BUCKET) {
                        transaction.commit();
                        return InteractionResult.SUCCESS_SERVER;
                    }
                }
            }
        }
        return InteractionResult.TRY_WITH_EMPTY_HAND;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState blockState, Level level, BlockPos blockPos, Player player, BlockHitResult blockHitResult) {
        if (!level.isClientSide()) {
            MelterGui.open(player, (MelterBlockEntity) level.getBlockEntity(blockPos));
            return InteractionResult.SUCCESS_SERVER;
        }
        return InteractionResult.PASS;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new MelterBlockEntity(blockPos, blockState);
    }
}
