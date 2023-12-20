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

public class TempBan implements CommandExecutor {

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
			Duration d = parseTime(args[1]);
			String msg = "You have been banned from mc.soyjak.party for the following reason:\n\n"
					+ String.join(" ", args).replace(args[0], "").replace(args[1], "") + "\n\nYour ban was filed on "
					+ formatTime(LocalDateTime.now()) + " and expires on " + formatTime(LocalDateTime.now().plus(d))
					+ "\nwhich is " + d.toDays() + " days and "
					+ ((d.toDays() == 0) ? d.toHours() : d.toHours() % d.toDays()) + " hours" + " from now.\n\n"
					+ "According to our server, your IP is: " + ip + ". The name you were posting with \nwas " + args[0]
					+ ".";

			if (d.toDays() <= 3) {
				msg += "\n\nBecause of the short length of your ban, you may not appeal it."
						+ " Please check back when\nyour ban has expired.";
			}
			if (p == null) {
				// Bukkit.getBanList(Type.PROFILE).addBan(args[0], msg,
				// Date.from(LocalDateTime.now().plus(d).toInstant(ZoneOffset.UTC)),
				// sender.getName()).save();
				Bukkit.getOfflinePlayer(args[0]).ban(msg, d, sender.getName());
				sender.sendMessage("banned offline player: " + args[0] + ". \n" + msg);
			} else {
				p.getWorld().strikeLightning(p.getLocation());
				p.ban(msg, d, null);
				sender.sendMessage("banned player: " + args[0] + ". \n" + msg);
				Bukkit.broadcastMessage(DARK_RED + "(" + args[0].toUpperCase() + " WAS BANNED FOR THIS POST)");
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
