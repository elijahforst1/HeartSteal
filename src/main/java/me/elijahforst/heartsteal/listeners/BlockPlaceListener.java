package me.elijahforst.heartsteal.listeners;

import me.elijahforst.heartsteal.utility.Util;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;


public class BlockPlaceListener implements Listener {
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        Block blockPlaced = e.getBlockPlaced();
        Location blockLoc = blockPlaced.getLocation();
        int x = blockPlaced.getX();
        int y = blockPlaced.getY();
        int z = blockPlaced.getZ();
        Player p = e.getPlayer();
        ItemStack itemPlaced = e.getItemInHand();
        int highestBlockY = Bukkit.getServer().getWorld("world").getHighestBlockYAt(blockLoc.getBlockX(),blockLoc.getBlockZ());
        if(Util.isRespawnItem(itemPlaced)){
            Bukkit.broadcastMessage("A respawn beacon has been placed at " + x + " " + y + " " + z);
            for(int i = 1; i < highestBlockY; i++){
                Bukkit.getServer().getWorld("world").getBlockAt(x,y + i, z).breakNaturally();
            }
            Bukkit.getServer().getWorld("world").getBlockAt(x,y-1,z).setType(Material.GOLD_BLOCK);
            Bukkit.getServer().getWorld("world").getBlockAt(x + 1,y-1,z).setType(Material.GOLD_BLOCK);
            Bukkit.getServer().getWorld("world").getBlockAt(x - 1,y-1,z).setType(Material.GOLD_BLOCK);
            Bukkit.getServer().getWorld("world").getBlockAt(x,y-1,z + 1).setType(Material.GOLD_BLOCK);
            Bukkit.getServer().getWorld("world").getBlockAt(x,y-1,z - 1).setType(Material.GOLD_BLOCK);
            Bukkit.getServer().getWorld("world").getBlockAt(x + 1,y-1,z + 1).setType(Material.GOLD_BLOCK);
            Bukkit.getServer().getWorld("world").getBlockAt(x - 1,y-1,z - 1).setType(Material.GOLD_BLOCK);
            Bukkit.getServer().getWorld("world").getBlockAt(x + 1,y-1,z - 1).setType(Material.GOLD_BLOCK);
            Bukkit.getServer().getWorld("world").getBlockAt(x - 1,y-1,z + 1).setType(Material.GOLD_BLOCK);

            //protecting blocks
            Util.setProtected(blockPlaced,true);
        }
    }
}
