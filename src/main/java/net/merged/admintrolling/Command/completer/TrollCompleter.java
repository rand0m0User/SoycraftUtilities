package net.merged.admintrolling.Command.completer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

public class TrollCompleter implements TabCompleter {

	private static String[] TROLLS = new String[] { "forcechat", "fakeop", "launch", "push", "burn", "buildtroll",
			"breaktroll", "adventure", "freeze", "mute", "creeper", "tnt", "smite", "trap", "fakeban", "crashkick",
			"fakejoin", "fakeleave" };

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 1) {
			List<String> completions = Bukkit.getOnlinePlayers().stream().map(Player::getName)
					.collect(Collectors.toList());
			for (int index = 0; index < completions.size(); index++) {
				completions.set(index, completions.get(index));
			}
			return completions;
		} else if (args.length == 2) {
			String argument1 = args[1].toLowerCase(Locale.ROOT);
			List<String> completions = new ArrayList<>(Arrays.asList(TROLLS));
			return StringUtil.copyPartialMatches(argument1, completions, new ArrayList<>(completions.size()));
		} else {
			return Arrays.asList("");
		}
	}
}
