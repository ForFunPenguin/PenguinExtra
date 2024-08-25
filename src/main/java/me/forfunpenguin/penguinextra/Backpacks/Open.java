package me.forfunpenguin.penguinextra.Backpacks;

import me.forfunpenguin.penguinextra.PenguinExtra;
import me.forfunpenguin.penguinextra.Utils.ItemUtils;
import me.forfunpenguin.penguinextra.Utils.NBTUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Open {

    private static String Backpack_Title = "背包";
    private static String path = PenguinExtra.getFolderPath() + "/Backpacks/";
    public static void openBackpack(Player player) {
        ItemStack backpack = player.getInventory().getItemInMainHand();
        int Tier = NBTUtils.getTagIntValue(backpack, "backpack_tier");
        String UUID = NBTUtils.getTagStringValue(backpack, "backpack_uuid");
        String backpack_player = NBTUtils.getTagStringValue(backpack, "backpack_player");
        //player.sendMessage(ChatColor.translateAlternateColorCodes('&', "Tier:  " + Tier + ", UUID: " + UUID));
        loadBackpack(player, Tier, UUID, backpack_player);

    }

    public static void loadBackpack(Player player, int Tier, String UUID, String backpack_player) {
        Inventory inv = Bukkit.createInventory(player, 9 * Tier, Backpack_Title);
        if (UUID == "") {
            ItemStack backpack = NBTUtils.putStringTag(player.getInventory().getItemInMainHand(), "backpack_uuid", java.util.UUID.randomUUID().toString());
            backpack = NBTUtils.putStringTag(backpack, "backpack_player", player.getUniqueId().toString());
            player.getInventory().setItemInMainHand(backpack);

        } else {
            File f = new File(path + "/" + backpack_player + "/", UUID + ".yml");
            FileConfiguration c = YamlConfiguration.loadConfiguration(f);
            //ItemStack[] list = (ItemStack[]) c.get("inventory.content");
            final ItemStack[] itemstacks = Objects.requireNonNull(c.getList("inventory.content")).stream().map(o -> (ItemStack) o).toArray(ItemStack[]::new);
            inv.setContents(itemstacks);
        }
        player.openInventory(inv);
    }
}
