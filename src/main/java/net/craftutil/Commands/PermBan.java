package net.craftutil.Commands;

import static org.bukkit.ChatColor.DARK_RED;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PermBan implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
			@NotNull String[] args) {
		if (sender.isOp()) {
			Player p = Bukkit.getServer().getPlayer(args[0]);
			String ip = "<offline>";
			if (p != null) {
				ip = p.getAddress().getAddress().toString().replace("/", "");
			}
			String msg = "You have been permanently banned from mc.soyjak.party for the following reason:\n\n"
					+ String.join(" ", args).replace(args[0], "") + "\n\nYour ban was filed on "
					+ formatTime(LocalDateTime.now()) + " and will NOT expire.\n\n"
					+ "According to our server, your IP is: " + ip + ". The name you were posting with \nwas " + args[0]
					+ ".\n\n";

			if (p == null) {
				// Bukkit.getBanList(BanList.Type.PROFILE).addBan(args[0], msg, (Date) null,
				// sender.getName()).save();
				Bukkit.getOfflinePlayer(args[0]).ban(msg, (Duration) null, sender.getName());
				sender.sendMessage("banned offline player: " + args[0] + ". \n" + msg);
			} else {
				p.getWorld().strikeLightning(p.getLocation());
				p.ban(msg, (Duration) null, null);
				sender.sendMessage("banned " + args[0] + ": \n" + msg);
				Bukkit.broadcastMessage(
						DARK_RED + "(" + args[0].toUpperCase() + " WAS PERMANENTLY BANNED FOR THIS POST)");
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
