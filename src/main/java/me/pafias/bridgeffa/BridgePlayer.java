package me.pafias.bridgeffa;

import org.bukkit.entity.Player;

import java.util.UUID;

public class BridgePlayer {

    private final BridgeFFA plugin = BridgeFFA.get();

    private final Player player;

    public BridgePlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public UUID getUUID() {
        return player.getUniqueId();
    }

    public String getName() {
        return player.getName();
    }

    public boolean isInFFAWorld() {
        return plugin.getSM().getVariables().worlds.contains(player.getLocation().getWorld());
    }

    public boolean isInSpawn() {
        if (plugin.getSM().getVariables().lobbyDetection.equalsIgnoreCase("ycoord")) {
            return player.getLocation().getY() >= plugin.getSM().getVariables().lobby.getY() - 1;
        } else if (plugin.getSM().getVariables().lobbyDetection.equalsIgnoreCase("bounds")) {
            int x = player.getLocation().getBlockX();
            int y = player.getLocation().getBlockY();
            int z = player.getLocation().getBlockZ();
            String xbounds = plugin.getSM().getVariables().lobbyXBounds;
            double xMin = Double.parseDouble(xbounds.split(",")[0]);
            double xMax = Double.parseDouble(xbounds.split(",")[1]);
            String ybounds = plugin.getSM().getVariables().lobbyYBounds;
            double yMin = Double.parseDouble(ybounds.split(",")[0]);
            double yMax = Double.parseDouble(ybounds.split(",")[1]);
            String zbounds = plugin.getSM().getVariables().lobbyZBounds;
            double zMin = Double.parseDouble(zbounds.split(",")[0]);
            double zMax = Double.parseDouble(zbounds.split(",")[1]);
            return (x > xMin && x < xMax) && (y > yMin && y < yMax) && (z > zMin && z < zMax);
        } else {
            int x = player.getLocation().getBlockX();
            int y = player.getLocation().getBlockY();
            int z = player.getLocation().getBlockZ();
            int xS = plugin.getSM().getVariables().lobby.getBlockX();
            int yS = plugin.getSM().getVariables().lobby.getBlockY();
            int zS = plugin.getSM().getVariables().lobby.getBlockZ();
            double hrS = plugin.getSM().getVariables().lobbyHRadius;
            double vrS = plugin.getSM().getVariables().lobbyVRadius;
            return (x > xS - hrS && x < xS + hrS) && (y > yS - vrS && y < yS + vrS) && (z > zS - hrS && z < zS + hrS);
            // return Math.abs(x - xS) < hrS && Math.abs(z - zS) < hrS && Math.abs(y - yS) < vrS;
            // return (y == yS || y == yS + vrS || y == yS - 1) && ((x == xS || x == xS + hrS || x == xS - hrS) && (z == zS || z == zS + hrS || z == zS - hrS));
        }
    }

}
