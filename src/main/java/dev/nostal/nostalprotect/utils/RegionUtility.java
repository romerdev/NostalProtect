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

import java.util.UUID;

import static dev.nostal.nostalprotect.utils.PermissionUtility.playerDebugMode;

public class RegionUtility {

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
