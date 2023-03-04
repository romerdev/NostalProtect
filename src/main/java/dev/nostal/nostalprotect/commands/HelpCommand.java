package dev.nostal.nostalprotect.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class HelpCommand implements CommandExecutor {

    public static MiniMessage miniMessage = MiniMessage.miniMessage();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Component component = miniMessage.deserialize("<#008BF8>[NostalProtect v1.0]<#BEBFC5> by Meffu");
        component = component.append(Component.newline());
        component = component.append(miniMessage.deserialize("<#008BF8>/nostalprotect<#BEBFC5> - Display this help command."));
        component = component.append(Component.newline());
        component = component.append(miniMessage.deserialize("<#008BF8>/npdebug<#BEBFC5> - Toggle the debug mode."));

        sender.sendMessage(component);

        return true;
    }
}
