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

package dev.nostal.nostalprotect.listeners.item;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import static dev.nostal.nostalprotect.utils.PermissionUtility.playerHasPermission;

public class ItemInteractListener implements Listener {

    @EventHandler
    public void onPlayerItemInteract(PlayerInteractEvent event) {
        Player player =  event.getPlayer();
        Material material = event.getMaterial();
        String permission = "item." + material.name() + ".use";

        // Block interacting is handled by the place permission.
        if (event.isBlockInHand()) {
            permission = "block." + material.name() + ".place";
        }

        switch (material) {
            // These materials will be handled with elsewhere.
            case AIR:
            case BUCKET:
            case LAVA_BUCKET:
            case WATER_BUCKET:
            case POWDER_SNOW_BUCKET:
            case MILK_BUCKET:
            case AXOLOTL_BUCKET:
            case COD_BUCKET:
            case PUFFERFISH_BUCKET:
            case SALMON_BUCKET:
            case TADPOLE_BUCKET:
            case TROPICAL_FISH_BUCKET:
            case ITEM_FRAME:
            case GLOW_ITEM_FRAME:
            case ARMOR_STAND:
            case PAINTING:
            case ACACIA_BOAT:
            case ACACIA_CHEST_BOAT:
            case BIRCH_BOAT:
            case BIRCH_CHEST_BOAT:
            case DARK_OAK_BOAT:
            case DARK_OAK_CHEST_BOAT:
            case JUNGLE_BOAT:
            case JUNGLE_CHEST_BOAT:
            case MANGROVE_BOAT:
            case MANGROVE_CHEST_BOAT:
            case OAK_BOAT:
            case OAK_CHEST_BOAT:
            case SPRUCE_BOAT:
            case SPRUCE_CHEST_BOAT:
               break;
            default:
                if (!playerHasPermission(permission, player)) {
                    event.setCancelled(true);
                    player.getInventory().remove(material);
                }
        }
    }

}
