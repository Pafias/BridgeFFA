package me.pafias.bridgeffa.events;

import me.pafias.bridgeffa.BridgeBlock;
import me.pafias.bridgeffa.BridgeFFA;
import me.pafias.bridgeffa.BridgePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockEvent;
import org.jetbrains.annotations.NotNull;

public class BridgeBlockBrokenEvent extends BlockEvent {

    private static final HandlerList handlers = new HandlerList();

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    private final BridgeBlock block;
    private final BridgePlayer breaker;

    public BridgeBlockBrokenEvent(@NotNull BridgeBlock block, BridgePlayer breaker) {
        super(block.getBlock());
        this.block = block;
        this.breaker = breaker;
    }

    public BridgeBlockBrokenEvent(@NotNull BridgeBlock block, Player player) {
        super(block.getBlock());
        this.block = block;
        this.breaker = BridgeFFA.get().getSM().getPlayerManager().getPlayer(player);
    }

    public BridgeBlock getBridgeBlock() {
        return block;
    }

    public BridgePlayer getWhoBroke() {
        return breaker;
    }

}
