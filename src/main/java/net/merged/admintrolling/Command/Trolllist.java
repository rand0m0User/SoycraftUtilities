package net.merged.admintrolling.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import net.utils.ColorChat;

public class Trolllist implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
			@NotNull String[] args) {
		if (sender.isOp()) {
			sender.sendMessage(
					ColorChat.chat("&b/troll &3<Player> forcechat <Message> &b: Forces a player to say something"));
			sender.sendMessage(ColorChat.chat("&b/troll &3<Player> fakeop &b: Makes the user think they got op"));
			sender.sendMessage(ColorChat.chat("&b/troll &3<Player> fakedeop &b: Makes the user think they lost op"));
			sender.sendMessage(ColorChat.chat("&b/troll &3<Player> push &b: Pushes a user"));
			sender.sendMessage(ColorChat.chat("&b/troll &3<Player> launches &b: Launches a user up into the air"));
			sender.sendMessage(ColorChat.chat("&b/troll &3<Player> burn &b: Burns a user"));
			sender.sendMessage(ColorChat.chat("&b/troll &3<Player> buildtroll &b: Makes a user unable to build"));
			sender.sendMessage(
					ColorChat.chat("&b/troll &3<Player> breaktroll &b: Makes a user unable to break blocks"));
			sender.sendMessage(ColorChat.chat("&b/troll &3<Player> freeze &b: Freezes or unfreezes a player"));
			sender.sendMessage(
					ColorChat.chat("&b/troll &3<Player> crashkick &b: kicks the player with obfuscated text"));
			sender.sendMessage(ColorChat.chat("&b/troll &3<Player> creeper &b: Spawns a creeper on a player"));
			sender.sendMessage(ColorChat.chat("&b/troll &3<Player> tnt &b: Spawns tnt on a player"));
			sender.sendMessage(ColorChat.chat("&b/troll &3<Player> smite &b: Strikes lightning on a player"));
			sender.sendMessage(
					ColorChat.chat("&b/troll &3<Player> trap &b: Traps a player in a bedrock and barrier box"));
			sender.sendMessage(ColorChat
					.chat("&b/troll &3<Player> fakeban &b: Gives a player a fake ban message with a rickroll link"));
			sender.sendMessage(ColorChat.chat(
					"&b/troll &3<Name> fakejoin &b: Gives everyone on the server a fakejoin message with a specific name"));
			sender.sendMessage(ColorChat.chat(
					"&b/troll &3<Name> fakeleave &b: Gives everyone on the server a fakeleave message with a specific name"));
		}
		return true;
	}
}
