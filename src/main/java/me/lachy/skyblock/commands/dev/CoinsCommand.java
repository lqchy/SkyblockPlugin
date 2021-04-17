package me.lachy.skyblock.commands.dev;

import me.lachy.skyblock.Skyblock;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class CoinsCommand implements CommandExecutor {

    private Skyblock plugin;

    public CoinsCommand(Skyblock plugin) {
        this.plugin = plugin;
        plugin.getCommand("coins").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if (player.isOp()) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target != null) {
                    ConfigurationSection section = plugin.getConfig().getConfigurationSection(target.getUniqueId().toString());
                    double toAdd = Double.parseDouble(args[1]);
                    double current = section.getDouble("coins");
                    double newCoins = current + toAdd;
                    section.set("coins", newCoins);
                    plugin.saveConfig();

                    player.sendMessage("§aCoins of player §6" + target.getName() + " §aincreased by §6" + toAdd + "§a. They now have §6" + newCoins + " §acoins.");
                    target.sendMessage("§aYou were given coins by an admin!");
                }
            }
        }
        return true;
    }
}
