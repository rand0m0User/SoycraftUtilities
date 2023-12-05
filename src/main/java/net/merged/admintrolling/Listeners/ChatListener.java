package net.merged.admintrolling.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import net.craftutil.Main;

public class ChatListener implements Listener {

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		if (Main.cantSpeek.contains(e.getPlayer())) {
			e.setCancelled(true);
			e.getPlayer().sendMessage("You have been muted by an operator!");
		}
	}
}
