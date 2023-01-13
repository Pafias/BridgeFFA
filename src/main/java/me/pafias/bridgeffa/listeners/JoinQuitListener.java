package me.pafias.bridgeffa.listeners;

import me.pafias.bridgeffa.BridgeFFA;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitListener implements Listener {

    private final BridgeFFA plugin;

    public JoinQuitListener(BridgeFFA plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        plugin.getSM().getPlayerManager().addPlayer(event.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        plugin.getSM().getPlayerManager().removePlayer(event.getPlayer());
    }

}
