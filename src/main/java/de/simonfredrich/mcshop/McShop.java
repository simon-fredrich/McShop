package de.simonfredrich.mcshop;

import de.simonfredrich.mcshop.commands.HealCommand;
import de.simonfredrich.mcshop.commands.HurtCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class McShop extends JavaPlugin {

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

    public void log(String text) {
        Bukkit.getConsoleSender().sendMessage(PREFIX+text);
    }

    private void register() {
        Bukkit.getPluginCommand("heal").setExecutor(new HealCommand());
        Bukkit.getPluginCommand("hurt").setExecutor(new HurtCommand());
    }
}
