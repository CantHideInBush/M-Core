package com.canthideinbush.morawamc.mcore.gender;

import com.canthideinbush.morawamc.mcore.commands.MainCommand;
import com.canthideinbush.utils.commands.InternalCommand;
import com.canthideinbush.utils.commands.ParentCommand;

public class GenderParentCommand extends ParentCommand {

    public GenderParentCommand() {
        subCommands.add(new CreateGender());
        subCommands.add(new RemoveGender());
        subCommands.add(new SetGender());
    }

    @Override
    public String getName() {
        return "gender";
    }

    @Override
    public Class<? extends InternalCommand> getParentCommandClass() {
        return MainCommand.class;
    }
}
