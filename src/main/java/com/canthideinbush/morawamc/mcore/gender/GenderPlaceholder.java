package com.canthideinbush.morawamc.mcore.gender;

import com.canthideinbush.morawamc.mcore.MCore;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GenderPlaceholder extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "mgender";
    }

    @Override
    public @NotNull String getAuthor() {
        return "CantHideInBush";
    }

    @Override
    public @NotNull String getVersion() {
        return MCore.instance().getDescription().getVersion();
    }

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {
        Gender gender = Gender.getGender(player.getName());
        if (gender == null) return "";
        String returnValue = "";
        switch (params) {
            case "name" -> returnValue = gender.name;
            case "shortname" -> returnValue = gender.shortName;
            case "symbol" -> returnValue = gender.symbol;
        }


        return ChatColor.translateAlternateColorCodes('&', returnValue);
    }
}
