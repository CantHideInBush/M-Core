package com.canthideinbush.morawamc.mcore.toxiczone;

import com.canthideinbush.morawamc.mcore.MCore;
import com.canthideinbush.utils.managers.Keyed;
import com.canthideinbush.utils.storing.ABSave;
import com.canthideinbush.utils.storing.YAMLElement;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.serialization.SerializableAs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@SerializableAs("ToxicZone")
public class ToxicZone implements ABSave, Keyed<String> {

    public ToxicZone(Map<String, Object> map) {
        deserializeFromMap(map);
    }

    @YAMLElement
    private String id;
    @YAMLElement
    public int interval = 20;
    @YAMLElement
    public double damage = 1;

    @YAMLElement
    public int startRadius = 3500;

    public int endRadius = 60000000;

    @Override
    public boolean saveNullFields() {
        return false;
    }

    @YAMLElement
    public Location center;

    public ToxicZone(String id, Location center) {
        this.id = id;
        this.center = center;
    }


    @Override
    public String getKey() {
        return id;
    }
}
