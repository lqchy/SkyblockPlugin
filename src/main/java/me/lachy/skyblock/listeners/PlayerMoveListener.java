package me.lachy.skyblock.listeners;

import me.lachy.skyblock.Skyblock;
import me.lachy.skyblock.items.ItemBuilder;
import me.lachy.skyblock.items.armor.CrystalArmor;
import me.lachy.skyblock.items.armor.MiscArmor;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftMonster;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftZombie;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;
import xyz.xenondevs.particle.data.color.RegularColor;

import java.util.*;

public class PlayerMoveListener implements Listener {

    private Skyblock plugin;

    public List<String> crystalColours = Arrays.asList("#002b06", "#00370c", "#00440f", "#005011", "#005e14",
            "#006b16", "#007917", "#008719", "#00951a", "#00a41a", "#00b21a", "#00c119",
            "#00d117", "#00e013", "#03ef0d", "#16ff02");


//    @EventHandler
//    public void crystalArmor(PlayerMoveEvent e) {
//        Player p = e.getPlayer();
//        List<ItemStack> armor = Arrays.asList(p.getInventory().getArmorContents());
//        byte lightLevel = p.getLocation().getBlock().getLightLevel();
//
//        int r = Integer.valueOf( crystalColours.get(lightLevel).substring( 1, 3 ), 16 );
//        int g = Integer.valueOf( crystalColours.get(lightLevel).substring( 3, 5 ), 16 );
//        int b = Integer.valueOf( crystalColours.get(lightLevel).substring( 5, 7 ), 16 );
//
//        armor.forEach(a -> {
//            LeatherArmorMeta m = (LeatherArmorMeta) a.getItemMeta();
//            m.setColor(Color.fromRGB(r, g, b));
//            a.setItemMeta(m);
//        });
//    }

    @EventHandler
    public void squidBoots(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        ItemStack boots = p.getInventory().getBoots();

        if (boots == null) return;

        if (boots.hasItemMeta()) {
            if (boots.getItemMeta().getDisplayName().equals("§aSquid Boots")) {
                Location l = p.getLocation();

                ArrayList<Location> middle = getCircle(l, 0.2, (int) Math.floor(Math.random() * 5));
                middle.forEach(location -> new ParticleBuilder(ParticleEffect.REDSTONE, location)
                        .setParticleData(new RegularColor(59, 59, 59))
                        .setOffsetX((float) Math.random() * 10)
                        .setOffsetZ((float) Math.random() * 10)
                        .setAmount(10)
                        .display(Bukkit.getOnlinePlayers()));
                ArrayList<Location> innermiddle = getCircle(l, 0.1, (int) Math.floor(Math.random() * 5));
                innermiddle.forEach(location -> new ParticleBuilder(ParticleEffect.REDSTONE, location)
                        .setParticleData(new RegularColor(59, 59, 59))
                        .setOffsetX((float) Math.random() * 10)
                        .setOffsetZ((float) Math.random() * 10)
                        .setAmount(10)
                        .display(Bukkit.getOnlinePlayers()));
                ArrayList<Location> inner = getCircle(l, 0.05, (int) Math.floor(Math.random() * 5));
                inner.forEach(location -> new ParticleBuilder(ParticleEffect.REDSTONE, location)
                        .setParticleData(new RegularColor(59, 59, 59))
                        .setOffsetX((float) Math.random() * 10)
                        .setOffsetZ((float) Math.random() * 10)
                        .setAmount(10)
                        .display(Bukkit.getOnlinePlayers()));
            }
        }
    }

    public ArrayList<Location> getCircle(Location center, double radius, int amount) {
        World world = center.getWorld();
        double increment = (2 * Math.PI) / amount;
        ArrayList<Location> locations = new ArrayList<>();
        for(int i = 0; i < amount; i++)
        {
            double angle = i * increment;
            double x = center.getX() + (radius * Math.cos(angle));
            double z = center.getZ() + (radius * Math.sin(angle));
            locations.add(new Location(world, x, center.getY(), z));
        }
        return locations;
    }

    @EventHandler
    public void fearHelmet(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        ItemStack fearHelmet = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
        ItemStack h = new ItemBuilder(fearHelmet).setSkullOwner("daFear").setName("§5Fear Helmet")
                .setLore("", "§eAbility: Intimidation", "§8Mobs in an §b8 block radius §8will not target you", "", "§5§lEPIC HELMET").toItemStack();

        if (p.getInventory().getHelmet() != null && p.getInventory().getHelmet().equals(h) && !(p.getGameMode() == GameMode.CREATIVE || p.getGameMode() == GameMode.SPECTATOR)) {
            Collection<Entity> entities = p.getNearbyEntities(8, 8, 8);
            for (Entity entity : entities) {
                if (entity instanceof Monster) {
                    p.sendMessage(entity.getCustomName());
                    ((CraftMonster) entity).setTarget(((LivingEntity) entity));
                }
            }
        }
    }
}
