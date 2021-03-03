package me.lachy.skyblock.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GrapplingHook extends ItemStack {

    public ItemStack getItem() {
        Material type = Material.FISHING_ROD;
        ItemStack item = new ItemStack(type);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§aGrappling Hook");
        item.setItemMeta(meta);

        return item;
    }



    public String getId() {
        return "GRAPPLING_HOOK";
    }

}
