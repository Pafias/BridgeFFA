package me.pafias.bridgeffa.services;

import me.pafias.bridgeffa.BridgeFFA;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Variables {

    private final BridgeFFA plugin;

    public Variables(BridgeFFA plugin) {
        this.plugin = plugin;
        reloadConfig();
    }

    public Set<World> worlds;
    public String deathMessageSuffix = "&7(&c%s ❤&7)";
    public boolean healOnKill = true;
    public Location lobby;
    public double lobbyHRadius = 20;
    public double lobbyVRadius = 6;
    public String lobbyXBounds;
    public String lobbyYBounds;
    public String lobbyZBounds;
    public int killCooldown = 5;
    public boolean overrideKillCommand = true;
    public String lobbyDetection = "ycoord";
    public boolean disableFallDamage = true;

    public void reloadConfig() {
        plugin.getConfig().options().copyDefaults(true);
        plugin.saveConfig();
        plugin.reloadConfig();
        FileConfiguration config = plugin.getConfig();
        worlds = config.getStringList("worlds").stream().map(s -> plugin.getServer().getWorld(s)).filter(Objects::nonNull).collect(Collectors.toSet());
        deathMessageSuffix = plugin.getConfig().getString("death_message_suffix");
        healOnKill = plugin.getConfig().getBoolean("heal_on_kill");
        disableFallDamage = plugin.getConfig().getBoolean("disable_falldamage");
        overrideKillCommand = plugin.getConfig().getBoolean("override_kill_command");
        killCooldown = plugin.getConfig().getInt("kill_command_cooldown");
        lobby = new Location(
                plugin.getServer().getWorld(plugin.getConfig().getString("lobby.world")),
                plugin.getConfig().getDouble("lobby.x"),
                plugin.getConfig().getDouble("lobby.y"),
                plugin.getConfig().getDouble("lobby.z"),
                (float) plugin.getConfig().getDouble("lobby.yaw"),
                (float) plugin.getConfig().getDouble("lobby.pitch")
        );
        lobbyHRadius = plugin.getConfig().getDouble("lobby.hradius");
        lobbyVRadius = plugin.getConfig().getDouble("lobby.vradius");
        lobbyXBounds = plugin.getConfig().getString("lobby.xbounds");
        lobbyYBounds = plugin.getConfig().getString("lobby.ybounds");
        lobbyZBounds = plugin.getConfig().getString("lobby.zbounds");
        lobbyDetection = plugin.getConfig().getString("lobby_detection");
    }

}
