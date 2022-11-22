package com.canthideinbush.morawamc.mcore.worldspawns;

import com.canthideinbush.morawamc.mcore.MCore;
import com.canthideinbush.morawamc.mcore.commands.MainCommand;
import com.canthideinbush.utils.commands.InternalCommand;
import com.canthideinbush.utils.storing.ArgParser;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RemoveWorldSpawn extends InternalCommand {

    @Override
    public boolean execute(Player sender, String[] args) {
        ArgParser parser = new ArgParser(args, getArgIndex());
        if (!parser.hasNext()) {
            sendConfigErrorMessage(sender, "command-arguments-insufficient");
            return false;
        }
        World world = parser.nextWorld();
        if (world == null) {
            sendConfigErrorMessage(sender, "world-nonexistent");
            return false;
        }

        MCore.instance().worldSpawnsStorage().set(world.getName(), null);

        sendConfigSuccessMessage(sender, "commands.remove-world-spawn.success");
        return true;
    }

    @Override
    public String getName() {
        return "removeworldspawn";
    }

    @Override
    public Class<? extends InternalCommand> getParentCommandClass() {
        return MainCommand.class;
    }

    @Override
    public List<String> complete(String[] args) {
        if (args.length - 1 == getArgIndex()) return Bukkit.getWorlds().stream().map(World::getName).collect(Collectors.toList());
        return Collections.emptyList();
    }
}
