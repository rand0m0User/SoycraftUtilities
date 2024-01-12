package net.merged.TNT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.TNTPrimeEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("deprecation")
public class DestructiveBlockListener implements Listener {
	
	private final List<Material> rails = new ArrayList<>(
			Arrays.asList(Material.ACTIVATOR_RAIL, Material.DETECTOR_RAIL, Material.POWERED_RAIL, Material.RAIL));

	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		if (e.getBlock().getType() == Material.TNT) {
			announce(String.format("Player &b%s &6placed the &cTNT &6block!", e.getPlayer().getName()));
		}
	}

	@EventHandler
	public void onDispense(BlockDispenseEvent e) {
		if (e.getBlock().getType() == Material.DISPENSER) {
			Material m = e.getItem().getType();
			if (m == Material.TNT) {
				announce("a &bDispenser &6dispensed a &cTNT &6block!");
			}
			if (m == Material.TNT_MINECART) {
				announce("a &bDispenser &6dispensed a &cTNT minecart&6!");
			}
		}
	}

	@EventHandler
	public void onExplode(ExplosionPrimeEvent e) {
		EntityType et = e.getEntity().getType();
		if (et == EntityType.MINECART_TNT) {
			announce("the &cTNT minecart &6was activated!");
		} else if (et == EntityType.ENDER_CRYSTAL) {
			announce("the &cEnd Crystal &6was activated!");
		}
	}

	@EventHandler
	public void onTNTPrime(TNTPrimeEvent e) { // migrate old message to TNTPrimeEvent
		switch (e.getCause()) {
		case PLAYER:
			// if a player is trying to light tnt manually... (also an indirect NULL check)
			if (e.getPrimingEntity().getType() == EntityType.PLAYER) {
				Player p = (Player) e.getPrimingEntity();
				announce(String.format("Player &b%s &6activated the &cTNT&6!", p.getName()));
			}
			break;
		case REDSTONE:
			announce("a &bRedstone signal &6activated the &cTNT&6!");
			break;
		case PROJECTILE:
			announce(String.format("a &b%s &6activated the &cTNT&6!",
					e.getPrimingEntity().getType().name().toLowerCase().replace("_", " ")));
			break;
		case DISPENSER: //may be redundant due to modispencermachanics the Dispenser-Block showing as a player
			announce("a &bDispenser &6activated the &cTNT&6!");
		default:
			break;
		}
	}

	@EventHandler
	public void onClick(PlayerInteractEvent e) {
		Block b = e.getClickedBlock();
		ItemStack is = e.getItem();
		if (is != null && b != null) { // null checks
			String pln = e.getPlayer().getName();
			if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				// if player is trying to place a tnt minecart on rails
				if (is.getType() == Material.TNT_MINECART && rails.contains(b.getType())) {
					announce(String.format("Player &b%s &6placed the &cTNT minecart&6!", pln));
				} else if (is.getType() == Material.END_CRYSTAL
						&& (b.getType() == Material.OBSIDIAN || b.getType() == Material.BEDROCK)) {
					announce(String.format("Player &b%s &6placed the &cEnd Crystal&6!", pln));
				}
			}
		}
	}

	// duplicate code removal
	public static void announce(String message) {
		Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&4[&cT&fN&cT&4] &6" + message));
	}
}
