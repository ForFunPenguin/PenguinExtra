package me.forfunpenguin.penguinextra.Backpacks;

import me.forfunpenguin.penguinextra.PenguinExtra;
import me.forfunpenguin.penguinextra.Utils.ItemUtils;
import me.forfunpenguin.penguinextra.Utils.NBTUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ItemBuilder {
    static PenguinExtra plugin = PenguinExtra.getPlugin();
    public static ItemStack Backpack(int Tier) {
        if (Tier > 0 && Tier <= 6) {
            int slot = 9 * Tier;
            ItemStack backpack = ItemUtils.getItem(ItemUtils.getHead("8351e505989838e27287e7afbc7f97e796cab5f3598a76160c131c940d0c5"), "&f背包", "&7欄位: &f" + slot);
            backpack = NBTUtils.putIntTag(backpack, "backpack_tier", Tier);
            //backpack = NBTUtils.putStringTag(backpack, "backpack_uuid", UUID.randomUUID().toString());
            backpack = NBTUtils.putStringTag(backpack, "item_id", "BACKPACKS");
            backpack = NBTUtils.putStringTag(backpack, "backpack_color", "BROWN");
            return backpack;
        } else {
            return null;
        }
    }

    public static ItemStack tierUpgrade(int Tier) {
        if (Tier > 0 && Tier <= 5) {
            if (Tier == 1) {
                ItemStack backpack = ItemUtils.getItem(ItemUtils.getHead("7658ccc7345559e9321f49ee1af67522e708dca8932b0a721cc34130731eb598"), "&f背包升級 (Tier2)", "&7可將背包欄位擴增至18格.", "", "&8在背包中將升級拖放至背包即可升級.");
                backpack = NBTUtils.putIntTag(backpack, "upgrade_slot", 9 + 9 * Tier);
                backpack = NBTUtils.putStringTag(backpack, "item_id", "BACKPACK_UPGRADE");
                return backpack;
            } else if (Tier == 2) {
                ItemStack backpack = ItemUtils.getItem(ItemUtils.getHead("24b953b2c0e952574f1ed29c81e82e53bcdb1ba683259c20daeef7d554a2a798"), "&f背包升級 (Tier3)", "&7可將背包欄位擴增至27格.", "", "&8在背包中將升級拖放至背包即可升級.");
                backpack = NBTUtils.putIntTag(backpack, "upgrade_slot", 9 + 9 * Tier);
                backpack = NBTUtils.putStringTag(backpack, "item_id", "BACKPACK_UPGRADE");
                return backpack;
            } else if (Tier == 3) {
                ItemStack backpack = ItemUtils.getItem(ItemUtils.getHead("844498a0fe278956e3d04135ef4b1343d0548a7e208c61b1fb6f3b4dbc240da8"), "&f背包升級 (Tier4)", "&7可將背包欄位擴增至36格.", "", "&8在背包中將升級拖放至背包即可升級.");
                backpack = NBTUtils.putIntTag(backpack, "upgrade_slot", 9 + 9 * Tier);
                backpack = NBTUtils.putStringTag(backpack, "item_id", "BACKPACK_UPGRADE");
                return backpack;
            } else if (Tier == 4) {
                ItemStack backpack = ItemUtils.getItem(ItemUtils.getHead("31f7cdfea2d21cd5f6ebbf48481761c6cbdf36d00fe64083686e9aeaa3f1f217"), "&f背包升級 (Tier5)", "&7可將背包欄位擴增至45格.", "", "&8在背包中將升級拖放至背包即可升級.");
                backpack = NBTUtils.putIntTag(backpack, "upgrade_slot", 9 + 9 * Tier);
                backpack = NBTUtils.putStringTag(backpack, "item_id", "BACKPACK_UPGRADE");
                return backpack;
            } else if (Tier == 5) {
                ItemStack backpack = ItemUtils.getItem(ItemUtils.getHead("4ba55671f97ff3bfc5be335ae92cd9749abd619e7afc2a6673597b80b755c741"), "&f背包升級 (Tier6)", "&7可將背包欄位擴增至54格.", "", "&8在背包中將升級拖放至背包即可升級.");
                backpack = NBTUtils.putIntTag(backpack, "upgrade_slot", 9 + 9 * Tier);
                backpack = NBTUtils.putStringTag(backpack, "item_id", "BACKPACK_UPGRADE");
                return backpack;
            }
            return null;
        } else {
            return null;
        }
    }

    public static ItemStack setLore(ItemStack backpack, int tier) {
        ItemStack output = backpack;
        ItemMeta backpack_itemmeta = backpack.getItemMeta();
        if (!NBTUtils.getTagStringValue(backpack, "backpack_skin").equalsIgnoreCase("")) {
            String[] lore = { "&7欄位: &f" + tier * 9 , "", backpack.getItemMeta().getLore().get(2)};
            List<String> lores = new ArrayList<>();
            for (String s : lore) {
                lores.add(ChatColor.translateAlternateColorCodes('&', s));
            }
            backpack_itemmeta.setLore(lores);
            output.setItemMeta(backpack_itemmeta);
            return output;
        } else {
            String[] lore = { "&7欄位: &f" + tier * 9 };
            List<String> lores = new ArrayList<>();
            for (String s : lore) {
                lores.add(ChatColor.translateAlternateColorCodes('&', s));
            }
            backpack_itemmeta.setLore(lores);
            output.setItemMeta(backpack_itemmeta);
            return output;
        }
    }

    public static ItemStack dye(int backpack_tier, String url, String UUID, String backpack_player, String Color) {
        ItemStack output = ItemUtils.getItem(ItemUtils.getHead(url), "&f背包", "&7欄位: &f" + backpack_tier * 9);
        output = NBTUtils.putIntTag(output, "backpack_tier", backpack_tier);
        output = NBTUtils.putStringTag(output, "item_id", "BACKPACKS");
        output = NBTUtils.putStringTag(output, "backpack_player", backpack_player);
        output = NBTUtils.putStringTag(output, "backpack_uuid", UUID);
        output = NBTUtils.putStringTag(output, "backpack_color", Color);
        return output;
    }

    public static ItemStack item_skin(String skin_id) {
        FileConfiguration file = plugin.getTextureFile();
        String Name = file.getString("Skins." + skin_id + ".Name");
        String Url = file.getString("Skins." + skin_id + ".Url");
        ItemStack output = ItemUtils.getItem(ItemUtils.getHead(Url), Name, "&7可將背包外觀套用成此造型.", "", "&8在背包中將造型拖放至背包即可使用.");
        output = NBTUtils.putStringTag(output, "item_id", "BACKPACK_SKINS");
        output = NBTUtils.putStringTag(output, "item_uuid", UUID.randomUUID().toString()); //純粹防堆疊
        output = NBTUtils.putStringTag(output, "skin_id", skin_id);
        return output;
    }

    public static ItemStack skin(int backpack_tier, String skin_id, String UUID, String backpack_player) {
        FileConfiguration file = plugin.getTextureFile();
        String Name = file.getString("Skins." + skin_id + ".Name");
        String Url = file.getString("Skins." + skin_id + ".Url");
        ItemStack output = ItemUtils.getItem(ItemUtils.getHead(Url), "&f背包", "&7欄位: &f" + backpack_tier * 9, "", "&d ♦ " + Name);
        output = NBTUtils.putIntTag(output, "backpack_tier", backpack_tier);
        output = NBTUtils.putStringTag(output, "item_id", "BACKPACKS");
        output = NBTUtils.putStringTag(output, "backpack_player", backpack_player);
        output = NBTUtils.putStringTag(output, "backpack_uuid", UUID);
        output = NBTUtils.putStringTag(output, "backpack_skin", skin_id);
        return output;
    }
}
