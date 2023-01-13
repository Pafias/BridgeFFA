package me.pafias.bridgeffa.ffa.services;

import me.pafias.bridgeffa.BridgeFFA;
import me.pafias.bridgeffa.ffa.Kit;
import me.pafias.bridgeffa.ffa.Spawn;
import me.pafias.bridgeffa.ffa.gui.KitMenu;
import me.pafias.bridgeffa.ffa.gui.SpawnMenu;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

public class ArmorstandManager {

    private final BridgeFFA plugin;

    public ArmorstandManager(BridgeFFA plugin) {
        this.plugin = plugin;
    }

    public void trigger(ArmorStand as, Player player, boolean leftclick) throws NullPointerException {
        if (as.isCustomNameVisible() && as.getCustomName() != null && plugin.getSM().getKitManager().exists(as.getCustomName())) {
            Kit kit = plugin.getSM().getKitManager().getKit(as.getCustomName());
            if (!leftclick) {
                SpawnMenu menu = new SpawnMenu(player, plugin.getSM().getSpawnManager().getSpawns().size(), kit);
                menu.open();
            } else {
                kit.give(player);
                plugin.getSM().getSpawnManager().getSpawns().get(0).teleport(player);
            }
        } else if (as.isCustomNameVisible() && as.getCustomName() != null && plugin.getSM().getSpawnManager().exists(as.getCustomName())) {
            Spawn spawn = plugin.getSM().getSpawnManager().getSpawn(as.getCustomName());
            if (!leftclick) {
                KitMenu menu = new KitMenu(player, plugin.getSM().getKitManager().getKits().size(), spawn);
                menu.open();
            } else {
                plugin.getSM().getKitManager().getKits().get(0).give(player);
                spawn.teleport(player);
            }
        }
    }

}
