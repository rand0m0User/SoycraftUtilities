package net.merged.BanHam.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import net.utils.ColorChat;

public class BanHammerEvent implements Listener {
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onHit(EntityDamageByEntityEvent e) {
		Entity Damager = e.getDamager();
		Entity Banned = e.getEntity();
		if (Damager instanceof Player) {
			Player DamagerP = (Player) Damager;
			ItemStack stack = DamagerP.getInventory().getItemInMainHand();
			if (stack.getType() != Material.AIR && stack.hasItemMeta()
					&& stack.getItemMeta().getLocalizedName().equals("ban_hammer")) {
				if (DamagerP.hasPermission("BanHammer.CanUseBanhammer")) {
					if (!(Banned instanceof Player)) {
						DamagerP.sendMessage(ColorChat
								.chat("&cYou can't ban a " + Banned.getType().name().toLowerCase().replace("_", " ")));
					} else {
						Player BannedP = (Player) Banned;
						if (BannedP.isOp()) {
							DamagerP.sendMessage(ColorChat.chat("&cYou can't ban an operator"));

						} else {
							// send command
							Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "ban "
									+ BannedP.getDisplayName() + " You have been hit by the ban hammer, literally");
							DamagerP.sendMessage(
									ColorChat.chat("&3You have sucessfully deleted " + BannedP.getDisplayName()));

							// public message
							Bukkit.broadcastMessage(ColorChat.chat(
									"&3" + BannedP.getDisplayName() + " &R&6Has been hit by the ban hammer, literally"));

							// play sound
							for (Player p : Bukkit.getOnlinePlayers()) {
								p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_YES, 1000000, 100);
							}

							// out with a bang
							World world = BannedP.getWorld();
							world.strikeLightning(BannedP.getLocation());

						}
					}
				} else {
					DamagerP.sendMessage(ColorChat
							.chat("&cYou can't ban a " + Banned.getType().name().toLowerCase().replace("_", " ")));
					for (int i = 0; i <= 15; i++) {
						DamagerP.getWorld().strikeLightning(DamagerP.getLocation());
					}
					DamagerP.damage(Integer.MAX_VALUE);
					DamagerP.sendMessage(ColorChat.chat("&3YOU ARE NOT WORTHY"));
				}
				e.setCancelled(true);
			}
		}
	}
}
