package me.pafias.bridgeffa.services;

import me.pafias.bridgeffa.BridgeBlock;
import me.pafias.bridgeffa.BridgeFFA;
import me.pafias.bridgeffa.BridgePlayer;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class BlocksManager {

    private final BridgeFFA plugin;

    public BlocksManager(BridgeFFA plugin) {
        this.plugin = plugin;
        this.blocks = new HashSet<>();
    }

    public BridgeBlock placedBlock(Block block, Player player) {
        BridgeBlock bb = new BridgeBlock(block, plugin.getSM().getPlayerManager().getPlayer(player));
        blocks.add(bb);
        return bb;
    }

    public BridgeBlock brokeBlock(Block block, Player player) {
        BridgeBlock bb = getBlock(block);
        bb.cancelRemoval();
        blocks.remove(bb);
        return bb;
    }

    private Set<BridgeBlock> blocks;

    public BridgeBlock getBlock(Block block) {
        return blocks.stream().filter(b -> b.getBlock().equals(block)).findAny().orElse(null);
    }

    public BridgeBlock getBlock(Player player) {
        return getBlock(plugin.getSM().getPlayerManager().getPlayer(player));
    }

    public BridgeBlock getBlock(BridgePlayer player) {
        return blocks.stream().filter(b -> b.getOwner().equals(player)).findAny().orElse(null);
    }

}

