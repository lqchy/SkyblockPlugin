package me.lachy.skyblock.listeners.events;

import me.lachy.skyblock.Skyblock;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerStatisticIncrementEvent;
import org.bukkit.util.Vector;

public class PlayerJumpEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private static final Listener listener = new PlayerJumpEventListener();
    private final PlayerStatisticIncrementEvent playerStatisticIncrementEvent;
    private boolean isCancelled = false;
    private Skyblock plugin;

    public PlayerJumpEvent(Skyblock plugin, Player player, PlayerStatisticIncrementEvent playerStatisticIncrementEvent) {
        super(player);
        this.playerStatisticIncrementEvent = playerStatisticIncrementEvent;
        this.plugin = plugin;
    }

    {
        Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        isCancelled = cancel;
        if (cancel) {
            player.setVelocity(new Vector());
            playerStatisticIncrementEvent.setCancelled(cancel);
        }
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    private static class PlayerJumpEventListener implements Listener {
        @EventHandler
        public void onPlayerStatisticIncrement(PlayerStatisticIncrementEvent event) {
            if (event.getStatistic() == Statistic.JUMP) {
                Bukkit.getServer().getPluginManager().callEvent(new PlayerJumpEvent(Skyblock.getInstance(), event.getPlayer(), event));
            }
        }
    }
}
