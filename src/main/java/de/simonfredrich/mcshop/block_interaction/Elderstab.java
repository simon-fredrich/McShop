package de.simonfredrich.mcshop.block_interaction;

import de.simonfredrich.mcshop.McShop;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Elderstab {

    public void spawnParticles(){
        for(Player player : Bukkit.getOnlinePlayers()){
            player.getInventory().getItemInMainHand();
            ItemStack hand = player.getInventory().getItemInMainHand();
            if(hand.getType().equals(Material.BLAZE_ROD) && hand.getItemMeta().getEnchants().containsKey(Enchantment.FIRE_ASPECT)){
                give_fireresistance(player);
            }
            if(hand.getType().equals(Material.BLAZE_ROD) && hand.getItemMeta().getEnchants().containsKey(Enchantment.DAMAGE_ALL) && hand.getItemMeta().getEnchants().containsKey(Enchantment.MENDING)) {
                give_regeneration(player);
            }
            if(hand.getType().equals(Material.NETHERITE_SWORD) && hand.getItemMeta().getEnchants().containsKey(Enchantment.LOOT_BONUS_MOBS) && hand.getItemMeta().getEnchantLevel(Enchantment.LOOT_BONUS_MOBS)==7) {
                give_slowness(player);
            }
        }
    }

    public void startRunnable(){
        Bukkit.getScheduler().runTaskTimer(McShop.INSTANCE, new Runnable(){

            @Override
            public void run() {
                spawnParticles();
            }
        }, 20, 20);//Time in ticks before first run and each time after that
    }

    private void give_fireresistance(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 30, 1));
    }

    private void give_regeneration(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 30, 3));
    }

    private void give_slowness(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 4));
    }
}
