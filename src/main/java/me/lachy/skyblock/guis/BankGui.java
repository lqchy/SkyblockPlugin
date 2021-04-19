package me.lachy.skyblock.guis;

import com.antarescraft.kloudy.signguilib.SignGUI;
import me.lachy.skyblock.Skyblock;
import me.mattstudios.mfgui.gui.components.util.GuiFiller;
import me.mattstudios.mfgui.gui.guis.Gui;
import me.mattstudios.mfgui.gui.guis.GuiItem;
import net.minecraft.server.v1_8_R3.MinecraftKey;
import net.minecraft.server.v1_8_R3.PacketPlayOutNamedSoundEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.craftbukkit.v1_8_R3.CraftSound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.DecimalFormat;
import java.util.Arrays;

public class BankGui {

    private final Skyblock plugin;

    public BankGui(Skyblock plugin) {
        this.plugin = plugin;
    }

    public Gui gui(Player player) {
        ConfigurationSection section = plugin.getConfig().getConfigurationSection(player.getUniqueId().toString());
        double amount = Double.parseDouble(String.valueOf(section.get("bank")));
        DecimalFormat formatter = new DecimalFormat("#,###.0");

        Gui bank = new Gui(3, "§eBank");

        GuiFiller filler = new GuiFiller(bank);
        ItemStack fillItem = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        ItemMeta meta = fillItem.getItemMeta();
        meta.setDisplayName("§0");
        fillItem.setItemMeta(meta);
        filler.fill(new GuiItem(fillItem));

        ItemStack withdraw = new ItemStack(Material.GOLD_BLOCK);
        ItemMeta wmeta = withdraw.getItemMeta();
        wmeta.setDisplayName("§aWithdraw Coins");
        wmeta.setLore(Arrays.asList("", "§7Withdraw coins from your bank account!", "", "§8Current Balance: §6" + formatter.format(amount)));
        withdraw.setItemMeta(wmeta);

        ItemStack deposit = new ItemStack(Material.DIAMOND);
        ItemMeta dmeta = deposit.getItemMeta();
        dmeta.setDisplayName("§aDeposit Coins");
        dmeta.setLore(Arrays.asList("", "§7Deposit coins to your bank account!", "", "§8Current Balance: §6" + formatter.format(amount)));
        deposit.setItemMeta(dmeta);

        bank.setItem(11, new GuiItem(withdraw));
        bank.setItem(15, new GuiItem(deposit));

        bank.addSlotAction(11, event -> {
            String[] text = {"", "△ △ △ △", "Enter the amount", "to withdraw"};
            SignGUI.openSignEditor((Player) event.getWhoClicked(), text);
        });

        bank.addSlotAction(15, event -> {
            String[] text = {"", "△ △ △ △", "Enter the amount", "to deposit"};
            SignGUI.openSignEditor((Player) event.getWhoClicked(), text);
        });

        bank.setDefaultClickAction(event -> event.setCancelled(true));

        return bank;
    }

    public void withdraw(ConfigurationSection section, Player player, double amount) {
        double bank = (double) section.get("bank");

        if (Double.isNaN(amount)) {
            player.sendMessage("Invalid amount!");
        }

        DecimalFormat formatter = new DecimalFormat("#,###.0");

        if (amount > bank)

        section.set("bank", bank - amount >= 0 ? bank - amount : 0);
        section.set("coins", section.getDouble("coins") + amount);

        plugin.saveConfig();

        CraftPlayer craftPlayer = (CraftPlayer) player;
        Location l = player.getLocation();

        String craftSound = CraftSound.getSound(Sound.NOTE_PIANO);
        PacketPlayOutNamedSoundEffect packet = new PacketPlayOutNamedSoundEffect(craftSound, l.getX(), l.getY(), l.getZ(), 2.0F, 2.0F);

        craftPlayer.getHandle().playerConnection.sendPacket(packet);

        player.sendMessage("§aWithdrew §6" + formatter.format(amount) + " coins§a from your bank account." +
                " There are now §6" + formatter.format(section.get("bank")) + " §aleft!");
    }

    public void deposit(ConfigurationSection section, Player player, double amount) {
        double bank = (double) section.get("bank");

        if (Double.isNaN(amount)) {
            player.sendMessage("Invalid amount!");
        }

        DecimalFormat formatter = new DecimalFormat("#,###.0");

        section.set("bank", bank + amount);
        section.set("coins", section.getDouble("coins") - amount >= 0 ? section.getDouble("coins") - amount : 0);

        plugin.saveConfig();

        CraftPlayer craftPlayer = (CraftPlayer) player;
        Location l = player.getLocation();

        String craftSound = CraftSound.getSound(Sound.NOTE_PIANO);
        PacketPlayOutNamedSoundEffect packet = new PacketPlayOutNamedSoundEffect(craftSound, l.getX(), l.getY(), l.getZ(), 2.0F, 2.0F);

        craftPlayer.getHandle().playerConnection.sendPacket(packet);

        player.sendMessage("§aDeposited §6" + formatter.format(amount) + " coins§a to your bank account." +
                " There are now §6" + formatter.format(section.get("bank")) + " §a!");
    }
}
