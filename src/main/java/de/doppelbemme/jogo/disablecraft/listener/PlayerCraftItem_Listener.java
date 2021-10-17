package de.doppelbemme.jogo.disablecraft.listener;

import de.doppelbemme.jogo.disablecraft.Disablecraft;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

public class PlayerCraftItem_Listener implements Listener {

    @EventHandler
    public void onCraft(CraftItemEvent event)
    {
        String craftedItem = event.getCurrentItem().getType().toString();
        Player player = (Player) event.getWhoClicked();

        if(Disablecraft.main.fileManager.itemCfg.contains(craftedItem+".restricted") && Disablecraft.main.fileManager.itemCfg.getBoolean(craftedItem+".restricted") == true)
        {
            if(player.hasPermission(Disablecraft.main.fileManager.itemCfg.getString(craftedItem+".permission")))
            {
                event.setCancelled(false);
                return;
            }

            event.setCancelled(true);
            player.sendMessage(Disablecraft.main.msg.prefix + Disablecraft.main.msg.CantCraftItem);
            return;
        }
    }
}
