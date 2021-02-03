package de.simonfredrich.mcshop;

import de.simonfredrich.mcshop.block_interaction.BlockCrusher;
import de.simonfredrich.mcshop.commands.HealCommand;
import de.simonfredrich.mcshop.commands.HurtCommand;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public final class McShop extends JavaPlugin implements Listener {

    public static String PREFIX = "§aMcShop §7§o";
    public static McShop INSTANCE;

    public McShop() {
        INSTANCE = this;
    }

    public ArrayList<Material> configPickaxeBlocks = new ArrayList<Material>();

    @Override
    public void onEnable() {
        // Plugin startup logic

        this.register();
        this.load_config();

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

    public void log_string_list(List<String> string_list) {
        for (String i : string_list)
        Bukkit.getConsoleSender().sendMessage(PREFIX+i);
    }

    private void register() {
        Bukkit.getPluginCommand("heal").setExecutor(new HealCommand());
        Bukkit.getPluginCommand("hurt").setExecutor(new HurtCommand());
        Bukkit.getPluginManager().registerEvents(new BlockCrusher(), this);
    }

    private void load_config() {
        List<String> loadPickaxeBlocks = McShop.this.getConfig().getStringList("blocks.pickaxe-blocks");
        for(String s: loadPickaxeBlocks) {
            if(Material.getMaterial(s) != null){
                configPickaxeBlocks.add(Material.getMaterial(s));
            }
        }
    }
}
