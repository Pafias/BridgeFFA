package me.pafias.bridgeffa.events;

import me.pafias.bridgeffa.BridgeBlock;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockEvent;
import org.jetbrains.annotations.NotNull;

public class BridgeBlockRemoveEvent extends BlockEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    private final BridgeBlock block;

    public BridgeBlockRemoveEvent(@NotNull BridgeBlock block) {
        super(block.getBlock());
        this.block = block;
    }

    public BridgeBlock getBridgeBlock() {
        return block;
    }

    private boolean cancelled;

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

}
