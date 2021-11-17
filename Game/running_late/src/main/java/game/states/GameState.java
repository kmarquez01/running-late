package game.states;

public enum GameState {

    MENU,
    PLAYING,
    RESTART,
    EXIT,
    PAUSED,
    GAMEOVER,
    GAMEWIN;

    public static GameState gameState = MENU;
}
