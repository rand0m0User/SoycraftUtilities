package net.craftutil.Commands.completer;

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

public class TempBanCompleter implements TabCompleter {

	private static String[] NUMBERS = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
	private static String[] TIMES = new String[] { "w", "d", "h", "m", "s" };

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
			String currentArg = args[args.length - 1].toLowerCase(Locale.ROOT).trim();
			String lastArg = args[args.length - 2].toLowerCase(Locale.ROOT).trim();
			String filter = lastArg;
			String arg = "";
			if (currentArg.contains(":")) {
				String[] split = currentArg.split(":", 2);
				filter = split[0] + ":";
				if (split.length > 1) {
					arg = split[1];
				}
			} else {
				filter = "";
				arg = currentArg;
			}

			List<String> completions = new ArrayList<>();
			if (arg.chars().allMatch(Character::isDigit)) {
				boolean addNumbers = true;
				if (currentArg.length() > 0) {
					char lastChar = currentArg.charAt(currentArg.length() - 1);
					if (Character.isDigit(lastChar)) {
						completions.addAll(Arrays.asList(TIMES));
						addNumbers = false;
					}
				}
				if (addNumbers) {
					completions.addAll(Arrays.asList(NUMBERS));
				}
			}
			completions = new ArrayList<>(completions);
			for (int index = 0; index < completions.size(); index++) {
				completions.set(index, filter + arg + completions.get(index));
			}

			return StringUtil.copyPartialMatches(filter + arg, completions, new ArrayList<>(completions.size()));
		} else {
			String lastArg = args[args.length - 2].toLowerCase(Locale.ROOT).trim();
			String filter = lastArg;
			String arg = args[args.length - 1].toLowerCase(Locale.ROOT).trim();
			List<String> completions = new ArrayList<>();
			completions.addAll(Arrays.asList(Rules.getRules()));
			return StringUtil.copyPartialMatches(filter + arg, completions, new ArrayList<>(completions.size()));
		}
	}
}
