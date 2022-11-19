package com.canthideinbush.morawamc.mcore.toxiczone.commands;

import com.canthideinbush.morawamc.mcore.commands.MainCommand;
import com.canthideinbush.morawamc.mcore.toxiczone.commands.AddPotionTagCommand;
import com.canthideinbush.morawamc.mcore.toxiczone.commands.CreateCommand;
import com.canthideinbush.morawamc.mcore.toxiczone.commands.EditZoneCommand;
import com.canthideinbush.morawamc.mcore.toxiczone.commands.RemoveCommand;
import com.canthideinbush.utils.commands.InternalCommand;
import com.canthideinbush.utils.commands.ParentCommand;

public class ToxicZoneParentCommand extends ParentCommand {

    public ToxicZoneParentCommand() {
        subCommands.add(new CreateCommand());
        subCommands.add(new RemoveCommand());
        subCommands.add(new EditZoneCommand());
        subCommands.add(new AddPotionTagCommand());
    }

    @Override
    public String getName() {
        return "toxiczone";
    }

    @Override
    public Class<? extends InternalCommand> getParentCommandClass() {
        return MainCommand.class;
    }

}
