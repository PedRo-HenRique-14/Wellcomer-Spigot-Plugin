package me.dodopredo.wellcomer.commands;

import com.sun.tools.javac.Main;
import me.dodopredo.wellcomer.Wellcomer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Objects;

public class GUICommand implements CommandExecutor {

    static Wellcomer plugin;

    public GUICommand(Wellcomer plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {



        if (sender instanceof Player){
            Player player = (Player)sender;

            Inventory guiConfig = Bukkit.createInventory(player, 9, ChatColor.BLACK + "Wellcomer Config");

            ItemStack disabelMessageChat = new ItemStack(Material.RED_WOOL);
            ItemStack enableMessageChat = new ItemStack(Material.GREEN_WOOL);
            ItemStack setMessageChat = new ItemStack(Material.FEATHER);

            ItemStack disableTitleMessage = new ItemStack(Material.RED_CONCRETE);
            ItemStack enableTitleMessage = new ItemStack(Material.GREEN_CONCRETE);
            ItemStack setTitleMessage = new ItemStack(Material.DARK_OAK_SIGN);

            ItemStack emptySlot = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);

            ItemMeta disableMessageChat_Meta = disabelMessageChat.getItemMeta();
            disableMessageChat_Meta.setDisplayName(ChatColor.RED + "Disable Message Chat");
            ArrayList<String> disableMessageChat_Lore = new ArrayList<>();
            disableMessageChat_Lore.add(ChatColor.DARK_RED + "Disable the message when a player join.");
            disableMessageChat_Meta.setLore(disableMessageChat_Lore);
            disabelMessageChat.setItemMeta(disableMessageChat_Meta);

            ItemMeta enableMessageChat_Meta = enableMessageChat.getItemMeta();
            enableMessageChat_Meta.setDisplayName(ChatColor.GREEN + "Enable Message Chat");
            ArrayList<String> enableMessageChat_Lore = new ArrayList<>();
            enableMessageChat_Lore.add(ChatColor.DARK_GREEN + "Enable the message when a player join.");
            enableMessageChat_Meta.setLore(enableMessageChat_Lore);
            enableMessageChat.setItemMeta(enableMessageChat_Meta);

            ItemMeta setMessageChat_Meta = setMessageChat.getItemMeta();
            setMessageChat_Meta.setDisplayName(ChatColor.ITALIC + "Set Join Message");
            ArrayList<String> setMessageChat_Lore = new ArrayList<>();
            setMessageChat_Lore.add(ChatColor.DARK_PURPLE + "Set the message when the player join.");
            setMessageChat_Meta.setLore(setMessageChat_Lore);
            setMessageChat.setItemMeta(setMessageChat_Meta);

            ItemMeta disableTitleMessage_Meta = disableTitleMessage.getItemMeta();
            disableTitleMessage_Meta.setDisplayName(ChatColor.RED + "Disable the Title");
            ArrayList<String> disableTitleMessage_Lore = new ArrayList<>();
            disableTitleMessage_Lore.add(ChatColor.DARK_RED + "Disable the title when a player join.");
            disableTitleMessage_Meta.setLore(disableTitleMessage_Lore);
            disableTitleMessage.setItemMeta(disableTitleMessage_Meta);

            ItemMeta enableTitleMessage_Meta = enableTitleMessage.getItemMeta();
            enableTitleMessage_Meta.setDisplayName(ChatColor.GREEN + "Enable the Title");
            ArrayList<String> enableTitleMessage_Lore = new ArrayList<>();
            enableTitleMessage_Lore.add(ChatColor.DARK_GREEN + "Enable the Title when a player join.");
            enableTitleMessage_Meta.setLore(enableTitleMessage_Lore);
            enableTitleMessage.setItemMeta(enableTitleMessage_Meta);

            ItemMeta setTitleMessage_Meta = setTitleMessage.getItemMeta();
            setTitleMessage_Meta.setDisplayName(ChatColor.ITALIC + "Set Title Message");
            ArrayList<String> setTitleMessage_Lore = new ArrayList<>();
            setTitleMessage_Lore.add(ChatColor.DARK_PURPLE + "Set the title when the player join.");
            setTitleMessage_Meta.setLore(setTitleMessage_Lore);
            setTitleMessage.setItemMeta(setMessageChat_Meta);

            ItemStack[] menu_items = {emptySlot, disabelMessageChat, enableMessageChat, setMessageChat, disableTitleMessage, enableTitleMessage, setTitleMessage, emptySlot, emptySlot};
            guiConfig.setContents(menu_items);
            player.openInventory(guiConfig);
        }

        return true;
    }

    public static String joinMessageActive (Boolean active){
        plugin.getConfig().set("joinMessage.active", active);
        plugin.saveConfig();
        return String.format("Join message set to §4%s§r", active);
    }

    public static String titleJoinMessageActive(Boolean active){
        plugin.getConfig().set("title.active", active);
        plugin.saveConfig();
        return String.format("Title message set to §4%s§r", active);
    }

    public static String getJoinMessage(){
        if (plugin.getConfig().getBoolean("joinMessage.active")){
            return plugin.getConfig().getString("joinMessage.message");
        }
        return "";
    }

    public static String getTitleMessage(){
        if (plugin.getConfig().getBoolean("title.active")){
            return plugin.getConfig().getString("title.titleMessage");
        }
        return "";
    }

    public static String getSubtitleMessage(){
        if (plugin.getConfig().getBoolean("title.active")){
            return plugin.getConfig().getString("title.subtitleMessage");
        }
        return "";
    }

    public static Boolean setTitleMessage(AsyncPlayerChatEvent event, String message){
        plugin.getConfig().set("title.titleMessage", message);
        plugin.saveConfig();
        event.getPlayer().sendTitle(String.format("%s", message), "subtitle", 10, 20, 10);
        event.getPlayer().sendMessage("§2Send the subtitle text in the chat§r");
        return false;
    }

    public static Boolean setSubtitleMessage(AsyncPlayerChatEvent event, String message){
        plugin.getConfig().set("title.subtitleMessage", message);
        plugin.saveConfig();
        event.getPlayer().sendTitle(plugin.getConfig().getString("title.titleMessage"), String.format("%s", message), 10, 20, 10);
        event.getPlayer().sendMessage("§2Done!§r");
        return false;
    }

    public static Boolean setJoinMessage(AsyncPlayerChatEvent event, Boolean editing, String message){
        if (editing) {
            plugin.getConfig().set("joinMessage.message", message);
            plugin.saveConfig();
            event.getPlayer().sendMessage(String.format("The message has changed to: %s", message));
            return false;
        }

        return false;
    }

}
