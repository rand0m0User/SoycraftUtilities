package net.craftutil;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;
import net.craftutil.Commands.*;
import net.craftutil.Commands.completer.*;
import net.merged.admintrolling.Command.Troll;
import net.merged.admintrolling.Command.completer.TrollCompleter;
import net.merged.admintrolling.Listeners.*;
import org.bukkit.Location;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static ArrayList<Player> cantBuild = new ArrayList();
    public static ArrayList<Player> cantBreak = new ArrayList();
    public static ArrayList<Player> cantSpeek = new ArrayList();
    public static ConcurrentHashMap<Player, Location> frozen = new ConcurrentHashMap();

    public final Logger console = this.getLogger();

    //I ❤ BOILERPLATE
    //I ❤ BOILERPLATE
    //I ❤ BOILERPLATE
    @Override
    public void onEnable() {
        console.info("Initializing commands");
        initCommand("tempban", new TempBan(), new TempBanCompleter());
        initCommand("permban", new PermBan(), new PermBanCompleter());
        initCommand("troll", new Troll(), new TrollCompleter());
        initCommand("trolllist", new Troll(), null);
        console.info("Commands initialized");
        console.info("Initializing listeners");
        //listen(new DestructiveBlockListener()); //merge tnt warning
        //listen(new ChatListener()); //merge greentext
        //listen(new BlockListener()); //window breaker deturant and diamond farm

        //admintrolling plus (merged)
        listen(new BuildTroll());
        listen(new Break());
        listen(new Frozen());
        listen(new ChatListener());

        console.info("Listeners initialized");
        console.info("Plugin is enabled.");
    }
    //I ❤ BOILERPLATE
    //I ❤ BOILERPLATE
    //I ❤ BOILERPLATE

    public void listen(Listener l) {
        this.getServer().getPluginManager().registerEvents(l, this);
    }

    //duplicate code removal
    private void initCommand(String cmd, CommandExecutor Executor, TabCompleter Completer) {
        PluginCommand c = getCommand(cmd);
        if (c != null) {
            c.setExecutor(Executor);
            c.setTabCompleter(Completer);
        }
    }

}
