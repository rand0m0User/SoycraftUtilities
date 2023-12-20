package net.craftutil;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import org.bukkit.Location;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import net.craftutil.Commands.*;
import net.craftutil.Commands.completer.*;
import net.craftutil.Listeners.*;
import net.merged.BanHam.Commands.*;
import net.merged.BanHam.Listeners.*;
import net.merged.TNT.DestructiveBlockListener;
import net.merged.admintrolling.Command.Troll;
import net.merged.admintrolling.Command.completer.TrollCompleter;
import net.merged.admintrolling.Listeners.*;
import net.merged.greentextPlus.GreentextChatListener;

public class Main extends JavaPlugin {

	public static ArrayList<Player> cantBuild = new ArrayList<Player>();
	public static ArrayList<Player> cantBreak = new ArrayList<Player>();
	public static ArrayList<Player> cantSpeek = new ArrayList<Player>();
	public static ConcurrentHashMap<Player, Location> frozen = new ConcurrentHashMap<Player, Location>();

	public final Logger console = this.getLogger();

	// I ❤ BOILERPLATE
	// I ❤ BOILERPLATE
	// I ❤ BOILERPLATE
	@Override
	public void onEnable() {
		console.info("Initializing commands");
		initCommand("tempban", new TempBan(), new TempBanCompleter());
		initCommand("permban", new PermBan(), new PermBanCompleter());

		// merged: admintrolling (plus)
		initCommand("troll", new Troll(), new TrollCompleter());
		initCommand("trolllist", new Troll(), null);

		// merged: ban hammer
		initCommand("banhammer", new GiveBanhammerCommand(), null);
		initCommand("ipbanhammer", new GiveIPBanhammerCommand(), null); // (plus)
		initCommand("kickhammer", new GiveKickHammerCommand(), null);

		console.info("Commands initialized");
		console.info("Initializing listeners");

		// disabled in development shit
		// listen(new BlockListener()); //window breaker deturant and diamond farm

		// salty knot datamining, may be removed in the future
		listen(new LootGenerateListener());

		//usable crafting table (like a tool)
		listen(new PlayerInteractListener());

		// merged: admintrolling (plus)
		listen(new BuildTroll());
		listen(new Break());
		listen(new Frozen());
		listen(new ChatListener());

		// merged: ban hammer
		listen(new BanHammerEvent());
		listen(new IPBanHammerEvent());
		listen(new KickHammerEvent());

		// merged: greentext plus
		listen(new GreentextChatListener());

		// merged: tnt warning (plus)
		listen(new DestructiveBlockListener());

		console.info("Listeners initialized");
		console.info("Plugin is enabled.");
	}
	// I ❤ BOILERPLATE
	// I ❤ BOILERPLATE
	// I ❤ BOILERPLATE

	public void listen(Listener l) {
		this.getServer().getPluginManager().registerEvents(l, this);
	}

	// duplicate code removal
	private void initCommand(String cmd, CommandExecutor Executor, TabCompleter Completer) {
		PluginCommand c = getCommand(cmd);
		if (c != null) {
			c.setExecutor(Executor);
			c.setTabCompleter(Completer);
		}
	}
}
