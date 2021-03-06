package me.lachy.skyblock.commands.dev;

import me.lachy.skyblock.Skyblock;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;

public class SpawnCommand implements CommandExecutor {

    private Skyblock plugin;

    public SpawnCommand(Skyblock plugin) {
        this.plugin = plugin;
        plugin.getCommand("spawn").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.isOp()) {
                switch (strings[0].toLowerCase()) {
                    case "spider":
                        Spider spider = (Spider) player.getWorld().spawnEntity(player.getEyeLocation(), EntityType.SPIDER);
                        ArmorStand spiderAS = (ArmorStand) player.getWorld().spawnEntity(player.getEyeLocation(), EntityType.ARMOR_STAND);
                        spider.setMaxHealth(100);
                        spider.setHealth(100);
                        spiderAS.setVisible(false);
                        spiderAS.spigot().isInvulnerable();
                        spiderAS.setSmall(true);
                        spiderAS.setCustomName("§7" + spider.getHealth() + "§a/" + spider.getMaxHealth());
                        spiderAS.setCustomNameVisible(true);
                        spider.setPassenger(spiderAS);
                        player.sendMessage("§aSpawned spider at eye location.");
                        break;
                    default:
                        player.sendMessage("§cInvalid mob type.");
                }
            } else {
                player.sendMessage(Skyblock.noPerms);
            }
        } else {
            commandSender.sendMessage(Skyblock.notPlayer);
        }

        return true;
    }
}
