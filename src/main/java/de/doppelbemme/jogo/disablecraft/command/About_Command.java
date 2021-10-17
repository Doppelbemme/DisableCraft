package de.doppelbemme.jogo.disablecraft.command;

import de.doppelbemme.jogo.disablecraft.Disablecraft;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class About_Command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Disablecraft.main.msg.prefix + Disablecraft.main.msg.ConsoleNotAllowed);
            return false;
        }

        Player player = (Player) sender;
        player.sendMessage(Disablecraft.main.msg.prefix + Disablecraft.main.msg.AboutPlugin);
        return false;

    }

}
