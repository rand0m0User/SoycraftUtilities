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

public class TempBan implements CommandExecutor {

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
			String timestr = "";
			try {
				timestr = args[1];
			} catch (Exception e) {
				sender.sendMessage(ColorChat.chat("&cyou must provide a ban duration (eg, 3d, 30d, 10m)!"));
				return true;
			}
			String reason = String.join(" ", args).replace(playerstr, "").replace(timestr, "");
			if (reason.strip() == "") {
				sender.sendMessage(ColorChat.chat("&cyou must provide a reason!"));
				return true;
			}
			Player p = Bukkit.getServer().getPlayer(playerstr);
			String ip = "<offline>";
			if (p != null) {
				ip = p.getAddress().getAddress().toString().replace("/", "");
			}
			Duration d = parseTime(timestr);
			String msg = "You have been banned from mc.soyjak.party for the following reason:\n\n" + reason
					+ "\n\nYour ban was filed on " + formatTime(LocalDateTime.now()) + " and expires on "
					+ formatTime(LocalDateTime.now().plus(d)) + "\nwhich is " + d.toDays() + " days and "
					+ ((d.toDays() == 0) ? d.toHours() : d.toHours() % d.toDays()) + " hours" + " from now.\n\n"
					+ "According to our server, your IP is: " + ip + ". The name you were posting with \nwas "
					+ playerstr + ".";
			if (d.toDays() <= 3) {
				msg += "\n\nBecause of the short length of your ban, you may not appeal it."
						+ " Please check back when\nyour ban has expired.";
			}
			if (p == null) {
				Bukkit.getOfflinePlayer(playerstr).ban(msg, d, sender.getName());
				sender.sendMessage("banned offline player: " + playerstr + ". \n" + msg);
			} else {
				p.getWorld().strikeLightning(p.getLocation());
				p.ban(msg, d, null);
				sender.sendMessage("banned player: " + playerstr + ". \n" + msg);
				Bukkit.broadcastMessage(ColorChat.chat("&4(" + playerstr.toUpperCase() + " WAS BANNED FOR THIS POST)"));
			}
			return true;
		}
		return true;
	}

	private String formatTime(LocalDateTime t) {
		return t.format(DateTimeFormatter.ofPattern("MMM dd,, yyyy")).replace(",,", "th").replace(" 0", " ")
				.replace("1th", "1st").replace("2th", "2nd").replace("3th", "3rd");
	}

	public Duration parseTime(String arg) {
		switch (arg.charAt(arg.length() - 1)) {
		case 's':
			return Duration.ofSeconds(Integer.parseInt(arg.replace('s', ' ').trim()));
		case 'm':
			return Duration.ofMinutes(Integer.parseInt(arg.replace('m', ' ').trim()));
		case 'h':
			return Duration.ofHours(Integer.parseInt(arg.replace('h', ' ').trim()));
		case 'd':
			return Duration.ofDays(Integer.parseInt(arg.replace('d', ' ').trim()));
		default:
			return Duration.ZERO;
		}
	}
}
