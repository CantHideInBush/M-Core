package com.canthideinbush.morawamc.mcore.toxiczone.commands;

import com.canthideinbush.morawamc.mcore.MCore;
import com.canthideinbush.morawamc.mcore.toxiczone.ToxicZone;
import com.canthideinbush.utils.commands.InternalCommand;
import com.canthideinbush.utils.storing.ArgParser;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

public class RemoveCommand extends InternalCommand {

    @Override
    public boolean execute(CommandSender sender, String[] args) {

        ArgParser parser = new ArgParser(args, getArgIndex());

        if (!parser.hasNext()) {
            sendConfigErrorMessage(sender, "command-arguments-insufficient");
            return false;
        }

        ToxicZone zone;
        if ((zone = MCore.instance().getToxicZonesManager().findByKey(parser.next())) == null) {
            sendConfigErrorMessage(sender, "common.zone-nonexistent");
            return false;
        }


        MCore.instance().getToxicZonesManager().unregister(zone);

        sendConfigSuccessMessage(sender, "commands.toxic-zone.remove.success");

        return true;
    }

    @Override
    public String getName() {
        return "remove";
    }

    @Override
    public Class<? extends InternalCommand> getParentCommandClass() {
        return ToxicZoneParentCommand.class;
    }

    @Override
    public List<String> complete(String[] args) {
        if (args.length - 1 == getArgIndex()) {
            return MCore.instance().getToxicZonesManager().getIdList();
        }
        return Collections.emptyList();
    }
}
