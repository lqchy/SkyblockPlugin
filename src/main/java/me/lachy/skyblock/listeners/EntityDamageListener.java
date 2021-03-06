package me.lachy.skyblock.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityDamageListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player && event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
            event.setCancelled(true);
        }

        if (event.getEntity().getPassenger() != null) {
            LivingEntity e = (LivingEntity) event.getEntity();
            event.getEntity().getPassenger().setCustomName("§7" + ((int) e.getHealth()) + "§a/" + ((int) e.getMaxHealth()));
        }
    }

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        if (event.getEntity().getPassenger() != null) {
            event.getEntity().getPassenger().remove();
        }
    }


}
