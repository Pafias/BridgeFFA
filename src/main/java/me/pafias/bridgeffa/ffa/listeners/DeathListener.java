package me.pafias.bridgeffa.ffa.listeners;

import me.pafias.bridgeffa.BridgeFFA;
import me.pafias.bridgeffa.BridgePlayer;
import me.pafias.bridgeffa.util.CC;
import org.bukkit.GameRule;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.text.DecimalFormat;
import java.util.Objects;

public class DeathListener implements Listener {

    private final BridgeFFA plugin;

    public DeathListener(BridgeFFA plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onDeath(PlayerDeathEvent event) {
        if (!plugin.getSM().getVariables().worlds.contains(event.getEntity().getLocation().getWorld()))
            return;
        if (plugin.parseVersion() <= 16.5 || (plugin.parseVersion() > 16.5 && Objects.equals(event.getEntity().getWorld().getGameRuleValue(GameRule.DO_IMMEDIATE_RESPAWN), false)))
            event.getEntity().setHealth(event.getEntity().getMaxHealth());
        Double killerHealth = null;
        if (event.getEntity().getKiller() != null && plugin.getSM().getVariables().healOnKill) {
            killerHealth = event.getEntity().getKiller().getHealth();
            event.getEntity().getKiller().setHealth(event.getEntity().getKiller().getMaxHealth());
        }
        event.getDrops().clear();
        event.getEntity().teleport(plugin.getSM().getVariables().lobby);
        event.getEntity().getInventory().clear();
        event.getEntity().getActivePotionEffects().forEach(pe -> event.getEntity().removePotionEffect(pe.getType()));
        if (event.getEntity().getKiller() != null) {
            if (event.getEntity().hasMetadata("NPC"))
                event.setDeathMessage(CC.tf("%s was slain by %s %s", event.getEntity().getName(), event.getEntity().getKiller().getName(), CC.tf(plugin.getSM().getVariables().deathMessageSuffix, new DecimalFormat("#.##").format(killerHealth / 2))));
            else
                event.setDeathMessage(event.getDeathMessage() + CC.tf(" %s", CC.tf(plugin.getSM().getVariables().deathMessageSuffix, new DecimalFormat("#.##").format(killerHealth / 2))));
            if (plugin.getSM().getVariables().healOnKill)
                event.getEntity().getKiller().setHealth(event.getEntity().getKiller().getMaxHealth());
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onRespawn(PlayerRespawnEvent event) {
        BridgePlayer user = plugin.getSM().getPlayerManager().getPlayer(event.getPlayer());
        if (user == null) return;
        if (!user.isInFFAWorld()) return;
        event.setRespawnLocation(plugin.getSM().getVariables().lobby);
    }

}
