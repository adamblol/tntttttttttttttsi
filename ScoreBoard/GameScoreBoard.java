package adam.main.ScoreBoard;

import adam.main.Game.Game;
import adam.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class GameScoreBoard {
    public static Scoreboard openScoreBoard(Player player, int playerSize) {

        // player checks
        String aliveStatus;
        if(player.getGameMode().equals(GameMode.SPECTATOR)) {
            aliveStatus = "Dead";
        } else {
            aliveStatus = "Alive";
        }


        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard score = manager.getNewScoreboard();

        // create it
        Objective object = score.registerNewObjective(ChatColor.RED + "tnt_run_game", Criteria.AIR, ChatColor.RED + "Tnt Run");
        object.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score players = object.getScore(ChatColor.YELLOW + "Players Alive: " + ChatColor.WHITE + playerSize);
        Score jumpsLeft = object.getScore(ChatColor.YELLOW + "Jumps Left: " + ChatColor.WHITE + Game.jumpsLeft.get(player));
        Score status1 = object.getScore(ChatColor.YELLOW + "Alive Status: " + ChatColor.WHITE + aliveStatus);
        players.setScore(4);
        jumpsLeft.setScore(3);
        status1.setScore(2);

        return score;
    }
}
