package moe.oko.debut.events;

import moe.oko.debut.commands.TeleportCommand;
import moe.oko.debut.handlers.TeleportHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class NewPlayerListener implements Listener {
    private TeleportCommand teleportCommand;
    public NewPlayerListener(TeleportCommand teleportCommand){
        this.teleportCommand = teleportCommand;
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        if (!(player.hasPlayedBefore())) {
            event.setJoinMessage(player.getName() + " joined for the first time");
            TeleportHandler.handleRandomTeleport(player, player.getWorld(), false);
            teleportCommand.addNewPlayer(player);
        }
    }
}