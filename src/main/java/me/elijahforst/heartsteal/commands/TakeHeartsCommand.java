package me.elijahforst.heartsteal.commands;

import me.elijahforst.heartsteal.utility.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TakeHeartsCommand implements CommandExecutor {

    // /takehearts <playerName> <hearts>

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(sender instanceof Player) {
            Player p = ((Player) sender).getPlayer();
            if(args.length != 2) {
                return false;
            } else {

                String playerName = args[0];
                double hearts = Integer.parseInt(args[1]) * 2;

                Player target = Bukkit.getServer().getPlayerExact(playerName);

                if(target == null) {
                    p.sendMessage("Could not find player. Player could be offline.");
                    return false;
                } else if(Util.getHearts(target) - hearts < 2) {
                    p.sendMessage("Invalid as this will leave the player with no hearts.");
                    return false;
                } else {
                    target.sendMessage("You have lost " + hearts/2 + " hearts!");
                    Util.setHearts(target, Util.getHearts(target) - hearts);
                    p.sendMessage(target.getDisplayName() + " has lost " + hearts/2 + " hearts!");
                }
            }
        }
        return true;
    }
}