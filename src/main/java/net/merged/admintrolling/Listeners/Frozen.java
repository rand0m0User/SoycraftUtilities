package net.merged.admintrolling.Listeners;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import net.craftutil.Main;

public class Frozen implements Listener {

	@EventHandler
	public void Move(PlayerMoveEvent e) {
		if (Main.frozen.containsKey(e.getPlayer())) {
			e.getPlayer().teleport((Location) Main.frozen.get(e.getPlayer()));
		}
	}
}
