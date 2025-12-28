package com.mysplugin.plot;

import com.mysplugin.Main;
import org.bukkit.*;

import java.util.*;

public class PlotManager {

    private final Main plugin;
    private final Map<UUID, Plot> plots = new HashMap<>();

    private final int plotSize = 64;
    private final int plotDistance = 200;
    private int plotIndex = 0;

    private World plotWorld;

    public PlotManager(Main plugin) {
        this.plugin = plugin;
        loadWorld();
        plugin.getLogger().info("PlotManager geladen");
    }

    private void loadWorld() {
        WorldCreator wc = new WorldCreator("plots");
        wc.type(WorldType.FLAT);
        plotWorld = wc.createWorld();
    }

    public Plot createPlot(UUID owner) {
        int x = plotIndex * plotDistance;
        plotIndex++;

        Location min = new Location(plotWorld, x, 64, 0);
        Location max = new Location(plotWorld, x + plotSize, 128, plotSize);

        Plot plot = new Plot(owner, min, max);
        plots.put(owner, plot);

        return plot;
    }

    public Plot getPlot(UUID uuid) {
        return plots.get(uuid);
    }

    public boolean canBuild(Player player, Location loc) {
        Plot plot = getPlot(player.getUniqueId());
        return plot != null && plot.isInside(loc);
    }
}