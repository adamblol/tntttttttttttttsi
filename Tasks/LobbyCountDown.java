package adam.main.Tasks;

import adam.main.Game.Game;
import adam.main.Game.Lobby;
import adam.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

public class LobbyCountDown extends BukkitRunnable {

    public static LobbyCountDown task;

    int lobbyCountDown = 20;

    @Override
    public void run() {
        task = this;
        lobbyCountDown--;
        Lobby.players.forEach(player -> {
            switch (lobbyCountDown) {
                case 15:
                    player.sendMessage(ChatColor.AQUA + "The game will begin in 15 seconds");
                    break;
                case 10:
                    player.sendMessage(ChatColor.AQUA + "The game will begin in 10 seconds");
                    break;
                case 5:
                    player.sendMessage(ChatColor.AQUA + "The game will begin in 5 seconds");
                    player.sendTitle("5", null, 20, 20, 20);
                    break;
                case 4:
                    player.sendMessage(ChatColor.AQUA + "The game will begin in 4 seconds");
                    player.sendTitle("4", null, 20, 20, 20);
                    break;
                case 3:
                    player.sendMessage(ChatColor.AQUA + "The game will begin in 3 seconds");
                    player.sendTitle("3", null, 20, 20, 20);
                    break;
                case 2:
                    player.sendMessage(ChatColor.AQUA + "The game will begin in 2 seconds");
                    player.sendTitle("2", null, 20, 20, 20);
                    break;
                case 1:
                    player.sendMessage(ChatColor.AQUA + "The game will begin in 1 seconds");
                    player.sendTitle("1", null, 20, 20, 20);
                    break;
                case 0:
                    // start game
                    player.teleport(player.getWorld().getSpawnLocation());
                    Game.players.add(player);
                    Game.startGame(player);
                    this.cancel();
                    break;
            }
        });
    }
}
