package me.elijahforst.heartsteal.commands;

import me.elijahforst.heartsteal.utility.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CombatTagPlayerCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player){
            Player p = ((Player) sender).getPlayer();
            if (args.length != 1) {
                return false;
            } else {
                Player target = Bukkit.getServer().getPlayerExact(args[0]);
                if(target != null) {
                    Util.addCombatTag(target);
                } else {
                    p.sendMessage("Couldn't find target. Target may be offline.");
                }
            }
        }
        return true;
    }
}
