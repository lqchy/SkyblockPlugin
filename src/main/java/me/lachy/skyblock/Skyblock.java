package me.lachy.skyblock;

import me.lachy.skyblock.commands.debug.WhereAmICommand;
import me.lachy.skyblock.commands.dev.ItemCommand;
import me.lachy.skyblock.items.ItemBuilder;
import me.lachy.skyblock.listeners.RightClickListener;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Skyblock extends JavaPlugin {

    public static String mainPrefix = "§2[SKYBLOCK]§r ";
    public static String debugPrefix = "§9[DEBUG]§r ";
    public static String devPrefix = "§c[DEV]§r ";
    public static String notPlayer = "Only players can use this command!";
    public static String noPerms = "§cYou do not have permission to use that command.";

    public List<ItemStack> items = new ArrayList<>();

    @Override
    public void onEnable() {
        getLogger().info(this.getName() + " " + this.getDescription().getVersion() + " has been enabled.");

        new ItemCommand(this);
        new WhereAmICommand(this);

        getServer().getPluginManager().registerEvents(new RightClickListener(this), this);

        initItems(items);
        initEvents(this);

    }

    @Override
    public void onDisable() {
        getLogger().info(this.getName() + " " + this.getDescription().getVersion() + " has been disabled.");
    }

    public void initItems(List<ItemStack> items) {
        items.add(new ItemBuilder(Material.FISHING_ROD).setName("§aGrappling Hook").setLore("", "§a§lUNCOMMON").toItemStack());
        items.add(new ItemBuilder(Material.WOOD_SWORD).setName("§fAspect of the Jerry").setLore("", "§f§lCOMMON").toItemStack());
    }

    public void initEvents(Skyblock plugin) {
        plugin.getServer().getPluginManager().registerEvents(new RightClickListener(plugin), plugin);
    }
}
