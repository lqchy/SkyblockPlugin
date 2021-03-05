package me.lachy.skyblock.listeners;

import org.bukkit.Location;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.HashMap;

public class GrappleListener implements Listener {

    private HashMap<String, Long> cooldown = new HashMap<>();
    private int cooldowntime = 2;

    @EventHandler
    public void projectileLaunch(PlayerFishEvent e) {
        if (!e.getPlayer().getInventory().getItemInHand().hasItemMeta()) return;
        if (!e.getPlayer().getInventory().getItemInHand().getItemMeta().getDisplayName().equals("§aGrappling Hook")) return;

        Player p = e.getPlayer();
        ItemStack item = p.getItemInHand();
        if (e.getState() == PlayerFishEvent.State.FAILED_ATTEMPT || e.getState() == PlayerFishEvent.State.IN_GROUND) {
            if (cooldown.containsKey(p.getName())) {
                if (cooldown.get(p.getName()) > System.currentTimeMillis()) {
                    p.sendMessage("§cSlow down!");
                    return;
                }
            }
            cooldown.put(p.getName(), System.currentTimeMillis() + (cooldowntime * 1000L));
            Location loc1 = p.getLocation();
            Location loc2 = e.getHook().getLocation();

            Vector v = new Vector(loc2.getX() - loc1.getX(), 1, loc2.getZ() - loc1.getZ());
            p.setVelocity(v);
        }
    }

    public HashMap<String, Long> getCooldown() {
        return cooldown;
    }
}
