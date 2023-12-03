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

public class PermBanCompleter implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 1) {
			List<String> completions = Bukkit.getOnlinePlayers().stream().map(Player::getName)
					.collect(Collectors.toList());
			for (int index = 0; index < completions.size(); index++) {
				completions.set(index, completions.get(index));
			}
			return completions;
		} else {
			String lastArg = args[args.length - 2].toLowerCase(Locale.ROOT).trim();
			String filter = lastArg;
			String arg = "";
			List<String> completions = new ArrayList<>();
			completions.addAll(Arrays.asList(Rules.getRules()));
			return StringUtil.copyPartialMatches(filter + arg, completions, new ArrayList<>(completions.size()));
		}
	}
}
