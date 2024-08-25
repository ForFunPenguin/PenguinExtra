package me.forfunpenguin.penguinextra.Menu;

import me.forfunpenguin.penguinextra.Utils.ItemUtils;
import me.forfunpenguin.penguinextra.Utils.NBTUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Changelog implements Listener {

    private static String changelog = "更新日誌";

    public static void openChangelog(Player player) {
        Inventory inv = Bukkit.createInventory(player, 9*6, changelog);
        inv.setItem(0, ItemUtils.getItem(new ItemStack(Material.PAPER), "&a20231107", "&7伺服器開放"));
        inv.setItem(1, ItemUtils.getItem(new ItemStack(Material.PAPER), "&a20231108", "&7新增墓碑插件"));
        inv.setItem(2, ItemUtils.getItem(new ItemStack(Material.PAPER), "&a20231110", "&7新增回家插件", "&7新增砍樹插件", "&7替換經濟插件 (防止硬幣儲存異常)", "&7修復文物鑑定時間"));
        inv.setItem(3, ItemUtils.getItem(new ItemStack(Material.PAPER), "&a20231111", "&7新增背包"));
        inv.setItem(4, ItemUtils.getItem(new ItemStack(Material.PAPER), "&a20231112", "&7新增模型引擎插件 (部分精英怪可以套用模型了)", "&7新增背包升級", "&7修復背包部分BUG (優化判斷式, 現在應該接近完美, 背包造型準備中)"));
        inv.setItem(5, ItemUtils.getItem(new ItemStack(Material.PAPER), "&a20231113", "&7新增墓穴, 海盜船, 黑暗大教堂", "&7新增哥布林模型, 100個新事件", "&710個挑戰聖殿 (精英物品附魔時有機率觸發)", "&7新增101個Boss建築", "&7新增107個冒險建築", "&7新增122個小型建築"));
        inv.setItem(6, ItemUtils.getItem(new ItemStack(Material.PAPER), "&a20231114", "&7新增背包染色"));
        inv.setItem(7, ItemUtils.getItem(new ItemStack(Material.PAPER), "&a20231115", "&7新增Craftenmine的實驗室", "&7新增入侵", "&7新增冰雪宮殿", "&7新增飛空艇"));
        inv.setItem(8, ItemUtils.getItem(new ItemStack(Material.PAPER), "&a20231117", "&7新增鐵聯盟競技場", "&7新增綠洲"));
        inv.setItem(9, ItemUtils.getItem(new ItemStack(Material.PAPER), "&a20231118", "&7新增背包商店&背包造型"));
        inv.setItem(49, ItemUtils.getItem(new ItemStack(Material.ARROW), "&a返回", "&7返回至快速選單"));
        player.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals(changelog)) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            if (event.getSlot() == 49) {
                player.closeInventory();
                player.performCommand("fm");
            }
        }
    }
}
