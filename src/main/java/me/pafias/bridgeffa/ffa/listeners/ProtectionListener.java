package me.pafias.bridgeffa.ffa.listeners;

import me.pafias.bridgeffa.BridgeFFA;
import me.pafias.bridgeffa.BridgePlayer;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class ProtectionListener implements Listener {

    private final BridgeFFA plugin;

    public ProtectionListener(BridgeFFA plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onItem(PlayerItemDamageEvent event) {
        if (!plugin.getSM().getVariables().worlds.contains(event.getPlayer().getWorld())) return;
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onFallDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        if (!event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) return;
        if (plugin.getSM().getVariables().disableFallDamage)
            event.setDamage(0);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onHunger(FoodLevelChangeEvent event) {
        if (!plugin.getSM().getVariables().worlds.contains(event.getEntity().getWorld())) return;
        event.setFoodLevel(20);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDrop(PlayerDropItemEvent event) {
        if (event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) return;
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPickup(PlayerPickupItemEvent event) {
        if (event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) return;
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        if (!plugin.getSM().getVariables().worlds.contains(event.getEntity().getWorld())) return;
        BridgePlayer user = plugin.getSM().getPlayerManager().getPlayer((Player) event.getEntity());
        if (user == null) return;
        if (user.isInSpawn()) event.setCancelled(true);
    }

}
