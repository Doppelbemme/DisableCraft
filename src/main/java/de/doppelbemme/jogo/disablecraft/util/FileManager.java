package de.doppelbemme.jogo.disablecraft.util;

import de.doppelbemme.jogo.disablecraft.Disablecraft;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class FileManager {

    File itemFile = new File("plugins/DisableCraft", "recipes.yml");
    public FileConfiguration itemCfg = YamlConfiguration.loadConfiguration(itemFile);

    File messageFile = new File("plugins/DisableCraft", "messages.yml");
    public FileConfiguration messageCfg = YamlConfiguration.loadConfiguration(messageFile);

    public void saveItemFile() {
        try {
            itemCfg.save(itemFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editRecipeConfig(Player player, String itemType, boolean activationState, String permission) {
        itemCfg.set(itemType+".restricted", activationState);
        itemCfg.set(itemType+".permission", permission);
        saveItemFile();

        player.sendMessage(Disablecraft.main.msg.prefix + "§e" + itemType + " §7mit Status §e" + activationState + " §7und Permission §e" + permission + " §7erfolgreich gespeichert.");
    }

    public void changeActivationState(Player player, String itemType, boolean activationState){
        itemCfg.set(itemType+".restricted", activationState);
        saveItemFile();

        player.sendMessage(Disablecraft.main.msg.prefix + "§e" + itemType + " §7mit Status §e" + activationState + " §7erfolgreich gespeichert.");
    }

    public void setupMessageFile() {

        messageCfg.addDefault("prefix", "&7[&bDisableCraft&7]");
        messageCfg.addDefault("ConsoleNotAllowed", "&cNur ein Spieler kann diesen Befehl benutzen!");
        messageCfg.addDefault("NoPermission", "&cDazu hast du keine Rechte.");
        messageCfg.addDefault("CantCraftItem", "&cDu besitzt nicht die nötigen Fähigkeiten um diesen Gegenstand herzustellen.");

        messageCfg.options().copyDefaults(true);
    }

    public void saveMessageFile() {
        try {
            messageCfg.save(messageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

