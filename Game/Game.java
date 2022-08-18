package adam.main.Game;

/*
all the stuff about the actual game will be handled/stored in here
*/

import adam.main.Main;
import adam.main.ScoreBoard.GameScoreBoard;
import adam.main.Tasks.EndGame;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Game {
    public static ArrayList<Player> players = new ArrayList<>();
    public static ArrayList<Player> playersDead = new ArrayList<>();
    public static boolean isGameInProgress = false;

    public static void startGame(Player player) {
        // game stuff
        isGameInProgress = true;
        player.sendMessage(ChatColor.YELLOW + "The game has begun");
        player.setScoreboard(GameScoreBoard.openScoreBoard(player, Lobby.players.size()));
    }

    public static void endGame() {
        players.forEach(player -> {
            player.setGameMode(GameMode.SPECTATOR);
            player.teleport(player.getWorld().getSpawnLocation());
            player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "The winner is: " + players.get(0).getDisplayName());
        });
        playersDead.forEach(player -> {
            player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "The winner is: " + players.get(0).getDisplayName());
        });

        // after 5 seconds teleport all to main lobby and unload main world
        new EndGame().runTaskLater(Main.plugin, 200L);
    }
}
