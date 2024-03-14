package net.craftutil.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import net.utils.ColorChat;

public class anon implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
			@NotNull String[] args) {
		Bukkit.broadcastMessage(ColorChat.chat("<&kAAAAA&r> " + String.join(" ", args)));
		return true;
	}
}
