package me.lachy.skyblock.commands.general;

import me.lachy.skyblock.Skyblock;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.block.CommandBlock;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BoopCommand implements CommandExecutor {

    private Skyblock plugin;

    public BoopCommand(Skyblock plugin) {
        this.plugin = plugin;
        plugin.getCommand("boop").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        //if (commandSender instanceof Player) {
            Player sender = (Player) commandSender;
            Player target = Bukkit.getPlayer(strings[0]);
            if (target == null) {
                sender.sendMessage("§cThat player is not online!");
                return false;
            }
            else {
                target.sendMessage("§dFrom §7" + sender.getName() + "§7: §d§lBoop!");
                target.playSound(target.getLocation(), Sound.ORB_PICKUP, 1.0F, 1.0F);
                sender.sendMessage("§dTo §7" + target.getName() + "§7: §d§lBoop!");
            }
        //}

        return true;
    }
}
