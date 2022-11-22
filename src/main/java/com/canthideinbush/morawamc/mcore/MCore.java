package com.canthideinbush.morawamc.mcore;

import com.canthideinbush.morawamc.mcore.commands.MainCommand;
import com.canthideinbush.morawamc.mcore.gender.Gender;
import com.canthideinbush.morawamc.mcore.gender.GenderPlaceholder;
import com.canthideinbush.morawamc.mcore.marriage.MarriagePlaceholder;
import com.canthideinbush.morawamc.mcore.toxiczone.ToxicZone;
import com.canthideinbush.morawamc.mcore.toxiczone.ToxicZoneHandler;
import com.canthideinbush.morawamc.mcore.toxiczone.ToxicZonesManager;
import com.canthideinbush.morawamc.mcore.worldspawns.TeleportListener;
import com.canthideinbush.utils.CHIBPlugin;
import com.canthideinbush.utils.storing.YAMLConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.serialization.ConfigurationSerialization;

public final class MCore extends CHIBPlugin {

    static {
        ConfigurationSerialization.registerClass(ToxicZone.class);
        ConfigurationSerialization.registerClass(Gender.class);
    }
    private static MCore instance;
    public static MCore instance() {
        return instance;
    }

    private YAMLConfig messagesConfig;
    private YAMLConfig toxicZonesStorage;
    private YAMLConfig worldSpawnsStorage;

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

        Bukkit.getPluginManager().registerEvents(new TeleportListener(), this);

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
        worldSpawnsStorage = new YAMLConfig(this, "worldspawns");
    }

    private void saveConfigurations() {
        messagesConfig.save();
        toxicZonesStorage.save();
        genderStorage.save();
        worldSpawnsStorage.save();
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

    public YAMLConfig worldSpawnsStorage() {
        return worldSpawnsStorage;
    }

    public YAMLConfig getGenderStorage() {
        return genderStorage;
    }

    public void reload() {
        loadConfigurations();
        loadManagers();
    }
}
