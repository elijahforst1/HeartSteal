package me.elijahforst.heartsteal.managers;

import me.elijahforst.heartsteal.HeartSteal;
import me.elijahforst.heartsteal.commands.CombatTagPlayerCommand;
import me.elijahforst.heartsteal.listeners.CombatTagListener;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class CombatTagManager {
    private HashMap<UUID, Long> combatList = new HashMap<UUID, Long>();

    public HeartSteal plugin;
    public CombatTagManager(HeartSteal plugin) {
        this.plugin = plugin;
    }

    public void addCombatTag(Player p) {
        combatList.put(p.getUniqueId(), System.currentTimeMillis());
        p.sendMessage(ChatColor.RED + "You are now combat logged for 30 seconds. Do not log out!");
    }

    public void removeCombatTag(Player p) {
        if (!combatList.containsKey(p.getUniqueId()))
            return;

        combatList.remove(p.getUniqueId());

        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(
                ChatColor.GREEN + "[ OUT OF COMBAT ]"
        ));
    }

    public boolean getCombatTag(Player p) {
        if (!combatList.containsKey(p.getUniqueId()))
            return false;

        long timeSinceHit = System.currentTimeMillis() - combatList.get(p.getUniqueId());
        return timeSinceHit <= 30000;
    }

    public void registerInteractions() {
        plugin.getServer().getPluginManager().registerEvents(new CombatTagListener(this), plugin);
        plugin.getCommand("combattag").setExecutor(new CombatTagPlayerCommand(this));
    }

    public void registerScheduler() {
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            if (combatList.isEmpty())
                return;

            for (UUID uuid : combatList.keySet()) {
                long time = combatList.get(uuid);

                long timeSinceHit = System.currentTimeMillis() - time;
                boolean isCombatTagged = timeSinceHit <= 30000;

                Player p = Bukkit.getPlayer(uuid);
                if (p == null)
                    continue;

                if (isCombatTagged) {
                    long secondsSinceHit = timeSinceHit / 1000;
                    long remaining = 30 - secondsSinceHit;

                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(
                            ChatColor.RED + "[ COMBAT TAG FOR " + remaining + " SECONDS ]"
                    ));

                    continue;
                }

                combatList.remove(uuid);

                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(
                        ChatColor.GREEN + "[ OUT OF COMBAT ]"
                ));
            }
        }, 20L, 0L);
    }
}
