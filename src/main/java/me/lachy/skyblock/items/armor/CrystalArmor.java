package me.lachy.skyblock.items.armor;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.Arrays;

public class CrystalArmor {

    public ItemStack helmet() {
        ItemStack item = new ItemStack(Material.LEATHER_HELMET);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        
        meta.setColor(Color.WHITE);
        meta.setDisplayName("§5Crystal Helmet");
        meta.setLore(Arrays.asList("", "§5§lEPIC HELMET"));

        meta.spigot().setUnbreakable(true);

        item.setItemMeta(meta);
        return item;
    }

    public ItemStack chestplate() {
        ItemStack item = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();

        meta.setColor(Color.WHITE);
        meta.setDisplayName("§5Crystal Chestplate");
        meta.setLore(Arrays.asList("", "§5§lEPIC CHESTPLATE"));

        meta.spigot().setUnbreakable(true);

        item.setItemMeta(meta);
        return item;
    }

    public ItemStack leggings() {
        ItemStack item = new ItemStack(Material.LEATHER_LEGGINGS);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();

        meta.setColor(Color.WHITE);
        meta.setDisplayName("§5Crystal Leggings");
        meta.setLore(Arrays.asList("", "§5§lEPIC LEGGINGS"));

        meta.spigot().setUnbreakable(true);

        item.setItemMeta(meta);
        return item;
    }

    public ItemStack boots() {
        ItemStack item = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();

        meta.setColor(Color.WHITE);
        meta.setDisplayName("§5Crystal Boots");
        meta.setLore(Arrays.asList("", "§5§lEPIC BOOTS"));

        meta.spigot().setUnbreakable(true);

        item.setItemMeta(meta);
        return item;
    }
}
