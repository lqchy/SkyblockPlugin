package me.lachy.skyblock.commands.dev;

import me.lachy.skyblock.Skyblock;
import me.lachy.skyblock.util.Util;
import me.mattstudios.mfgui.gui.components.util.ItemBuilder;
import me.mattstudios.mfgui.gui.guis.GuiItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import me.mattstudios.mfgui.gui.guis.PaginatedGui;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Comparator;

public class ItemCommand implements CommandExecutor {

    private Skyblock plugin;
    private Util util;

    public ItemCommand(Skyblock plugin) {
        this.plugin = plugin;
        plugin.getCommand("items").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Skyblock.notPlayer);
            return false;
        } else {
            Player player = (Player) sender;
            if (player.isOp()) {
                PaginatedGui gui = new PaginatedGui(6, 45, "Items");
                gui.setItem(6, 3, ItemBuilder.from(Material.ARROW).setName("§7Previous").asGuiItem(event -> {
                    gui.previous(); event.setCancelled(true);
                }));
                gui.setItem(6, 7, ItemBuilder.from(Material.ARROW).setName("§7Next").asGuiItem(event -> {
                    gui.next(); event.setCancelled(true);
                }));

                ItemStack filler = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
                ItemMeta meta = filler.getItemMeta(); meta.setDisplayName("§0"); filler.setItemMeta(meta);

                gui.getFiller().fillBottom(new GuiItem(filler));

                plugin.items.sort(Comparator.comparing(o -> ChatColor.stripColor(o.getItemMeta().getDisplayName())));
                plugin.items.forEach(itemStack -> gui.addItem(new GuiItem(itemStack, event -> {
                    event.getWhoClicked().getInventory().addItem(itemStack);
                    event.setCancelled(true);
                    event.getWhoClicked().closeInventory();
                })));

                gui.open(player);

                return true;
            } else {
                player.sendMessage(Skyblock.noPerms);
                return false;
            }
        }
    }
}
