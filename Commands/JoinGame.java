package adam.main.Commands;

import adam.main.Game.Game;
import adam.main.Game.Lobby;
import adam.main.Main;
import adam.main.ScoreBoard.LobbyScoreBoard;
import adam.main.Tasks.LobbyCountDown;
import adam.main.World.WorldManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JoinGame implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player;

        if(sender instanceof Player) {
            player = (Player) sender;
        } else {
            return true;
        }

        // if player is already in the game
        if(Lobby.players.contains(player)) {
            player.sendMessage(ChatColor.GREEN + "You are already in this game");
            return true;
        }

        // handling joining the game if one is setup
        if(!Lobby.isLobbySetUp) {
            player.sendMessage(ChatColor.GREEN + "No game found. Creating one");
            WorldManager.unloadWorld();
            WorldManager.createWorld();
            WorldManager.loadWorld(player);
            player.sendMessage(ChatColor.GREEN + player.getDisplayName() + " has joined the lobby (1/" + Main.plugin.getConfig().getInt("max-players") + ")");
            Lobby.isLobbySetUp = true;
            Lobby.players.add(player);
            player.setGameMode(GameMode.SURVIVAL);
            player.setScoreboard(LobbyScoreBoard.openScoreBoard(1));
            return true;
        }

        // if a game is setup send them to it
        WorldManager.loadWorld(player);
        Lobby.isLobbySetUp = true;
        Lobby.players.add(player);
        Lobby.players.forEach(player1 -> {
            player1.sendMessage(ChatColor.GREEN + player.getDisplayName() + " has joined the lobby (" + Lobby.players.size() + "/" + Main.plugin.getConfig().getInt("max-players") + ")");
            player1.setScoreboard(LobbyScoreBoard.openScoreBoard(Lobby.players.size()));
        });
        player.setGameMode(GameMode.SURVIVAL);

        // if game size == min-players start a countdown
        if(Lobby.players.size() >= Main.plugin.getConfig().getInt("min-players")) {
            Lobby.players.forEach(player1 -> {
                player1.sendMessage(ChatColor.AQUA + "The game will begin in 20 seconds.");
            });

            new LobbyCountDown().runTaskTimer(Main.plugin, 0L, 20L);
        }

        return true;
    }
}
