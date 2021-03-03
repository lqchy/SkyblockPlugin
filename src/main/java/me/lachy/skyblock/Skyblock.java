package me.lachy.skyblock;

import org.bukkit.plugin.java.JavaPlugin;

public final class Skyblock extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info(this.getName() + " " + this.getDescription().getVersion() + " has been enabled.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
