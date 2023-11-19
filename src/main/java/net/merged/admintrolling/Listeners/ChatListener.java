package net.merged.admintrolling.Listeners;

import net.craftutil.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        if (Main.cantSpeek.contains(e.getPlayer())) {
            e.setCancelled(true);
            e.getPlayer().sendMessage("You have been muted by an operator!");
        }
    }
}
