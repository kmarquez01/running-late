package game.states;

public enum GameState {

    PLAYING,
    MENU,
    SETTINGS,
    EXIT,
    PAUSED;

    public static GameState gameState = MENU;
}
