package net.kunmc.lab.widerworldborder;

import net.kunmc.lab.widerworldborder.command.CommandConst;
import net.kunmc.lab.widerworldborder.command.CommandController;
import net.kunmc.lab.widerworldborder.config.ConfigManager;
import net.kunmc.lab.widerworldborder.event.MobDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class WiderWorldBorder extends JavaPlugin {

    public static WiderWorldBorder plugin;

    public static WiderWorldBorder getPlugin(){
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;

        getServer().getPluginManager().registerEvents(new MobDeathEvent(), plugin);

        ConfigManager.loadConfig(false);

        getCommand(CommandConst.MAIN).setExecutor(new CommandController());
        getCommand(CommandConst.MAIN).setTabCompleter(new CommandController());
    }
}
