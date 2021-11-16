package game.states;

public enum GameState {

    PLAYING,
    MENU,
    SETTINGS,
    EXIT,
    PAUSED,
    GAMEOVER,
    GAMEWIN;

    public static GameState gameState = MENU;
}
