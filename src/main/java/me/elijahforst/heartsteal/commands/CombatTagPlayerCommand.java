package me.elijahforst.heartsteal.commands;

import me.elijahforst.heartsteal.managers.CombatTagManager;
import me.elijahforst.heartsteal.utility.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CombatTagPlayerCommand implements CommandExecutor {
    private final CombatTagManager manager;
    public CombatTagPlayerCommand(CombatTagManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player))
            return true;

        Player p = (Player) sender;
        if (args.length != 1)
            return false;

        Player target = Bukkit.getServer().getPlayerExact(args[0]);
        if(target != null) {
            manager.addCombatTag(target);
        } else {
            p.sendMessage("Couldn't find target. Target may be offline.");
        }

        return true;
    }
}
