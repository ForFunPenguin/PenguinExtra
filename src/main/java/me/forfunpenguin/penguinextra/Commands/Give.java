package me.forfunpenguin.penguinextra.Commands;

import me.forfunpenguin.penguinextra.Backpacks.ItemBuilder;
import me.forfunpenguin.penguinextra.Utils.ItemUtils;
import me.forfunpenguin.penguinextra.Utils.NBTUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Give implements TabExecutor {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 1) {
            List<String> temp = new ArrayList<String>(Arrays.asList(
                    "backback_tier_1"));
            return temp;
        }
        return null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if (sender.isOp()) {
            switch (args[0]) {
                case "backback_tier_1":
                    player.getInventory().addItem(ItemBuilder.Backpack(1));
                    return true;
            }
        }
        return true;
    }
}
