package com.canthideinbush.morawamc.mcore.toxiczone.commands;

import com.canthideinbush.morawamc.mcore.MCore;
import com.canthideinbush.utils.commands.InternalCommand;
import com.canthideinbush.utils.commands.ParentCommand;
import com.canthideinbush.utils.storing.ArgParser;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class EditZoneCommand extends ParentCommand {

    public EditZoneCommand() {
        subCommands.add(new SetCenterCommand());
        subCommands.add(new SetIntervalCommand());
        subCommands.add(new SetDamageCommand());
        subCommands.add(new SetStartRadiusCommand());
        subCommands.add(new SetEndRadiusCommand());
    }


    @Override
    public boolean execute(CommandSender sender, String[] args) {

        ArgParser parser = new ArgParser(args, getArgIndex());

        if (!parser.hasNext()) {
            sendConfigErrorMessage(sender, "command-arguments-insufficient");
            return false;
        }


        if (MCore.instance().getToxicZonesManager().findByKey(parser.next()) == null) {
            sendConfigErrorMessage(sender, "common.zone-nonexistent");
            return false;
        }

        if (!parser.hasNext()) {
            sendConfigErrorMessage(sender, "command-arguments-insufficient");
            return false;
        }

        InternalCommand command;
        if ((command = getSubCommand(parser.next())) == null) {
            sendConfigErrorMessage(sender, "common.unknown-argument");
            return false;
        }

        return command.execute(sender, args);
    }

    @Override
    public String getName() {
        return "edit";
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
        else if (args.length - 1 == getArgIndex() + 1) {
            return getSubcommands().stream().map(InternalCommand::getName).collect(Collectors.toList());
        }
        else {
            InternalCommand command;
            if ((command = getSubCommand(args[getArgIndex()])) != null) {
                return command.complete(args);
            }
        }

        return Collections.emptyList();
    }

    @Override
    public int getArgCount() {
        return  2;
    }
}
