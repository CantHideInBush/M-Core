package com.canthideinbush.morawamc.mcore.gender;


import com.canthideinbush.morawamc.mcore.MCore;
import com.canthideinbush.utils.storing.ABSave;
import com.canthideinbush.utils.storing.YAMLElement;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@SerializableAs("Gender")
public class Gender implements ABSave  {

    public Gender(Map<String, Object> map) {
        deserializeFromMap(map);
    }

    public Gender(String name, String shortName, String symbol) {
        this.name = name;
        this.shortName = shortName;
        this.symbol = symbol;
    }

    public String name;

    @YAMLElement
    public String shortName;


    @YAMLElement
    public String symbol;


    public void save() {
        MCore.instance().getGenderStorage().set("Genders." + name, this);
    }
    public void remove() {
        MCore.instance().getGenderStorage().set("Genders." + name, null);
    }

    public static Gender getByName(String name) {
        if (name == null) return null;
        Gender gender = MCore.instance().getGenderStorage().getObject("Genders." + name, Gender.class);
        if (gender != null) gender.name = name;
        return gender;
    }

    public static List<String> getNameList() {
        ConfigurationSection section = MCore.instance().getGenderStorage().getConfigurationSection("Genders");
        if (section == null) return Collections.emptyList();
        return new ArrayList<>(section.getKeys(false));
    }

    public static void setGender(Player player, String gender) {
        MCore.instance().getGenderStorage().set("Players." + player.getName(), gender);
    }

    public static  Gender getGender(String player) {
        return getByName(MCore.instance().getGenderStorage().getString("Players." + player, null));
    }


}
