package net.merged.admintrolling.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import net.craftutil.Main;

public class BuildTroll implements Listener {

	@EventHandler
	public void Build(BlockPlaceEvent e) {
		if (Main.cantBuild.contains(e.getPlayer())) {
			e.setCancelled(true);
		}
	}
}
