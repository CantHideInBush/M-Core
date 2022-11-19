package com.canthideinbush.morawamc.mcore.toxiczone.commands;

import com.canthideinbush.morawamc.mcore.MCore;
import com.canthideinbush.morawamc.mcore.toxiczone.ToxicZone;
import com.canthideinbush.utils.storing.ArgParser;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

public class AddRegionCommand extends EditZoneSubCommand {

    @Override
    public boolean execute(CommandSender sender, String[] args) {


        ArgParser parser = new ArgParser(args, getArgIndex());

        if (!parser.hasNext()) {
            sendConfigErrorMessage(sender, "command-arguments-insufficient");
            return false;
        }

        String id = parser.next();

        ToxicZone zone = getZone(args);
        if (MCore.getInstance().getToxicZonesManager().getByRegion(zone.world, id) != null) {
            sendConfigErrorMessage(sender, "commands.toxic-zone.edit.add-region.taken");
            return false;
        }

        zone.regions.add(id);
        sendConfigSuccessMessage(sender, "commands.toxic-zone.edit.add-region.success");

        return true;
    }

    @Override
    public String getName() {
        return "addregion";
    }


    @Override
    public List<String> complete(String[] args) {
        if (args.length - 1 == getArgIndex()) {
            return Collections.singletonList(" ");
        }
        return Collections.emptyList();
    }
}
