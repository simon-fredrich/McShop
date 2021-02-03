package de.simonfredrich.mcshop.block_interaction;

import de.simonfredrich.mcshop.McShop;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;

public class BlockCrusher implements Listener {

    BlockFace block_face;

    @EventHandler
    public void playerBreak(PlayerInteractEvent e) {
        block_face = e.getBlockFace();
        Player player_interact = e.getPlayer();
        Block block = e.getClickedBlock();
        check_not_bohr(block, player_interact);
    }

    private void check_not_bohr(Block block, Player player_interact) {
        for (Material m : McShop.INSTANCE.configPickaxeBlocks)
            try {
                if (block.getType().equals(m)) {
                    slow_bohr(player_interact);
                }
            } catch (NullPointerException np) {
                System.out.print("NullPointer because of block type.");
            }

    }

    private void slow_bohr(Player player) {
        ItemStack player_item = player.getInventory().getItemInMainHand();
        String display_name = player_item.getItemMeta().getDisplayName();
        Material item_material = player_item.getType();
        if (display_name.equals("Bohrer") && item_material.equals(Material.DIAMOND_PICKAXE)) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 20, 3));
        }
    }

    @EventHandler
    public void blockBreak(BlockBreakEvent e) {
        Block block = e.getBlock();
        Player player = e.getPlayer();
        is_bed(block, player);

        crush_blocks(player, block, block_face);
    }


    private void is_bed(Block block, Player player) {
        if (block.getType() == Material.WHITE_BED || block.getType() == Material.BLUE_BED || block.getType() == Material.BLACK_BED || block.getType() == Material.RED_BED) {
            player.playSound(player.getLocation(), Sound.ENTITY_WITHER_DEATH, 1.0f, 1.0f);
        }
    }

    private void block_crusher(Block block, Player player, GameMode game_mode, String orientation) {
        ItemStack player_item = player.getInventory().getItemInMainHand();
        String display_name = player_item.getItemMeta().getDisplayName();
        Material item_material = player_item.getType();
        if (display_name.equals("Bohrer") && item_material.equals(Material.DIAMOND_PICKAXE)) {
            int x = 0, y = 0, z = 0;
            switch (orientation) {
                case "xy":
                    x = block.getX() - 1;
                    y = block.getY() + 1;
                    z = block.getZ();
                    break;
                case "yz":
                    x = block.getX();
                    y = block.getY() + 1;
                    z = block.getZ() - 1;
                    break;
                case "xz":
                    x = block.getX() + 1;
                    y = block.getY();
                    z = block.getZ() + 1;
                    break;
            }

            World world = block.getWorld();
            boolean broken_block_mat = false;
            for (Material m : McShop.INSTANCE.configPickaxeBlocks) {
                if (block.getType().equals(m)) broken_block_mat = true;
            }

            boolean unbreaking_case = true;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    Location loc = new Location(world, x, y, z);
                    for (Material m : McShop.INSTANCE.configPickaxeBlocks) {
                        if (loc.getBlock().getType().equals(m) && broken_block_mat) {
                            if (game_mode.equals(GameMode.CREATIVE)) {
                                loc.getBlock().setType(Material.AIR);
                            } else {
                                loc.getBlock().breakNaturally();
                                if (player_item.containsEnchantment(Enchantment.DURABILITY) && unbreaking_case) {
                                    unbreaking_case = false;
                                } else {
                                    Damageable meta = (Damageable) player_item.getItemMeta();
                                    int damage = meta.getDamage();
                                    meta.setDamage(damage+1);
                                    player_item.setItemMeta((ItemMeta) meta);
                                }
                            }
                        }
                    }
                    switch (orientation) {
                        case "xy":
                        case "yz":
                            y = y - 1;
                            break;
                        case "xz":
                            x = x - 1;
                            break;
                    }
                }
                switch (orientation) {
                    case "xy":
                        x = x + 1;
                        y = block.getY() + 1;
                        break;
                    case "yz":
                        z = z + 1;
                        y = block.getY() + 1;
                        break;
                    case "xz":
                        z = z - 1;
                        x = block.getX() + 1;
                        break;
                }
            }
        }
    }

    private void crush_blocks(Player player, Block block, BlockFace block_face) {
        GameMode game_mode = player.getGameMode();
        switch (block_face.name()) {
            case "NORTH":
            case "SOUTH":
                block_crusher(block, player, game_mode, "xy");
                break;
            case "EAST":
            case "WEST":
                block_crusher(block, player, game_mode, "yz");
                break;
            case "UP":
            case "DOWN":
                block_crusher(block, player, game_mode, "xz");
                break;
        }
    }
}
