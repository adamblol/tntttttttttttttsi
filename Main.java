package adam.main;

import adam.main.Commands.JoinGame;
import adam.main.Commands.LeaveGame;
import adam.main.Listeners.BlockBreakEvent;
import adam.main.Listeners.PlayerDamageEvent;
import adam.main.Listeners.PlayerMoveEvent;
import adam.main.World.WorldManager;
import org.bukkit.plugin.java.JavaPlugin;

/*
only handles 1 game idk how to do multiple
 */

public final class Main extends JavaPlugin {

    public static Main plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Tnt Run Plugin Has Loaded");
        plugin = this;

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        // creating the world on load of plugin
        WorldManager.createWorld();

        // register commands
        getCommand("joingame").setExecutor(new JoinGame());
        getCommand("leavegame").setExecutor(new LeaveGame());

        // register events
        getServer().getPluginManager().registerEvents(new PlayerMoveEvent(), this);
        getServer().getPluginManager().registerEvents(new BlockBreakEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerDamageEvent(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Tnt Run Plugin Has Disabled");
    }
}
