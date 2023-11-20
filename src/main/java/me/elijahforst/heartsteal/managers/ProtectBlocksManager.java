package me.elijahforst.heartsteal.managers;

import com.jeff_media.customblockdata.CustomBlockData;
import me.elijahforst.heartsteal.HeartSteal;
import me.elijahforst.heartsteal.listeners.BlockBreakListener;
import me.elijahforst.heartsteal.listeners.BlockExplodeListener;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class ProtectBlocksManager {


    private static String PDC_KEY_IS_PROTECTED = "is_protected";
    public HeartSteal plugin;

    public ProtectBlocksManager(HeartSteal plugin) {
        this.plugin = plugin;
    }

    public void registerInteractions() {
        plugin.getServer().getPluginManager().registerEvents(new BlockExplodeListener(this), plugin);
        plugin.getServer().getPluginManager().registerEvents(new BlockBreakListener(this), plugin);
    }

    public void protectBlock(Block block, boolean protect) {
        NamespacedKey key = new NamespacedKey(HeartSteal.getInstance(), PDC_KEY_IS_PROTECTED);

        PersistentDataContainer customBlockData = new CustomBlockData(block, HeartSteal.getInstance());
        customBlockData.set(key, PersistentDataType.BOOLEAN, protect);
    }

    public static boolean getProtected(Block block) {
        NamespacedKey key = new NamespacedKey(HeartSteal.getInstance(), PDC_KEY_IS_PROTECTED);

        PersistentDataContainer customBlockData = new CustomBlockData(block, HeartSteal.getInstance());
        return customBlockData.getOrDefault(key, PersistentDataType.BOOLEAN, false);
    }
}
