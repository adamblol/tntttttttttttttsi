package adam.main.Listeners;

import adam.main.Game.Game;
import adam.main.Main;
import adam.main.ScoreBoard.GameScoreBoard;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerMoveEvent implements Listener {
    @EventHandler
    public void OnPlayerMove(org.bukkit.event.player.PlayerMoveEvent event) {
        if(!Game.isGameInProgress) {
            return;
        }

        Player player = event.getPlayer();

        if(!Game.players.contains(player)) {
            return;
        }

        // means player is dead
        if(player.getGameMode().equals(GameMode.SPECTATOR)) {
            return;
        }

        // if the Y gets to 22 then kill them gay way of handling this (i think)
        if(player.getLocation().getY() <= 22) {
            player.setGameMode(GameMode.SPECTATOR);
            int playersAlive = Game.players.size() - 1;
            Game.players.forEach(player1 -> {
                player1.sendMessage(ChatColor.YELLOW + player.getDisplayName() + " has died. " + playersAlive + " Player(s) remain");
                player1.setScoreboard(GameScoreBoard.openScoreBoard(player1, playersAlive));
            });
            Game.playersDead.forEach(player1 -> {
                player1.sendMessage(ChatColor.YELLOW + player.getDisplayName() + " has died. " + playersAlive + " Player(s) remain");
                player1.setScoreboard(GameScoreBoard.openScoreBoard(player1, playersAlive));
            });
            Game.players.remove(player);
            Game.playersDead.add(player);
            player.teleport(player.getWorld().getSpawnLocation());

            // someone won
            if(Game.players.size() == 1) {
                Game.endGame();
                return;
            }

            return;
        }

        // if game in progress every 2 blocks below the person u want to destroy
        Block oneBelow = player.getWorld().getBlockAt(player.getLocation().getBlockX(), player.getLocation().getBlockY() - 1, player.getLocation().getBlockZ());
        Block twoBelow = player.getWorld().getBlockAt(player.getLocation().getBlockX(), player.getLocation().getBlockY() - 2, player.getLocation().getBlockZ());

        if(oneBelow.getType().equals(Material.AIR)) {
            return;
        }

        // add a delay on breaking the blocks
        new BukkitRunnable() {
            @Override
            public void run() {
                oneBelow.setType(Material.AIR);
                twoBelow.setType(Material.AIR);
            }
        }.runTaskLater(Main.plugin, 5L);
    }
}
