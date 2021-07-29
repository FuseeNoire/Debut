package moe.oko.debut.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportCommand implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (args.length == 0) { sender.sendMessage("arguments expected."); return false; }
        if (!(sender instanceof Player)) { sender.sendMessage("must be a player to run this command."); return false; }
        Player tpTarget = Bukkit.getPlayer(args[0]);
        Player player = Bukkit.getPlayer(sender.getName());
        player.teleport(tpTarget);
     return true;
    }
}