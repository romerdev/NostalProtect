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

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketEntityEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;

import static dev.nostal.nostalprotect.utils.PlayerActionUtility.playerCanPerformAction;

public class BucketInteractListener implements Listener {

    @EventHandler
    public void onPlayerBucketFill(PlayerBucketFillEvent event) {
        Player player = event.getPlayer();
        Material bucket = event.getBucket();
        String permission = "item." + bucket.name() + ".fill";
        Location location = event.getBlock().getLocation();

        if (!playerCanPerformAction(player, permission, location, bucket, true)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerBucketEmpty(PlayerBucketEmptyEvent event) {
        Player player = event.getPlayer();
        Material bucket = event.getBucket();
        String permission = "item." + bucket.name() + ".empty";
        Location location = event.getBlock().getLocation();

        switch (bucket) {
            case AXOLOTL_BUCKET:
            case COD_BUCKET:
            case PUFFERFISH_BUCKET:
            case SALMON_BUCKET:
            case TADPOLE_BUCKET:
            case TROPICAL_FISH_BUCKET:
                permission = "item." + bucket.name() + ".release";
        }

        if (!playerCanPerformAction(player, permission, location, bucket, true)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerBucketCapture(PlayerBucketEntityEvent event) {
        Player player = event.getPlayer();
        Material bucket = event.getOriginalBucket().getType();
        String permission = "item." + bucket.name() + ".capture";
        Location location = event.getEntity().getLocation();

        if (!playerCanPerformAction(player, permission, location, bucket, true)) {
            event.setCancelled(true);
        }
    }

}
