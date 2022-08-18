package adam.main.Game;

/*
all the stuff about the actual game will be handled/stored in here
*/

import adam.main.Main;
import adam.main.ScoreBoard.GameScoreBoard;
import adam.main.Tasks.EndGame;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    public static ArrayList<Player> players = new ArrayList<>();
    public static ArrayList<Player> playersDead = new ArrayList<>();
    public static boolean isGameInProgress = false;
    // power ups xd
    public static HashMap<Player, Integer> jumpsLeft = new HashMap<>();

    public static void startGame(Player player) {
        // game stuff
        isGameInProgress = true;
        jumpsLeft.put(player, 5);
        player.sendMessage(ChatColor.YELLOW + "The game has begun");
        player.setScoreboard(GameScoreBoard.openScoreBoard(player, Lobby.players.size()));
        // give feather
        ItemStack doubleJumpFeather = new ItemStack(Material.FEATHER);
        ItemMeta doubleMeta = doubleJumpFeather.getItemMeta();
        doubleMeta.setDisplayName(ChatColor.GREEN + "Double Jump");
        doubleJumpFeather.setItemMeta(doubleMeta);
        player.getInventory().setItem(0, doubleJumpFeather);
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
