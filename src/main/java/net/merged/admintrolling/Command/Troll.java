package net.merged.admintrolling.Command;

import static net.craftutil.Main.cantBreak;
import static net.craftutil.Main.cantBuild;
import static net.craftutil.Main.cantSpeek;
import static net.craftutil.Main.frozen;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import net.utils.ColorChat;

public class Troll implements CommandExecutor {

	public void err(CommandSender sender) {
		sender.sendMessage(ColorChat.chat("&4Sorry, that player doesnt exist..."));
	}

	public void succes(CommandSender sender, String cmd, String pln) {
		sender.sendMessage(ColorChat.chat("&bSuccesfully " + cmd + "ed player&3 " + pln + "&b!"));
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
			@NotNull String[] args) {
		if (sender.isOp()) {
			if (args.length >= 2) {
				Player pl = Bukkit.getPlayer(args[0]);
				String pln = args[0];
				Location loc = null;
				if (pl != null) {
					pln = pl.getName();
					loc = pl.getLocation();
				}

				switch (args[1]) {
				case "creeper":
					if (loc == null) {
						err(sender);
						break;
					}
					pl.getLocation().getWorld().spawnEntity(loc, EntityType.CREEPER);
					succes(sender, args[1], pln);
					break;

				case "tnt":
					if (loc == null) {
						err(sender);
						break;
					}
					loc.getWorld().spawnEntity(loc, EntityType.PRIMED_TNT);
					succes(sender, args[1], pln);

					break;

				case "smite":
					if (loc == null) {
						err(sender);
						break;
					}
					loc.getWorld().strikeLightning(loc);
					succes(sender, args[1], pln);
					break;

				case "trap":
					if (loc == null) {
						err(sender);
						break;
					}
					pl.getWorld().getBlockAt(loc.subtract(0.0D, 1.0D, 0.0D)).setType(Material.BEDROCK);
					pl.getWorld().getBlockAt(loc.add(0.0D, 3.0D, 0.0D)).setType(Material.BEDROCK);
					pl.getWorld().getBlockAt(loc.add(0.0D, -2.0D, 1.0D)).setType(Material.BEDROCK);
					pl.getWorld().getBlockAt(loc.add(1.0D, 0.0D, -1.0D)).setType(Material.BEDROCK);
					pl.getWorld().getBlockAt(loc.add(-2.0D, 0.0D, 0.0D)).setType(Material.BEDROCK);
					pl.getWorld().getBlockAt(loc.add(1.0D, 0.0D, -1.0D)).setType(Material.BEDROCK);
					pl.getWorld().getBlockAt(loc.add(0.0D, 1.0D, 2.0D)).setType(Material.BARRIER);
					pl.getWorld().getBlockAt(loc.add(1.0D, 0.0D, -1.0D)).setType(Material.BARRIER);
					pl.getWorld().getBlockAt(loc.add(-2.0D, 0.0D, 0.0D)).setType(Material.BARRIER);
					pl.getWorld().getBlockAt(loc.add(1.0D, 0.0D, -1.0D)).setType(Material.BARRIER);
					succes(sender, args[1], pln);
					break;

				case "fakeban":
					pl.kickPlayer(ColorChat.chat(
							"&aUnfortunetly, you have been flagged by our anticheat for &3Cheating/Hacking&a.\n If you'd like to be unbanned, please appeal here: \n&3https://bit.ly/3F5Dihz"));
					succes(sender, args[1] + "n", pln);
					break;

				case "crashkick":
					if (loc == null) {
						err(sender);
						break;
					}
					String s = "";
					for (int i = 0; i < 16300; i++) { // use wisely, this creates ALOT of lag
						s += "AAAAAAAAAAAAAAAA";
					}
					pl.kickPlayer(ColorChat.chat("&k" + s));
					succes(sender, args[1], pln);
					break;

				case "fakejoin":
					Bukkit.broadcastMessage(ColorChat.chat("&e" + args[0] + " joined the game"));
					succes(sender, args[1], pln);
					break;

				case "fakeleave":
					Bukkit.broadcastMessage(ColorChat.chat("&e" + args[0] + " left the game"));
					succes(sender, args[1], pln);
					break;

				case "fakeop":
					pl.sendMessage(
							ColorChat.chat("&7&o[" + sender.getName() + ": Made " + pln + " a server operator]"));
					succes(sender, args[1], pln);
					break;
				case "push":
					if (loc == null) {
						err(sender);
						break;
					}
					pl.setVelocity(pl.getVelocity().setY(0));
					pl.setVelocity(pl.getLocation().getDirection().multiply(-4.1D));
					pl.setVelocity(pl.getVelocity().setY(0));
					succes(sender, args[1], pln);
					break;
				case "launch":
					if (loc == null) {
						err(sender);
						break;
					}
					pl.setVelocity(pl.getVelocity().setY(10));
					succes(sender, args[1], pln);
					break;
				case "burn":
					if (loc == null) {
						err(sender);
						break;
					}
					Block tBlock = pl.getLocation().getBlock();
					tBlock.setType(Material.FIRE);
					succes(sender, args[1], pln);
					break;

				case "buildtroll":
					if (loc == null) {
						err(sender);
						break;
					}
					if (!cantBuild.contains(pl)) {
						cantBuild.add(pl);
						sender.sendMessage(
								ColorChat.chat("&bSuccesfully disallowed building for player&3 " + pln + "&b!"));
					} else {
						cantBuild.remove(pl);
						sender.sendMessage(
								ColorChat.chat("&bSuccesfully reallowed building for player&3 " + pln + "&b!"));
					}
					break;
				case "breaktroll":
					if (loc == null) {
						err(sender);
						break;
					}
					if (!cantBreak.contains(pl)) {
						cantBreak.add(pl);
						sender.sendMessage(
								ColorChat.chat("&bSuccesfully disallowed breaking for player&3 " + pln + "&b!"));
					} else {
						cantBreak.remove(pl);
						sender.sendMessage(
								ColorChat.chat("&bSuccesfully reallowed breaking for player&3 " + pln + "&b!"));
					}
					break;
				case "adventure":
					if (loc == null) {
						err(sender);
						break;
					}
					if (!cantBreak.contains(pl) && !cantBuild.contains(pl)) {
						cantBreak.add(pl);
						cantBuild.add(pl);
						sender.sendMessage(ColorChat
								.chat("&bSuccesfully disallowed breaking and building for player&3 " + pln + "&b!"));
					} else {
						cantBreak.remove(pl);
						cantBuild.remove(pl);
						sender.sendMessage(ColorChat
								.chat("&bSuccesfully reallowed breaking and building for player&3 " + pln + "&b!"));
					}
					break;

				case "freeze":
					if (loc == null) {
						err(sender);
						break;
					}
					if (!frozen.containsKey(pl)) {
						frozen.put(pl, pl.getLocation());
						sender.sendMessage(ColorChat.chat("&bSuccesfully froze player&3 " + pln + "&b!"));
					} else {
						frozen.remove(pl);
						sender.sendMessage(ColorChat.chat("&bSuccesfully unfroze player&3 " + pln + "&b!"));
					}
					break;
				case "mute":
					if (loc == null) {
						err(sender);
						break;
					}
					if (!cantSpeek.contains(pl)) {
						cantSpeek.add(pl);
						sender.sendMessage(ColorChat.chat("&bSuccesfully muted player&3 " + pln + "&b!"));
					} else {
						cantSpeek.remove(pl);
						sender.sendMessage(ColorChat.chat("&bSuccesfully unmuted player&3 " + pln + "&b!"));
					}
					break;
				default:
					break;
				}

				if (args[1].equalsIgnoreCase("forceChat") && args.length >= 3) {
					String path = "";
					for (int i = 2; i < args.length; ++i) {
						if (i != args.length - 1) {
							path = path + args[i] + " ";
						} else {
							path = path + args[i];
						}
					}
					//pl.chat(path);
					Bukkit.broadcastMessage("<" + args[0] + "> " + path);

					sender.sendMessage(ColorChat.chat("&bSuccesfully made &3" + pln + " &bsay &3" + path + "&b!"));
				}
			}
		}
		return true;

	}

}
