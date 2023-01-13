package me.pafias.bridgeffa.listeners;

import me.pafias.bridgeffa.BridgeBlock;
import me.pafias.bridgeffa.BridgeFFA;
import me.pafias.bridgeffa.BridgePlayer;
import me.pafias.bridgeffa.events.BridgeBlockBrokenEvent;
import me.pafias.bridgeffa.events.BridgeBlockPlacedEvent;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class GameListener implements Listener {

    private final BridgeFFA plugin;

    public GameListener(BridgeFFA plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onBlockPlace(BlockPlaceEvent event) {
        if (event.isCancelled()) return;
        if (event.getPlayer().getGameMode().equals(GameMode.CREATIVE))
            return;
        if (!plugin.getSM().getVariables().worlds.contains(event.getBlock().getWorld())) return;
        BridgePlayer player = plugin.getSM().getPlayerManager().getPlayer(event.getPlayer());
        if (player.isInSpawn())
            event.setCancelled(true);
        if (!event.isCancelled()) {
            BridgeBlock bb = plugin.getSM().getBlocksManager().placedBlock(event.getBlock(), event.getPlayer());
            plugin.getServer().getPluginManager().callEvent(new BridgeBlockPlacedEvent(bb, event.getPlayer()));
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.isCancelled()) return;
        if (event.getPlayer().getGameMode().equals(GameMode.CREATIVE))
            return;
        if (!plugin.getSM().getVariables().worlds.contains(event.getBlock().getWorld())) return;
        BridgeBlock bb = plugin.getSM().getBlocksManager().getBlock(event.getBlock());
        if (bb == null) {
            event.setCancelled(true);
            return;
        }
        BridgePlayer player = plugin.getSM().getPlayerManager().getPlayer(event.getPlayer());
        if (player.isInSpawn())
            event.setCancelled(true);
        if (!event.isCancelled()) {
            plugin.getSM().getBlocksManager().brokeBlock(event.getBlock(), event.getPlayer());
            plugin.getServer().getPluginManager().callEvent(new BridgeBlockBrokenEvent(bb, event.getPlayer()));
        }
    }

}
