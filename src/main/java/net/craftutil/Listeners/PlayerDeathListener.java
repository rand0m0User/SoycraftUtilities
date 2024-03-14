package net.craftutil.Listeners;

import java.util.regex.Pattern;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;

public class PlayerDeathListener implements Listener {

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		if (e.getDeathMessage().contains("Indoraptor")) {
			Component msg = e.deathMessage();
			msg = msg.replaceText(
					TextReplacementConfig.builder().replacement("eaten").match(Pattern.quote("slain")).build());
			e.deathMessage(msg);
		}
	}
}
