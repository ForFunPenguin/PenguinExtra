package me.forfunpenguin.penguinextra.Commands;

import me.forfunpenguin.penguinextra.Backpacks.ItemBuilder;
import me.forfunpenguin.penguinextra.PenguinExtra;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Reload implements CommandExecutor {
    PenguinExtra plugin = PenguinExtra.getPlugin();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c你沒有足夠的權限!"));
            return false;
        }
        plugin.reloadConfig();
        return true;
    }
}
