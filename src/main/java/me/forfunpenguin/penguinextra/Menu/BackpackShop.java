package me.forfunpenguin.penguinextra.Menu;

import me.forfunpenguin.penguinextra.Backpacks.ItemBuilder;
import me.forfunpenguin.penguinextra.PenguinExtra;
import me.forfunpenguin.penguinextra.Utils.ItemUtils;
import me.forfunpenguin.penguinextra.Utils.NBTUtils;
import me.hsgamer.bettereconomy.BetterEconomy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public class BackpackShop implements Listener {
    private static String shop = "背包商店";
    static PenguinExtra plugin = PenguinExtra.getPlugin();

    public static void openChangelog(Player player) {
        Inventory inv = Bukkit.createInventory(player, 9*6, shop);
        FileConfiguration file = plugin.getTextureFile();
        int slot = 0;
        for(String skin_id : file.getConfigurationSection("Skins").getKeys(false)){
            //player.sendMessage(skin_id);
            ItemStack skin = ItemUtils.getItem(new ItemStack(ItemUtils.getHead(file.getString("Skins." + skin_id + ".Url"))), file.getString("Skins." + skin_id + ".Name"), "&7可將背包外觀套用成此造型.", "", "&7價格: &6" + file.getString("Skins." + skin_id + ".Price"), "", "&e點擊購買!");
            skin = NBTUtils.putStringTag(skin, "skin_id", skin_id);
            inv.setItem(slot, skin);
            slot++;
        }
        inv.setItem(49, ItemUtils.getItem(new ItemStack(Material.ARROW), "&a返回", "&7返回至快速選單"));
        player.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals(shop)) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            if (event.getSlot() == 49) {
                player.closeInventory();
                player.performCommand("fm");
            } else {
                if (event.getCurrentItem() == null) return;
                if (event.getClickedInventory().getType() == InventoryType.PLAYER) return;
                if (event.getCurrentItem().getType() == Material.AIR) return;
                if (NBTUtils.getTagStringValue(event.getCurrentItem(), "skin_id") != null) {
                    String skin_id = NBTUtils.getTagStringValue(event.getCurrentItem(), "skin_id");
                    FileConfiguration file = plugin.getTextureFile();
                    String Name = file.getString("Skins." + skin_id + ".Name");
                    String Url = file.getString("Skins." + skin_id + ".Url");
                    int Price = file.getInt("Skins." + skin_id + ".Price");
                    BetterEconomy betterEconomy = (BetterEconomy) Bukkit.getServer().getPluginManager().getPlugin("BetterEconomy");
                    Double player_balance = betterEconomy.getEconomyHandler().get(player.getUniqueId());
                    if (player_balance >= Price) {
                        betterEconomy.getEconomyHandler().withdraw(player.getUniqueId(), Price);
                        ItemStack skin = ItemBuilder.item_skin(skin_id);
                        player.getInventory().addItem(skin);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a你用" + Price + "&a個硬幣購買了" + Name + "&a."));
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 1, 2);
                    } else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c你沒有足夠的硬幣購買此物品. 你還需要" + (int) Math.ceil(Price - player_balance) + "&c個硬幣"));
                        player.playSound(player, Sound.ENTITY_VILLAGER_NO, 1, 1);
                    }

                }
            }
        }
    }
}
