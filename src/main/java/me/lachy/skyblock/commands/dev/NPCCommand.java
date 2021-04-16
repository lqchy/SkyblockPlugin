package me.lachy.skyblock.commands.dev;

import me.lachy.skyblock.Skyblock;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class NPCCommand implements CommandExecutor {

    private Skyblock plugin;

    public NPCCommand(Skyblock plugin) {
        this.plugin = plugin;
        plugin.getCommand("npc").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("create")) {
                    String name = args[1];
                    CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, name.replace('§', '&')).spawn(player.getLocation());
                }
            }
            return true;
        } else {
            return false;
        }
    }
}
