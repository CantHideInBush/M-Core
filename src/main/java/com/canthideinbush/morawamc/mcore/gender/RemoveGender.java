package com.canthideinbush.morawamc.mcore.gender;

import com.canthideinbush.utils.commands.InternalCommand;
import com.canthideinbush.utils.storing.ArgParser;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

public class RemoveGender extends InternalCommand  {
    @Override
    public boolean execute(CommandSender sender, String[] args) {

        ArgParser parser = new ArgParser(args, getArgIndex());
        if (!parser.hasNext()) {
            sendConfigErrorMessage(sender, "command-arguments-insufficient");
            return false;
        }

        String name = parser.next();
        if (Gender.getByName(name) == null) {
            sendConfigErrorMessage(sender, "commands.gender.remove.name-nonexistent");
            return false;
        }

        Gender.getByName(name).remove();
        sendConfigSuccessMessage(sender, "commands.gender.remove.success");


        return true;
    }

    @Override
    public String getName() {
        return "remove";
    }

    @Override
    public Class<? extends InternalCommand> getParentCommandClass() {
        return GenderParentCommand.class;
    }

    @Override
    public List<String> complete(String[] args) {
        if (args.length - 1 == getArgIndex()) return Gender.getNameList();
        return Collections.emptyList();
    }
}
