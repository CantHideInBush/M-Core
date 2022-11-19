package com.canthideinbush.morawamc.mcore.toxiczone;

import com.canthideinbush.morawamc.mcore.MCore;
import com.canthideinbush.utils.WorldGuardUtils;
import com.canthideinbush.utils.managers.KeyedStorage;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
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
        MCore.getInstance().getToxicZonesStorage().set("ToxicZones", objects);
    }

    public void load() {
        MCore.getInstance().getToxicZonesStorage().getList("ToxicZones", Collections.emptyList()).forEach(tZ -> this.register((ToxicZone) tZ));
    }

    public List<ToxicZone> getByWorld(World world) {
        return objects.stream().filter(tZ -> tZ.world.equals(world)).collect(Collectors.toList());
    }

    public ToxicZone getByRegion(World world, String id) {
        return objects.stream().filter(tZ -> tZ.world.equals(world) && tZ.regions.contains(id)).findAny().orElse(null);
    }

    public ToxicZone getByRegion(Location world, String id) {
        return objects.stream().filter(tZ -> tZ.world.equals(world.getWorld()) && tZ.regions.contains(id)).findAny().orElse(null);
    }

    public ToxicZone getByLocation(Location location) {
        ProtectedRegion region = WorldGuardUtils.getHighestPriorityRegion(location);
        if (region == null) return null;
        else return getByRegion(location.getWorld(), region.getId());
    }

    public List<String> getIdList() {
        return objects.stream().map(ToxicZone::getKey).collect(Collectors.toList());
    }

}
