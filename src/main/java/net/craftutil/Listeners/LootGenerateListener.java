package net.craftutil.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.LootGenerateEvent;

import net.merged.admintrolling.utils.ColorChat;

public class LootGenerateListener implements Listener {

	@EventHandler
	public void onLootGenerate(LootGenerateEvent e) {
		if (e.getEntity() instanceof Player) {
			Location l = e.getEntity().getLocation();
			Player g = (Player) e.getEntity();
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (p.isOp()) {
					p.sendMessage(ColorChat.chat("&7&o" + g.getName() + " generated a loot chest at: " + l.blockX()
							+ ", " + l.blockY() + ", " + l.blockZ()));
				}
			}
		}
	}
}
