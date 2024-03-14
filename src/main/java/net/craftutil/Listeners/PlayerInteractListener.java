package net.craftutil.Listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener { // I ❤ BOILERPLATE

	// ####### crafting table funcionality #######
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if (e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
			Player p = e.getPlayer();
			// if the player is sneaking while holding a crafting table
			if (p.isSneaking() && p.getInventory().getItemInMainHand().getType().equals(Material.CRAFTING_TABLE)) {
				p.openWorkbench(p.getLocation(), true); // open the crafting UI
			}
			if (p.isSneaking() && p.getInventory().getItemInMainHand().getType().equals(Material.STONECUTTER)) {
				p.openStonecutter(p.getLocation(), true); // open the stonecutter UI
			}
		}
	}
}
