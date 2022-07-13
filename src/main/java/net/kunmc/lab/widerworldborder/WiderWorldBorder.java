package net.kunmc.lab.widerworldborder;

import net.kunmc.lab.widerworldborder.command.CommandConst;
import net.kunmc.lab.widerworldborder.command.CommandController;
import net.kunmc.lab.widerworldborder.config.ConfigManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class WiderWorldBorder extends JavaPlugin {

    public static WiderWorldBorder plugin;

    @Override
    public void onEnable() {
        plugin = this;

        ConfigManager.loadConfig(false);
        getCommand(CommandConst.MAIN).setExecutor(new CommandController());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
