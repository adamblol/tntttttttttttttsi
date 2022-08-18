package adam.main.Listeners;

import adam.main.Game.Game;
import adam.main.Game.GamePerks.DoubleJump;
import adam.main.ScoreBoard.GameScoreBoard;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteract implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if(!event.hasItem()) {
            return;
        }

        if(!event.getItem().getType().equals(Material.FEATHER)) {
            return;
        }

        if(!event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            return;
        }

        Player player = event.getPlayer();

        if(Game.jumpsLeft.get(player) == 0) {
            return;
        }

        Game.jumpsLeft.replace(player, Game.jumpsLeft.get(player) - 1);
        DoubleJump.doDoubleJump(player, Game.jumpsLeft.get(player));
        player.sendMessage(ChatColor.GREEN + "You have " + Game.jumpsLeft.get(player) + " jump boost left");
        player.setScoreboard(GameScoreBoard.openScoreBoard(player, Game.players.size()));
    }
}
