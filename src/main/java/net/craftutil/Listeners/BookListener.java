package net.craftutil.Listeners;

import static org.bukkit.Bukkit.getServer;

import java.nio.charset.StandardCharsets;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEditBookEvent;

import net.utils.ColorChat;

public class BookListener implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBookEdit(PlayerEditBookEvent e) {
		for (String bookPage : e.getNewBookMeta().getPages()) {
			if (!StandardCharsets.US_ASCII.newEncoder().canEncode(bookPage)) {
				e.setCancelled(true);
				getServer().broadcastMessage(ColorChat.chat(
						"&4" + e.getPlayer().getDisplayName() + " tried to write non ascii characters into a book!"));
				String s = "";
				for (int i = 0; i < 16300; i++) { // use wisely, this creates ALOT of lag
					s += "AAAAAAAAAAAAAAAA";
				}
				e.getPlayer().kickPlayer(ColorChat.chat("&k" + s));
				return;
			}
		}
	}
}
