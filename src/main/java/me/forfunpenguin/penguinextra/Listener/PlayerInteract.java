package me.forfunpenguin.penguinextra.Listener;

import me.forfunpenguin.penguinextra.Backpacks.Open;
import me.forfunpenguin.penguinextra.Utils.NBTUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.UUID;

public class PlayerInteract implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getPlayer().getInventory().getItemInMainHand() == null || event.getPlayer().getInventory().getItemInMainHand().getType() == Material.AIR) return;
        if (!NBTUtils.hasTag(event.getPlayer().getInventory().getItemInMainHand())) return;
        if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (NBTUtils.getTagStringValue(event.getPlayer().getInventory().getItemInMainHand(), "item_id").equalsIgnoreCase("BACKPACKS")) {
                event.setCancelled(true);
                event.getPlayer().closeInventory();
                Open.openBackpack(event.getPlayer());
            }
        }
    }
}
