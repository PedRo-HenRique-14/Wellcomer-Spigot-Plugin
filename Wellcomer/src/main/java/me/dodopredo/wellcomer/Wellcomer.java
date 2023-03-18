package me.dodopredo.wellcomer;

import me.dodopredo.wellcomer.commands.GUICommand;
import me.dodopredo.wellcomer.events.ClickEvent;
import me.dodopredo.wellcomer.events.PlayerManager;
import org.bukkit.Bukkit;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Wellcomer extends JavaPlugin {

    FileConfiguration config = getConfig();

    public void onEnable() { //Executado toda vez que o plugin é iniciado pelo servidor

        saveDefaultConfig();
        config.options().copyDefaults(true);

        getCommand("menu").setExecutor(new GUICommand(this));

        Bukkit.getPluginManager().registerEvents(new PlayerManager(), this);
        Bukkit.getPluginManager().registerEvents(new ClickEvent(), this);

        Bukkit.getConsoleSender().sendMessage("§6Wellcomer§r inicializado com §asucesso§r!");

    }

    public void onDisable() {

        Bukkit.getConsoleSender().sendMessage("§6Wellcomer§r §cdesabilitado§r.");

    }

}
