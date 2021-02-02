package de.simonfredrich.mcshop.block_interaction;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class BlockCrusher implements Listener {

    BlockFace block_face;

    @EventHandler
    public void playerBreak(PlayerInteractEvent e) {
        block_face = e.getBlockFace();
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

    private void block_crusher_xy(Block block, Player player, GameMode game_mode) {
        ItemStack stone_axe = new ItemStack(Material.STONE_AXE);
        if (player.getInventory().getItemInMainHand().equals(stone_axe)) {
            int x = block.getX() - 1;
            int y = block.getY() + 1;
            int z = block.getZ();
            World world = block.getWorld();

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    Location loc = new Location(world, x, y, z);
                    if (game_mode.equals(GameMode.CREATIVE)) {
                        loc.getBlock().setType(Material.AIR);
                    } else {
                        loc.getBlock().breakNaturally();
                    }
                    y = y - 1;
                }
                x = x + 1;
                y = block.getY() + 1;
            }
        }
    }

    private void block_crusher_yz(Block block, Player player, GameMode game_mode) {
        ItemStack stone_axe = new ItemStack(Material.STONE_AXE);
        if (player.getInventory().getItemInMainHand().equals(stone_axe)) {
            int x = block.getX();
            int y = block.getY() + 1;
            int z = block.getZ() - 1;
            World world = block.getWorld();

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    Location loc = new Location(world, x, y, z);
                    if (game_mode.equals(GameMode.CREATIVE)) {
                        loc.getBlock().setType(Material.AIR);
                    } else {
                        loc.getBlock().breakNaturally();
                    }
                    y = y - 1;
                }
                z = z + 1;
                y = block.getY() + 1;
            }
        }
    }

    private void block_crusher_xz(Block block, Player player, GameMode game_mode) {
        ItemStack stone_axe = new ItemStack(Material.STONE_AXE);
        if (player.getInventory().getItemInMainHand().equals(stone_axe)) {
            int x = block.getX() + 1;
            int y = block.getY();
            int z = block.getZ() + 1;
            World world = block.getWorld();

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    Location loc = new Location(world, x, y, z);
                    if (game_mode.equals(GameMode.CREATIVE)) {
                        loc.getBlock().setType(Material.AIR);
                    } else {
                        loc.getBlock().breakNaturally();
                    }
                    x = x - 1;
                }
                z = z - 1;
                x = block.getX() + 1;
            }
        }
    }

    private void crush_blocks(Player player, Block block, BlockFace block_face) {
        GameMode game_mode = player.getGameMode();
        switch (block_face.name()) {
            case "NORTH":
            case "SOUTH":
                block_crusher_xy(block, player, game_mode);
                break;
            case "EAST":
            case "WEST":
                block_crusher_yz(block, player, game_mode);
                break;
            case "UP":
            case "DOWN":
                block_crusher_xz(block, player, game_mode);
                break;
        }
    }
}
