package me.lachy.skyblock.listeners;

import me.lachy.skyblock.Skyblock;
import me.lachy.skyblock.items.ItemBuilder;
import me.mattstudios.mfgui.gui.guis.Gui;
import me.mattstudios.mfgui.gui.guis.GuiItem;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

import java.util.HashSet;

public class RightClickListener implements Listener {

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            Player player = event.getPlayer();
            ItemStack item = player.getInventory().getItemInHand();

            if (!item.hasItemMeta()) return;
            //if (!player.getGameMode().equals(GameMode.SURVIVAL)) return;

            switch (ChatColor.stripColor(item.getItemMeta().getDisplayName()).toLowerCase()) {
                case "aspect of the jerry":
                    player.getWorld().playSound(player.getLocation(), Sound.VILLAGER_IDLE, 1.0F, 1.0F);
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
                        rain1.getWhoClicked().getWorld().setStorm(true);
                        rain1.getWhoClicked().getWorld().setThundering(false);
                        rain1.getWhoClicked().sendMessage("§aWeather set to §9Rain§a.");
                        rain1.getWhoClicked().closeInventory();
                        rain1.setCancelled(true);
                    }));
                    weatherStickGui.setItem(15, new GuiItem(thunder, th -> {
                        th.getWhoClicked().getWorld().setStorm(true);
                        th.getWhoClicked().getWorld().setThundering(true);
                        th.getWhoClicked().sendMessage("§aWeather set to §fThunder§a.");
                        th.getWhoClicked().closeInventory();
                        th.setCancelled(true);
                    }));

                    weatherStickGui.open(player);
                    break;
                case "aspect of the end":
                    Location loc = player.getLocation();
                    Vector dir = loc.getDirection();
                    dir.normalize();

                    BlockIterator iterator = new BlockIterator(player.getWorld(),
                            player.getLocation().toVector(),
                            player.getLocation().getDirection().toBlockVector(),
                            1.0, 8);

                    double multiply;

                    while (iterator.hasNext()) {
                        if (!iterator.next().getType().equals(Material.AIR)) {
                            multiply = iterator.next().getLocation().distance(player.getLocation(loc));
                            dir.multiply(multiply - 2);
                            loc.add(dir);

                            player.teleport(loc);
                            player.getWorld().playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0F, 1.0F);
                            player.sendMessage("§cThere were blocks in the way!");
                            return;
                        }
                    }

                    dir.multiply(8);
                    loc.add(dir);

                    player.teleport(loc);
                    if (!player.getTargetBlock((HashSet<Byte>) null, 8).getType().equals(Material.AIR)) {
                        player.sendMessage("§cThere are blocks in the way!");
                        return;
                    }
                    player.getWorld().playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0F, 1.0F);
                    break;
            }
        }
    }
}
