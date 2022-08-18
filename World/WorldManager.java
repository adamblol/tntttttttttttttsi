package adam.main.World;

import adam.main.Main;
import org.bukkit.*;
import org.bukkit.entity.Player;

public class WorldManager {

    public static World world = null;

    public static void createWorld() {
        WorldCreator worldCreator = new WorldCreator(Main.plugin.getConfig().getString("tntrun-world-name"));
        world = worldCreator.createWorld();
        world.setAutoSave(false);
        world.setDifficulty(Difficulty.PEACEFUL);
        world.setPVP(false);
    }

    public static void loadWorld(Player player) {
        if(world == null) {
            player.sendMessage(ChatColor.RED + "Create the world before trying to join it");
            return;
        }

        // if created send them to spawn point
        player.teleport(world.getSpawnLocation());
    }

    public static void unloadWorld() {
        if(world == null) {
            System.out.println("There is no world to unload");
            return;
        }

        Bukkit.unloadWorld(world, false);
    }

}
