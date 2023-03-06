package dev.nostal.nostalprotect.utils;

import dev.nostal.nostalprotect.NostalProtect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import static dev.nostal.nostalprotect.utils.PermissionUtility.playerHasPermission;
import static dev.nostal.nostalprotect.utils.RegionUtility.playerCanModifyBlockAtLocation;

public class PlayerActionUtility {

    private static final JavaPlugin plugin = NostalProtect.getPlugin(NostalProtect.class);

    public static boolean playerCanPerformAction(Player player, String permission, Location location, Material material, Boolean removeItem) {
        if (!playerHasPermission(permission, player)) {
            if (plugin.getConfig().getBoolean("removeDisallowedItems") && removeItem) {
                player.getInventory().remove(material);
            }

            return false;
        }

        if (!playerCanModifyBlockAtLocation(location, player)) {
            return false;
        }

        return true;
    }

}
