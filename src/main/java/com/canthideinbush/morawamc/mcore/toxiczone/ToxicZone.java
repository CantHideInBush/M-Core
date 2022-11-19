package com.canthideinbush.morawamc.mcore.toxiczone;

import com.canthideinbush.utils.managers.Keyed;
import com.canthideinbush.utils.storing.ABSave;
import com.canthideinbush.utils.storing.YAMLElement;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.serialization.SerializableAs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@SerializableAs("ToxicZone")
public class ToxicZone implements ABSave, Keyed<String> {

    public ToxicZone(Map<String, Object> map) {
        deserializeFromMap(map);
    }

    @YAMLElement
    private String id;
    @YAMLElement
    public World world;
    @YAMLElement
    public int interval = 20;
    @YAMLElement
    public double damage = 1;
    @YAMLElement
    public Collection<String> regions = new ArrayList<>();
    

    public ToxicZone(String id, World world) {
        this.id = id;
        this.world = world;
    }


    @Override
    public String getKey() {
        return id;
    }
}
