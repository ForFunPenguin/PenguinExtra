package me.forfunpenguin.penguinextra.Menu;

import me.forfunpenguin.penguinextra.Market.Menu;
import me.forfunpenguin.penguinextra.Utils.ItemUtils;
import me.forfunpenguin.penguinextra.Utils.NBTUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class FastMenu implements CommandExecutor, Listener {

    private static String Shop = "快速選單";

    // /give @p minecraft:player_head{display:{Name:"{\"text\":\"Football\"}"},SkullOwner:{Id:[I;1129053990,821841785,-2068817992,2133808012],Properties:{textures:[{Value:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2YzMzhkNjZkNTM4ZWYyNzRlNjU2ODY3MTdjOTk0ZDRhNTFmNjU4MWIxMjQxOTE2MjI2YmMzNWFiY2MxNGEzZiJ9fX0="}]}}} 1
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        Inventory inv = Bukkit.createInventory(player, 9*1, Shop);
        inv.setItem(0, ItemUtils.getItem(new ItemStack(Material.CHEST), "&a市場", "&e點擊開啟!"));
        ItemStack itemStack = ItemUtils.getItem(new ItemStack(ItemUtils.getHead("c4b3a208b25f6c6cf67f3452df37504d658900bb4d7243c9704eeded120e263e")), "&a背包商店", "&e點擊開啟!");
        inv.setItem(1, NBTUtils.putIntTag(itemStack, "HideFlags", 32));
        inv.setItem(2, ItemUtils.getItem(new ItemStack(Material.DIAMOND_PICKAXE), "&a採礦選單", "&8/mine menu", "", "&e點擊開啟!"));
        inv.setItem(3, ItemUtils.getItem(new ItemStack(Material.FISHING_ROD), "&a釣魚選單", "&8/fish menu", "", "&e點擊開啟!"));
        inv.setItem(4, ItemUtils.getItem(new ItemStack(ItemUtils.getHead("c3d336cba3c3bf424bfbf1caa01aadc8afb9068b199ba02eab1d8dd62f3fb6")), "&a市集", "&8/bazaar", "", "&e點擊開啟!"));
        inv.setItem(5, ItemUtils.getItem(new ItemStack(Material.RED_BED), "&a回家", "&8/home", "", "&e點擊回家!"));

        inv.setItem(7, ItemUtils.getItem(new ItemStack(Material.CRAFTING_TABLE), "&a物品配方",   "", "&e點擊查看!"));
        inv.setItem(8, ItemUtils.getItem(new ItemStack(Material.BOOKSHELF), "&a更新日誌",   "", "&e點擊查看!"));

        player.openInventory(inv);
        return true;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals(Shop)) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            if (event.getClickedInventory().getType() == InventoryType.PLAYER) return;
            if (event.getSlot() == 0) {
                player.closeInventory();
                Menu.openMarketMain(player);
            }
            else if (event.getSlot() == 1) {
                player.closeInventory();
                BackpackShop.openChangelog(player);
            }
            else if (event.getSlot() == 2) {
                player.closeInventory();
                player.performCommand("mine menu");
            }
            else if (event.getSlot() == 3) {
                player.closeInventory();
                player.performCommand("fish menu");
            }
            else if (event.getSlot() == 4) {
                player.closeInventory();
                player.performCommand("bazaar");
            } else if (event.getSlot() == 5) {
                player.closeInventory();
                player.performCommand("home");
            }else if (event.getSlot() == 7) {
                player.closeInventory();
                CustomItems.openCustomItems(player);
            } else if (event.getSlot() == 8) {
                player.closeInventory();
                Changelog.openChangelog(player);
            }

        }
    }
}
