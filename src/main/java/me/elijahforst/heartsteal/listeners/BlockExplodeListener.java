package me.elijahforst.heartsteal.listeners;

import me.elijahforst.heartsteal.utility.Util;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;

public class BlockExplodeListener implements Listener {
    @EventHandler
    public void onBlockExplode(BlockExplodeEvent e) {
        Block broken = e.getBlock();
        if(Util.getProtected(broken)){
            e.setCancelled(true);
        }
    }
}
