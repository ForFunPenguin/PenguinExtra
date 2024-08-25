package me.forfunpenguin.penguinextra.Listener;

import me.forfunpenguin.penguinextra.Utils.NBTUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class HeadEquip implements Listener {

    @EventHandler
    public void onHeadEquip(InventoryClickEvent event) {
        if (event.getInventory().getType() == InventoryType.CRAFTING) {
            if (event.getAction() != InventoryAction.MOVE_TO_OTHER_INVENTORY) return;
            if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) return;
            if (event.getCurrentItem().getItemMeta() == null) return;
            if (!NBTUtils.hasTag(event.getCurrentItem())) return;
            if (event.getSlot() >= 0 && event.getSlot() <= 8) {
                if (event.getWhoClicked().getInventory().firstEmpty() == -1) {
                    event.setCancelled(true);
                    return;
                }
                if (NBTUtils.getTagStringValue(event.getCurrentItem(), "item_id") == null) return;
                event.setCancelled(true);
                int emptyslot = getEmptySlot((Player)event.getWhoClicked(), event.getSlot(), 9, 35);
                if (emptyslot != event.getSlot()) {
                    event.getWhoClicked().getInventory().setItem(emptyslot, event.getCurrentItem());
                    event.getWhoClicked().getInventory().setItem(event.getSlot(), null);
                }
            } else if (event.getSlot() >= 9 && event.getSlot() <= 35) {
                if (event.getWhoClicked().getInventory().firstEmpty() == -1) {
                    event.setCancelled(true);
                    return;
                }
                if (NBTUtils.getTagStringValue(event.getCurrentItem(), "item_id") == null) return;
                int emptyslot = getEmptySlot((Player)event.getWhoClicked(), event.getSlot(), 0, 8);
                if (emptyslot != event.getSlot()) {
                    event.getWhoClicked().getInventory().setItem(emptyslot, event.getCurrentItem());
                    event.getWhoClicked().getInventory().setItem(event.getSlot(), null);
                }
            }

        }
    }

    public int getEmptySlot(Player player, int itemslot, int startslot, int endslot) {
        int output = itemslot;
        for (int i=startslot; i<=endslot; i++){
            //player.sendMessage(player.getInventory().getItem(i).getType().toString());
            if (player.getInventory().getItem(i) == null || player.getInventory().getItem(i).getType() == Material.AIR) {
                output = i;
                break;
            }
        }
        return output;
    }
}
