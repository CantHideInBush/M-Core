package com.canthideinbush.morawamc.mcore.toxiczone.commands;

import com.canthideinbush.morawamc.mcore.toxiczone.ToxicZone;
import com.canthideinbush.utils.storing.ArgParser;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class SetEndRadiusCommand extends EditZoneSubCommand {

    @Override
    public boolean execute(Player sender, String[] args) {


        ArgParser parser = new ArgParser(args, getArgIndex());

        if (!parser.hasNext()) {
            sendConfigErrorMessage(sender, "command-arguments-insufficient");
            return false;
        }

        ToxicZone zone = getZone(args);

        int radius;
        try {
            radius = parser.nextInt();
        } catch (NumberFormatException e) {
            sendConfigErrorMessage(sender, "incorrect-data-type");
            return false;
        }

        zone.endRadius = radius;



        sendConfigSuccessMessage(sender, "commands.toxic-zone.edit.set-end-radius.success", radius);

        return true;
    }

    @Override
    public String getName() {
        return "setend";
    }


    @Override
    public List<String> complete(String[] args) {
        return Collections.emptyList();
    }
}
