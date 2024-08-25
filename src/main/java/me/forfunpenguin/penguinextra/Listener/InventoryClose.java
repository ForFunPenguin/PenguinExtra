package me.forfunpenguin.penguinextra.Listener;

import me.forfunpenguin.penguinextra.Backpacks.Open;
import me.forfunpenguin.penguinextra.PenguinExtra;
import me.forfunpenguin.penguinextra.Utils.NBTUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import java.io.File;
import java.io.IOException;

public class InventoryClose implements Listener {

    private String path = PenguinExtra.getFolderPath() + "/Backpacks/";

    @EventHandler(priority = EventPriority.HIGH)
    public void onInventoryClose(InventoryCloseEvent event) throws IOException {
        if (!event.getPlayer().getOpenInventory().getTitle().equalsIgnoreCase("背包")) return;
        Inventory inventory = event.getInventory();
        if (!(event.getPlayer() instanceof Player)) {
            return;
        }
        if (inventory.getType() != InventoryType.CHEST) {
            return;
        }
        if (event.getPlayer().getInventory().getItemInMainHand().getType() == Material.AIR) {
            return;
        }
        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta() == null) {
            return;
        }
        if (NBTUtils.getTagStringValue(event.getPlayer().getInventory().getItemInMainHand(), "item_id").equalsIgnoreCase("BACKPACKS")) {
            File f = new File(path + "/" + NBTUtils.getTagStringValue(event.getPlayer().getInventory().getItemInMainHand(), "backpack_player") + "/", NBTUtils.getTagStringValue(event.getPlayer().getInventory().getItemInMainHand(), "backpack_uuid") + ".yml");
            FileConfiguration c = YamlConfiguration.loadConfiguration(f);
            c.set("inventory.content", inventory.getContents());
            c.save(f);
        }
    }
}
