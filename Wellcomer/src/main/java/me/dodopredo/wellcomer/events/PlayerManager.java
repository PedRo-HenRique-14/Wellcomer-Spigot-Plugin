package me.dodopredo.wellcomer.events;


import me.dodopredo.wellcomer.Wellcomer;
import me.dodopredo.wellcomer.commands.GUICommand;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;


public class PlayerManager implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent pje) {
        pje.setJoinMessage(GUICommand.getJoinMessage());
        pje.getPlayer().sendTitle(
                GUICommand.getJoinTitleMessage(0),
                GUICommand.getJoinTitleMessage(1),
                GUICommand.getTitleTime(0),
                GUICommand.getTitleTime(1),
                GUICommand.getTitleTime(2)
        );
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent pce){

    }
}
