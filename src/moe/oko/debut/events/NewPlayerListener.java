package moe.oko.debut.events;

import moe.oko.debut.handlers.JoinMessageHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import moe.oko.debut.handlers.TeleportHandler;

public class NewPlayerListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        if (!(player.hasPlayedBefore())) {
            JoinMessageHandler.firstJoinMessage(player.getName());
            TeleportHandler.handleRandomTeleport(player, player.getWorld());
        }
    }
}
