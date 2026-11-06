package dev.zestyblaze.fabtools;

import dev.zestyblaze.fabtools.registry.BlockEntityRegistry;
import dev.zestyblaze.fabtools.registry.BlockRegistry;
import dev.zestyblaze.fabtools.reload.FluidDataMap;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FabTools implements ModInitializer {
	public static final String MOD_ID = "fabtools";
	public static final Logger LOGGER = LogManager.getLogger();

	public static ResourceLocation loc(String id) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, id);
	}

	@Override
	public void onInitialize() {
		ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(new FluidDataMap());
		BlockRegistry.register();
		BlockEntityRegistry.register();

		FluidStorage.SIDED.registerForBlockEntity((entity, direction) -> entity.internalStorage, BlockEntityRegistry.MELTER);
	}
}