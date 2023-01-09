package de.simonfredrich.mcshop.commands;

import de.simonfredrich.mcshop.McShop;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HurtCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            McShop.INSTANCE.log("You are not a player.");
            return true;
        }

        Player player = (Player) sender;
        player.playSound(player.getLocation(), Sound.BLOCK_BASALT_HIT, 1.2f, 1.2f);
        player.damage(5d);
        player.sendMessage("You have received damage.");

        return true;
    }
}
