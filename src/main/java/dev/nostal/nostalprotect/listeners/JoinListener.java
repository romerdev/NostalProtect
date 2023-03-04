package dev.nostal.nostalprotect.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

import static dev.nostal.nostalprotect.utils.PermissionUtility.getPlayerDebugMode;

public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();

        getPlayerDebugMode().put(uuid, false);
    }

    /*
     * /nostalprotect and /np are the same.
     * /np or /np help for information command.
     * /np debug for toggling debug mode.
     */
}
