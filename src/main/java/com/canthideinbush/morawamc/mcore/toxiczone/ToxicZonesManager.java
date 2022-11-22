package com.canthideinbush.morawamc.mcore.toxiczone;

import com.canthideinbush.morawamc.mcore.MCore;
import com.canthideinbush.utils.managers.KeyedStorage;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ToxicZonesManager implements KeyedStorage<ToxicZone> {


    public ToxicZonesManager() {
        load();
    }


    private final ArrayList<ToxicZone> objects = new ArrayList<>();

    @Override
    public Collection<ToxicZone> getObjects() {
        return objects;
    }

    public void save() {
        MCore.instance().getToxicZonesStorage().set("ToxicZones", objects);
    }

    public void load() {
        MCore.instance().getToxicZonesStorage().getList("ToxicZones", Collections.emptyList()).forEach(tZ -> this.register((ToxicZone) tZ));
        ArrayList<ToxicZone> toRemove = new ArrayList<>();
        for (ToxicZone zone : objects) {
            if (zone.center == null) {
                toRemove.add(zone);
                MCore.instance().getLogger().info("Removing legacy toxic zone: " + zone.getKey());
            }
        }
        toRemove.forEach(this::unregister);
    }

    public List<ToxicZone> getByWorld(World world) {
        return objects.stream().filter(tZ -> tZ.center.getWorld().equals(world)).collect(Collectors.toList());
    }

    public List<String> getIdList() {
        return objects.stream().map(ToxicZone::getKey).collect(Collectors.toList());
    }

    public static boolean isSafe(Player player) {
        for (ToxicZone zone : MCore.instance().getToxicZonesManager().getByWorld(player.getWorld())) {
            Location loc = player.getLocation();
            Location center = zone.center.clone();
            loc.setY(0);
            center.setY(0);
            double distance = loc.distance(center);
            if (distance >= zone.startRadius && distance < zone.endRadius) return false;
        }
        return true;
    }

}
