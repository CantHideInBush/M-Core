package com.canthideinbush.morawamc.mcore.gender;

import com.canthideinbush.utils.commands.InternalCommand;
import com.canthideinbush.utils.storing.ArgParser;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class SetGender extends InternalCommand {

    @Override
    public boolean execute(Player sender, String[] args) {

        ArgParser parser = new ArgParser(args, getArgIndex());
        if (!parser.hasNext()) {
            sendConfigErrorMessage(sender, "command-arguments-insufficient");
            return false;
        }

        String name = parser.next();
        Gender gender;
        if ((gender = Gender.getByName(name)) == null) {
            sendConfigErrorMessage(sender, "commands.gender.set.gender-nonexistent");
            return false;
        }

        Gender.setGender(sender, gender.name);
        sendConfigSuccessMessage(sender, "commands.gender.set.success");


        return true;
    }

    @Override
    public String getName() {
        return "set";
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
