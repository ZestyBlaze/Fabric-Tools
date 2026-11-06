package dev.zestyblaze.fabtools.gui;

import dev.zestyblaze.fabtools.block.entity.MelterBlockEntity;
import eu.pb4.sgui.api.elements.GuiElementBuilder;
import eu.pb4.sgui.api.gui.SimpleGui;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Items;

public class MelterGui extends SimpleGui {
    private final MelterBlockEntity forge;

    public MelterGui(ServerPlayer player, MelterBlockEntity forge) {
        super(MenuType.GENERIC_9x3, player, false);
        this.forge = forge;
    }

    public static void open(Player player, MelterBlockEntity forge) {
        MelterGui gui = new MelterGui((ServerPlayer) player, forge);
        gui.setTitle(Component.literal("Melter"));
        GuiElementBuilder empty = new GuiElementBuilder(Items.GRAY_STAINED_GLASS_PANE).setItemName(Component.literal(""));

        for (int i = 0; i < 19; i += 9) {
            gui.setSlot(i, empty);
        }
        for (int i = 2; i < 8; i++) {
            gui.setSlot(i, empty);
        }
        for (int i = 8; i < 27; i += 9) {
            gui.setSlot(i, empty);
        }
        for (int i = 20; i < 26; i++) {
            gui.setSlot(i, empty);
        }

        gui.open();
    }

    @Override
    public void onTick() {
        this.setSlot(13, new GuiElementBuilder(Items.LIGHT_GRAY_STAINED_GLASS)
                .setName(Component.literal("Buckets: " + forge.internalStorage.amount))
        );
    }
}
