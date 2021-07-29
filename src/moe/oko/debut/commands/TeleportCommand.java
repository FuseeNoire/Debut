package moe.oko.debut.commands;

import moe.oko.debut.Debut;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class TeleportCommand implements CommandExecutor{

    ArrayList<Player> newPlayers = new ArrayList<Player>();
    ArrayList<Player> requestingPlayers = new ArrayList<Player>();
    ArrayList<Player> requestedPlayers =  new ArrayList<Player>();

    public void addNewPlayer(Player newPlayer) {
        newPlayers.add(newPlayer);
        Bukkit.getScheduler().runTaskLater(Debut.getPlugin(Debut.class), () -> newPlayers.remove(newPlayer), 36000);
    }

    public boolean requestTeleport(Player p1, Player p2) {

        if (!(newPlayers.contains(p1))) {
            p1.sendMessage("times up buckaroo.");
            return false;
        }

        p2.sendMessage(p1.getName() + " is requesting a tp.");
        requestingPlayers.add(p1);
        requestedPlayers.add(p2);
        newPlayers.remove(p1);
        return true;
    }

    public boolean acceptTeleport(Player p1, Player p2) {

        if (!(requestingPlayers.contains(p2) && requestedPlayers.contains(p1))) {
            p1.sendMessage("no incoming requests from this player.");
            return false;
        }

        if (!(requestingPlayers.indexOf(p2)==requestedPlayers.indexOf(p1))) {
            p1.sendMessage("no incoming requests from this player.");
            return false;
        }

        p2.teleport(p1);
        requestingPlayers.remove(p2);
        requestedPlayers.remove(p1);
        return true;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("sender needs to be a player.");
            return false;
        }

        if (args.length == 1) {
            sender.sendMessage("expected arguments.");
            return false;
        }

        Player p1 = ((Player) sender).getPlayer();
        Player p2 = Bukkit.getPlayer(args[1]);

        switch (args[0]) {
            case "request":
                return requestTeleport(p1, p2);
            case "accept":
                return acceptTeleport(p1, p2);
            default:
                sender.sendMessage("invalid arguments.");
                return false;
        }
    }
}