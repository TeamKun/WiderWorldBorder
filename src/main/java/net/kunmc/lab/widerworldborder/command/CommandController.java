package net.kunmc.lab.widerworldborder.command;

import net.kunmc.lab.widerworldborder.config.ConfigManager;
import net.kunmc.lab.widerworldborder.game.GameManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommandController implements CommandExecutor, TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();
        if(args.length == 1){
            String input = args[args.length-1];
            String[] target = {CommandConst.START,CommandConst.STOP,CommandConst.WIDE_RANGE};
            completions.addAll(Arrays.asList(target).stream()
                    .filter(e -> e.startsWith(input)).collect(Collectors.toList()));
        }else if(args.length == 2 && args[0].equals(CommandConst.WIDE_RANGE)){
            completions.add("<Number>");
        }
        return completions;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0){
            sender.sendMessage(ChatColor.GREEN + "----------コマンド一覧----------");
            sender.sendMessage("/wwb start : ゲームの開始");
            sender.sendMessage("/wwb stop : ゲームの停止");
            sender.sendMessage("/wwb widerRange : Mobを倒したときにワールドボーダーの広がる範囲の表示");
            sender.sendMessage("/wwb widerRange <Number> : Mobを倒したときにワールドボーダーの広がる範囲の設定");
            sender.sendMessage(ChatColor.GREEN + "----------コマンド一覧----------");
        }else if(args.length == 1){
            if(args[0].equals(CommandConst.START)){
                if(GameManager.isStop()){
                    GameManager.controller(GameManager.GameStatus.MODE_START);
                    sender.sendMessage(ChatColor.GREEN + "ゲームを開始しました。");
                }else{
                    sender.sendMessage(ChatColor.RED + "ゲームはすでに開始されています。");
                }
            }else if(args[0].equals(CommandConst.STOP)){
                if(GameManager.isRunning()){
                    GameManager.controller(GameManager.GameStatus.MODE_STOP);
                    sender.sendMessage(ChatColor.GREEN + "ゲームを停止しました。");
                }else{
                    sender.sendMessage(ChatColor.RED + "ゲームはすでに停止されています。");
                }
            }else if(args[0].equals(CommandConst.WIDE_RANGE)){
                ConfigManager.loadConfig(true);
                Integer num = ConfigManager.integerConfig.get(CommandConst.WIDE_RANGE);
                sender.sendMessage(ChatColor.GREEN + "Mobを倒したときに広がるサイズは" + num + "に設定されています。");
            }else{
                sender.sendMessage(ChatColor.RED + "コマンドの形式が異なります。");
                return false;
            }
        }else if(args.length == 2){
            if(args[0].equals(CommandConst.WIDE_RANGE)){
                    try{
                        if(Integer.parseInt(args[1]) > 0){
                        int num = Integer.parseInt(args[1]);
                            ConfigManager.integerConfig.put(CommandConst.WIDE_RANGE,num);
                            ConfigManager.setConfig(CommandConst.WIDE_RANGE);
                            ConfigManager.loadConfig(true);
                            sender.sendMessage(ChatColor.GREEN + "Mobを倒したときに広がるサイズを" + args[1] + "に設定しました。");
                        }else{
                            sender.sendMessage(ChatColor.RED+"引数には自然数を入力してください。");
                            return false;
                        }
                }
                catch (NumberFormatException ex){
                    sender.sendMessage(ChatColor.RED+"引数には自然数を入力してください。");
                    return false;
                }
            }
        }else{
            sender.sendMessage(ChatColor.RED + "コマンドの形式が異なります。");
            return false;
        }
        return true;

    }
}