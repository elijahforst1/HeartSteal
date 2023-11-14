package me.elijahforst.heartsteal;

import me.elijahforst.heartsteal.commands.*;
import me.elijahforst.heartsteal.listeners.*;
import me.elijahforst.heartsteal.managers.CombatTagManager;
import me.elijahforst.heartsteal.utility.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public final class HeartSteal extends JavaPlugin implements Listener {
    private static HeartSteal instance;

    public static HeartSteal getInstance() {
        if (instance == null) {
            return (instance = HeartSteal.getPlugin(HeartSteal.class));
        }

        return instance;
    }

    @Override
    public void onEnable() {
        // Write Default Config
        getConfig().set("heart_loss", getConfig().getBoolean("heart_loss", true));

        // Plugin startup logic
        System.out.println("HeartSteal started");

        CombatTagManager combatTagManager = new CombatTagManager(this);
        combatTagManager.registerScheduler();
        combatTagManager.registerInteractions();

        getServer().getPluginManager().registerEvents(new PlayerDeathListener(),this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(),this);
        getServer().getPluginManager().registerEvents(new PlayerRightClickListener(),this);
        getServer().getPluginManager().registerEvents(new BlockPlaceListener(),this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(),this);
        getServer().getPluginManager().registerEvents(new BlockExplodeListener(),this);

        getCommand("givehearts").setExecutor(new GiveHeartsCommand());
        getCommand("takehearts").setExecutor(new TakeHeartsCommand());
        getCommand("giveheartitem").setExecutor(new GiveHeartItemCommand());
        getCommand("toggleheartloss").setExecutor(new HeartLossToggleCommand());
        getCommand("withdrawhearts").setExecutor(new WithdrawHeartsCommand());

        ShapedRecipe respawnBeacon = new ShapedRecipe(new NamespacedKey(this, "respawnBeacon"), Util.getRespawnItem());
        respawnBeacon.shape("XXX","XYX","DDD");
        respawnBeacon.setIngredient('X', new RecipeChoice.ExactChoice(Util.getHeartItem()));
        respawnBeacon.setIngredient('Y',Material.NETHERITE_INGOT);
        respawnBeacon.setIngredient('D',Material.DIAMOND);

        Bukkit.addRecipe(respawnBeacon);
    }


    @Override
    public void onDisable() {
        // Save Config
        saveConfig();

        // Plugin shutdown logic
        System.out.println("HeartSteal off");
    }
}
