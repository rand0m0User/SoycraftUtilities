package net.craftutil.Listeners;

import java.time.Duration;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

	ConsoleCommandSender console = Bukkit.getServer().getConsoleSender(); // I ❤ BOILERPLATE
	
	//the names of any proxy bots go here

	String names = "SexCraft69 ServerSeeker_net ServerSeeker";

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (names.contains(p.getDisplayName())) {
			console.sendMessage("another proxy bites the dust: " + p.getAddress().getAddress().toString());
			p.banIp("ban evasion via the use of proxy lists and scanners is forbiden", (Duration) null, names, false);
		}
	}
}
