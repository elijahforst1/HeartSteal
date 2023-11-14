package me.elijahforst.heartsteal.commands;

import me.elijahforst.heartsteal.utility.Util;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class WithdrawHeartsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {


        if (sender instanceof Player) {
            Player p = ((Player) sender);
            if (args.length != 1) {
                return false;
            } else {
                int withdraw = Integer.parseInt(args[0]);
                int heartsBeforeWithdraw = (int)(Util.getHearts(p)/2);
                if(withdraw >= heartsBeforeWithdraw){
                    p.sendMessage("This would remove all your hearts. The most you can withdraw is " + (heartsBeforeWithdraw-1));
                } else {
                    Util.setHearts(p, Util.getHearts(p) - withdraw*2);
                    ItemStack heart = Util.getHeartItem().clone();
                    heart.setAmount(withdraw);
                    p.getInventory().addItem(heart);
                }
            }
        }
        return true;
    }
}
