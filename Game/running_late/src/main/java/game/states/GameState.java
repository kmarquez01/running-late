package game.states;

public enum GameState {

    PLAYING,
    MENU,
    RESTART,
    EXIT,
    PAUSED,
    GAMEOVER;

    public static GameState gameState = MENU;
}
