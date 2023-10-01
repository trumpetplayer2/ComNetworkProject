package me.trumpetplayer2.CompNetworkProject;

import org.bukkit.plugin.java.JavaPlugin;

import me.trumpetplayer2.CompNetworkProject.Commands.ExampleCommand;

public class Main extends JavaPlugin{
    private static Main instance;
    public static Main getInstance() {
        //Retrieve instance
        return instance;
    }
    @Override
    public void onEnable() {
        instance = this;
        getCommand("example").setExecutor(new ExampleCommand());
        getCommand("example").setTabCompleter(new ExampleCommand());
        /*
         * this.saveDefaultConfig();
         * this.getServer().getPluginManager().registerEvents((Event))
         */
    }
    @Override
    public void onDisable() {
        instance = null;
    }
}
