package me.elijahforst.heartsteal.commands;

import me.elijahforst.heartsteal.utility.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class GiveHeartItemCommand implements CommandExecutor {
    @Override

    // /giveheartitem [player] [amount]

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = ((Player) sender).getPlayer();

            if (args.length != 2) {
                return false;
            } else {
                Player target = Bukkit.getServer().getPlayerExact(args[0]);
                Inventory targetInv = target.getInventory();

                for(int i =0; i < Integer.parseInt(args[1]); i++) {
                    targetInv.addItem(Util.getHeartItem());
                }
            }
        }
        return true;
    }
}
