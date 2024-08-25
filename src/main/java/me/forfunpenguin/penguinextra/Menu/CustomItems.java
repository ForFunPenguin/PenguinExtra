package me.forfunpenguin.penguinextra.Menu;

import me.forfunpenguin.penguinextra.Backpacks.ItemBuilder;
import me.forfunpenguin.penguinextra.PenguinExtra;
import me.forfunpenguin.penguinextra.Utils.ItemUtils;
import me.forfunpenguin.penguinextra.Utils.NBTUtils;
import org.apache.commons.lang.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class CustomItems implements Listener {
    private static String customitems = "物品配方";
    private static String recipe = "配方";

    public static void openCustomItems(Player player) {
        Inventory inv = Bukkit.createInventory(player, 9*6, customitems);
        inv.setItem(0, ItemBuilder.Backpack(1));
        inv.setItem(1, ItemBuilder.tierUpgrade(1));
        inv.setItem(2, ItemBuilder.tierUpgrade(2));
        inv.setItem(3, ItemBuilder.tierUpgrade(3));
        inv.setItem(4, ItemBuilder.tierUpgrade(4));
        inv.setItem(5, ItemBuilder.tierUpgrade(5));
        inv.setItem(49, ItemUtils.getItem(new ItemStack(Material.ARROW), "&a返回", "&7返回至快速選單"));
        player.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals(customitems)) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            if (event.getSlot() == 49) {
                player.closeInventory();
                player.performCommand("fm");
            } else {
                if (event.getCurrentItem() == null) return;
                if (event.getClickedInventory().getType() == InventoryType.PLAYER) return;
                if (event.getCurrentItem().getType() != Material.AIR) {
                    if (event.getSlot() == 0) {
                        viewRecipe(player, "backpack_tier_1");
                    } else if (event.getSlot() == 1) {
                        viewRecipe(player, "upgrade_tier_1");
                    } else if (event.getSlot() == 2) {
                        viewRecipe(player, "upgrade_tier_2");
                    } else if (event.getSlot() == 3) {
                        viewRecipe(player, "upgrade_tier_3");
                    } else if (event.getSlot() == 4) {
                        viewRecipe(player, "upgrade_tier_4");
                    } else if (event.getSlot() == 5) {
                        viewRecipe(player, "upgrade_tier_5");
                    }
                }
            }
        } else if (event.getView().getTitle().equals(recipe)) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            if (event.getSlot() == 49) {
                player.closeInventory();
                openCustomItems(player);
            }
        }
    }

    public void viewRecipe(Player player, String recipeID) {
        Inventory inv = Bukkit.createInventory(player, 9*6, recipe);
        int[] airList = { 10, 11, 12, 19, 20, 21, 23, 25, 28, 29, 30 };
        for (int i=0; i<54; i++) {
            if (!ArrayUtils.contains( airList, i )) {
                inv.setItem(i, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
            } else {
                if (i == 23) {
                    inv.setItem(i, new ItemStack(Material.CRAFTING_TABLE));
                }
            }
        }
        switch (recipeID) {
            case "backpack_tier_1":
                inv.setItem(10, new ItemStack(Material.LEATHER));
                inv.setItem(11, new ItemStack(Material.LEATHER));
                inv.setItem(12, new ItemStack(Material.LEATHER));
                inv.setItem(19, new ItemStack(Material.LEAD));
                inv.setItem(20, new ItemStack(Material.CHEST));
                inv.setItem(21, new ItemStack(Material.LEAD));
                inv.setItem(28, new ItemStack(Material.LEATHER));
                inv.setItem(29, new ItemStack(Material.LEATHER));
                inv.setItem(30, new ItemStack(Material.LEATHER));
                inv.setItem(25, ItemBuilder.Backpack(1));
                break;
            case "upgrade_tier_1":
                inv.setItem(10, new ItemStack(Material.LEATHER));
                inv.setItem(11, new ItemStack(Material.LEATHER));
                inv.setItem(12, new ItemStack(Material.LEATHER));
                inv.setItem(19, new ItemStack(Material.COAL_BLOCK));
                inv.setItem(20, new ItemStack(Material.CHEST));
                inv.setItem(21, new ItemStack(Material.COAL_BLOCK));
                inv.setItem(28, new ItemStack(Material.LEATHER));
                inv.setItem(29, new ItemStack(Material.LEATHER));
                inv.setItem(30, new ItemStack(Material.LEATHER));
                inv.setItem(25, ItemBuilder.tierUpgrade(1));
                break;
            case "upgrade_tier_2":
                inv.setItem(10, new ItemStack(Material.LEATHER));
                inv.setItem(11, new ItemStack(Material.LEATHER));
                inv.setItem(12, new ItemStack(Material.LEATHER));
                inv.setItem(19, new ItemStack(Material.IRON_BLOCK));
                inv.setItem(20, new ItemStack(Material.CHEST));
                inv.setItem(21, new ItemStack(Material.IRON_BLOCK));
                inv.setItem(28, new ItemStack(Material.LEATHER));
                inv.setItem(29, new ItemStack(Material.LEATHER));
                inv.setItem(30, new ItemStack(Material.LEATHER));
                inv.setItem(25, ItemBuilder.tierUpgrade(2));
                break;
            case "upgrade_tier_3":
                inv.setItem(10, new ItemStack(Material.LEATHER));
                inv.setItem(11, new ItemStack(Material.LEATHER));
                inv.setItem(12, new ItemStack(Material.LEATHER));
                inv.setItem(19, new ItemStack(Material.GOLD_BLOCK));
                inv.setItem(20, new ItemStack(Material.CHEST));
                inv.setItem(21, new ItemStack(Material.GOLD_BLOCK));
                inv.setItem(28, new ItemStack(Material.LEATHER));
                inv.setItem(29, new ItemStack(Material.LEATHER));
                inv.setItem(30, new ItemStack(Material.LEATHER));
                inv.setItem(25, ItemBuilder.tierUpgrade(3));
                break;
            case "upgrade_tier_4":
                inv.setItem(10, new ItemStack(Material.LEATHER));
                inv.setItem(11, new ItemStack(Material.LEATHER));
                inv.setItem(12, new ItemStack(Material.LEATHER));
                inv.setItem(19, new ItemStack(Material.DIAMOND_BLOCK));
                inv.setItem(20, new ItemStack(Material.CHEST));
                inv.setItem(21, new ItemStack(Material.DIAMOND_BLOCK));
                inv.setItem(28, new ItemStack(Material.LEATHER));
                inv.setItem(29, new ItemStack(Material.LEATHER));
                inv.setItem(30, new ItemStack(Material.LEATHER));
                inv.setItem(25, ItemBuilder.tierUpgrade(4));
                break;
            case "upgrade_tier_5":
                inv.setItem(10, new ItemStack(Material.LEATHER));
                inv.setItem(11, new ItemStack(Material.LEATHER));
                inv.setItem(12, new ItemStack(Material.LEATHER));
                inv.setItem(19, new ItemStack(Material.EMERALD_BLOCK));
                inv.setItem(20, new ItemStack(Material.CHEST));
                inv.setItem(21, new ItemStack(Material.EMERALD_BLOCK));
                inv.setItem(28, new ItemStack(Material.LEATHER));
                inv.setItem(29, new ItemStack(Material.LEATHER));
                inv.setItem(30, new ItemStack(Material.LEATHER));
                inv.setItem(25, ItemBuilder.tierUpgrade(5));
                break;
        }
        inv.setItem(49, ItemUtils.getItem(new ItemStack(Material.ARROW), "&a返回", "&7返回至物品配方"));
        player.openInventory(inv);
    }
}
