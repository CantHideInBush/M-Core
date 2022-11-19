package com.canthideinbush.morawamc.mcore.toxiczone.commands;

import com.canthideinbush.morawamc.mcore.toxiczone.commands.EditZoneSubCommand;
import com.canthideinbush.utils.storing.ArgParser;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class SetIntervalCommand extends EditZoneSubCommand {

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        ArgParser parser = new ArgParser(args, getArgIndex());
        if (!parser.hasNext()) {
            sendConfigErrorMessage(sender, "command-arguments-insufficient");
            return false;
        }

        int interval;

        try {
            interval = parser.nextInt();
        } catch (NumberFormatException e) {
            sendConfigErrorMessage(sender, "incorrect-data-type");
            return false;
        }

        if (interval < 1) interval = 1;

        getZone(args).interval = interval;

        sendConfigSuccessMessage(sender, "commands.toxic-zone.edit.set-interval.success", interval);

        return true;
    }

    @Override
    public String getName() {
        return "setinterval";
    }

    @Override
    public List<String> complete(String[] args) {
        if (args.length - 1 == getArgIndex()) {
            if (getZone(args) != null) return Collections.singletonList(Optional.of(getZone(args).interval).orElse(20) + "");
        }
        return Collections.emptyList();
    }
}
