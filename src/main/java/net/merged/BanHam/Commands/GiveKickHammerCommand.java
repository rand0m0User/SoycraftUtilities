package net.merged.BanHam.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class GiveKickHammerCommand implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
			@NotNull String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "ERROR: \t console can't use this command\n\tsorry");
			return true;
		} else {
			Player player = (Player) sender;
			if (!player.hasPermission("BanHammer.CanUseBanhammer")) {
				return true;
			} else {
				ItemStack Kh = new ItemStack(Material.DIAMOND_AXE, 1);
				ItemMeta KhMeta = Kh.getItemMeta();
				KhMeta.setDisplayName(ChatColor.YELLOW + "KICK HAMMER");
				KhMeta.setLocalizedName("kick_hammer");
				List<String> lore = new ArrayList<>();
				lore.add(ChatColor.LIGHT_PURPLE + "Hit a noob with it!");
				KhMeta.setLore(lore);
				Kh.setItemMeta(KhMeta);
				player.getInventory().addItem(Kh);
			}
		}

		return true;
	}
}
