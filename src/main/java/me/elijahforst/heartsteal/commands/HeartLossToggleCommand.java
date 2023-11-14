package me.elijahforst.heartsteal.commands;

import me.elijahforst.heartsteal.utility.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HeartLossToggleCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player) {
            Player p = ((Player) sender).getPlayer();
            if (args.length != 0) {
                return false;
            } else {
                if (Util.getHeartLossSwitch()) {
                    Util.setHeartLossSwitch(false);
                    p.sendMessage("Heart loss is now off!");
                } else {
                    Util.setHeartLossSwitch(true);
                    p.sendMessage("Heart loss is now on!");
                }
            }
        }
        return true;
    }
}
