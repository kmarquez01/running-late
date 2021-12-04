package game;

/**
 * Responsible for launching the game
 */
public class GameLauncher {

    public static void main(String[] args) {
        GameScreen game = new GameScreen();
        game.setupGame();
        game.startGameThread();
    }
}