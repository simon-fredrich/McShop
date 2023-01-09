package de.simonfredrich.mcshop.commands;

import de.simonfredrich.mcshop.McShop;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Trampoline implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (!(commandSender instanceof Player)) {
            McShop.INSTANCE.log("You are not a player.");
            return true;
        }

        Player player = (Player) commandSender;
        World world = player.getWorld();

        double xloc = player.getLocation().getX();
        double yloc = player.getLocation().getY();
        double zloc = player.getLocation().getZ();

        for (int i = 0; i<5; i++) {
            for (int j = 0; j<5; j++) {
                Location location = new Location(world, xloc+i, yloc, zloc+j);
                world.getBlockAt(location).setType(Material.STONE);
            }
        }

        return true;
    }
}
