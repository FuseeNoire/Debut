package moe.oko.debut.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.List;

public class DebugCommands implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args[0].equalsIgnoreCase( "newPlayers")) {
            if (args[1].equalsIgnoreCase("list")) {
                StringBuilder players = new StringBuilder();
                for (Player newPlayer : OtpCommands.newPlayers) {
                    players.append(newPlayer.getName()).append(", ");
                }
                if(players.toString().endsWith(", ")) {
                    players = new StringBuilder(players.substring(0, players.length() - 2));
                }
                sender.sendMessage("There are " + OtpCommands.newPlayers.size() + " new players: " + players);
                return true;
                }
            Player player;
            try {
                player = Bukkit.getPlayer(args[2]);
            } catch (Exception e) {
                sender.sendMessage("Third argument must be a player name.");
                return true;
            }
            switch (args[1]) {
                case "add" -> {
                    OtpCommands.newPlayers.add(player);
                    return true;
                }
                case "remove" -> {
                    OtpCommands.newPlayers.remove(player);
                    return true;
                }
                default -> {
                    sender.sendMessage("Unexpected argument.");
                    return true;
                }
            }
        }
        sender.sendMessage("Unexpected argument.");
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        // TODO: make onTabComplete not complete insanity
        if (args.length == 1) {
            return List.of("newPlayers");
        }
        if (args.length == 2) {
            return List.of("add","list","remove");
        }
        if (args.length == 3) {
            return null;
        }
        return List.of("");
    }
}

