package adam.main.Game.GamePerks;

import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class DoubleJump {
    public static void doDoubleJump(Player player, int uses) {
        // push them 2 blocks foward
        // 3 blocks high

        if(getDirection(player) == "East") {
            Vector velocity = player.getVelocity();
            velocity.setY(0.45);
            velocity.setX(0.3);
            player.setVelocity(velocity);
        } else {
            Vector velocity = player.getVelocity();
            velocity.setY(0.45);
            velocity.setX(-0.3);
            player.setVelocity(velocity);
        }
    }

    private static String getDirection(Player player) {
        double rotation = player.getLocation().getYaw() - 180;
        if (rotation < 0) {
            rotation += 360.0;
        }
        player.sendMessage(Double.toString(rotation));
        if (0 <= rotation && rotation < 22.5) {
            return "North";
        }
        if (22.5 <= rotation && rotation < 67.5) {
            return "North East";
        }
        if (67.5 <= rotation && rotation < 112.5) {
            return "East";
        }
        if (112.5 <= rotation && rotation < 157.5) {
            return "South East";
        }
        if (157.5 <= rotation && rotation < 202.5) {
            return "South";
        }
        if (202.5 <= rotation && rotation < 247.5) {
            return "South West";
        }
        if (247.5 <= rotation && rotation < 292.5) {
            return "West";
        }
        if (292.5 <= rotation && rotation < 337.5) {
            return "North West";
        }
        if (337.5 <= rotation && rotation <= 360) {
            return "North";
        }

        return null;
    }
}
