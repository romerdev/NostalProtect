package dev.nostal.nostalprotect.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

import static dev.nostal.nostalprotect.utils.PermissionUtility.getPlayerDebugMode;

public class DebugCommand implements CommandExecutor {

    public static MiniMessage miniMessage = MiniMessage.miniMessage();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            Component component = miniMessage.deserialize("<#008BF8>[NostalProtect v1.0]<#BEBFC5> this command can only be executed by players.");
            sender.sendMessage(component);
            return false;
        }

        Player player = (Player) sender;
        UUID uuid = player.getUniqueId();

        if (getPlayerDebugMode().get(uuid).equals(false)) {
            getPlayerDebugMode().put(uuid, true);

            Component component = miniMessage.deserialize("<#008BF8>[NostalProtect v1.0]<#BEBFC5> Debug mode has been turned on.");
            sender.sendMessage(component);

            return true;
        }

        getPlayerDebugMode().put(uuid, false);

        Component component = miniMessage.deserialize("<#008BF8>[NostalProtect v1.0]<#BEBFC5> Debug mode has been turned off.");
        sender.sendMessage(component);

        return true;
    }
}
