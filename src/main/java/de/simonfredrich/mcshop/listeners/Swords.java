package de.simonfredrich.mcshop.listeners;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class Swords implements Listener {

    @EventHandler
    public void on_run(PlayerJumpEvent event) {
        Player player = event.getPlayer();
        ItemStack hand = player.getInventory().getItemInMainHand();
        if(hand.getType().equals(Material.NETHERITE_SWORD) && hand.getItemMeta().getEnchants().containsKey(Enchantment.LOOT_BONUS_MOBS) && hand.getItemMeta().getEnchantLevel(Enchantment.LOOT_BONUS_MOBS)==7) {
            player.damage(0.5d);
        }
    }

}
