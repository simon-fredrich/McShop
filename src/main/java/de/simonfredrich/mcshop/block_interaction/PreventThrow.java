package de.simonfredrich.mcshop.block_interaction;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class PreventThrow implements Listener {

    @EventHandler
    public void prevent_throw(PlayerDropItemEvent e) {
        Player player = e.getPlayer();
        ItemStack dropped_item = e.getItemDrop().getItemStack();

        Map<Enchantment, Integer> enchantment = dropped_item.getItemMeta().getEnchants();
        if (dropped_item.getType().equals(Material.BLAZE_ROD) && enchantment.containsKey(Enchantment.KNOCKBACK)) {
            player.sendMessage("§4Du solltes diese Sache nicht wegschmeißen.");
            e.setCancelled(true);
        }
    }
}
