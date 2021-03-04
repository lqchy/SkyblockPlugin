package me.lachy.skyblock.listeners;

import me.lachy.skyblock.Skyblock;
import me.lachy.skyblock.items.ItemBuilder;
import me.mattstudios.mfgui.gui.guis.Gui;
import me.mattstudios.mfgui.gui.guis.GuiItem;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class RightClickListener implements Listener {

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
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
                case "weather stick":
                    Gui weatherStickGui = new Gui(3, "Weather Stick");
                    ItemStack sunny = new ItemBuilder(Material.DOUBLE_PLANT).setName("§eSunny").addLoreLine("§7Sets the weather to §eSunny.").toItemStack();
                    ItemStack rain = new ItemBuilder(Material.WATER_BUCKET).setName("§9Rain").addLoreLine("§7Sets the weather to §9Rain.").toItemStack();
                    ItemStack thunder = new ItemBuilder(Material.FLINT_AND_STEEL).setName("§fThunder").addLoreLine("§7Sets the weather to §fThunder.").toItemStack();
                    weatherStickGui.setItem(11, new GuiItem(sunny, sun -> {
                        sun.getWhoClicked().getWorld().setStorm(false);
                        sun.getWhoClicked().sendMessage("§aWeather set to §eSunny§a.");
                        sun.getWhoClicked().closeInventory();
                        sun.setCancelled(true);
                    }));
                    weatherStickGui.setItem(13, new GuiItem(rain, rain1 -> {
                        rain1.getWhoClicked().getWorld().setWeatherDuration(99999);
                        rain1.getWhoClicked().sendMessage("§aWeather set to §9Rain§a.");
                        rain1.getWhoClicked().closeInventory();
                        rain1.setCancelled(true);
                    }));
                    weatherStickGui.setItem(15, new GuiItem(thunder, th -> {
                        th.getWhoClicked().getWorld().setThundering(true);
                        th.getWhoClicked().sendMessage("§aWeather set to §fThunder§a.");
                        th.getWhoClicked().closeInventory();
                        th.setCancelled(true);
                    }));

                    weatherStickGui.open(player);
            }
        }
    }
}
