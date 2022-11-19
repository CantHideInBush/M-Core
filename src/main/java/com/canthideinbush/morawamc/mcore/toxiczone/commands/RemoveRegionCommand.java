package com.canthideinbush.morawamc.mcore.toxiczone.commands;

import com.canthideinbush.morawamc.mcore.toxiczone.ToxicZone;
import com.canthideinbush.morawamc.mcore.toxiczone.commands.EditZoneSubCommand;
import com.canthideinbush.utils.storing.ArgParser;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RemoveRegionCommand extends EditZoneSubCommand {

    @Override
    public boolean execute(CommandSender sender, String[] args) {

        ArgParser parser = new ArgParser(args, getArgIndex());
        if (!parser.hasNext()) {
            sendConfigErrorMessage(sender, "command-arguments-insufficient");
            return false;
        }

        String id = parser.next();
        ToxicZone zone = getZone(args);
        if (!zone.regions.contains(id)) {
            sendConfigErrorMessage(sender, "commands.toxic-zone.edit.remove-region.region-nonexistent");
            return false;
        }

        zone.regions.remove(id);

        sendConfigErrorMessage(sender, "commands.toxic-zone.edit.remove-region.success");


        return true;
    }

    @Override
    public String getName() {
        return "removeregion";
    }

    @Override
    public List<String> complete(String[] args) {
        if (args.length - 1 == getArgIndex()) {
            return new ArrayList<>(getZone(args).regions);
        }
        return Collections.emptyList();
    }
}
