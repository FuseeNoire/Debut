package moe.oko.debut.events;

import moe.oko.debut.handlers.RandomLocationHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class RespawnListener implements Listener {
    @EventHandler
    public static void onPlayerRespawn(PlayerRespawnEvent event){
        if (!(event.isBedSpawn())){
            event.setRespawnLocation(RandomLocationHandler.randomLocation());
        }
    }
}
