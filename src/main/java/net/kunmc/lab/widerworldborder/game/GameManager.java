package net.kunmc.lab.widerworldborder.game;

public class GameManager{
    public static GameStatus runningMode = GameStatus.MODE_STOP;

    public enum GameStatus{
        //ゲームの状態
        MODE_START,
        MODE_STOP
    }

    public static void controller(GameStatus runningMode){
        GameManager.runningMode = runningMode;
    }

    public static boolean isRunning() {
        return runningMode == GameStatus.MODE_START;
    }

    public static boolean isStop() {
        return runningMode == GameStatus.MODE_STOP;
    }

}
