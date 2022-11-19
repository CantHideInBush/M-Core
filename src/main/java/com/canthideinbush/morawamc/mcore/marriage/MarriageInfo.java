package com.canthideinbush.morawamc.mcore.marriage;

import com.canthideinbush.utils.storing.ABSave;
import com.canthideinbush.utils.storing.YAMLElement;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.entity.Player;

@SerializableAs("Marriage")
public class MarriageInfo implements ABSave {


    @YAMLElement
    public String first;
    @YAMLElement
    public String second;

    public String getFirst() {
        return first;
    }

    public String getSecond() {
        return second;
    }

    public OfflinePlayer getFirstOPlayer() {
        return Bukkit.getOfflinePlayer(first);
    }

    public OfflinePlayer getSecondOPlayer() {
        return Bukkit.getOfflinePlayer(second);
    }

    public String getOpposite(String name) {
        return first.equalsIgnoreCase(name) ? second : first;
    }

    public void save() {

    }



}
