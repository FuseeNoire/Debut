package moe.oko.debut.commands;

import moe.oko.debut.Debut;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class OtpCommands implements TabExecutor {

    public static ArrayList<Player> newPlayers = new ArrayList<>();
    private static final ArrayList<Player> requestingPlayers = new ArrayList<>();
    private static final ArrayList<Player> requestedPlayers = new ArrayList<>();

    private boolean otpRequest(Player requesting, Player requested) {
        if (!(newPlayers.contains(requesting))) {
            requesting.sendMessage("Only new players can use /otp.");
            return true;
        }
        if (requestingPlayers.contains(requesting)) {
            requesting.sendMessage("You can only have one out going teleport request at a time.");
            return true;
        }
        requestingPlayers.add(requesting);
        requestedPlayers.add(requested);
        requesting.sendMessage("Teleport request sent.");
        requested.sendMessage(requesting.getName() + " is requesting a teleport.");
        Bukkit.getScheduler ().runTaskLater (Debut.getPlugin(Debut.class), () -> otpDecline(requesting, requested), 600);
        return true;
    }

    private boolean otpAccept(Player requesting, Player requested) {
        if (!(requestingPlayers.contains(requesting)) || !(requestedPlayers.contains(requested)) || !(requestingPlayers.indexOf(requesting) == requestedPlayers.indexOf(requested))) {
            requested.sendMessage("You have no incoming requests from " + requesting.getName() + ".");
            return true;
        }
        requestingPlayers.remove(requesting);
        requestedPlayers.remove(requested);
        newPlayers.remove(requesting);
        requesting.teleport(requested);
        return true;
    }

    private boolean otpDecline(Player requesting, Player requested) {
        if (!(requestingPlayers.contains(requesting)) || !(requestedPlayers.contains(requested)) || !(requestingPlayers.indexOf(requesting) == requestedPlayers.indexOf(requested))) {
            requested.sendMessage("You have no incoming requests from " + requesting.getName() + ".");
            return true;
        }
        requestingPlayers.remove(requesting);
        requestedPlayers.remove(requested);
        requesting.sendMessage(requested.getName() + " has declined your teleport request, or did not accept it in time.");
        requested.sendMessage("You have either declined " + requesting.getName() + ", or have either waited to long to accept.");
        return true;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Command sender must be player.");
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage("Arguments expected");
            return true;
        }

        Player firstPlayer = (Player) sender;
        Player secondPlayer;

        // /otp request playerName
        if (args[0].equalsIgnoreCase("request")) {
            try {
                secondPlayer = Bukkit.getPlayer(args[1]);
            } catch (Exception e) {
                sender.sendMessage("There is no player by that name.");
                return true;
            }
            return otpRequest(firstPlayer, secondPlayer);
        }

        // /otp accept playerName
        if (args[0].equalsIgnoreCase("accept")) {
            try {
                secondPlayer = Bukkit.getPlayer(args[1]);
            } catch (Exception e) {
                sender.sendMessage("There is no player by that name.");
                return true;
            }
            return otpAccept(secondPlayer, firstPlayer);
        }

        // /otp decline playerName
        if (args[0].equalsIgnoreCase("decline")) {
            try {
                secondPlayer = Bukkit.getPlayer(args[1]);
            } catch (Exception e) {
                sender.sendMessage("Unexpected argument.");
                return true;
            }
            return otpDecline(secondPlayer, firstPlayer);
        }

        // /otp playerName
        else {
            try {
                secondPlayer = Bukkit.getPlayer(args[0]);
            } catch (Exception e) {
                sender.sendMessage("Unexpected argument.");
                return true;
            }
            return otpRequest(firstPlayer, secondPlayer);
        }

    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        // TODO: make onTabComplete not complete insanity
        if (args.length == 1) {
            return List.of("accept", "decline", "request");
        }
        if (args.length == 2) {
            return null;
        }
        return List.of("");
    }
}