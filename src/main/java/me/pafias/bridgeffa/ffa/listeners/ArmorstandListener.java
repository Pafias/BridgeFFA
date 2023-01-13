package me.pafias.bridgeffa.ffa.listeners;

import me.pafias.bridgeffa.BridgeFFA;
import me.pafias.bridgeffa.BridgePlayer;
import org.bukkit.GameMode;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class ArmorstandListener implements Listener {

    private final BridgeFFA plugin;

    public ArmorstandListener(BridgeFFA plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent event) {
        if (!(event.getRightClicked() instanceof ArmorStand)) return;
        if (event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) return;
        BridgePlayer user = plugin.getSM().getPlayerManager().getPlayer(event.getPlayer());
        if (user == null) return;
        if (!user.isInFFAWorld()) return;
        event.setCancelled(true);
        plugin.getSM().getArmorstandManager().trigger((ArmorStand) event.getRightClicked(), user.getPlayer(), false);
    }

    @EventHandler
    public void onClick(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) return;
        if (!(event.getEntity() instanceof ArmorStand)) return;
        if (((Player) event.getDamager()).getGameMode().equals(GameMode.CREATIVE)) return;
        BridgePlayer user = plugin.getSM().getPlayerManager().getPlayer((Player) event.getDamager());
        if (user == null) return;
        if (!user.isInFFAWorld()) return;
        event.setCancelled(true);
        event.setDamage(0);
        plugin.getSM().getArmorstandManager().trigger((ArmorStand) event.getEntity(), user.getPlayer(), true);
    }

}
