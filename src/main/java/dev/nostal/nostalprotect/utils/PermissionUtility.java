/*
 * This file is part of NostalProtect, licensed under the MIT License.
 *
 *  Copyright (c) Meffu (Romer) <romer@nostal.dev>
 *  Copyright (c) contributors
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

package dev.nostal.nostalprotect.utils;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PermissionUtility {
    private static final Map<UUID, Boolean> playerDebugMode = new HashMap<>();
    public static final HashMap<UUID, PermissionAttachment> perms = new HashMap<UUID, PermissionAttachment>();
    public static boolean playerDebugMode(UUID uuid) {
        return playerDebugMode.getOrDefault(uuid, false);
    }

    public static Map<UUID, Boolean> getPlayerDebugMode() {
        return playerDebugMode;
    }

    public static boolean playerHasPermission(String permission, Player player) {

        if (playerDebugMode(player.getUniqueId())) {
            player.sendMessage(MiniMessage.miniMessage().deserialize("<#008BF8>[DEBUG]<#BEBFC5> Need permission <#008BF8>np." + permission + "<#BEBFC5> for this action."));
        }

        return (player.hasPermission("np." + permission) || player.hasPermission("np.bypass"));

    }

}