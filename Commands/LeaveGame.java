package adam.main.Commands;

import adam.main.Game.Lobby;
import adam.main.Main;
import adam.main.Tasks.LobbyCountDown;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LeaveGame implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player;

        if(sender instanceof Player) {
            player = (Player) sender;
        } else {
            return true;
        }

        // handling leaving the game if they are in one

        // if they arent in a game and state is lobby
        if(!Lobby.players.contains(player)) {
            player.sendMessage(ChatColor.RED + "You have to be in a game to use this command");
            return true;
        }

        // if game state is lobby
        if(Lobby.players.contains(player)) {
            if(Lobby.players.size() <= 1) {
                Lobby.isLobbySetUp = false;
            } else {
                int players = Lobby.players.size() - 1;
                Lobby.players.forEach(player1 -> {
                    player1.sendMessage(ChatColor.GREEN + player1.getDisplayName() + " has left the lobby (" + players + "/" + Main.plugin.getConfig().getInt("max-players") + ")");
                });
            }

            Lobby.players.remove(player);
            if(Lobby.players.size() < Main.plugin.getConfig().getInt("min-players") && !LobbyCountDown.task.isCancelled()) {
                Lobby.players.forEach(player1 -> {
                    player1.sendMessage(ChatColor.RED + "Countdown Delayed due to not enough players");
                });
                LobbyCountDown.task.cancel();
            }
            player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
            player.teleport(Bukkit.getWorld(Main.plugin.getConfig().getString("main-world-name")).getSpawnLocation());
            player.sendMessage(ChatColor.GREEN + "You have left the game and been returned to the main world");

            return true;
        }

        return true;
    }
}
