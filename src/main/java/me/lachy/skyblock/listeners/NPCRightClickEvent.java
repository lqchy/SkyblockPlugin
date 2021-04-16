package me.lachy.skyblock.listeners;

import me.lachy.skyblock.Skyblock;
import me.lachy.skyblock.guis.CadetShopGUI;
import me.mattstudios.mfgui.gui.guis.Gui;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NPCRightClickEvent implements Listener {

    private Skyblock plugin;
    public NPCRightClickEvent(Skyblock plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onNPCRightClick(net.citizensnpcs.api.event.NPCRightClickEvent event) {
        if (event.getNPC().isSpawned() && event.getNPC().getName().equals("§eCadet4U")) {
            Gui cadet = new CadetShopGUI(plugin).gui();
            cadet.open(event.getClicker());
        }
    }

}
