package me.dodopredo.wellcomer.events;

import me.dodopredo.wellcomer.Wellcomer;
import me.dodopredo.wellcomer.commands.GUICommand;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.io.IOException;
import java.text.Format;

public class ClickEvent implements Listener {

    public Wellcomer plugin;

    private String playerMessageName;
    private String whoClicked;

    private Boolean editJoinMessage;
    private Boolean editTitleMessage;


    @EventHandler
    public void clickEvent(InventoryClickEvent event){

        Player player = (Player) event.getWhoClicked();

        if (event.getView().getTitle().equalsIgnoreCase(ChatColor.BLACK + "Wellcomer Config")) {

            Material itemType = event.getCurrentItem().getType();

            if(itemType.equals(Material.GREEN_WOOL)) {
                player.sendMessage(GUICommand.joinMessageActive(true));
            }

            if (itemType.equals(Material.RED_WOOL)) {
                player.sendMessage(GUICommand.joinMessageActive(false));
            }

            if (itemType.equals(Material.FEATHER)) {
                whoClicked = player.getName();
                editJoinMessage =true;
                player.closeInventory();
                player.sendTitle("Type in chat the new message", "", 10, 70, 20);
            }

            if (itemType.equals(Material.GREEN_CONCRETE)) {
                player.sendMessage(GUICommand.titleJoinMessageActive(true));
            }

            if (itemType.equals(Material.RED_CONCRETE)) {
                player.sendMessage(GUICommand.titleJoinMessageActive(false));
            }

            if (itemType.equals(Material.DARK_OAK_SIGN)) {
                whoClicked = player.getName();
                editTitleMessage =true;
                player.closeInventory();
                player.sendTitle("Type in chat the new title message", "", 10, 70, 20);


            }

            event.setCancelled(true);
        }


    }

    @EventHandler
    public void playerEventChat(AsyncPlayerChatEvent event) {

        playerMessageName = event.getPlayer().getName();
        editJoinMessage = GUICommand.setJoinMessage( event, editJoinMessage, whoClicked, playerMessageName, event.getMessage());

    }

}
