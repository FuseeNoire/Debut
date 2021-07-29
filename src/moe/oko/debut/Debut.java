package moe.oko.debut;

import moe.oko.debut.commands.TeleportCommand;
import org.bukkit.plugin.java.JavaPlugin;
import moe.oko.debut.events.RespawnListener;
import moe.oko.debut.events.NewPlayerListener;

public class Debut extends JavaPlugin {
    
    String plugin = "[Debut] ";
    
    @Override
    public void onEnable(){
        getServer().getConsoleSender().sendMessage(plugin + "Début loaded.");
        getServer().getPluginManager().registerEvents(new NewPlayerListener(), this);
        getServer().getPluginManager().registerEvents(new RespawnListener(), this);
        this.getCommand("otp").setExecutor(new TeleportCommand());
    }
   
    @Override
    public void onDisable(){
        getServer().getConsoleSender().sendMessage(plugin + "Début shutting down.");
    }

}
