package me.elijahforst.heartsteal.listeners;

import me.elijahforst.heartsteal.utility.Util;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.inventory.ItemStack;

public class ItemDespawnListener implements Listener {
    @EventHandler
    public void onItemDespawn(ItemDespawnEvent e){
        ItemStack item = e.getEntity().getItemStack();
        if(Util.isHeart(item)){
            e.setCancelled(true);
        }
    }
}
