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

import static dev.nostal.nostalprotect.utils.DebugUtility.*;

public class RegionUtility {

    public static final HashMap<UUID, PermissionAttachment> perms = new HashMap<UUID, PermissionAttachment>();
    private final static RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();

    public static boolean playerCanModifyBlockAtLocation(Location location, Player player, String permission) {
        RegionQuery query = container.createQuery();
        ApplicableRegionSet set = query.getApplicableRegions(BukkitAdapter.adapt(location));

        UUID playerUuid = player.getUniqueId();

        permission = "np." + permission;

        if (player.hasPermission("np.bypass.region")) {
            if (playerDebugMode(playerUuid)) {
                player.sendMessage(MiniMessage.miniMessage().deserialize("<#008BF8>[REGION DEBUG]<#BEBFC5> Allowed because you can bypass regions."));
                player.sendMessage(MiniMessage.miniMessage().deserialize("<#008BF8>[DEBUG]<#BEBFC5> Need permission <#008BF8>" + permission + "<#BEBFC5> for this action."));
            }

            return (player.hasPermission(permission) || player.hasPermission("np.bypass"));
        }
        
        boolean isInOthersRegion = false;
        String othersRegion = "";

        for (ProtectedRegion region : set) {
            if ((region.getMembers().contains(playerUuid) || region.getOwners().contains(playerUuid)) && (player.hasPermission(permission + ".own") || player.hasPermission(permission))) {
                if (playerDebugMode(playerUuid)) {
                    player.sendMessage(MiniMessage.miniMessage().deserialize("<#008BF8>[DEBUG]<#BEBFC5> Need permission <#008BF8>" + permission + ".own<#BEBFC5> for this action."));
                }

                return true;
            }

            if (player.hasPermission(permission + "." + region.getId())) {
                if (playerDebugMode(playerUuid)) {
                    player.sendMessage(MiniMessage.miniMessage().deserialize("<#008BF8>[DEBUG]<#BEBFC5> Need permission <#008BF8>" + permission + "." + region.getId() + "<#BEBFC5> for this action."));
                }
                return true;
            }

            othersRegion = region.getId();
            isInOthersRegion = true;
        }
        
        if (isInOthersRegion && playerDebugMode(playerUuid)) {
            if (!othersRegion.equals("")) {
                player.sendMessage(MiniMessage.miniMessage().deserialize("<#008BF8>[DEBUG]<#BEBFC5> Need permission <#008BF8>" + permission + ".own<#BEBFC5> or <#008BF8>" + permission + "."+ othersRegion +"<#BEBFC5> for this action."));
            } else {
                player.sendMessage(MiniMessage.miniMessage().deserialize("<#008BF8>[DEBUG]<#BEBFC5> Need permission <#008BF8>" + permission + ".own<#BEBFC5> for this action."));
            }

            return false;
        }

        if (playerDebugMode(playerUuid)) {
            player.sendMessage(MiniMessage.miniMessage().deserialize("<#008BF8>[DEBUG]<#BEBFC5> Need permission <#008BF8>" + permission + ".global<#BEBFC5> for this action."));
        }

        if (player.hasPermission(permission + ".global")) {
            return true;
        }

        return false;
    }

}
