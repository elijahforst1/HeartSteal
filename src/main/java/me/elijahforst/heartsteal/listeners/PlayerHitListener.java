package me.elijahforst.heartsteal.listeners;

import me.elijahforst.heartsteal.utility.Util;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerHitListener implements Listener {
    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent e) {
        if (e.getEntityType() == EntityType.PLAYER && e.getDamager().getType() == EntityType.PLAYER) {
            Player victim = (Player) e.getEntity();
            Player attacker = (Player) e.getDamager();
            Util.addCombatTag(victim);
            Util.addCombatTag(attacker);
        }
    }
}
