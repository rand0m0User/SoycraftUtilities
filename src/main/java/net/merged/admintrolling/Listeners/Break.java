package net.merged.admintrolling.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import net.craftutil.Main;

public class Break implements Listener {

	@EventHandler
	public void BreakBlock(BlockBreakEvent e) {
		if (Main.cantBreak.contains(e.getPlayer())) {
			e.setCancelled(true);
		}
	}
}
