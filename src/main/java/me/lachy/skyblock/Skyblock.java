package me.lachy.skyblock;

import me.lachy.skyblock.commands.dev.ItemCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class Skyblock extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info(this.getName() + " " + this.getDescription().getVersion() + " has been enabled.");

        new ItemCommand(this);
    }

    @Override
    public void onDisable() {
        getLogger().info(this.getName() + " " + this.getDescription().getVersion() + " has been disabled.");
    }
}
