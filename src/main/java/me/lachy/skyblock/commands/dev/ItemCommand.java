package me.lachy.skyblock.commands.dev;

import me.lachy.skyblock.Skyblock;
import me.lachy.skyblock.items.GrapplingHook;
import me.lachy.skyblock.util.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemCommand implements CommandExecutor {

    private Skyblock plugin;
    private Util util;

    public ItemCommand(Skyblock plugin) {
        this.plugin = plugin;
        plugin.getCommand("item").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command!");
            return false;
        } else {
            Player player = (Player) sender;
            if (player.isOp()) {
                ItemStack item = new GrapplingHook().getItem();

                player.getInventory().addItem(item);
                player.sendMessage(util.devPrefix + "§aYou were given a GRAPPLING_HOOK");
                return true;
            } else {
                player.sendMessage("§cYou do not have permission to use that command.");
                return false;
            }
        }
    }
}
