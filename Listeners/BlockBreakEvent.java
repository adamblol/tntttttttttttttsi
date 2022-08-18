package adam.main.Listeners;

import adam.main.Game.Game;
import adam.main.Game.Lobby;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class BlockBreakEvent implements Listener {
    @EventHandler
    public void OnBlockBreak(org.bukkit.event.block.BlockBreakEvent event) {
        Player player = event.getPlayer();

        if(Game.players.contains(player) || Lobby.players.contains(player)) {
            player.sendMessage(ChatColor.RED + "You cant break blocks");
            event.setCancelled(true);
        }
    }
}
