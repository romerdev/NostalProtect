/*
 *  This file is part of NostalProtect, licensed under the MIT License.
 *
 *  Copyright (c) 2023 romerdev (Romer)
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

package dev.nostal.nostalprotect.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

import static dev.nostal.nostalprotect.utils.DebugUtility.getPlayerDebugMode;

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
