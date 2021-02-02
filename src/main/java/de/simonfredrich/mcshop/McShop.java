package de.simonfredrich.mcshop;

import de.simonfredrich.mcshop.block_interaction.BlockCrusher;
import de.simonfredrich.mcshop.commands.HealCommand;
import de.simonfredrich.mcshop.commands.HurtCommand;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;


public final class McShop extends JavaPlugin implements Listener {

    public static String PREFIX = "§aMcShop §7§o";
    public static McShop INSTANCE;

    public McShop() {
        INSTANCE = this;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic

        this.register();

        log("has loaded.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        log("has detached.");
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        e.getBlock().setType(Material.BONE_BLOCK);
    }

    public void log(String text) {
        Bukkit.getConsoleSender().sendMessage(PREFIX+text);
    }

    private void register() {
        Bukkit.getPluginCommand("heal").setExecutor(new HealCommand());
        Bukkit.getPluginCommand("hurt").setExecutor(new HurtCommand());
        Bukkit.getPluginManager().registerEvents(new BlockCrusher(), this);
    }
}
