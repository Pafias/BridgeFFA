package me.pafias.bridgeffa;

import me.pafias.bridgeffa.events.BridgeBlockRemoveEvent;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class BridgeBlock {

    private final BridgeFFA plugin = BridgeFFA.get();

    private final Block block;
    private final BridgePlayer owner;
    private BukkitTask task;

    public BridgeBlock(Block block, BridgePlayer owner) {
        this.block = block;
        this.owner = owner;
        setRemovalAfter(10);
    }

    public Block getBlock() {
        return block;
    }

    public BridgePlayer getOwner() {
        return owner;
    }

    public void remove() {
        BridgeBlockRemoveEvent event = new BridgeBlockRemoveEvent(this);
        plugin.getServer().getPluginManager().callEvent(event);
        if (event.isCancelled()) return;
        block.setType(Material.AIR);
    }

    public void setRemovalAfter(int seconds) {
        task = new BukkitRunnable() {
            @Override
            public void run() {
                remove();
            }
        }.runTaskLater(plugin, (seconds * 20L));
    }

    public void cancelRemoval() {
        if (task != null)
            task.cancel();
    }

}
