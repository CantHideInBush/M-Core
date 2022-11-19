package com.canthideinbush.morawamc.mcore.toxiczone.commands;

import com.canthideinbush.morawamc.mcore.MCore;
import com.canthideinbush.morawamc.mcore.toxiczone.ToxicZone;
import com.canthideinbush.utils.storing.ArgParser;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class SetCenterCommand extends EditZoneSubCommand {

    @Override
    public boolean execute(Player sender, String[] args) {


        ArgParser parser = new ArgParser(args, getArgIndex());

        if (!parser.hasNext()) {
            sendConfigErrorMessage(sender, "command-arguments-insufficient");
            return false;
        }

        ToxicZone zone = getZone(args);
        zone.center = sender.getLocation();


        sendConfigSuccessMessage(sender, "commands.toxic-zone.edit.set-center.success");

        return true;
    }

    @Override
    public String getName() {
        return "setcenter";
    }


    @Override
    public List<String> complete(String[] args) {
        return Collections.emptyList();
    }
}
