package com.canthideinbush.morawamc.mcore.toxiczone.commands;

import com.canthideinbush.morawamc.mcore.MCore;
import com.canthideinbush.utils.commands.InternalCommand;
import com.canthideinbush.utils.storing.ArgParser;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Collections;
import java.util.List;

public class AddPotionTagCommand extends InternalCommand  {

    @Override
    public boolean execute(Player sender, String[] args) {

        ItemStack stack = sender.getInventory().getItemInMainHand();
        if (stack.getType().equals(Material.AIR)) {
            sendConfigErrorMessage(sender, "common.item-in-hand-required");
            return false;
        }

        ArgParser parser = new ArgParser(args, getArgIndex());
        if (!parser.hasNext()) {
            sendConfigErrorMessage(sender, "commands.toxic-zone.add-potion-tag.duration-required");
            return false;
        }

        double duration;
        try {
            duration = parser.nextDouble();
        } catch (NumberFormatException e) {
            sendConfigErrorMessage(sender, "incorrect-data-type");
            return false;
        }

        ItemMeta meta = stack.getItemMeta();
        meta.getPersistentDataContainer().set(new NamespacedKey(MCore.instance(), "AntiRadiationDuration"), PersistentDataType.DOUBLE, duration * 20);
        stack.setItemMeta(meta);
        sendConfigSuccessMessage(sender, "commands.toxic-zone.add-potion-tag.success");
        return true;
    }

    @Override
    public String getName() {
        return "addpotiontag";
    }

    @Override
    public Class<? extends InternalCommand> getParentCommandClass() {
        return ToxicZoneParentCommand.class;
    }

    @Override
    public List<String> complete(String[] args) {
        if (args.length - 1 == getArgIndex()) {
            return Collections.singletonList("10");
        }
        return Collections.emptyList();
    }
}
