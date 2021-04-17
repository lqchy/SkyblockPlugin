package me.lachy.skyblock.guis;

import me.lachy.skyblock.Skyblock;
import me.mattstudios.mfgui.gui.components.util.GuiFiller;
import me.mattstudios.mfgui.gui.guis.Gui;
import me.mattstudios.mfgui.gui.guis.GuiItem;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class CadetShopGUI {

    private Skyblock plugin;

    public CadetShopGUI(Skyblock plugin) {
        this.plugin = plugin;
    }

    public Gui gui() {
        Gui cadet = new Gui(3, "§eCadet4U");

        GuiFiller filler = new GuiFiller(cadet);
        ItemStack fillItem = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        ItemMeta meta = fillItem.getItemMeta();
        meta.setDisplayName("§0");
        fillItem.setItemMeta(meta);
        filler.fill(new GuiItem(fillItem));

        ItemStack talisman = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
        SkullMeta smeta = (SkullMeta) talisman.getItemMeta();
        smeta.setOwner("Cadet4U");
        smeta.setDisplayName("§aCadet Talisman");
        smeta.setLore(Arrays.asList("", "§eAbility: Tallness", "§7Reduces speed because Cadet is too tall", "", "§a§lUNCOMMON", "", "§8Price:", "§6100 coins"));
        talisman.setItemMeta(smeta);

        cadet.setItem(10, new GuiItem(talisman));
        cadet.addSlotAction(10, event -> {
            double coins = plugin.getConfig().getConfigurationSection(event.getWhoClicked().getUniqueId().toString()).getDouble("coins");
            if (coins >= 100) {
                event.getWhoClicked().getInventory().addItem(talisman);
                event.getWhoClicked().getWorld().playSound(event.getWhoClicked().getLocation(), Sound.ORB_PICKUP, 1.0F, 1.0F);
                event.getWhoClicked().sendMessage("§aPurchased " + talisman.getItemMeta().getDisplayName().replace("§a", "§6"));
                plugin.getConfig().getConfigurationSection(event.getWhoClicked().getUniqueId().toString()).set("coins", coins - 100);
                plugin.saveConfig();
            } else {
                event.getWhoClicked().getWorld().playSound(event.getWhoClicked().getLocation(), Sound.ENDERMAN_HIT, 1.0F, 1.0F);
                event.getWhoClicked().sendMessage("§cYou cannot afford this item!");
            }
            event.setCancelled(true);
        });

        cadet.setDefaultClickAction(event -> event.setCancelled(true));

        return cadet;
    }

}