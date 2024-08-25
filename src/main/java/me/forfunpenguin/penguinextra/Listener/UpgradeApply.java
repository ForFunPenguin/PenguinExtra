package me.forfunpenguin.penguinextra.Listener;

import me.forfunpenguin.penguinextra.Backpacks.ItemBuilder;
import me.forfunpenguin.penguinextra.PenguinExtra;
import me.forfunpenguin.penguinextra.Utils.NBTUtils;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Dye;
import org.bukkit.material.MaterialData;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UpgradeApply implements Listener {
    PenguinExtra plugin = PenguinExtra.getPlugin();

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player)event.getWhoClicked();
        if (event.getCurrentItem() == null || event.getCursor() == null) return;
        if (NBTUtils.hasTag(event.getCursor()) && NBTUtils.hasTag(event.getCurrentItem())) {
            if (NBTUtils.getTagStringValue(event.getCursor(), "item_id").equalsIgnoreCase("BACKPACK_UPGRADE") && NBTUtils.getTagStringValue(event.getCurrentItem(), "item_id").equalsIgnoreCase("BACKPACKS")) {
                event.setCancelled(true);
                ItemStack backpack = event.getCurrentItem();
                ItemStack upgrade = event.getCursor();
                int upgrade_slot = NBTUtils.getTagIntValue(upgrade, "upgrade_slot");
                int backpack_tier = NBTUtils.getTagIntValue(backpack, "backpack_tier");
                if (backpack_tier == 6) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c你的背包欄位已無法再擴增了!"));
                } else {
                    if (upgrade_slot - backpack_tier * 9 == 9) {
                        if (event.getView().getTitle().equals("背包")) {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c你不能在背包開啟時升級背包!"));
                            player.playSound(player, Sound.ENTITY_VILLAGER_NO, 1, 1);
                            return;
                        }
                        upgrade.setAmount(upgrade.getAmount() - 1);
                        player.setItemOnCursor(upgrade);
                        backpack_tier += 1;
                        backpack = ItemBuilder.setLore(backpack, backpack_tier);
                        backpack = NBTUtils.putIntTag(backpack, "backpack_tier", backpack_tier);
                        event.setCurrentItem(backpack);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a你的背包已擴增到" + backpack_tier * 9 + "格!"));
                        player.playSound(player, Sound.BLOCK_ANVIL_USE, 1, 1);
                    } else {
                        if (upgrade_slot - backpack_tier * 9 > 9) {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c你的背包還無法使用此升級!"));
                            player.playSound(player, Sound.ENTITY_VILLAGER_NO, 1, 1);
                        } else {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c你的背包已使用過此升級了!"));
                            player.playSound(player, Sound.ENTITY_VILLAGER_NO, 1, 1);
                        }
                    }
                }
            } else if (NBTUtils.getTagStringValue(event.getCursor(), "item_id").equalsIgnoreCase("BACKPACK_SKINS") && NBTUtils.getTagStringValue(event.getCurrentItem(), "item_id").equalsIgnoreCase("BACKPACKS")) {
                event.setCancelled(true);
                ItemStack backpack = event.getCurrentItem();
                ItemStack skinItem = event.getCursor();
                if (!NBTUtils.getTagStringValue(backpack, "backpack_skin").equalsIgnoreCase(NBTUtils.getTagStringValue(skinItem, "skin_id"))) {
                    player.setItemOnCursor(new ItemStack(Material.AIR));
                    event.setCurrentItem(ItemBuilder.skin(NBTUtils.getTagIntValue(backpack, "backpack_tier"), NBTUtils.getTagStringValue(skinItem, "skin_id"), NBTUtils.getTagStringValue(backpack, "backpack_uuid"), NBTUtils.getTagStringValue(backpack, "backpack_player")));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a你的背包造型已套用為" + skinItem.getItemMeta().getDisplayName() + "&a."));
                    player.playSound(player, Sound.BLOCK_ANVIL_USE, 1, 1);
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c你的背包已經是此造型了!"));
                    player.playSound(player, Sound.ENTITY_VILLAGER_NO, 1, 1);
                }

            }
        } else {
            if (NBTUtils.hasTag(event.getCurrentItem())) {
                if (!NBTUtils.getTagStringValue(event.getCurrentItem(), "item_id").equalsIgnoreCase("BACKPACKS")) return;
                if (event.getCursor().getType().toString().contains("DYE")) {
                    String dye = event.getCursor().getType().toString();
                    String color = dye.replace("_DYE", "");
                    FileConfiguration file = plugin.getTextureFile();
                    String Url = file.getString("Color." + color);
                    if (Url != null) {
                        event.setCancelled(true);
                        ItemStack backpack = event.getCurrentItem();
                        ItemStack dyeItem = event.getCursor();
                        if (!NBTUtils.getTagStringValue(backpack, "backpack_color").equalsIgnoreCase(color)) {
                            dyeItem.setAmount(dyeItem.getAmount() - 1);
                            player.setItemOnCursor(dyeItem);
                            event.setCurrentItem(ItemBuilder.dye(NBTUtils.getTagIntValue(backpack, "backpack_tier"), Url, NBTUtils.getTagStringValue(backpack, "backpack_uuid"), NBTUtils.getTagStringValue(backpack, "backpack_player"), color));
                            player.playSound(player, Sound.ITEM_BONE_MEAL_USE, 1, 0.1f);
                            player.playSound(player, Sound.ITEM_BONE_MEAL_USE, 1, 0.5f);
                            player.playSound(player, Sound.ITEM_BONE_MEAL_USE, 1, 1);
                        } else {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c無法將背包染成一樣顏色!"));
                            player.playSound(player, Sound.ENTITY_VILLAGER_NO, 1, 1);
                        }

                    }
                }
            }
        }
    }
}
