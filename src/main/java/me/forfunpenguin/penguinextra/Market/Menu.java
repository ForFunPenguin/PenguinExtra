package me.forfunpenguin.penguinextra.Market;

import me.forfunpenguin.penguinextra.PenguinExtra;
import me.forfunpenguin.penguinextra.Utils.ItemUtils;
import me.forfunpenguin.penguinextra.Utils.NBTUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.File;

public class Menu implements Listener {
    private static String market = "市場";
    static PenguinExtra plugin = PenguinExtra.getPlugin();

    public static void openMarketMain(Player player) {
        Inventory inv = Bukkit.createInventory(player, 9*6, market);
        FileConfiguration file = plugin.getMarketFile();
        for(String type : file.getKeys(false)){
            ItemStack item = ItemUtils.getItem(new ItemStack(Material.valueOf(file.getString(type + ".Material"))), ChatColor.translateAlternateColorCodes('&', file.getString(type + ".Name")), "&e點擊進入!");
            item = NBTUtils.putStringTag(item, "type", type);
            inv.setItem(file.getInt(type + ".Slot"), item);
        }
        inv.setItem(49, ItemUtils.getItem(new ItemStack(Material.ARROW), "&a返回", "&7返回至快速選單"));
        inv.setItem(53, ItemUtils.getItem(new ItemStack(Material.CHEST_MINECART), "&a快速販賣", "&7此處可以快速販賣你的物品.", "", "&e點擊開啟!"));
        player.openInventory(inv);
    }

    public static void openMarket(Player player, String type) {
        FileConfiguration file = plugin.getMarketFile();
        File MarketDataFile = new File(plugin.getDataFolder() + "/Market/", "Market_Data.yml");
        Inventory inv = Bukkit.createInventory(player, 9*6, market + " -> " + ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', file.getString(type + ".Name"))));
        int slot = 0;
        if (MarketDataFile.exists()) {
            for(String item : file.getConfigurationSection(type + ".Items").getKeys(false)){
                String section = type + ".Items." + item;
                ItemStack itemStack;
                itemStack = ItemUtils.getItem(new ItemStack(Material.valueOf(item)), null, "&f購買價格: &cN/A", "&f販賣價格: &cN/A", "&f庫存: &a0 / " + file.getInt(section + ".Init_Amount"), "", "&e點擊開啟交易介面!");
                inv.setItem(slot, itemStack);
                slot++;
            }
        } else {
            for(String item : file.getConfigurationSection(type + ".Items").getKeys(false)){
                String section = type + ".Items." + item;
                ItemStack itemStack;
                itemStack = ItemUtils.getItem(new ItemStack(Material.valueOf(item)), null, "&f購買價格: &6" + file.getDouble(section + ".Price"), "&f販賣價格: &6" + file.getDouble(section + ".Price") * 0.985, "&f庫存: &a" + getAmountString(file.getInt(section + ".Init_Amount"), file.getInt(section + ".Init_Amount")), "", "&e點擊開啟交易介面!");
                inv.setItem(slot, itemStack);
                slot++;
            }
        }
        inv.setItem(49, ItemUtils.getItem(new ItemStack(Material.ARROW), "&a返回", "&7返回至快速選單"));
        inv.setItem(53, ItemUtils.getItem(new ItemStack(Material.CHEST_MINECART), "&a快速販賣", "&7此處可以快速販賣你的物品.", "", "&e點擊開啟!"));
        player.openInventory(inv);
    }

    public static void openMarketTrade(Player player, String type) {

    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals(market)) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            if (event.getSlot() == 49) {
                player.closeInventory();
                player.performCommand("fm");
            } else {
                if (event.getCurrentItem() == null) return;
                if (NBTUtils.hasTag(event.getCurrentItem())) {
                    if (NBTUtils.getTagStringValue(event.getCurrentItem(), "type") == null) return;
                    openMarket(player, NBTUtils.getTagStringValue(event.getCurrentItem(), "type"));
                }
            }
        }
    }

    private static String getAmountString(int init_amount, int current_amount) {
        if (current_amount >= init_amount) {
            return "&a" + init_amount + "&7/&a" + current_amount;
        } else {
            if (current_amount < init_amount / 2) {
                return "&c" + init_amount + "&7/&a" + current_amount;
            } else {
                return "&e" + init_amount + "&7/&a" + current_amount;
            }
        }
    }

}
