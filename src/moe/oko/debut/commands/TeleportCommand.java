package moe.oko.debut.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportCommand implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(!(sender instanceof Player player)) {
            sender.sendMessage("You must be a player to run this command!");
            return false;
        }
        if (args.length == 0) {
            // needs help (stupid)
            sender.sendMessage("arguments expected.");
            return true;
        }
     return true;
    }
}