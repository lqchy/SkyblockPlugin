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
            plugin.getConfig().getConfigurationSection(player.getUniqueId().toString()).set(strings[0].toLowerCase(), Integer.valueOf(strings[1]));
            player.sendMessage("§aSet " + strings[0] + " to " + strings[1]);
            plugin.saveConfig();
        }

        return true;
    }
}
