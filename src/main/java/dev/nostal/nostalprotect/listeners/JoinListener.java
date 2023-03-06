package dev.nostal.nostalprotect.listeners;

import dev.nostal.nostalprotect.NostalProtect;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.PermissionAttachment;

import java.util.UUID;

import static dev.nostal.nostalprotect.utils.PermissionUtility.getPlayerDebugMode;
import static dev.nostal.nostalprotect.utils.RegionUtility.perms;

public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        getPlayerDebugMode().put(uuid, false);

        PermissionAttachment attachment = event.getPlayer().addAttachment(NostalProtect.getPlugin(NostalProtect.class));
        perms.put(uuid, attachment);

        PermissionAttachment playerPerms = perms.get(uuid);
        playerPerms.setPermission("worldguard.region.bypass.*", true);
    }

}
