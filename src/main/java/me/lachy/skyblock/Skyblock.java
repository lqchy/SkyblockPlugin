package me.lachy.skyblock;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import me.lachy.skyblock.commands.debug.StatsCommand;
import me.lachy.skyblock.commands.debug.WhereAmICommand;
import me.lachy.skyblock.commands.dev.ItemCommand;
import me.lachy.skyblock.commands.dev.SpawnCommand;
import me.lachy.skyblock.items.ItemBuilder;
import me.lachy.skyblock.listeners.EntityDamageListener;
import me.lachy.skyblock.listeners.GrappleListener;
import me.lachy.skyblock.listeners.LoginListener;
import me.lachy.skyblock.listeners.RightClickListener;
import org.bson.Document;
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
        new SpawnCommand(this);
        new StatsCommand(this);

        getServer().getPluginManager().registerEvents(new RightClickListener(), this);
        getServer().getPluginManager().registerEvents(new GrappleListener(), this);
        getServer().getPluginManager().registerEvents(new EntityDamageListener(), this);
        getServer().getPluginManager().registerEvents(new LoginListener(), this);

        initItems(items);
    }

    public static MongoClient connectToMongo() {
        return MongoClients.create("mongodb+srv://lachlan:AFhRHd4TfDzamD4R@tlgolgends1.1bsyw.mongodb.net/skyblock?retryWrites=true&w=majority");
    }

    @Override
    public void onDisable() {
        getLogger().info(this.getName() + " " + this.getDescription().getVersion() + " has been disabled.");
    }

    public void initItems(List<ItemStack> items) {
        items.add(new ItemBuilder(Material.FISHING_ROD).setName("§aGrappling Hook").setLore("", "§a§lUNCOMMON").toItemStack());
        items.add(new ItemBuilder(Material.WOOD_SWORD).setName("§fAspect of the Jerry").setLore("", "§f§lCOMMON").toItemStack());
        items.add(new ItemBuilder(Material.STICK).setName("§aWeather Stick").setLore("", "§a§lUNCOMMON").toItemStack());
        items.add(new ItemBuilder(Material.DIAMOND_SWORD).setName("§9Aspect of the End").setLore("", "§9§lRARE").toItemStack());
        items.add(new ItemBuilder(Material.BLAZE_ROD).setName("§9Archer's Stick").setlore("", "§9§lRARE").toItemStack());
        items.add(new ItemBuilder(Material.GHAST_TEAR).setname("§aNons' tear").setlore("", "§a§lUNCOMMON").toItemStack());
    }

}
