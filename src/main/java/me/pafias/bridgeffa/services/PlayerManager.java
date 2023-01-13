package me.pafias.bridgeffa.services;

import me.pafias.bridgeffa.BridgeFFA;
import me.pafias.bridgeffa.BridgePlayer;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class PlayerManager {

    public PlayerManager(BridgeFFA plugin) {
        players = new HashSet<>();
    }

    private final Set<BridgePlayer> players;

    public BridgePlayer getPlayer(Player player) {
        return getPlayer(player.getUniqueId());
    }

    public BridgePlayer getPlayer(UUID uuid) {
        return players.stream().filter(p -> p.getUUID().equals(uuid)).findAny().orElse(null);
    }

    public BridgePlayer getPlayer(String name) {
        return players.stream().filter(p -> p.getName().toLowerCase().startsWith(name.toLowerCase())).findAny().orElse(null);
    }

    public void addPlayer(Player player) {
        players.add(new BridgePlayer(player));
    }

    public void removePlayer(Player player) {
        players.remove(getPlayer(player));
    }

}
