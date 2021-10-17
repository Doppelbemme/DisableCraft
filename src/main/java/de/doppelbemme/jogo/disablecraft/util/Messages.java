package de.doppelbemme.jogo.disablecraft.util;

import de.doppelbemme.jogo.disablecraft.Disablecraft;
import org.bukkit.ChatColor;

public class Messages{

    public String prefix = ChatColor.translateAlternateColorCodes('&', Disablecraft.main.fileManager.messageCfg.getString("prefix")) + " ";

    public String ConsoleNotAllowed = ChatColor.translateAlternateColorCodes('&', Disablecraft.main.fileManager.messageCfg.getString("ConsoleNotAllowed"));
    public String NoPermission = ChatColor.translateAlternateColorCodes('&', Disablecraft.main.fileManager.messageCfg.getString("NoPermission"));
    public String CantCraftItem = ChatColor.translateAlternateColorCodes('&', Disablecraft.main.fileManager.messageCfg.getString("CantCraftItem"));

    public String AboutPlugin = "§7Dieser Server benutzt §eDisableCraft 1.0.0-SNAPSHOT §7von §eDoppelbemme§7.";

}
