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
import dev.nostal.nostalprotect.NostalProtect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class PlayerActionUtility {
    private static final JavaPlugin plugin = NostalProtect.getPlugin(NostalProtect.class);

    public static boolean playerCanPerformAction(Player player, String[] permission, Location location, Material material, boolean removeItem) {
        if (plugin.getConfig().getBoolean("useWorldGuardRegions")) {
            if (!playerCanModifyBlockAtLocation(location, player, permission)) {
                removeItemFromInventory(removeItem, player, material);
                return false;
            }
            return true;
        } else if (!playerHasPermission(player, permission)) {
            removeItemFromInventory(removeItem, player, material);
            return false;
        }
        return true;
    }

    private static boolean playerHasPermission(Player player, String[] permission) {
        String basePermission = "np." + permission[0] + ".";
        String materialFirstPermission = basePermission + permission[1] + "." + permission[2];
        String actionFirstPermission = basePermission + permission[2] + "." + permission[1];

        return (player.hasPermission(materialFirstPermission) || player.hasPermission(actionFirstPermission) || player.hasPermission("np.bypass"));
    }

    private static boolean playerCanModifyBlockAtLocation(Location location, Player player, String[] permission) {
        RegionContainer regionContainer = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionQuery regionQuery = regionContainer.createQuery();
        ApplicableRegionSet applicableRegions = regionQuery.getApplicableRegions(BukkitAdapter.adapt(location));
        UUID playerUuid = player.getUniqueId();
        String basePermission = "np." + permission[0] + ".";
        String materialFirstPermission = basePermission + permission[1] + "." + permission[2];
        String actionFirstPermission = basePermission + permission[2] + "." + permission[1];

        if (player.hasPermission("np.bypass.region")) {
            return playerHasPermission(player, permission);
        }

        for (ProtectedRegion region : applicableRegions) {
            if (region.getMembers().contains(playerUuid) || region.getOwners().contains(playerUuid)) {
                if (playerHasPermission(player, permission)) {
                    return true;
                } else if (player.hasPermission(materialFirstPermission + "." + region.getId()) || player.hasPermission(actionFirstPermission + "." + region.getId())) {
                    return true;
                }
            }
        }

        return (player.hasPermission(materialFirstPermission + ".global") || player.hasPermission(actionFirstPermission + ".global"));
    }

    private static void removeItemFromInventory(boolean removeItem, Player player, Material material) {
        if (!removeItem || !plugin.getConfig().getBoolean("removeDisallowedItems")) {
            return;
        }

        player.getInventory().remove(material);
    }

}
