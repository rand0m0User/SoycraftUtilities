package net.craftutil;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class Settings {

	public static boolean AprilFoolsDisable;
	public static boolean AprilFoolsOveride;
	public static boolean BookBan;
	public static boolean Explosives;
	public static boolean FestiveWordfiltersDisable;
	public static boolean FestiveWordfiltersOveride;
	public static boolean JuneDisable;
	public static boolean JuneOveride;
	public static boolean Loot;
	public static boolean Wordfilters;

	private static FileConfiguration Config;
	private static File configFile;

	private static final Plugin plugin = Main.plugin;

	public void AprilFoolsDisable(boolean bol) {
		AprilFoolsDisable = bol;
		Config.set("Main.AprilFoolsDisable", bol);
	}

	public void AprilFoolsOveride(boolean bol) {
		AprilFoolsOveride = bol;
		Config.set("Main.AprilFoolsOveride", bol);
	}

	public void BookBan(boolean bol) {
		BookBan = bol;
		Config.set("Main.BookBan", bol);
	}

	public void Explosives(boolean bol) {
		Explosives = bol;
		Config.set("Main.Explosives", bol);
	}

	public void FestiveWordfiltersDisable(boolean bol) {
		FestiveWordfiltersDisable = bol;
		Config.set("Main.FestiveWordfiltersDisable", bol);
	}

	public void FestiveWordfiltersOveride(boolean bol) {
		FestiveWordfiltersOveride = bol;
		Config.set("Main.FestiveWordfiltersOveride", bol);
	}

	public void JuneDisable(boolean bol) {
		JuneDisable = bol;
		Config.set("Main.JuneDisable", bol);
	}

	public void JuneOveride(boolean bol) {
		JuneOveride = bol;
		Config.set("Main.JuneOveride", bol);
	}

	public void Loot(boolean bol) {
		Loot = bol;
		Config.set("Main.Loot", bol);
	}

	public void save() {
		try {
			Config.save(configFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setup() {
		SetupConfig();
		AprilFoolsDisable(Config.getBoolean("Main.AprilFoolsDisable"));
		AprilFoolsOveride(Config.getBoolean("Main.AprilFoolsOveride"));
		BookBan(Config.getBoolean("Main.BookBan"));
		Explosives(Config.getBoolean("Main.Explosives"));
		FestiveWordfiltersDisable(Config.getBoolean("Main.FestiveWordfiltersDisable"));
		FestiveWordfiltersOveride(Config.getBoolean("Main.FestiveWordfiltersOveride"));
		Loot(Config.getBoolean("Main.Loot"));
		JuneDisable(Config.getBoolean("Main.JuneDisable"));
		JuneOveride(Config.getBoolean("Main.JuneOveride"));
		Wordfilters(Config.getBoolean("Main.Wordfilters"));
	}

	public void SetupConfig() {
		configFile = new File(plugin.getDataFolder(), "config.yml");
		if (!configFile.exists() || Bukkit.getOnlinePlayers().isEmpty()) {
			configFile.getParentFile().mkdir();
			plugin.saveResource("config.yml", false);
		}
		Config = new YamlConfiguration();
		try {
			Config.load(configFile);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}

	public void Wordfilters(boolean bol) {
		Wordfilters = bol;
		Config.set("Main.Wordfilters", bol);
	}
}
