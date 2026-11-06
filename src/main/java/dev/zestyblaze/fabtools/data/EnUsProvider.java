package dev.zestyblaze.fabtools.data;

import dev.zestyblaze.fabtools.data.impl.DataBasedLanguageProvider;
import dev.zestyblaze.fabtools.registry.BlockRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class EnUsProvider extends DataBasedLanguageProvider {
    public EnUsProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider provider, TranslationBuilder builder) {
        builder.add(BlockRegistry.MELTER, "Forge");
        builder.add("block.fabtools.melter.fluid", "Buckets: %s");
    }
}
