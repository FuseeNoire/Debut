package moe.oko.debut.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DebugCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args[0].equalsIgnoreCase( "newPlayers")) {
            if (args[1].equalsIgnoreCase("list")){
                int i = 0;
                while (i < OtpCommands.newPlayers.size()) {
                    sender.sendMessage(OtpCommands.newPlayers.get(i).getName());
                    i++;
                }
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
}

