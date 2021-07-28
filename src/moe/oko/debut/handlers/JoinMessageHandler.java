package moe.oko.debut.handlers;

import org.bukkit.Bukkit;

public class JoinMessageHandler {
    public static void firstJoinMessage(String name){
        Bukkit.broadcastMessage( name + " just joined for the first time!");
    }
}