package com.canthideinbush.morawamc.mcore.toxiczone;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import java.util.UUID;

public class AntiRadiationPotionEffect {


    private final double decremental;
    private final UUID player;
    private final BossBar bossBar;

    public AntiRadiationPotionEffect(Player player, double duration) {
        decremental = 1 / duration;
        this.player = player.getUniqueId();
        (bossBar = Bukkit.createBossBar(ChatColor.GREEN + "Odporność na radiację", BarColor.GREEN, BarStyle.SOLID)).addPlayer(player);
    }


    private boolean removed = false;

    public boolean isRemoved() {
        return removed;
    }

    public boolean update() {
        if (removed) return true;
        if (bossBar.getProgress() - decremental < 0) {
            remove();
            return true;
        }
        bossBar.setProgress(bossBar.getProgress() - decremental);
        return false;
    }

    public void remove() {
        removed = true;
        bossBar.removePlayer(Bukkit.getPlayer(player));
    }


    public UUID getPlayer() {
        return player;
    }

    public BossBar getBossBar() {
        return bossBar;
    }
}
