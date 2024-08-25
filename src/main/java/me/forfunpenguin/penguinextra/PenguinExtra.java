package me.forfunpenguin.penguinextra;

import me.forfunpenguin.penguinextra.Backpacks.ItemBuilder;
import me.forfunpenguin.penguinextra.Commands.Give;
import me.forfunpenguin.penguinextra.Commands.Reload;
import me.forfunpenguin.penguinextra.Listener.*;
import me.forfunpenguin.penguinextra.Market.Menu;
import me.forfunpenguin.penguinextra.Menu.BackpackShop;
import me.forfunpenguin.penguinextra.Menu.Changelog;
import me.forfunpenguin.penguinextra.Menu.CustomItems;
import me.forfunpenguin.penguinextra.Menu.FastMenu;
import org.bukkit.*;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public final class PenguinExtra extends JavaPlugin {

    private static PenguinExtra plugin;
    public List<Keyed> recipeKeyList;
    NamespacedKey key;
    private File TextureFile;
    private File MarketFile;
    private FileConfiguration Texture;
    private FileConfiguration Market;
    @Override
    public void onEnable() {
        plugin = this;
        key = NamespacedKey.fromString("backpack", this);
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&b[PenguinExtra] &a插件已啟用!"));

        createTextureFile();
        createMarketFile();
        getServer().getPluginManager().registerEvents(new FastMenu(), this);
        getServer().getPluginManager().registerEvents(new Changelog(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
        getServer().getPluginManager().registerEvents(new InventoryClose(), this);
        getServer().getPluginManager().registerEvents(new CustomItems(), this);
        getServer().getPluginManager().registerEvents(new InventoryClick(), this);
        getServer().getPluginManager().registerEvents(new HeadEquip(), this);
        getServer().getPluginManager().registerEvents(new UpgradeApply(), this);
        getServer().getPluginManager().registerEvents(new BackpackShop(), this);
        getServer().getPluginManager().registerEvents(new Menu(), this);

        plugin.getCommand("fastmenu").setExecutor(new FastMenu());
        plugin.getCommand("giveitem").setExecutor(new Give());
        plugin.getCommand("reload").setExecutor(new Reload());

        createRecipes();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static PenguinExtra getPlugin() {
        return plugin;
    }

    public static String getFolderPath() {
        return getPlugin().getDataFolder().getAbsolutePath();
    }

    public void reloadConfig() {
        getLogger().log(Level.INFO, "Reloading config file..");
        createRecipes();
        Texture = new YamlConfiguration();
        try {
            Texture.load(TextureFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        getLogger().log(Level.INFO, "Successful reloaded config file!");
    }

    public void createRecipes() {
        if (recipeKeyList != null) {
            recipeKeyList.forEach((x) -> {
                Bukkit.removeRecipe(x.getKey());
            });
            recipeKeyList.clear();
        }
        recipeKeyList = new ArrayList<>();

        ItemStack backpack_tier_1 = ItemBuilder.Backpack(1);
        ShapedRecipe rootBackpackRecipe_tier_1 = new ShapedRecipe(new NamespacedKey("backpack", "backpack_tier_1"), backpack_tier_1);
        rootBackpackRecipe_tier_1.shape("AAA", "BCB", "AAA");
        rootBackpackRecipe_tier_1.setIngredient('A', Material.LEATHER);
        rootBackpackRecipe_tier_1.setIngredient('B', Material.LEAD);
        rootBackpackRecipe_tier_1.setIngredient('C', Material.CHEST);

        ItemStack upgrade_tier_1 = ItemBuilder.tierUpgrade(1);
        ShapedRecipe rootBackpackRecipe_tier_2 = new ShapedRecipe(new NamespacedKey("backpack", "upgrade_tier_1"), upgrade_tier_1);
        rootBackpackRecipe_tier_2.shape("AAA", "BCB", "AAA");
        rootBackpackRecipe_tier_2.setIngredient('A', Material.LEATHER);
        rootBackpackRecipe_tier_2.setIngredient('B', Material.COAL_BLOCK);
        rootBackpackRecipe_tier_2.setIngredient('C', Material.CHEST);

        ItemStack upgrade_tier_2 = ItemBuilder.tierUpgrade(2);
        ShapedRecipe rootBackpackRecipe_tier_3 = new ShapedRecipe(new NamespacedKey("backpack", "upgrade_tier_2"), upgrade_tier_2);
        rootBackpackRecipe_tier_3.shape("AAA", "BCB", "AAA");
        rootBackpackRecipe_tier_3.setIngredient('A', Material.LEATHER);
        rootBackpackRecipe_tier_3.setIngredient('B', Material.IRON_BLOCK);
        rootBackpackRecipe_tier_3.setIngredient('C', Material.CHEST);

        ItemStack upgrade_tier_3 = ItemBuilder.tierUpgrade(3);
        ShapedRecipe rootBackpackRecipe_tier_4 = new ShapedRecipe(new NamespacedKey("backpack", "upgrade_tier_3"), upgrade_tier_3);
        rootBackpackRecipe_tier_4.shape("AAA", "BCB", "AAA");
        rootBackpackRecipe_tier_4.setIngredient('A', Material.LEATHER);
        rootBackpackRecipe_tier_4.setIngredient('B', Material.GOLD_BLOCK);
        rootBackpackRecipe_tier_4.setIngredient('C', Material.CHEST);

        ItemStack upgrade_tier_4 = ItemBuilder.tierUpgrade(4);
        ShapedRecipe rootBackpackRecipe_tier_5 = new ShapedRecipe(new NamespacedKey("backpack", "upgrade_tier_4"), upgrade_tier_4);
        rootBackpackRecipe_tier_5.shape("AAA", "BCB", "AAA");
        rootBackpackRecipe_tier_5.setIngredient('A', Material.LEATHER);
        rootBackpackRecipe_tier_5.setIngredient('B', Material.DIAMOND_BLOCK);
        rootBackpackRecipe_tier_5.setIngredient('C', Material.CHEST);

        ItemStack upgrade_tier_5 = ItemBuilder.tierUpgrade(5);
        ShapedRecipe rootBackpackRecipe_tier_6 = new ShapedRecipe(new NamespacedKey("backpack", "upgrade_tier_5"), upgrade_tier_5);
        rootBackpackRecipe_tier_6.shape("AAA", "BCB", "AAA");
        rootBackpackRecipe_tier_6.setIngredient('A', Material.LEATHER);
        rootBackpackRecipe_tier_6.setIngredient('B', Material.EMERALD_BLOCK);
        rootBackpackRecipe_tier_6.setIngredient('C', Material.CHEST);

        Bukkit.addRecipe(rootBackpackRecipe_tier_1);
        recipeKeyList.add(rootBackpackRecipe_tier_1);
        Bukkit.addRecipe(rootBackpackRecipe_tier_2);
        recipeKeyList.add(rootBackpackRecipe_tier_2);
        Bukkit.addRecipe(rootBackpackRecipe_tier_3);
        recipeKeyList.add(rootBackpackRecipe_tier_3);
        Bukkit.addRecipe(rootBackpackRecipe_tier_4);
        recipeKeyList.add(rootBackpackRecipe_tier_4);
        Bukkit.addRecipe(rootBackpackRecipe_tier_5);
        recipeKeyList.add(rootBackpackRecipe_tier_5);
        Bukkit.addRecipe(rootBackpackRecipe_tier_6);
        recipeKeyList.add(rootBackpackRecipe_tier_6);
    }

    public FileConfiguration getTextureFile() {
        return this.Texture;
    }

    private void createTextureFile() {
        TextureFile = new File(getDataFolder(), "Texture.yml");
        if (!TextureFile.exists()) {
            TextureFile.getParentFile().mkdirs();
            saveResource("Texture.yml", false);
        }

        Texture = new YamlConfiguration();
        try {
            Texture.load(TextureFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        /* User Edit:
            Instead of the above Try/Catch, you can also use
            YamlConfiguration.loadConfiguration(customConfigFile)
        */
    }

    public FileConfiguration getMarketFile() {
        return this.Market;
    }
    private void createMarketFile() {
        MarketFile = new File(getDataFolder() + "/Market/", "Item_Data.yml");
        if (!MarketFile.exists()) {
            MarketFile.getParentFile().mkdirs();
            saveResource("Market/Item_Data.yml", false);
        }

        Market = new YamlConfiguration();
        try {
            Market.load(MarketFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        /* User Edit:
            Instead of the above Try/Catch, you can also use
            YamlConfiguration.loadConfiguration(customConfigFile)
        */
    }
}
