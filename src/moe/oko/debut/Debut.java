package moe.oko.debut;

import moe.oko.debut.commands.DebugCommands;
import moe.oko.debut.commands.OtpCommands;
import moe.oko.debut.events.PlayerJoinListener;
import moe.oko.debut.events.RespawnListener;
import org.bukkit.plugin.java.JavaPlugin;

public class Debut extends JavaPlugin {

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new RespawnListener(), this);
        getCommand("otp").setExecutor(new OtpCommands());
        getCommand("debut").setExecutor(new DebugCommands());
    }

    @Override
    public void onDisable() {
    }

}

