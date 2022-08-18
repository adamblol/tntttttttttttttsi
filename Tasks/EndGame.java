package adam.main.Tasks;

import adam.main.Game.Game;
import adam.main.Game.Lobby;
import adam.main.Main;
import adam.main.World.WorldManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.scheduler.BukkitRunnable;

public class EndGame extends BukkitRunnable {
    @Override
    public void run() {
        Game.players.forEach(player -> {
            player.teleport(Bukkit.getWorld(Main.plugin.getConfig().getString("main-world-name")).getSpawnLocation());
            player.setGameMode(GameMode.CREATIVE);
            player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        });
        Game.playersDead.forEach(player -> {
            player.teleport(Bukkit.getWorld(Main.plugin.getConfig().getString("main-world-name")).getSpawnLocation());
            player.setGameMode(GameMode.CREATIVE);
            player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        });

        // reset values to default
        Game.players.clear();
        Game.playersDead.clear();
        Game.isGameInProgress = false;
        Lobby.players.clear();
        Lobby.isLobbySetUp = false;
        WorldManager.unloadWorld();
    }
}
