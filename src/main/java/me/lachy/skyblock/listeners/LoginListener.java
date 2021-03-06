package me.lachy.skyblock.listeners;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import me.lachy.skyblock.Skyblock;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;

public class LoginListener implements Listener {

    @EventHandler
    public void onLogin(PlayerJoinEvent event) {
        Document doc = new Document("uuid", event.getPlayer().getUniqueId().toString());

        event.getPlayer().sendMessage(String.valueOf(event.getPlayer().getUniqueId().toString()));
        MongoClient connection = Skyblock.connectToMongo();
        MongoCollection<Document> collection = connection.getDatabase("skyblock").getCollection("stats");
        collection.insertOne(new Document().append("uuid", event.getPlayer().getUniqueId().toString()).append("mana", 100).append("defence", 100));
        event.getPlayer().sendMessage("§aDocument created.");
    }
}
