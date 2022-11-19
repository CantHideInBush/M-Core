package com.canthideinbush.morawamc.mcore.toxiczone.commands;

import com.canthideinbush.morawamc.mcore.MCore;
import com.canthideinbush.morawamc.mcore.toxiczone.ToxicZone;
import com.canthideinbush.utils.commands.InternalCommand;
import com.canthideinbush.utils.storing.ArgParser;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CreateCommand extends InternalCommand {

    @Override
    public boolean execute(CommandSender sender, String[] args) {

        ArgParser parser = new ArgParser(args, getArgIndex());

        if (!parser.hasNext()) {
            sendConfigErrorMessage(sender, "command-arguments-insufficient");
            return false;
        }

        String id = parser.next();

        if (!parser.hasNext()) {
            sendConfigErrorMessage(sender, "command-arguments-insufficient");
            return false;
        }

        if (MCore.getInstance().getToxicZonesManager().findByKey(id) != null) {
            sendConfigErrorMessage(sender, "commands.toxic-zone.create.id-taken");
            return false;
        }

        String world = parser.next();
        if (Bukkit.getWorld(world) == null) {
            sendConfigErrorMessage(sender, "common.world-nonexistent");
            return false;
        }





        MCore.getInstance().getToxicZonesManager().register(new ToxicZone(id, Bukkit.getWorld(world)));

        sendConfigSuccessMessage(sender, "commands.toxic-zone.create.success");


        return true;
    }

    @Override
    public String getName() {
        return "create";
    }

    @Override
    public Class<? extends InternalCommand> getParentCommandClass() {
        return ToxicZoneParentCommand.class;
    }

    @Override
    public List<String> complete(String[] args) {
        if (args.length - 1 == getArgIndex()) {
            return Collections.singletonList(" ");
        }
        else if (args.length - 1 == getArgIndex() + 1) {
            return Bukkit.getWorlds().stream().map(World::getName).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
