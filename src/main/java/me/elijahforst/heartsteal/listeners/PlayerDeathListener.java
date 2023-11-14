package me.elijahforst.heartsteal.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.Inventory;
import me.elijahforst.heartsteal.utility.Util;


public class PlayerDeathListener implements Listener{

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player victim = e.getEntity();
        Player killer = victim.getKiller();

        if(Util.getHeartLossSwitch()) {
            //Ban code(WORKING)
            if (Util.getHearts(victim) <= 2.0) {
                Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "ban " + victim.getDisplayName() + " You have lost ALL your lives!");
                return;
            }

            if (killer != null && killer != victim) {
                Inventory killerInv = killer.getInventory();
                if (Util.getHearts(killer) < 40) {
                    Util.setHearts(killer, Util.getHearts(killer) + 2);
                } else {
                    killerInv.addItem(Util.getHeartItem());
                }
            } else {
                victim.getLocation().getBlock().getWorld().dropItemNaturally(victim.getLocation(), Util.getHeartItem());
            }

            Util.setHearts(victim, Util.getHearts(victim) - 2);
        }
    }
}
