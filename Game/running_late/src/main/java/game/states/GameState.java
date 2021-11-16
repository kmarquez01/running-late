package game.states;

public enum GameState {

    PLAYING,
    MENU,
    RESTART,
    EXIT,
    PAUSED,
    GAMEOVER,
    GAMEWIN;

    public static GameState gameState = MENU;
}
