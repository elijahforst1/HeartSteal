package me.elijahforst.heartsteal.listeners;

import me.elijahforst.heartsteal.managers.CombatTagManager;
import me.elijahforst.heartsteal.utility.Util;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CombatTagListener implements Listener {
    private final CombatTagManager manager;
    public CombatTagListener(CombatTagManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent e) {
        if (e.getEntityType() != EntityType.PLAYER || e.getDamager().getType() != EntityType.PLAYER)
            return;

        Player victim = (Player) e.getEntity();
        Player attacker = (Player) e.getDamager();

        manager.addCombatTag(victim);
        manager.addCombatTag(attacker);
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (!manager.getCombatTag(p))
            return;

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

        manager.removeCombatTag(p);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        if (!manager.getCombatTag(player))
            return;

        manager.removeCombatTag(player);
    }
}
