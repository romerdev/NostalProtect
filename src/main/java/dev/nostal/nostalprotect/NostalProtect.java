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

package dev.nostal.nostalprotect;

import dev.nostal.nostalprotect.commands.DebugCommand;
import dev.nostal.nostalprotect.listeners.JoinListener;
import dev.nostal.nostalprotect.listeners.block.BlockBreakListener;
import dev.nostal.nostalprotect.listeners.block.BlockInteractListener;
import dev.nostal.nostalprotect.listeners.block.BlockPlaceListener;
import dev.nostal.nostalprotect.listeners.entity.EntityBreakListener;
import dev.nostal.nostalprotect.listeners.entity.EntityDamageListener;
import dev.nostal.nostalprotect.listeners.entity.EntityInteractListener;
import dev.nostal.nostalprotect.listeners.entity.EntityPlaceListener;
import dev.nostal.nostalprotect.listeners.item.ItemDropListener;
import dev.nostal.nostalprotect.listeners.item.ItemInteractListener;
import dev.nostal.nostalprotect.listeners.item.ItemPickupListener;
import dev.nostal.nostalprotect.listeners.item.BucketInteractListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

import static dev.nostal.nostalprotect.utils.ConfigUtility.initConfig;

public final class NostalProtect extends JavaPlugin {

    @Override
    public void onEnable() {
        Objects.requireNonNull(this.getCommand("npdebug")).setExecutor(new DebugCommand());
        registerListeners();
        initConfig();
    }

    private void registerListeners() {
        PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new BlockBreakListener(), this);
        pluginManager.registerEvents(new BlockInteractListener(), this);
        pluginManager.registerEvents(new BlockPlaceListener(), this);

        pluginManager.registerEvents(new EntityBreakListener(), this);
        pluginManager.registerEvents(new EntityDamageListener(), this);
        pluginManager.registerEvents(new EntityInteractListener(), this);
        pluginManager.registerEvents(new EntityPlaceListener(), this);

        pluginManager.registerEvents(new ItemDropListener(), this);
        pluginManager.registerEvents(new ItemInteractListener(), this);
        pluginManager.registerEvents(new ItemPickupListener(), this);

        pluginManager.registerEvents(new BucketInteractListener(), this);

        pluginManager.registerEvents(new JoinListener(), this);
    }

}
