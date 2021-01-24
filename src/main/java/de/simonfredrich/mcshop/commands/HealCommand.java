package de.simonfredrich.mcshop.commands;

import de.simonfredrich.mcshop.McShop;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            McShop.INSTANCE.log(McShop.PREFIX+"You are not a player.");
            return true;
        }

        Player player = (Player) sender;

        if (player.hasPermission("de.simonfredrich.mcshop.heal")) {
            player.setHealth(20d);
            player.sendMessage(McShop.PREFIX + "You have been healed.");
            player.playSound(player.getLocation(), Sound.BLOCK_BELL_USE, 1.2f, 1.2f);
        } else {
            player.sendMessage("You don't have permission to use this command.");
        }

        return true;
    }
}
