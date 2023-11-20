package me.elijahforst.heartsteal.utility;

import com.jeff_media.customblockdata.CustomBlockData;
import me.elijahforst.heartsteal.HeartSteal;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public class Util {
    private static String PDC_KEY_IS_HEART = "is_heart";
    private static String PDC_KEY_IS_RESPAWN_ITEM = "is_respawn_item";


    private static boolean heartLossSwitch = true;

    public static double getHearts(Player p) {
        return p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
    }

    public static void setHearts(Player p, double h) {
        p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(h);
    }


    public static boolean getHeartLossSwitch() {
        return HeartSteal.getInstance().getConfig().getBoolean("heart_loss", true);
    }

    public static void setHeartLossSwitch(boolean heartLossSwitch) {
        HeartSteal.getInstance().getConfig().set("heart_loss", heartLossSwitch);
    }

    public static boolean isHeart(ItemStack item) {
        NamespacedKey isHeartKey = new NamespacedKey(HeartSteal.getInstance(), PDC_KEY_IS_HEART);

        ItemMeta meta = item.getItemMeta();
        if (meta == null)
            return false;

        PersistentDataContainer container = meta.getPersistentDataContainer();
        return container.getOrDefault(isHeartKey, PersistentDataType.BOOLEAN, false);
    }

    public static ItemStack getHeartItem() {
        ItemStack heart = new ItemStack(Material.NETHER_STAR, 1);
        ItemMeta heartMeta = heart.getItemMeta();
        heartMeta.setDisplayName(ChatColor.RED + "HEART");

        List<String> heartLore = Arrays.asList(
                "A player heart!",
                "Right click while holding to gain a heart!"
        );

        heartMeta.setLore(heartLore);
        heartMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        heartMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        PersistentDataContainer container = heartMeta.getPersistentDataContainer();

        NamespacedKey heartItem = new NamespacedKey(HeartSteal.getInstance(), PDC_KEY_IS_HEART);
        container.set(heartItem, PersistentDataType.BOOLEAN, true);

        heart.setItemMeta(heartMeta);

        return heart;
    }

    public static ItemStack getRespawnItem() {
        ItemStack respawnBeacon = new ItemStack(Material.BEACON, 1);
        ItemMeta respawnMeta = respawnBeacon.getItemMeta();
        respawnMeta.setDisplayName(ChatColor.AQUA + "BEACON OF LIFE");

        //making lore array
        ArrayList<String> respawnLore = new ArrayList<String>();
        respawnLore.add("This beacon has the ability to revive a player!");
        respawnLore.add("Be careful as it will broadcast its location!");
        respawnLore.add("This beacon takes 10 minutes to charge up and can be used by anyone once charged!");
        respawnLore.add("Placing this block will destroy a 3 by 3 area underneath it");
        respawnLore.add("and will also destroy all blocks above it to the world height!");

        respawnMeta.setLore(respawnLore);
        respawnMeta.addEnchant(Enchantment.CHANNELING, 1, true);

        PersistentDataContainer container = respawnMeta.getPersistentDataContainer();

        NamespacedKey isRespawnItem = new NamespacedKey(HeartSteal.getInstance(), PDC_KEY_IS_RESPAWN_ITEM);
        container.set(isRespawnItem, PersistentDataType.BOOLEAN, true);

        respawnBeacon.setItemMeta(respawnMeta);

        return respawnBeacon;
    }

    public static boolean isRespawnItem(ItemStack item) {
        NamespacedKey isRespawnItem = new NamespacedKey(HeartSteal.getInstance(), PDC_KEY_IS_RESPAWN_ITEM);

        ItemMeta meta = item.getItemMeta();
        if (meta == null)
            return false;

        PersistentDataContainer container = meta.getPersistentDataContainer();
        return container.getOrDefault(isRespawnItem, PersistentDataType.BOOLEAN, false);
    }


}
