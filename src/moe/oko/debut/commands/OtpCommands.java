package moe.oko.debut.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class OtpCommands implements CommandExecutor {

    public static ArrayList<Player> newPlayers = new ArrayList<Player>();
    private static ArrayList<Player> requestingPlayers = new ArrayList<Player>();
    private static ArrayList<Player> requestedPlayers = new ArrayList<Player>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Command sender must be player.");
            return true;
        }
        if (args.length < 2) {
            sender.sendMessage("Expected arguments.");
            return true;
        }
        Player p1 = (Player) sender;
        Player p2 = null;
        try {
            p2 = Bukkit.getPlayer(args[1]);
        } catch (Exception e) {
            sender.sendMessage("Second argument must be a player name.");
            return true;
        }
        switch (args[0]) {
            case "request":
                if (!(newPlayers.contains(p1))) {
                    sender.sendMessage("Command sender must be new player.");
                    return true;
                }
                requestingPlayers.add(p1);
                requestedPlayers.add(p2);
                newPlayers.remove(p1);
                p1.sendMessage("Teleport request sent.");
                p2.sendMessage(p1.getName() + " is requesting to teleport.");
                return true;
            case "accept":
                if (!((requestedPlayers.contains(p1) && requestingPlayers.contains(p2)))) {
                    sender.sendMessage("No incoming requests from this player.");
                    return true;
                }
                if (!(requestedPlayers.indexOf(p1) == requestingPlayers.indexOf(p2))) {
                    sender.sendMessage("No incoming requests from this player.");
                    return true;
                }
                p2.teleport(p1);
                requestingPlayers.remove(p2);
                requestedPlayers.remove(p1);
                return true;
            default:
                sender.sendMessage("Unexpected argument.");
                return true;
        }
    }
}


