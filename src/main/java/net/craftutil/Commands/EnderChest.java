package net.craftutil.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import net.utils.ColorChat;

public class EnderChest implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
			@NotNull String[] args) {
		if (sender instanceof Player player) {
			player.openInventory(player.getEnderChest());
		} else {
			sender.sendMessage(ColorChat.chat("&cOnly players can use this command."));
		}
		return true;
	}
}
