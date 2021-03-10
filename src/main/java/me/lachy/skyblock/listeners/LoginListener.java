package me.lachy.skyblock.listeners;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import me.lachy.skyblock.Skyblock;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class LoginListener implements Listener {

    private Skyblock plugin;

    public LoginListener(Skyblock plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onLogin(PlayerJoinEvent event) {
        boolean exists = plugin.getConfig().isConfigurationSection(event.getPlayer().getUniqueId().toString());

        if (!exists) {
            ConfigurationSection section = plugin.getConfig().createSection(event.getPlayer().getUniqueId().toString());
            section.set("defence", 0);
            section.set("mana", 100);
            section.set("health", 100);

            plugin.saveConfig();
        } else {

            new BukkitRunnable() {
                @Override
                public void run() {
                    ConfigurationSection section = plugin.getConfig().getConfigurationSection(event.getPlayer().getUniqueId().toString());

                    String message = "§c" + section.get("health").toString() + "§a      " + section.get("defence") + "§b      " + section.get("mana");
                    PacketPlayOutChat packet = new PacketPlayOutChat(new ChatComponentText(message), (byte) 2);
                    ((CraftPlayer) event.getPlayer()).getHandle().playerConnection.sendPacket(packet);
                }
            }.runTaskTimer(plugin, 1, 20);
        }
    }
}
