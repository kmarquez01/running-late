package game.states;

/**
 * Holds all the different states of the game
 */
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
