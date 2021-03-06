package me.lachy.skyblock.commands.debug;

import me.lachy.skyblock.Skyblock;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StatsCommand implements CommandExecutor {

    private Skyblock plugin;

    public StatsCommand(Skyblock plugin) {
        this.plugin = plugin;
        plugin.getCommand("stats").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            player.setMaxHealth(100);
            player.setHealth(player.getMaxHealth());
            player.sendMessage("Set to 100");
        }

        return true;
    }
}
