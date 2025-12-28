package com.mysplugin.utils;

import com.mysplugin.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class EventListener implements Listener {

    private final Main plugin;

    public EventListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        if (!plugin.getPlotManager().canBuild(e.getPlayer(), e.getBlock().getLocation())) {
            e.setCancelled(true);
            e.getPlayer().sendMessage("§cDu darfst hier nicht bauen!");
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (!plugin.getPlotManager().canBuild(e.getPlayer(), e.getBlock().getLocation())) {
            e.setCancelled(true);
            e.getPlayer().sendMessage("§cDu darfst hier nicht abbauen!");
        }
    }
}
