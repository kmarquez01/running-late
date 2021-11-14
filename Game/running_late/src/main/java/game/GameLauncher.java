package game;

public class GameLauncher {

//    public GameLauncher() {
//
//        Game game = new Game();
//        JFrame gameFrame = new JFrame("276 game");
//        gameFrame.setPreferredSize(new Dimension(game.tile * game.gameCol,
//                game.tile * game.gameRow));
//        gameFrame.add(game);
//        game.startGameThread();
//
//        gameFrame.setResizable(false);
//        gameFrame.pack();
//        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        gameFrame.setLocationRelativeTo(null);
//        gameFrame.setVisible(true);
//    }

    public static void main(String[] args) {
        GameScreen game = new GameScreen();
        game.startGameThread();
//        GameLauncher game = new GameLauncher();
    }
}