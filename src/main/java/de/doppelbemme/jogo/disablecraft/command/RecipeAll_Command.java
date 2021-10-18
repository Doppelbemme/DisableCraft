package de.doppelbemme.jogo.disablecraft.command;

import de.doppelbemme.jogo.disablecraft.Disablecraft;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RecipeAll_Command implements CommandExecutor {

    int initSlot = 0;
    int slot = 0;
    int recipeTask = 0;

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

        if (args.length == 0) {
            sendHelpMessage(player);
            return false;
        }

        if (args.length > 2) {
            sendHelpMessage(player);
            return false;
        }

        initSlot = player.getInventory().getHeldItemSlot();

        recipeTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(Disablecraft.main, new Runnable() {
            @Override
            public void run() {
                player.getInventory().setHeldItemSlot(slot);
                String itemType = player.getItemInHand().getType().toString();
                slot++;

                if (args.length == 1) {
                    String state = args[0];
                    if (state.equalsIgnoreCase("false")) {
                        if(!itemType.equalsIgnoreCase("AIR")) {
                            Disablecraft.main.fileManager.changeActivationState(player, itemType, Boolean.parseBoolean(state));
                        }
                    } else {
                        sendHelpMessage(player);
                        Bukkit.getScheduler().cancelTask(recipeTask);
                        player.getInventory().setHeldItemSlot(initSlot);
                    }
                } else if (args.length == 2) {
                    String state = args[0];
                    String permission = args[1].toLowerCase();
                    if (state.equalsIgnoreCase("true")) {
                        if(!itemType.equalsIgnoreCase("AIR")) {
                            Disablecraft.main.fileManager.editRecipeConfig(player, itemType, Boolean.parseBoolean(state), permission);
                        }
                    } else {
                        sendHelpMessage(player);
                        Bukkit.getScheduler().cancelTask(recipeTask);
                        player.getInventory().setHeldItemSlot(initSlot);
                    }
                }
                if (slot == 9) {
                    Bukkit.getScheduler().cancelTask(recipeTask);
                    slot = 0;
                    recipeTask = 0;
                    player.getInventory().setHeldItemSlot(initSlot);
                }
            }
        }, 0, 15);

        return false;
    }

    public void sendHelpMessage(Player player) {
        player.sendMessage(Disablecraft.main.msg.prefix + "§cNutze: §7/§crecipe §7<§ctrue§7/§cfalse§7> §7(§cPermission§7)");
    }

}
