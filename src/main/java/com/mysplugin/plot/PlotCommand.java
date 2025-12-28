package com.mysplugin.plot;

import com.mysplugin.Main;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class PlotCommand implements CommandExecutor {

    private final PlotManager plotManager;

    public PlotCommand(Main plugin) {
        this.plotManager = plugin.getPlotManager();
        plugin.getCommand("plot").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player player)) return true;

        if (args.length == 0 || args[0].equalsIgnoreCase("create")) {

            if (plotManager.getPlot(player.getUniqueId()) != null) {
                player.sendMessage("§cDu hast bereits ein Plot!");
                return true;
            }

            Plot plot = plotManager.createPlot(player.getUniqueId());
            player.teleport(plotWorldSpawn(plot));

            player.sendMessage("§aDein Plot wurde erstellt!");
            return true;
        }

        return false;
    }

    private org.bukkit.Location plotWorldSpawn(Plot plot) {
        return plot.getOwner() != null
                ? plotWorld(plot).clone().add(5, 1, 5)
                : null;
    }

    private org.bukkit.Location plotWorld(Plot plot) {
        return plot.isInside(plotWorldSpawn(plot)) ? plotWorldSpawn(plot) : plotWorldSpawn(plot);
    }
}
