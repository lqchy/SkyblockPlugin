package me.lachy.skyblock;

import me.lachy.skyblock.commands.debug.StatsCommand;
import me.lachy.skyblock.commands.debug.WhereAmICommand;
import me.lachy.skyblock.commands.dev.CoinsCommand;
import me.lachy.skyblock.commands.dev.ItemCommand;
import me.lachy.skyblock.commands.dev.NPCCommand;
import me.lachy.skyblock.commands.dev.SpawnCommand;
import me.lachy.skyblock.commands.general.BoopCommand;
import me.lachy.skyblock.items.ItemBuilder;
import me.lachy.skyblock.items.armor.CrystalArmor;
import me.lachy.skyblock.items.armor.MiscArmor;
import me.lachy.skyblock.listeners.*;
import me.lachy.skyblock.managers.ScoreboardManager;
import net.citizensnpcs.api.CitizensAPI;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.DecimalFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public final class Skyblock extends JavaPlugin {

    public static String mainPrefix = "§2[SKYBLOCK]§r ";
    public static String debugPrefix = "§9[DEBUG]§r ";
    public static String devPrefix = "§c[DEV]§r ";
    public static String notPlayer = "Only players can use this command!";
    public static String noPerms = "§cYou do not have permission to use that command.";

    public List<ItemStack> items = new ArrayList<>();

    public CrystalArmor crystalArmor;

    {
        crystalArmor = new CrystalArmor();
    }

    public MiscArmor miscArmor;

    {
        miscArmor = new MiscArmor();
    }

    @Override
    public void onEnable() {
        getLogger().info(this.getName() + " " + this.getDescription().getVersion() + " has been enabled.");

        new ItemCommand(this);
        new WhereAmICommand(this);
        new SpawnCommand(this);
        new StatsCommand(this);
        new BoopCommand(this);
        new NPCCommand(this);
        new CoinsCommand(this);

        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.getOnlinePlayers().forEach(player -> {
                    int coins = getConfig().getConfigurationSection(player.getUniqueId().toString()).getInt("coins");
                    int health = getConfig().getConfigurationSection(player.getUniqueId().toString()).getInt("health");
                    int maxHealth = getConfig().getConfigurationSection(player.getUniqueId().toString()).getInt("maxHealth");
                    int defence = getConfig().getConfigurationSection(player.getUniqueId().toString()).getInt("defence");
                    int mana = getConfig().getConfigurationSection(player.getUniqueId().toString()).getInt("mana");
                    ScoreboardManager scoreboardManager = new ScoreboardManager("§e§lSKYBLOCK");

                    Instant instant = Instant.now();
                    ZoneId zoneId = ZoneId.of("Europe/London");
                    ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, zoneId);

                    scoreboardManager.addLine("§8" + zdt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a")));
                    scoreboardManager.addBlankSpace();

                    double amount = Double.parseDouble(String.valueOf(coins));
                    DecimalFormat formatter = new DecimalFormat("#,###.0");
                    scoreboardManager.addLine("§6Ⓒ: " + formatter.format(amount));

                    scoreboardManager.addBlankSpace();
                    scoreboardManager.addLine("§cⒽ: " + health + "/" + maxHealth);
                    scoreboardManager.addLine("§aⒹ: " + defence);
                    scoreboardManager.addLine("§bⓂ: " + mana);

                    scoreboardManager.addBlankSpace();
                    scoreboardManager.addLine("§e" + Bukkit.getServer().getOnlinePlayers().size() + " §fonline " + (Bukkit.getServer().getOnlinePlayers().size() >= 2 ? "players!" : "player!"));

                    player.setScoreboard(scoreboardManager.getScoreboard());
                });
            }
        }.runTaskTimer(this, 0, 30);

        initItems(items);
        initEvents();
    }

    private void initEvents() {
        getServer().getPluginManager().registerEvents(new RightClickListener(), this);
        getServer().getPluginManager().registerEvents(new GrappleListener(), this);
        getServer().getPluginManager().registerEvents(new EntityDamageListener(), this);
        getServer().getPluginManager().registerEvents(new LoginListener(this), this);
        getServer().getPluginManager().registerEvents(new MobSpawnListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerMoveListener(), this);
        getServer().getPluginManager().registerEvents(new SignUpdateListener(this), this);

        CitizensAPI.registerEvents(new NPCRightClickEvent(this));
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
        items.add(new ItemBuilder(Material.BLAZE_ROD).setName("§9Archer's Stick").setLore("", "§9§lRARE").toItemStack());
        items.add(new ItemBuilder(Material.GHAST_TEAR).setName("§aNon's Tear").setLore("", "§a§lUNCOMMON").toItemStack());
        items.add(new ItemBuilder(Material.IRON_SWORD).setName("§6Hyperion").setLore("", "§6§lLEGENDARY").toItemStack());
        items.add(crystalArmor.helmet());
        items.add(crystalArmor.chestplate());
        items.add(crystalArmor.leggings());
        items.add(crystalArmor.boots());
        items.add(new ItemBuilder(Material.LEATHER_BOOTS).setName("§aSquid Boots").setLore("", "§a§lUNCOMMON BOOTS")
                .setInfinityDurability().setLeatherArmorColor(Color.BLACK).toItemStack());
    }
}
