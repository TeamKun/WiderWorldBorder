package net.kunmc.lab.widerworldborder.game;

import java.util.ArrayList;
import java.util.List;

public class MobListManager {

    public static List<String> mobList = new ArrayList<>();
    public static ListStatus listStatus = ListStatus.LIST_ON;

    public enum ListStatus{
        //ゲームの状態
        LIST_ON,
        LIST_OFF
    }

    public static void controller(ListStatus listStatus){MobListManager.listStatus = listStatus;}

    public static boolean isOn() {return listStatus == ListStatus.LIST_ON;}

    public static boolean isOff() {return listStatus == ListStatus.LIST_OFF;}

}
