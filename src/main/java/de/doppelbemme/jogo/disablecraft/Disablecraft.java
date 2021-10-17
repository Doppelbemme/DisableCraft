package de.doppelbemme.jogo.disablecraft;

import de.doppelbemme.jogo.disablecraft.command.About_Command;
import de.doppelbemme.jogo.disablecraft.command.Recipe_Command;
import de.doppelbemme.jogo.disablecraft.listener.PlayerCraftItem_Listener;
import de.doppelbemme.jogo.disablecraft.util.FileManager;
import de.doppelbemme.jogo.disablecraft.util.Messages;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Disablecraft extends JavaPlugin {

    public static Disablecraft main;
    public Messages msg;
    public FileManager fileManager;

    @Override
    public void onEnable() {
        main = this;
        fileManager = new FileManager();

        getCommand("about").setExecutor(new About_Command());
        getCommand("recipe").setExecutor(new Recipe_Command());

        PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new PlayerCraftItem_Listener(), this);

        fileManager.saveItemFile();
        fileManager.setupMessageFile();
        fileManager.saveMessageFile();

        msg = new Messages();
    }

    @Override
    public void onDisable() {

    }

}
