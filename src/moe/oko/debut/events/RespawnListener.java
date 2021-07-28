package moe.oko.debut.events;

import moe.oko.debut.handlers.TeleportHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class RespawnListener implements Listener {
    @EventHandler
    public static void onPlayerRespawn(PlayerRespawnEvent event){
        Player player = event.getPlayer();
        if (!(event.isBedSpawn())){
            TeleportHandler.handleRandomTeleport(player, player.getWorld(), true);
        }
    }
}
