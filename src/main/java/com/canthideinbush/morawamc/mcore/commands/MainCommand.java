package com.canthideinbush.morawamc.mcore.commands;

import com.canthideinbush.morawamc.mcore.gender.GenderParentCommand;
import com.canthideinbush.morawamc.mcore.toxiczone.commands.ToxicZoneParentCommand;
import com.canthideinbush.utils.CHIBPlugin;
import com.canthideinbush.utils.commands.InternalCommand;
import com.canthideinbush.utils.commands.ParentCommand;

public class MainCommand extends ParentCommand {

    public MainCommand(CHIBPlugin plugin) {
        super(plugin);
        subCommands.add(new ToxicZoneParentCommand());
        subCommands.add(new GenderParentCommand());
        subCommands.add(new ReloadCommand());
    }


    @Override
    public String getName() {
        return "mcore";
    }

    @Override
    public Class<? extends InternalCommand> getParentCommandClass() {
        return null;
    }
}
