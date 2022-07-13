package net.kunmc.lab.widerworldborder.config;

import net.kunmc.lab.widerworldborder.WiderWorldBorder;
import net.kunmc.lab.widerworldborder.command.CommandConst;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;

public class ConfigManager {
    static FileConfiguration config;

    public static Map<String,Integer> integerConfig = new HashMap<>();

    /**
     * configの読み込み
     * @param isReload reloadExecution
     */
    public static void loadConfig(boolean isReload){
        WiderWorldBorder.plugin.saveDefaultConfig();

        //configのリロード
        if(isReload){
            WiderWorldBorder.plugin.reloadConfig();
        }

        //configの取得
        config = WiderWorldBorder.plugin.getConfig();

        //範囲 default:
        String range = CommandConst.WIDE_RANGE;
        integerConfig.put(range, config.getInt(range));
    }

    /**
     * configの保存
     * @param key configName
     */
    public static void setConfig(String key){
        if(integerConfig.containsKey(key)){
            config.set(key, integerConfig.get(key));
        }
        WiderWorldBorder.plugin.saveConfig();
    }
}
