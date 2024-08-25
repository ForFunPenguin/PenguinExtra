package me.forfunpenguin.penguinextra.Listener;

import me.forfunpenguin.penguinextra.Backpacks.Open;
import me.forfunpenguin.penguinextra.Utils.NBTUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;

public class InventoryClick implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        //if (!(event.getWhoClicked() instanceof Player)) return;
        //Player player = (Player)event.getWhoClicked();
        //if (!event.getInventory().getType().equals(InventoryType.WORKBENCH)) return;
        //if (event.getCurrentItem().getType() == Material.AIR) return;
        //if (event.getSlot() == 0) {
            //player.sendMessage(event.getCurrentItem().toString());
        //}
        if (event.getView().getTitle().equals("背包")) {
            if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) return;
            if (event.getCurrentItem().getItemMeta() == null) return;
            if (!NBTUtils.hasTag(event.getCurrentItem())) return;
            if (NBTUtils.getTagStringValue(event.getCurrentItem(), "item_id").equalsIgnoreCase("BACKPACKS")) {
                event.setCancelled(true);
            }
        }
    }
}
