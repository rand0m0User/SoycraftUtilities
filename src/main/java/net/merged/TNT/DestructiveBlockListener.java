package net.merged.TNT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
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
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import net.craftutil.Settings;
import net.utils.ColorChat;

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

	private void dotitle(Location l, String title) {
		Collection<Player> players = l.getNearbyPlayers(50);
		for (Player p : players) {
			p.sendTitle(ChatColor.translateAlternateColorCodes('&', title), "", 10, 4 * 20, 10);
		}
	}

	@EventHandler
	public void onExplode(ExplosionPrimeEvent e) {
		EntityType et = e.getEntity().getType();
		if (et == EntityType.MINECART_TNT) {
			if (!Settings.Explosives) {
				dotitle(e.getEntity().getLocation(), "&4&l(YOU) WILL NEVER BE A WOMAN!");
				e.getEntity().remove();
				e.setCancelled(true);
			}
			announce("the &cTNT minecart &6was activated!");
		} else if (et == EntityType.ENDER_CRYSTAL) {
			if (!Settings.Explosives) {
				dotitle(e.getEntity().getLocation(), "&4&l(YOU) WILL NEVER BE A WOMAN!");
				e.getEntity().remove();
				e.setCancelled(true);
			}
			announce("the &cEnd Crystal &6was activated!");
		}
	}

	// merge 12+ command blocks into the plugin
	@EventHandler
	public void onEntitySpawn(EntitySpawnEvent e) {
		if (Settings.Explosives) {
			return;
		}
		Boolean cancel = false;
		Boolean msg1 = false;
		Boolean msg2 = false;
		switch (e.getEntity().getType()) {
		case MINECART_TNT, PRIMED_TNT, WITHER:
			cancel = true;
			msg1 = true;
			break;
		case ENDER_CRYSTAL:
			cancel = true;
			msg2 = true;
			break;
		default:
			break;
		}
		Collection<Player> players = e.getEntity().getLocation().getNearbyPlayers(50);
		if (msg1) {
			dotitle(e.getEntity().getLocation(), "&4&l(YOU) WILL NEVER BE A WOMAN!");
		}
		if (msg2) {
			dotitle(e.getEntity().getLocation(), "&4&l2B2T LOST!");

		}
		e.setCancelled(cancel);
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
		case DISPENSER: // may be redundant due to modispencermachanics the Dispenser-Block showing as a
						// player
			announce("a &bDispenser &6activated the &cTNT&6!");
			break;
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
		Bukkit.broadcastMessage(ColorChat.chat("&4[&cT&fN&cT&4] &6" + message));
	}
}
