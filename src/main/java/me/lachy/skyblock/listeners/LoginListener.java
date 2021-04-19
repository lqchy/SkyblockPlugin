package me.lachy.skyblock.listeners;

import me.lachy.skyblock.Skyblock;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

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
            section.set("maxHealth", 100);
            section.set("coins", 0);
            section.set("bank", 0);

            plugin.saveConfig();
        }
    }
}
