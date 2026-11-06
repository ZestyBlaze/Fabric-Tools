package dev.zestyblaze.fabtools.registry;

import dev.zestyblaze.fabtools.FabTools;
import dev.zestyblaze.fabtools.block.entity.MelterBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class BlockEntityRegistry {
    public static final BlockEntityType<MelterBlockEntity> MELTER = FabricBlockEntityTypeBuilder.create(MelterBlockEntity::new, BlockRegistry.MELTER).build();

    public static void register() {
        Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, FabTools.loc("melter"), MELTER);
    }
}
