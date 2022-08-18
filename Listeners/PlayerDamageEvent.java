package adam.main.Listeners;

import adam.main.Game.Game;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerDamageEvent implements Listener {
    @EventHandler
    public void OnPlayerDamage(EntityDamageEvent event) {
        Player player;

        if(event.getEntity() instanceof Player) {
            player = (Player) event.getEntity();
        } else {
            return;
        }

        // if a player and is in the array
        if(!Game.players.contains(player)) {
            return;
        }

        event.setCancelled(true);
    }
}
