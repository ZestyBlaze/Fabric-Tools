package dev.zestyblaze.fabtools.reload;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluid;

import java.util.*;

public record FluidMap(boolean replace, Map<Item, Fluid> smeltMap) {
    public static Codec<FluidMap> CODEC = RecordCodecBuilder.create(in -> in.group(
            Codec.BOOL.fieldOf("replace").forGetter(FluidMap::replace),
            Codec.unboundedMap(BuiltInRegistries.ITEM.byNameCodec(), BuiltInRegistries.FLUID.byNameCodec()).fieldOf("values").forGetter(FluidMap::smeltMap)
    ).apply(in, FluidMap::new));

    public static Map<Item, Fluid> merge(List<FluidMap> raws) {
        Map<Item, Fluid> set = new HashMap<>();
        for (FluidMap raw : raws) {
            if (raw.replace) {
                set = new HashMap<>();
            }
            set.putAll(raw.smeltMap);
        }

        Map<Item, Fluid> out = new HashMap<>(set.size());
        out.putAll(set);
        return out;
    }
}
