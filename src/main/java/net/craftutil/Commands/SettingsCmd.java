package net.craftutil.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import net.craftutil.GUI.SettingsGUI;
import net.utils.ColorChat;

public class SettingsCmd implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
			@NotNull String[] args) {
		if (sender instanceof Player p) {
			if (p.isOp()) {
				SettingsGUI g = new SettingsGUI();
				p.openInventory(g.create());
			} else {
				sender.sendMessage(ColorChat.chat("&cYou don't have permission to use that command."));
			}
		} else {
			sender.sendMessage(ColorChat.chat("&cOnly players can use this command."));
		}
		return true;
	}
}
