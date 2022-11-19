package com.canthideinbush.morawamc.mcore.gender;

import com.canthideinbush.utils.commands.InternalCommand;
import com.canthideinbush.utils.storing.ArgParser;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

public class CreateGender extends InternalCommand  {
    @Override
    public boolean execute(CommandSender sender, String[] args) {

        ArgParser parser = new ArgParser(args, getArgIndex());
        if (!parser.hasNext(3)) {
            sendConfigErrorMessage(sender, "command-arguments-insufficient");
            return false;
        }

        String name = parser.next();
        if (Gender.getByName(name) != null) {
            sendConfigErrorMessage(sender, "commands.gender.create.name-taken");
            return false;
        }




        Gender newGender = new Gender(name, parser.next(), parser.next());
        newGender.save();
        sendConfigSuccessMessage(sender, "commands.gender.create.success");


        return true;
    }

    @Override
    public String getName() {
        return "create";
    }

    @Override
    public Class<? extends InternalCommand> getParentCommandClass() {
        return GenderParentCommand.class;
    }

    @Override
    public List<String> complete(String[] args) {
        if (args.length - 1 == getArgIndex()) return Collections.singletonList(getUtilsProvider().chat.getMessage("common.name"));
        if (args.length - 1 == getArgIndex() + 1) return Collections.singletonList(getUtilsProvider().chat.getMessage("common.short-name"));
        if (args.length - 1 == getArgIndex() + 2) return Collections.singletonList(getUtilsProvider().chat.getMessage("common.symbol"));
        return Collections.emptyList();
    }
}
