package me.pafias.bridgeffa;

import me.pafias.bridgeffa.ffa.services.ArmorstandManager;
import me.pafias.bridgeffa.ffa.services.KitManager;
import me.pafias.bridgeffa.ffa.services.SpawnManager;
import me.pafias.bridgeffa.services.BlocksManager;
import me.pafias.bridgeffa.services.PlayerManager;
import me.pafias.bridgeffa.services.Variables;

public class ServicesManager {

    public ServicesManager(BridgeFFA plugin) {
        variables = new Variables(plugin);
        playerManager = new PlayerManager(plugin);
        blocksManager = new BlocksManager(plugin);
        kitManager = new KitManager(plugin);
        spawnManager = new SpawnManager(plugin);
        armorstandManager = new ArmorstandManager(plugin);
    }

    private final Variables variables;

    public Variables getVariables() {
        return variables;
    }

    private final PlayerManager playerManager;

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    private final BlocksManager blocksManager;

    public BlocksManager getBlocksManager() {
        return blocksManager;
    }

    private final KitManager kitManager;

    public KitManager getKitManager() {
        return kitManager;
    }

    private final SpawnManager spawnManager;

    public SpawnManager getSpawnManager() {
        return spawnManager;
    }

    private final ArmorstandManager armorstandManager;

    public ArmorstandManager getArmorstandManager() {
        return armorstandManager;
    }

}
