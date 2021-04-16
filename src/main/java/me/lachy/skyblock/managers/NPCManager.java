package me.lachy.skyblock.managers;

import com.mojang.authlib.GameProfile;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class NPCManager {

    public void createNPC(Player player, String name) {
        Location loc = player.getLocation(); // Player's location

        MinecraftServer nmsServer = ((CraftServer) Bukkit.getServer()).getServer(); // The server
        WorldServer nmsWorld = ((CraftWorld) player.getWorld()).getHandle(); // The world
        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), name.replace('&', '§')); // A new profile with a random UUID and the name you give it

        EntityPlayer npc = new EntityPlayer(nmsServer, nmsWorld, gameProfile, new PlayerInteractManager(nmsWorld)); // Creates a new Player on the server, in that world, with that profile
        Player npcPlayer = npc.getBukkitEntity().getPlayer(); // Bukkit instance of that player
        npcPlayer.setPlayerListName(""); // Set their name on tab to nothing

        npc.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch()); // Set the location of the NPC to the player's current location

        PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection; // The player's connection to the server
        connection.sendPacket(
                new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc) // Make it so the server can see the NPC
        );
        connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc)); // Spawn the NPC
        connection.sendPacket(
                new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, npc) // Remove the NPC from the server's tab list but leaving it visible
        );
    }
}
