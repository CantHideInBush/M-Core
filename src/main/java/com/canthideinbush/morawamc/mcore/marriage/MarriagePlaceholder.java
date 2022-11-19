package com.canthideinbush.morawamc.mcore.marriage;

import me.clip.placeholderapi.PlaceholderHook;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MarriagePlaceholder extends PlaceholderExpansion {

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {
        return GsonComponentSerializer.gson().serialize(Component.text("Test").style(Style.style().hoverEvent(HoverEvent.showText(Component.text("test")))));
    }

    @Override
    public @NotNull String getIdentifier() {
        return "marriage";
    }

    @Override
    public @NotNull String getAuthor() {
        return "CantHideInBush";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }
}
