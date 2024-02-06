package net.craftutil.Commands;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import net.utils.ColorChat;

public class PermBan implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
			@NotNull String[] args) {
		if (sender.isOp()) {
			String playerstr = "";
			try {
				playerstr = args[0];
			} catch (Exception e) {
				sender.sendMessage(ColorChat.chat("&cyou must provide a player!"));
				return true;
			}
			String reason = String.join(" ", args).replace(playerstr, "");
			if (reason == "") {
				sender.sendMessage(ColorChat.chat("&cyou must provide a reason!"));
				return true;
			}
			Player p = Bukkit.getServer().getPlayer(args[0]);
			String ip = "<offline>";
			if (p != null) {
				ip = p.getAddress().getAddress().toString().replace("/", "");
			}
			String msg = "You have been permanently banned from mc.soyjak.party for the following reason:\n\n" + reason
					+ "\n\nYour ban was filed on " + formatTime(LocalDateTime.now()) + " and will NOT expire.\n\n"
					+ "According to our server, your IP is: " + ip + ". The name you were posting with \nwas "
					+ playerstr + ".\n\n";

			if (p == null) {
				Bukkit.getOfflinePlayer(playerstr).ban(msg, (Duration) null, sender.getName());
				sender.sendMessage("banned offline player: " + playerstr + ". \n" + msg);
			} else {
				p.getWorld().strikeLightning(p.getLocation());
				p.ban(msg, (Duration) null, null);
				if (!ip.startsWith("<")) {
					Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "ban-ip " + ip + reason);
				}
				sender.sendMessage("banned " + playerstr + ": \n" + msg);
				Bukkit.broadcastMessage(
						ColorChat.chat("&4(" + playerstr.toUpperCase() + " WAS PERMANENTLY BANNED FOR THIS POST)"));
			}
			return true;
		}
		return true;
	}

	private String formatTime(LocalDateTime t) {
		return t.format(DateTimeFormatter.ofPattern("MMM dd,, yyyy")).replace(",,", "th").replace(" 0", " ")
				.replace("1th", "1st").replace("2th", "2nd").replace("3th", "3rd");
	}
}
