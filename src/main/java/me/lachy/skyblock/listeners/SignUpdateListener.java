package me.lachy.skyblock.listeners;

import com.antarescraft.kloudy.signguilib.SignGUIUpdateEvent;
import me.lachy.skyblock.Skyblock;
import me.lachy.skyblock.guis.BankGui;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class SignUpdateListener implements Listener {

    private Skyblock plugin;

    public SignUpdateListener(Skyblock plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onSignUpdate(SignGUIUpdateEvent e) {
        ConfigurationSection section = plugin.getConfig().getConfigurationSection(e.getPlayer().getUniqueId().toString());
        if (e.getSignText()[3].equals("to withdraw")) new BankGui(plugin).withdraw(section, e.getPlayer(), Double.parseDouble(e.getSignText()[0]));
        if (e.getSignText()[3].equals("to deposit")) new BankGui(plugin).deposit(section, e.getPlayer(), Double.parseDouble(e.getSignText()[0]));
    }

}
