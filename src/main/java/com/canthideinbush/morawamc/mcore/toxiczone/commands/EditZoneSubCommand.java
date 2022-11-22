package com.canthideinbush.morawamc.mcore.toxiczone.commands;


import com.canthideinbush.morawamc.mcore.MCore;
import com.canthideinbush.morawamc.mcore.toxiczone.ToxicZone;
import com.canthideinbush.utils.commands.InternalCommand;

public abstract class EditZoneSubCommand extends InternalCommand {

    protected ToxicZone getZone(String[] args) {
        return MCore.instance().getToxicZonesManager().findByKey(args[getArgIndex() - getParentCommand().getArgCount()]);
    }

    @Override
    public Class<? extends InternalCommand> getParentCommandClass() {
        return EditZoneCommand.class;
    }
}
