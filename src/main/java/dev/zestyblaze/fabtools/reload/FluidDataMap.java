package dev.zestyblaze.fabtools.reload;

import dev.zestyblaze.fabtools.FabTools;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluid;

import java.util.HashMap;
import java.util.Map;

public class FluidDataMap extends MergeableCodecDataManager<FluidMap, Map<Item, Fluid>> implements IdentifiableResourceReloadListener {
    public static final Map<Item, Fluid> ITEM_FLUID_MAP = new HashMap<>();

    public FluidDataMap() {
        super("fabtools", FluidMap.CODEC, FluidMap::merge);
    }

    @Override
    protected void apply(Map<ResourceLocation, Map<Item, Fluid>> processedData, ResourceManager resourceManager, ProfilerFiller profiler) {
        super.apply(processedData, resourceManager, profiler);

        this.data.forEach((resourceLocation, itemFluidMap) -> {
            ITEM_FLUID_MAP.putAll(itemFluidMap);
        });
    }

    @Override
    public ResourceLocation getFabricId() {
        return ResourceLocation.fromNamespaceAndPath(FabTools.MOD_ID, "fluid_map");
    }
}
