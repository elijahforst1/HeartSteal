package me.elijahforst.heartsteal.utility;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Util {

    private static boolean heartLossSwitch = true;
    private static HashMap<UUID, Long> combatList = new HashMap<UUID, Long>();

    private static HashMap<Block, Boolean> protectList = new HashMap<Block, Boolean>();

    public static double getHearts(Player p) {
        return p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
    }

    public static void setHearts(Player p, double h) {
        p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(h);
    }


    public static boolean getHeartLossSwitch() {
        return heartLossSwitch;
    }

    public static void setHeartLossSwitch(boolean heartLossSwitch) {
        Util.heartLossSwitch = heartLossSwitch;
    }

    public static void addCombatTag(Player p){
        combatList.put(p.getUniqueId(),System.currentTimeMillis());
        p.sendMessage(ChatColor.RED + "You are now combat logged for 30 seconds. Do not log out!");
    }

    public static boolean getCombatTag(Player p){
        if(!combatList.containsKey(p.getUniqueId())) {
            combatList.put(p.getUniqueId(), System.currentTimeMillis() - 30001);
        }
        Long timeSinceHit = System.currentTimeMillis() - combatList.get(p.getUniqueId());
        if(timeSinceHit <= 30000){
            return true;
        } else {
            return false;
        }
    }

    public static boolean isHeart(ItemStack item) {
        if(item.getType() == Material.NETHER_STAR && item.getItemMeta().getDisplayName().equals(ChatColor.RED + "HEART") && item.getItemMeta().hasEnchants()){
            return true;
        } else {
            return false;
        }
    }

    public static ItemStack getHeartItem() {
        ItemStack heart = new ItemStack(Material.NETHER_STAR,1);
        ItemMeta heartMeta = heart.getItemMeta();
        heartMeta.setDisplayName(ChatColor.RED + "HEART");

        //making heartlore arrayList
        ArrayList<String> heartLore = new ArrayList<String>();
        heartLore.add("A player heart!");
        heartLore.add("Right click while holding to gain a heart!");

        heartMeta.setLore(heartLore);
        heartMeta.addEnchant(Enchantment.DURABILITY,1,true);
        heartMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        heart.setItemMeta(heartMeta);

        return heart;
    }

    public static ItemStack getRespawnItem() {
        ItemStack respawnBeacon = new ItemStack(Material.BEACON,1);
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
        respawnMeta.addEnchant(Enchantment.CHANNELING,1,true);
        respawnBeacon.setItemMeta(respawnMeta);

        return respawnBeacon;
    }

    public static boolean isRespawnItem(ItemStack item) {
        if(item.getType() == Material.BEACON && item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "BEACON OFF LIFE") && item.getItemMeta().hasEnchants()){
            return true;
        } else {
            return false;
        }
    }

    public static void setProtected(Block block, boolean protect){
        protectList.put(block,protect);
    }

    public static boolean getProtected(Block block){
        if(!protectList.containsKey(block)){
            protectList.put(block,false);
        }
        return protectList.get(block);
    }
}
