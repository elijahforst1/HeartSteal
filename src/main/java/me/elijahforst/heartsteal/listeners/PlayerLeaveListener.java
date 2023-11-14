package me.elijahforst.heartsteal.listeners;

import me.elijahforst.heartsteal.utility.Util;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PlayerLeaveListener implements Listener {
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if(Util.getCombatTag(p)){
            Location spawn = p.getBedSpawnLocation();
            Location logLocation = p.getLocation();
            Block deathBlock = logLocation.getBlock();
            Inventory inv = p.getInventory();
            ItemStack[] invList = inv.getContents();
            for(int i = 0; i < invList.length; i++) {
                if(invList[i] != null) {
                    deathBlock.getWorld().dropItemNaturally(logLocation, invList[i]);
                }
            }
            deathBlock.getWorld().dropItemNaturally(logLocation, Util.getHeartItem());
            inv.clear();
            p.setHealth(0.0);
        }
    }
}
