package com.canthideinbush.morawamc.mcore.toxiczone;

import com.canthideinbush.morawamc.mcore.MCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Iterator;

public class ToxicZoneHandler implements Listener {

    private final BukkitTask task;
    private final BukkitTask asyncTask;
    private int tick = 0;

    private final BossBar bossBar;

    private final ArrayList<AntiRadiationPotionEffect> antiRadiation = new ArrayList<>();

    public ToxicZoneHandler() {
        Bukkit.getPluginManager().registerEvents(this, MCore.instance());
        bossBar = Bukkit.createBossBar(ChatColor.RED + "Strefa Skażenia", BarColor.RED, BarStyle.SEGMENTED_20);
        task = Bukkit.getScheduler().runTaskTimer(MCore.instance(), () -> {

            for (Player player : Bukkit.getOnlinePlayers()) {
                for (ToxicZone zone : MCore.instance().getToxicZonesManager().getByWorld(player.getWorld())) {
                    Location loc = player.getLocation();
                    loc.setY(0);
                    Location zoneCenter = zone.center.clone();
                    zoneCenter.setY(0);
                    double distance = loc.distance(zoneCenter);
                    if (distance >= zone.startRadius && distance < zone.endRadius && zone.center.getWorld().equals(player.getWorld())) {
                        AntiRadiationPotionEffect effect = antiRadiationPotionEffectByPlayer(player);
                        if (effect == null && tick % zone.interval == 0) {
                            player.damage(zone.damage);
                            player.setNoDamageTicks(zone.interval);
                            player.addPotionEffect(new PotionEffect(tick % (zone.interval * 2) == 0 ? PotionEffectType.WITHER : PotionEffectType.POISON, zone.interval, 0));
                        }
                        if (effect != null) {
                            if (!bossBar.getPlayers().contains(player)) effect.getBossBar().removePlayer(player);
                            else if (!effect.isRemoved()) effect.getBossBar().addPlayer(player);
                        }
                        bossBar.addPlayer(player);

                    } else bossBar.removePlayer(player);
                }
            }

            tick++;
        }, 0, 1);
        asyncTask = Bukkit.getScheduler().runTaskTimerAsynchronously(MCore.instance(), () -> {
            Iterator<AntiRadiationPotionEffect> iterator = antiRadiation.iterator();
            AntiRadiationPotionEffect antiRadiation;
            while (iterator.hasNext()) {
                antiRadiation = iterator.next();
                if (Bukkit.getPlayer(antiRadiation.getPlayer()) != null && antiRadiation.update()) iterator.remove();
            }
        }, 0, 1);

    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntityType().equals(EntityType.PLAYER)) {
            Player player = (Player) event.getEntity();
            if ((event.getCause().equals(EntityDamageEvent.DamageCause.WITHER)
             || event.getCause().equals(EntityDamageEvent.DamageCause.POISON))
             && !ToxicZonesManager.isSafe(player)) {
                event.setCancelled(true);
            }

        }
    }

    @EventHandler
    public void onConsume(PlayerItemConsumeEvent event) {
        double duration = getAntiRadiationDuration(event.getItem());
        if (duration > 0) {
            AntiRadiationPotionEffect current = antiRadiationPotionEffectByPlayer(event.getPlayer());
            if (current != null) {
                current.remove();
            }
            antiRadiation.add(new AntiRadiationPotionEffect(event.getPlayer(), duration));
        }
    }

    public BukkitTask getTask() {
        return task;
    }

    public BukkitTask getAsyncTask() {
        return asyncTask;
    }

    public AntiRadiationPotionEffect antiRadiationPotionEffectByPlayer(Player player) {
        return antiRadiation.stream().filter(aR -> aR.getPlayer().equals(player.getUniqueId())).findAny().orElse(null);
    }

    private double getAntiRadiationDuration(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        return meta.getPersistentDataContainer().has(new NamespacedKey(MCore.instance(), "AntiRadiationDuration")) ?
                meta.getPersistentDataContainer().get(new NamespacedKey(MCore.instance(), "AntiRadiationDuration"), PersistentDataType.DOUBLE) : 0;
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (antiRadiationPotionEffectByPlayer(event.getPlayer()) != null) {
            antiRadiationPotionEffectByPlayer(event.getPlayer()).getBossBar().addPlayer(event.getPlayer());
        }
    }


}
