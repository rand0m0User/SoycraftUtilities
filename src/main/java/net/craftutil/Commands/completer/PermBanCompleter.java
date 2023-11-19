/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author core 2 duo pc 2
 */
public class PermBanCompleter implements TabCompleter {

    private static String[] RULES = new String[]{
        "1 - You will not post, link to, or request illegal content.",
        "1.1 - Attempting to get the site taken down via DDOS, false reports, etc. is forbidden.",
        "2 - No advocacy, promotion of, or posting of pedophilia and pedophilic content.",
        "3 - Advertising for imageboards, discord servers, telegram channels, crypto projects, etc. is not permitted.",
        "3.1 - Participation in hostile or disruptive off-site communities or servers is forbidden.",
        "4 - Excessive spam such as linespam, catalog wiping, and using script bots is not permitted",
        "(craft) 2 - No malicious unwarranted griefing or continuous unecessary aggression.",
        "(craft) 3 - No chat flooding. This is very tolerant, just dont send like 50 messages a second.",
        "(craft) 4 - No ban evasion. Bans are shared, so you will be banned from the sharty if you ban evade on the 'craft."
    };

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 1) {
            List<String> completions = Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList());
            for (int index = 0; index < completions.size(); index++) {
                completions.set(index, completions.get(index));
            }
            return completions;
        } else {
            String lastArg = args[args.length - 2].toLowerCase(Locale.ROOT).trim();
            String filter = lastArg;
            String arg = "";
            List<String> completions = new ArrayList<>();
            completions.addAll(Arrays.asList(RULES));
            return StringUtil.copyPartialMatches(filter + arg, completions, new ArrayList<>(completions.size()));
        }
    }
}
