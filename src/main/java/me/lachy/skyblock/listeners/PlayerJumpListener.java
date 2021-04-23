package me.lachy.skyblock.listeners;

import me.lachy.skyblock.items.ItemBuilder;
import me.lachy.skyblock.listeners.events.PlayerJumpEvent;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerStatisticIncrementEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class PlayerJumpListener implements Listener {

    private final HashMap<String, Long> cooldown = new HashMap<>();

    @EventHandler
    public void onJump(PlayerStatisticIncrementEvent event) {
        ItemStack cadetLeggings = new ItemBuilder(Material.LEATHER_LEGGINGS)
                .setName("§9Cadet Leggings")
                .setLore("", "§eAbility: Long Legs", "§8Gives a double jump effect every §b2 seconds§8 while jumping.", "", "§9§lRARE LEGGINGS")
                .setLeatherArmorColor(Color.GREEN)
                .setInfinityDurability()
                .toItemStack();

        if (cooldown.containsKey(event.getPlayer().getName())) {
            if (cooldown.get(event.getPlayer().getName()) > System.currentTimeMillis()) {
                return;
            }
        }

        int cooldowntime = 2;
        if (event.getStatistic().equals(Statistic.JUMP)
                && event.getPlayer().getInventory().getLeggings() != null
                && event.getPlayer().getInventory().getLeggings().equals(cadetLeggings)
                && (event.getPlayer().getGameMode() != GameMode.SPECTATOR || event.getPlayer().getGameMode() != GameMode.CREATIVE)) {

            event.getPlayer().setVelocity(event.getPlayer().getLocation().getDirection().multiply(0.75d).setY(0.6d));
            cooldown.put(event.getPlayer().getName(), System.currentTimeMillis() + (cooldowntime * 1000L));
        }
    }
}