package me.elijahforst.heartsteal.listeners;

import me.elijahforst.heartsteal.managers.CombatTagManager;
import me.elijahforst.heartsteal.managers.ProtectBlocksManager;
import me.elijahforst.heartsteal.utility.Util;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class BlockExplodeListener implements Listener {

    private final ProtectBlocksManager manager;
    public BlockExplodeListener(ProtectBlocksManager manager) {
        this.manager = manager;
    }
    @EventHandler
    public void onBlockExplode(EntityExplodeEvent e) {
        for(Block block : e.blockList()) {
            block.
        }
    }
}
