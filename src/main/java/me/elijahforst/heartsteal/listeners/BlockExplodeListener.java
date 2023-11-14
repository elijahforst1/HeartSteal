package me.elijahforst.heartsteal.listeners;

import me.elijahforst.heartsteal.utility.Util;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class BlockExplodeListener implements Listener {
    @EventHandler
    public void onBlockExplode(EntityExplodeEvent e) {
        e.blockList().removeIf(Util::getProtected);
    }
}
