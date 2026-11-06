package dev.zestyblaze.fabtools.registry;

import dev.zestyblaze.fabtools.FabTools;
import dev.zestyblaze.fabtools.block.MelterBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class BlockRegistry {
    public static final Block MELTER = new MelterBlock(BlockBehaviour.Properties.of().setId(key("melter")));

    public static void register() {
        Registry.register(BuiltInRegistries.BLOCK, FabTools.loc("melter"), MELTER);
    }

    private static ResourceKey<Block> key(String id) {
        return ResourceKey.create(Registries.BLOCK, FabTools.loc(id));
    }
}
