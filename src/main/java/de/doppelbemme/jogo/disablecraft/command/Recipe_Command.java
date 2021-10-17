package de.doppelbemme.jogo.disablecraft.command;

import de.doppelbemme.jogo.disablecraft.Disablecraft;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Recipe_Command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Disablecraft.main.msg.prefix + Disablecraft.main.msg.ConsoleNotAllowed);
            return false;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("disablecraft.command.recipe")) {
            player.sendMessage(Disablecraft.main.msg.prefix + Disablecraft.main.msg.NoPermission);
            return false;
        }

        String itemType = player.getItemInHand().getType().toString();

        if (itemType.equalsIgnoreCase("AIR")) {
            player.sendMessage(Disablecraft.main.msg.prefix + "§7Bitte halte ein Item in der Hand.");
            return false;
        }

        if (args.length == 1) {
            String state = args[0];
            if (state.equalsIgnoreCase("false")) {
                Disablecraft.main.fileManager.changeActivationState(player, itemType, Boolean.parseBoolean(state));
                return true;
            }
            sendHelpMessage(player);
            return false;
        } else if (args.length == 2) {
            String state = args[0];
            if (state.equalsIgnoreCase("true")) {
                String permission = args[1].toLowerCase();
                Disablecraft.main.fileManager.editRecipeConfig(player, itemType, Boolean.parseBoolean(state), permission);
                return true;
            }
            sendHelpMessage(player);
            return false;
        } else if (args.length == 0 || args.length > 2) {
            sendHelpMessage(player);
            return false;
        }
        return false;
    }

    public void sendHelpMessage(Player player) {
        player.sendMessage(Disablecraft.main.msg.prefix + "§cNutze: §7/§crecipe §7<§ctrue§7/§cfalse§7> §7(§cPermission§7)");
    }

}
