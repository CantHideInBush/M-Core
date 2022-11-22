package com.canthideinbush.morawamc.mcore.worldspawns;

import com.canthideinbush.morawamc.mcore.MCore;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class TeleportListener implements Listener {

    @EventHandler
    public void portalEvent(PlayerPortalEvent event) {
        World world = event.getTo().getWorld();
        if (MCore.instance().worldSpawnsStorage().contains(world.getName())) {
            event.setCancelled(true);
            event.getPlayer().teleport(MCore.instance().worldSpawnsStorage().getLocation(world.getName()));
        }
    }

    @EventHandler
    public void onTeleport(PlayerTeleportEvent event) {
        if (!event.getCause().equals(PlayerTeleportEvent.TeleportCause.END_PORTAL)) return;
        World world = event.getTo().getWorld();
        if (MCore.instance().worldSpawnsStorage().contains(world.getName())) {
            event.setCancelled(true);
            event.getPlayer().teleport(MCore.instance().worldSpawnsStorage().getLocation(world.getName()));
        }
    }

}
