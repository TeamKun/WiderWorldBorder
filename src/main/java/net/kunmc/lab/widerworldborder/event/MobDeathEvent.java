package net.kunmc.lab.widerworldborder.event;

import net.kunmc.lab.widerworldborder.command.CommandConst;
import net.kunmc.lab.widerworldborder.config.ConfigManager;
import net.kunmc.lab.widerworldborder.game.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.WorldBorder;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class MobDeathEvent implements Listener {

    @EventHandler
    public void onMobDeathEvent(EntityDamageByEntityEvent event) {
        //ゲーム
        if(GameManager.isRunning()) {
            //攻撃を受ける側がLivingEntityでなければreturn
            if (!(event.getEntity() instanceof LivingEntity)) {
                return;
            }
            //攻撃を受ける側がPlayerならreturn
            if (event.getEntity() instanceof Player) {
                return;
            }
            //攻撃する側がPlayerじゃない場合return
            if (!(event.getDamager() instanceof Player)) {
                return;
            }

            //HPが0になったときに実行
            LivingEntity mob = (LivingEntity) event.getEntity();

            if (!(mob.getHealth() - event.getDamage() > 0)) {
                //ワールドボーダーを広げる処理
                WorldBorder worldBorder = event.getDamager().getWorld().getWorldBorder();
                Integer num = ConfigManager.integerConfig.get(CommandConst.WIDE_RANGE);
                worldBorder.setSize(worldBorder.getSize() + num);
                //全体に通知
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.sendMessage(ChatColor.AQUA + "ワールドボーダーのサイズが" + num + "ブロック増えました。");
                }
            }
        }
    }

}
