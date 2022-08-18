package adam.main.ScoreBoard;

import adam.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class LobbyScoreBoard {
    public static Scoreboard openScoreBoard(int playerSize) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard score = manager.getNewScoreboard();

        // create it
        Objective object = score.registerNewObjective(ChatColor.RED + "tnt_run_lobby", Criteria.AIR, ChatColor.RED + "Tnt Run");
        object.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score players = object.getScore(ChatColor.YELLOW + "Players: " + ChatColor.WHITE + "(" + playerSize + "/" + Main.plugin.getConfig().getInt("max-players") + ")");
        players.setScore(1);

        return score;
    }
}
