package net.craftutil.GUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.craftutil.Main;
import net.craftutil.Settings;
import net.craftutil.GUI.Items.Items;
import net.md_5.bungee.api.ChatColor;

public class SettingsGUI implements Listener {
	public final String invName = ChatColor.translateAlternateColorCodes('&', "&6&o&lSettings");

	private String clickto(boolean i) {
		return i ? "§cClick to Disable" : "§aClick to Enable";
	}

	public Inventory create() {
		Inventory inventory = Bukkit.createInventory(null, (9 * 5), invName);

		ArrayList<ItemStack> items = new ArrayList<>();

		for (int i = 0; i < 9; i++) {
			items.add(Items.blackglass());
		}

		items.add(stack(Material.TNT, Settings.Explosives, "§6§lExplosives" + enabledordisabled(Settings.Explosives),
				Arrays.asList("§7Whether or not players can use explosive items", " ", clickto(Settings.Explosives))));
		items.add(stack(Material.WRITABLE_BOOK, Settings.Wordfilters,
				"§6§lWordfilters" + enabledordisabled(Settings.Wordfilters),
				Arrays.asList("§7Modify player chat to replace certen words with \"safer\" replacements", " ",
						clickto(Settings.Wordfilters))));
		items.add(stack(Material.CHEST, Settings.Loot, "§6§lLoot logging" + enabledordisabled(Settings.Loot),
				Arrays.asList(
						"§7send a message in OP chat whenever a player generates loot, this info can be used to pinpoint where people hide griefing materials for later use",
						" ", clickto(Settings.Loot))));
		items.add(stack(Material.WRITABLE_BOOK, Settings.AprilFoolsOveride,
				"§6§lAprilFools Overide" + enabledordisabled(Settings.AprilFoolsOveride),
				Arrays.asList("§7enable the AprilFools wordfilters reguarless of the month", " ",
						clickto(Settings.AprilFoolsOveride))));
		items.add(stack(Material.CHERRY_SAPLING, Settings.JuneOveride,
				"§6§lJune Overide" + enabledordisabled(Settings.JuneOveride),
				Arrays.asList("§7enable all messages being ~-~rainbow text~-~ reguarless of the month", " ",
						clickto(Settings.JuneOveride))));
		items.add(stack(Material.SPRUCE_SAPLING, Settings.FestiveWordfiltersOveride,
				"§6§lFestive Overide" + enabledordisabled(Settings.FestiveWordfiltersOveride),
				Arrays.asList("§7enable the festive wordfilters reguarless of the month", " ",
						clickto(Settings.FestiveWordfiltersOveride))));
		items.add(stack(Material.SHULKER_BOX, Settings.BookBan,
				"§6§lBookBan handler" + enabledordisabled(Settings.BookBan),
				Arrays.asList("§7anti book ban event handler that crashes wouldbe book banners such as you know who", " ",
						clickto(Settings.BookBan))));

		for (int i = 0; i < 2; i++) {
			items.add(new ItemStack(Material.AIR));
		}

		for (int i = 0; i < 9; i++) {
			items.add(Items.blackglass());
			// items.add(stack(Material.BLACK_STAINED_GLASS_PANE, false, "§7⇧ General
			// Settings",
			// Arrays.asList("§7⇩ Bag Settings")));
		}

		for (int i = 0; i < 9; i++) {
			items.add(new ItemStack(Material.AIR));
		}

		for (int i = 0; i < 9; i++) {
			items.add(Items.blackglass());
		}

		inventory.setContents(items.toArray(new ItemStack[0]));
		return inventory;
	}

	private String enabledordisabled(boolean i) {
		return i ? " §a(Enabled)" : " §c(Disabled)";
	}

	public void handleClick(InventoryClickEvent ev) {
		if (!ev.getView().getTitle().equalsIgnoreCase(invName)) {
			return;
		}
		Player p = (Player) ev.getWhoClicked();
		ItemStack stack = ev.getCurrentItem();

		if (stack == null || stack.getType().equals(Material.BLACK_STAINED_GLASS_PANE)) {
			ev.setCancelled(true);
			return;
		}

		String[] name = stack.getItemMeta().getDisplayName().split(" ");
		String settingsname = String.join(" ", Arrays.copyOfRange(name, 0, name.length - 1));
		boolean isEnabled = name[name.length - 1].equalsIgnoreCase("§a(Enabled)");

		Settings s = new Settings();

		switch (settingsname) {
		case "§6§lExplosives" -> {
			s.Explosives(!isEnabled);
			if (isEnabled) {
				p.sendMessage("§cDisabled §aExplosives.");
			} else {
				p.sendMessage("§aEnabled Explosives.");
			}
			p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_LEATHER, 1, 1);
		}
		case "§6§lWordfilters" -> {
			s.Wordfilters(!isEnabled);
			if (isEnabled) {
				p.sendMessage("§cDisabled §aWordfilters.");
			} else {
				p.sendMessage("§aEnabled Wordfilters.");
			}
			p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_LEATHER, 1, 1);
		}
		case "§6§lLoot logging" -> {
			s.Loot(!isEnabled);
			if (isEnabled) {
				p.sendMessage("§cDisabled §aLoot logging.");
			} else {
				p.sendMessage("§aEnabled Loot logging.");
			}
			p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_LEATHER, 1, 1);
		}
		case "§6§lAprilFools Overide" -> {
			s.AprilFoolsOveride(!isEnabled);
			if (isEnabled) {
				p.sendMessage("§cDisabled §aAprilFools Overide.");
			} else {
				p.sendMessage("§aEnabled AprilFools Overide.");
			}
			p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_LEATHER, 1, 1);
		}
		case "§6§lJune Overide" -> {
			s.JuneOveride(!isEnabled);
			if (isEnabled) {
				p.sendMessage("§cDisabled §aJune Overide.");
			} else {
				p.sendMessage("§aEnabled June Overide.");
			}
			p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_LEATHER, 1, 1);
		}
		case "§6§lFestive Overide" -> {
			s.FestiveWordfiltersOveride(!isEnabled);
			if (isEnabled) {
				p.sendMessage("§cDisabled §aFestive Overide.");
			} else {
				p.sendMessage("§aEnabled Festive Overide.");
			}
			p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_LEATHER, 1, 1);
		}
		case "§6§lBookBan handler" -> {
			s.BookBan(!isEnabled);
			if (isEnabled) {
				p.sendMessage("§cDisabled §aBookBan handler.");
			} else {
				p.sendMessage("§aEnabled BookBan handler.");
			}
			p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_LEATHER, 1, 1);
		}
		}
		ev.setCancelled(true);
		p.openInventory(new SettingsGUI().create());
	}

	private ItemStack stack(Material mat, boolean ench, String name, List<String> lore) {
		ItemStack stack = new ItemStack(mat);
		ItemMeta meta = stack.getItemMeta();
		meta.setDisplayName(name);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.setLore(lore);
		stack.setItemMeta(meta);
		if (ench)
			stack.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 1);
		return stack;
	}

}
