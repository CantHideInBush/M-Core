package com.canthideinbush.morawamc.mcore.commands;

import com.canthideinbush.morawamc.mcore.MCore;
import com.canthideinbush.utils.commands.InternalCommand;
import org.bukkit.command.ConsoleCommandSender;

import java.util.Collections;
import java.util.List;

public class ReloadCommand extends InternalCommand {

    @Override
    public boolean execute(ConsoleCommandSender sender, String[] args) {

        MCore.instance().reload();

        sendConfigSuccessMessage(sender, "commands-reload-success");


        return true;
    }

    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public Class<? extends InternalCommand> getParentCommandClass() {
        return MainCommand.class;
    }

    @Override
    public List<String> complete(String[] strings) {
        return Collections.emptyList();
    }
}
