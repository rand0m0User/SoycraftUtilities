package net.utils;

import net.md_5.bungee.api.ChatColor;

public class ColorChat {

	public static String chat(String string) {
		return ChatColor.translateAlternateColorCodes('&', string);
	}
}
