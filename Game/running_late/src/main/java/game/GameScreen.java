package game;
import game.characters.Player;
import game.tiles.TileManager;
import game.utils.CollisionHandler;
import game.utils.KeyHandler;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel implements Runnable {

    // These are for setting the size of the game window:
    public int tileSize = 48;
    public int gameCol = 30;
    public int gameRow = 20;

    // The thread that will run the game:
    private Thread gameThread;

    // A KeyHandler to handle all input
    KeyHandler input = new KeyHandler();

    // A player for the user to control
    Player player = new Player(this, input);

    public CollisionHandler collisionHandler = new CollisionHandler(this);

    public TileManager tileManager = new TileManager(this);

    // Creates the game window
    public GameScreen(){

        JFrame gameFrame = new JFrame("276 game");
        gameFrame.setPreferredSize(new Dimension(tileSize * gameCol, tileSize * gameRow));

        setBackground(Color.black);
        setDoubleBuffered(true);
        addKeyListener(input);

        gameFrame.setResizable(false);
        gameFrame.pack();
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.add(this);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);
        setFocusable(true);

    }

    // Starts the game
    public void startGameThread () {
        gameThread = new Thread(this);
        gameThread.start();
    }

    // Runs the game
    // Is responsible for the gameloop (frame rate basically)
    @Override
    public void run () {

        double drawInterval =  1000/60;
        double refreshAt = System.currentTimeMillis() + drawInterval;

        while(gameThread != null) {

            refresh();
            repaint();

            // This stops the game from updating way too fast
            // Basically, it caps the game at a fixed frame rate:
            try {
                double remainingTime = refreshAt - System.currentTimeMillis();
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep( (long)remainingTime );
                refreshAt += drawInterval;
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    // everything that can change with each frame goes here:
    private void refresh() {
        player.move();
    }

    // This is called everytime repaint() is called
    // It's responsible for displaying everything to the screen
    // So every graphic must go here
    public void paintComponent(Graphics graphic) {
        super.paintComponent(graphic);
        Graphics2D G2D = (Graphics2D)graphic;
        tileManager.draw(G2D);
        player.draw(G2D);
        G2D.dispose();
    }


}
