package com.mysplugin.plot;

import org.bukkit.Location;
import java.util.UUID;

public class Plot {

    private UUID owner;
    private Location min;
    private Location max;

    public Plot(UUID owner, Location min, Location max) {
        this.owner = owner;
        this.min = min;
        this.max = max;
    }

    public UUID getOwner() {
        return owner;
    }

    public boolean isInside(Location loc) {
        if (!loc.getWorld().equals(min.getWorld())) return false;

        return loc.getX() >= min.getX() && loc.getX() <= max.getX()
            && loc.getY() >= min.getY() && loc.getY() <= max.getY()
            && loc.getZ() >= min.getZ() && loc.getZ() <= max.getZ();
    }
}