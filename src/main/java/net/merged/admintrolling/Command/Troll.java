package net.merged.admintrolling.Command;

import org.jetbrains.annotations.NotNull;
import static net.craftutil.Main.*;
import net.merged.admintrolling.utils.ColorChat;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class Troll implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender.isOp()) {
            if (args.length >= 2) {
                Player pl = Bukkit.getPlayer(args[0]);
                String pln = pl.getName();
                Location loc = pl.getLocation();
                String path;
                switch (args[1]) {
                    case "creeper":
                        pl.getLocation().getWorld().spawnEntity(loc, EntityType.CREEPER);
                        sender.sendMessage(ColorChat.chat("&bSuccesfully creepered player&3 " + pln + "&b!"));
                        break;

                    case "tnt":
                        loc.getWorld().spawnEntity(loc, EntityType.PRIMED_TNT);
                        sender.sendMessage(ColorChat.chat("&bSuccesfully tnt'd player&3 " + pln + "&b!"));
                        break;

                    case "smite":
                        loc.getWorld().strikeLightning(loc);
                        sender.sendMessage(ColorChat.chat("&bSuccesfully smited player&3 " + pln + "&b!"));
                        break;

                    case "trap":
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
                        sender.sendMessage(ColorChat.chat("&bSuccesfully trapped player&3 " + pln + "&b!"));
                        break;

                    case "fakeban":
                        pl.kickPlayer(ColorChat.chat("&aUnfortunetly, you have been flagged by our anticheat for &3Cheating/Hacking&a.\n If you'd like to be unbanned, please appeal here: \n&3https://bit.ly/3F5Dihz"));
                        sender.sendMessage(ColorChat.chat("&bSuccesfully fakebanned player&3 " + pln + "&b!"));
                        break;

                    case "fakejoin":
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            p.sendMessage(ColorChat.chat("&e" + args[0] + " joined the game"));
                        }
                        sender.sendMessage(ColorChat.chat("&bSuccesfully fakejoined player name &3" + args[0] + "&b!"));
                        break;

                    case "fakeleave":
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            p.sendMessage(ColorChat.chat("&e" + args[0] + " left the game"));
                        }
                        sender.sendMessage(ColorChat.chat("&bSuccesfully fakeleft player name &3" + args[0] + "&b!"));
                        break;
                }
                if (args.length >= 2) {
                    switch (args[1]) {
                        case "fakeOp":
                            pl.sendMessage(ColorChat.chat("&7&o[" + sender.getName() + ": Made " + pln + " a server operator]"));
                            sender.sendMessage(ColorChat.chat("&bSent &3" + pln + " &ba fake op message!"));
                            break;
                        case "push":
                            pl.setVelocity(pl.getVelocity().setY(0));
                            pl.setVelocity(pl.getLocation().getDirection().multiply(-4.1D));
                            pl.setVelocity(pl.getVelocity().setY(0));
                            sender.sendMessage(ColorChat.chat("&bPushed player &3" + pln + "&b!"));
                            break;
                        case "launch":
                            pl.setVelocity(pl.getVelocity().setY(10));
                            sender.sendMessage(ColorChat.chat("&bLaunched player &3" + pln + "&b!"));
                            break;
                        case "burn":
                            Block tBlock = pl.getLocation().getBlock();
                            tBlock.setType(Material.FIRE);
                            sender.sendMessage(ColorChat.chat("&bSuccesfully burned player&3 " + pln + "&b!"));
                            break;

                        case "buildtroll":
                            if (!cantBuild.contains(pl)) {
                                cantBuild.add(pl);
                                sender.sendMessage(ColorChat.chat("&bSuccesfully disallowed building for player&3 " + pln + "&b!"));
                            } else {
                                cantBuild.remove(pl);
                                sender.sendMessage(ColorChat.chat("&bSuccesfully reallowed building for player&3 " + pln + "&b!"));
                            }
                            break;
                        case "breaktroll":
                            if (!cantBreak.contains(pl)) {
                                cantBreak.add(pl);
                                sender.sendMessage(ColorChat.chat("&bSuccesfully disallowed breaking for player&3 " + pln + "&b!"));
                            } else {
                                cantBreak.remove(pl);
                                sender.sendMessage(ColorChat.chat("&bSuccesfully reallowed breaking for player&3 " + pln + "&b!"));
                            }
                            break;
                        case "adventure":
                            if (!cantBreak.contains(pl) && !cantBuild.contains(pl)) {
                                cantBreak.add(pl);
                                cantBuild.add(pl);
                                sender.sendMessage(ColorChat.chat("&bSuccesfully disallowed breaking and building for player&3 " + pln + "&b!"));
                            } else {
                                cantBreak.remove(pl);
                                cantBuild.remove(pl);
                                sender.sendMessage(ColorChat.chat("&bSuccesfully reallowed breaking and building for player&3 " + pln + "&b!"));
                            }
                            break;

                        case "freeze":
                            if (!frozen.containsKey(pl)) {
                                frozen.put(pl, pl.getLocation());
                                sender.sendMessage(ColorChat.chat("&bSuccesfully froze player&3 " + pln + "&b!"));
                            } else {
                                frozen.remove(pl);
                                sender.sendMessage(ColorChat.chat("&bSuccesfully unfroze player&3 " + pln + "&b!"));
                            }
                            break;
                        case "mute":
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
                }
                if (args[1].equalsIgnoreCase("forceChat") && args.length >= 3) {
                    path = "";
                    for (int i = 2; i < args.length; ++i) {
                        if (i != args.length - 1) {
                            path = path + args[i] + " ";
                        } else {
                            path = path + args[i];
                        }
                    }
                    pl.chat(path);
                    sender.sendMessage(ColorChat.chat("&bSuccesfully made &3" + pln + " &bsay &3" + path + "&b!"));
                }
            }
        }
        return true;
    }
}
