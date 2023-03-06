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
package dev.nostal.nostalprotect.utils;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.util.HashMap;
import java.util.UUID;

import static dev.nostal.nostalprotect.utils.PermissionUtility.playerDebugMode;

public class RegionUtility {

    public static final HashMap<UUID, PermissionAttachment> perms = new HashMap<UUID, PermissionAttachment>();

    private final static RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();

    public static boolean playerCanModifyBlockAtLocation(Location location, Player player) {
        RegionQuery query = container.createQuery();
        ApplicableRegionSet set = query.getApplicableRegions(BukkitAdapter.adapt(location));

        UUID playerUuid = player.getUniqueId();

        if (set == null) {
            if (playerDebugMode(playerUuid)) {
                player.sendMessage(MiniMessage.miniMessage().deserialize("<#008BF8>[REGION DEBUG]<#BEBFC5> Canceled because no regions where found in this world."));
            }

            return false;
        }

        if (player.hasPermission("np.bypass.region")) {
            if (playerDebugMode(playerUuid)) {
                player.sendMessage(MiniMessage.miniMessage().deserialize("<#008BF8>[REGION DEBUG]<#BEBFC5> Allowed because you can bypass region member- or ownership."));
            }

            return true;
        }

        for (ProtectedRegion region : set) {
            if (region.getMembers().contains(playerUuid) || region.getOwners().contains(playerUuid)) {
                if (playerDebugMode(playerUuid)) {
                    player.sendMessage(MiniMessage.miniMessage().deserialize("<#008BF8>[REGION DEBUG]<#BEBFC5> Allowed because you are a member or owner of this region."));
                }

                return true;
            }
        }

        if (playerDebugMode(playerUuid)) {
            player.sendMessage(MiniMessage.miniMessage().deserialize("<#008BF8>[REGION DEBUG]<#BEBFC5> Denied because you are not a member or owner of this region."));
        }

        return false;
    }

}
