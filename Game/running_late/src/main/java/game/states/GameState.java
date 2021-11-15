package game.states;

public enum GameState {

    PLAYING,
    MENU,
    SETTINGS,
    EXIT,
    PAUSED,
    GAMEOVER;

    public static GameState gameState = MENU;
}
