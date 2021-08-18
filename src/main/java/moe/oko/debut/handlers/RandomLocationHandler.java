package moe.oko.debut.handlers;

import moe.oko.debut.Debut;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.List;
import java.util.Random;

public class RandomLocationHandler {
    public static Location randomLocation() {
        Random ran = new Random();
        World world = Bukkit.getWorlds().get(0);
        List<String> testPath = Debut.getPlugin(Debut.class).getConfig().getStringList("possible-spawns");
        String cordsString = testPath.get(ran.nextInt(testPath.size()));
        String[] splitString = cordsString.split(", ");
        int x = Integer.parseInt(splitString[0]);
        int z = Integer.parseInt(splitString[1]);
        int y = (world.getHighestBlockYAt(x, z) + 1);
        Location location = new Location(world, x, y, z);
        return location;
    }
}

