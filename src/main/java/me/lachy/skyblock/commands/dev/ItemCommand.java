package me.lachy.skyblock.commands.dev;

import me.lachy.skyblock.Skyblock;
import me.lachy.skyblock.items.GrapplingHook;
import me.lachy.skyblock.util.Util;
import me.mattstudios.mfgui.gui.components.util.ItemBuilder;
import me.mattstudios.mfgui.gui.guis.GuiItem;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import me.mattstudios.mfgui.gui.guis.PaginatedGui;

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
                gui.setItem(6, 3, ItemBuilder.from(Material.PAPER).setName("Previous").asGuiItem(event -> gui.previous()));
                gui.setItem(6, 7, ItemBuilder.from(Material.PAPER).setName("Next").asGuiItem(event -> gui.next()));

                plugin.items.forEach(itemStack -> gui.addItem(new GuiItem(itemStack)));

                gui.open(player);

                return true;
            } else {
                player.sendMessage(Skyblock.noPerms);
                return false;
            }
        }
    }
}
