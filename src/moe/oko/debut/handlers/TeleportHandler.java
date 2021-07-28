package moe.oko.debut.handlers;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.World;
import java.util.ArrayList;
import java.util.Random;

public class TeleportHandler {
    public static void handleRandomTeleport(Player player, World world){

        Random ran = new Random();
        ArrayList<Integer> xList = new ArrayList<Integer>();
        ArrayList<Integer> zList = new ArrayList<Integer>();

        // list of x cords
        xList.add(103);
        xList.add(-232);
        xList.add(321);

        //list of z cords
        zList.add(987);
        zList.add(-10);
        zList.add(831);

        int cordSet = ran.nextInt(2);
        int x = xList.get(cordSet);
        int z = zList.get(cordSet);
        int y = (world.getHighestBlockYAt(x, z) + 1);

        Location location = new Location(world, x, y, z);
        player.teleport(location);
    }
}
