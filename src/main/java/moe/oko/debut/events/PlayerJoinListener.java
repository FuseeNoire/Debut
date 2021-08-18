package moe.oko.debut.events;

import moe.oko.debut.Debut;
import moe.oko.debut.commands.OtpCommands;
import moe.oko.debut.handlers.RandomLocationHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPlayedBefore()) {
            event.setJoinMessage(player.getName() + " " + Debut.getPlugin(Debut.class).getConfig().getString("first-join-message"));
            player.teleport(RandomLocationHandler.randomLocation());
            OtpCommands.newPlayers.add(player);
            Bukkit.getScheduler().runTaskLater(Debut.getPlugin(Debut.class), () -> OtpCommands.newPlayers.remove(player), 36000);
        }
    }
}
