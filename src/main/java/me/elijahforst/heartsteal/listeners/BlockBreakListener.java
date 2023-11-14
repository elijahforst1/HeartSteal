package me.elijahforst.heartsteal.listeners;

import me.elijahforst.heartsteal.utility.Util;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Block broken = e.getBlock();
        Player p = e.getPlayer();
        if(Util.getProtected(broken)){
            e.setCancelled(true);
            p.sendMessage("You cannot destroy this block!");
        }
    }
}
