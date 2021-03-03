package me.lachy.skyblock.commands.debug;

import me.lachy.skyblock.Skyblock;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WhereAmICommand implements CommandExecutor {

    private Skyblock plugin;

    public WhereAmICommand(Skyblock plugin) {
        this.plugin = plugin;
        plugin.getCommand("whereami").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(Skyblock.notPlayer);
            return false;
        } else {
            Player player = (Player) commandSender;
            if (player.hasPermission("skyblock.debug")) {
                player.sendMessage(Skyblock.debugPrefix + "You are currently in world: " + player.getWorld().getName());
                return true;
            } else {
                player.sendMessage(Skyblock.noPerms);
                return false;
            }
        }
    }
}
