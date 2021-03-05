package me.lachy.skyblock.commands.debug;

import me.lachy.skyblock.Skyblock;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StatsCommand implements CommandExecutor {

    private Skyblock plugin;

    public StatsCommand(Skyblock plugin) {
        this.plugin = plugin;
        plugin.getCommand("stats").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Skyblock.connectToMongo().getDatabase("skyblock").getCollection("stats");

        return true;
    }
}
