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

package dev.nostal.nostalprotect.listeners.item;

import com.destroystokyo.paper.event.player.PlayerPickupExperienceEvent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerPickupArrowEvent;

import static dev.nostal.nostalprotect.utils.PlayerActionUtility.playerCanPerformAction;

public class ItemPickupListener implements Listener {

    @EventHandler
    public void onPlayerItemPickup(EntityPickupItemEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getEntity();
        Material material = event.getItem().getItemStack().getType();
        String[] permission = {"item", material.name(), "pickup"};
        Location location = event.getItem().getLocation();

        if (!playerCanPerformAction(player, permission, location, material, false)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerArrowPickup(PlayerPickupArrowEvent event) {
        Player player = event.getPlayer();
        Material material = event.getItem().getItemStack().getType();
        String[] permission = {"item", material.name(), "pickup"};
        Location location = event.getItem().getLocation();

        if (!playerCanPerformAction(player, permission, location, material, false)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerExperiencePickup(PlayerPickupExperienceEvent event) {
        Player player = event.getPlayer();
        Material material = Material.EXPERIENCE_BOTTLE;
        String[] permission = {"item", material.name(), "pickup"};
        Location location = event.getExperienceOrb().getLocation();

        if (!playerCanPerformAction(player, permission, location, material, false)) {
            event.getExperienceOrb().remove();
            event.setCancelled(true);
        }
    }

}
