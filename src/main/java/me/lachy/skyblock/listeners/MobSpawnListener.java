package me.lachy.skyblock.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class MobSpawnListener implements Listener {

    @EventHandler
    public void onSpawn(CreatureSpawnEvent event) {
        if (!event.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.SPAWNER_EGG)) return;

        LivingEntity entity = event.getEntity();
        if (entity.getType() == EntityType.ARMOR_STAND) return;
        Location loc = new Location(event.getLocation().getWorld(), event.getLocation().getX(), event.getLocation().getY() + 3, event.getLocation().getZ());
        ArmorStand health = (ArmorStand) event.getEntity().getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);

        entity.setMaxHealth(100);
        entity.setHealth(100);
        health.setVisible(false);
        health.spigot().isInvulnerable();
        health.setSmall(true);
        health.setCustomName("§7" + entity.getName() + " §8| §a" + ((int) entity.getHealth()) + "§2/" + ((int) entity.getMaxHealth()));
        health.setCustomNameVisible(true);
        entity.setPassenger(health);
    }
}
