package me.lachy.skyblock.commands.dev;

import me.lachy.skyblock.Skyblock;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;

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
                    BigDecimal toAdd = BigDecimal.valueOf(Long.parseLong(args[1]));
                    double current = section.getDouble("coins");
                    BigDecimal newCoins = BigDecimal.valueOf(current + toAdd.doubleValue());
                    section.set("coins", newCoins.doubleValue());
                    plugin.saveConfig();

                    DecimalFormat f = new DecimalFormat("#,###.0");

                    player.sendMessage("§aCoins of player §6" + target.getName() + " §aincreased by §6" + f.format(toAdd.intValue()) + "§a. They now have §6" + f.format(newCoins.intValue()) + " §acoins.");
                    target.sendMessage("§aYou were given coins by an admin!");
                }
            }
        }
        return true;
    }
}
