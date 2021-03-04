package me.lachy.skyblock.listeners;

import me.lachy.skyblock.Skyblock;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class RightClickListener implements Listener {

    private Skyblock plugin;

    public RightClickListener(Skyblock plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInHand();

        if (!item.hasItemMeta()) return;
        if (!player.getGameMode().equals(GameMode.SURVIVAL)) return;

        switch (ChatColor.stripColor(item.getItemMeta().getDisplayName()).toLowerCase()) {
            case "aspect of the jerry":
                player.getWorld().playSound(player.getLocation(), Sound.VILLAGER_IDLE, 1.0F, 1.0F);
                break;
            case "grappling hook":
                player.sendMessage("WIP");
                break;
        }

    }
}
