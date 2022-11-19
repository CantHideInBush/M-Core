package com.canthideinbush.morawamc.mcore.toxiczone.commands;

import com.canthideinbush.morawamc.mcore.toxiczone.commands.EditZoneSubCommand;
import com.canthideinbush.utils.storing.ArgParser;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class SetDamageCommand extends EditZoneSubCommand {

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        ArgParser parser = new ArgParser(args, getArgIndex());
        if (!parser.hasNext()) {
            sendConfigErrorMessage(sender, "command-arguments-insufficient");
            return false;
        }

        double damage;

        try {
            damage = parser.nextDouble();
        } catch (NumberFormatException e) {
            sendConfigErrorMessage(sender, "incorrect-data-type");
            return false;
        }

        if (damage < 1) damage = 1;

        getZone(args).damage = damage;

        sendConfigSuccessMessage(sender, "commands.toxic-zone.edit.set-damage.success", damage);

        return true;
    }

    @Override
    public String getName() {
        return "setdamage";
    }

    @Override
    public List<String> complete(String[] args) {
        if (args.length - 1 == getArgIndex()) {
            if (getZone(args) != null) return Collections.singletonList(Optional.of(getZone(args).damage).orElse(1.0) + "");
        }
        return Collections.emptyList();
    }
}
