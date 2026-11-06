package dev.zestyblaze.fabtools.block.entity;

import dev.zestyblaze.fabtools.registry.BlockEntityRegistry;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.base.FilteringStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class MelterBlockEntity extends BlockEntity {
    public final SingleFluidStorage internalStorage = SingleFluidStorage.withFixedCapacity(12 * FluidConstants.BUCKET, this::setChanged);
    public final Storage<FluidVariant> exposedInternalStorage = FilteringStorage.insertOnlyOf(internalStorage);

    public MelterBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(BlockEntityRegistry.MELTER, blockPos, blockState);
    }

    public SingleFluidStorage getInternalStorage() {
        return internalStorage;
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.saveAdditional(tag, provider);
        tag.putLong("amount", internalStorage.amount);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.loadAdditional(tag, provider);
        internalStorage.amount = tag.getLong("amount");
    }
}
