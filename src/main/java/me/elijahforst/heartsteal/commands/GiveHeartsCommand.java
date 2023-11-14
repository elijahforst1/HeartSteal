package me.elijahforst.heartsteal.commands;

import me.elijahforst.heartsteal.utility.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveHeartsCommand implements CommandExecutor {

    // /givehearts <playerName> <hearts>
    
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
                    p.sendMessage("Could not find player. They could be offline!");
                } else {
                    target.sendMessage("You have been granted " + hearts/2 + " hearts!");
                    Util.setHearts(target, Util.getHearts(target) + hearts);
                    p.sendMessage(target.getDisplayName() + " has been granted " + hearts/2 + " hearts!");
                }
            }
        }


        return true;
    }
}
