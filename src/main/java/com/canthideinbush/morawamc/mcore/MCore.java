package com.canthideinbush.morawamc.mcore;

import com.canthideinbush.morawamc.mcore.commands.MainCommand;
import com.canthideinbush.morawamc.mcore.gender.Gender;
import com.canthideinbush.morawamc.mcore.gender.GenderPlaceholder;
import com.canthideinbush.morawamc.mcore.marriage.MarriagePlaceholder;
import com.canthideinbush.morawamc.mcore.toxiczone.ToxicZone;
import com.canthideinbush.morawamc.mcore.toxiczone.ToxicZoneHandler;
import com.canthideinbush.morawamc.mcore.toxiczone.ToxicZonesManager;
import com.canthideinbush.utils.CHIBPlugin;
import com.canthideinbush.utils.storing.YAMLConfig;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

public final class MCore extends CHIBPlugin {

    static {
        ConfigurationSerialization.registerClass(ToxicZone.class);
        ConfigurationSerialization.registerClass(Gender.class);
    }
    private static MCore instance;
    public static MCore getInstance() {
        return instance;
    }

    private YAMLConfig messagesConfig;
    private YAMLConfig toxicZonesStorage;

    private YAMLConfig genderStorage;


    private ToxicZonesManager toxicZonesManager;
    private ToxicZoneHandler toxicZoneHandler;

    @Override
    public void onEnable() {
        instance = this;

        loadConfigurations();
        CHIBInit();
        loadManagers();

        new MainCommand(this);

        new MarriagePlaceholder().register();
        new GenderPlaceholder().register();

    }

    @Override
    public void onDisable() {

        saveManagers();
        saveConfigurations();

    }


    private void loadConfigurations() {
        messagesConfig = new YAMLConfig(this, "messages", true);
        toxicZonesStorage = new YAMLConfig(this, "toxicZones", false);
        genderStorage = new YAMLConfig(this, "genders", false);
    }

    private void saveConfigurations() {
        messagesConfig.save();
        toxicZonesStorage.save();
        genderStorage.save();
    }

    private void loadManagers() {
        toxicZonesManager = new ToxicZonesManager();
        toxicZoneHandler = new ToxicZoneHandler();
    }

    private void saveManagers() {
        toxicZonesManager.save();
    }

    @Override
    public String getPrefix() {
        return ChatColor.GREEN + "[" + ChatColor.GRAY + "MCore" + ChatColor.GREEN + "]";
    }

    @Override
    public YAMLConfig getMessageConfig() {
        return messagesConfig;
    }

    public ToxicZonesManager getToxicZonesManager() {
        return toxicZonesManager;
    }

    public YAMLConfig getToxicZonesStorage() {
        return toxicZonesStorage;
    }

    public ToxicZoneHandler getToxicZoneHandler() {
        return toxicZoneHandler;
    }

    public YAMLConfig getGenderStorage() {
        return genderStorage;
    }

    public void reload() {
        loadConfigurations();
        loadManagers();
    }
}
