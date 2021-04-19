package me.lachy.skyblock.items.armor;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.Arrays;

public class MiscArmor {

    public ItemStack squidBoots() {
        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);

        LeatherArmorMeta itemMeta = (LeatherArmorMeta) boots.getItemMeta();

        itemMeta.setColor(Color.BLACK);
        itemMeta.setDisplayName("§aSquid Boots");
        itemMeta.setLore(Arrays.asList("", "§a§lUNCOMMON BOOTS"));

        itemMeta.spigot().setUnbreakable(true);

        boots.setItemMeta(itemMeta);
        return boots;
    }

}
