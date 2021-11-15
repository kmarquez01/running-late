package game;
import game.characters.Character;
import game.characters.Player;
import game.tiles.TileManager;
import game.utils.CollisionHandler;
import game.utils.KeyHandler;
import game.utils.MouseInput;
import game.states.*;

import game.objects.Reward;
import game.utils.RewardSetter;
import game.characters.Punishment;
import game.utils.PunishmentSetter;

//
//import game.objects.BonusReward;
//import game.utils.BonusRewardSetter;
//import game.characters.Enemy;
//import game.utils.EnemySetter;


import javax.swing.*;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.TitlePaneLayout;

import java.awt.*;

public class GameScreen extends JPanel implements Runnable {

    // These are for setting the size of the game window:
    public int tileSize = 48;
    public int gameCol = 30;
    public int gameRow = 20;

    private int score = 0;

    // The thread that will run the game:
    private Thread gameThread;

    // A KeyHandler to handle all input
    KeyHandler input = new KeyHandler();

    MouseInput mouseInput = new MouseInput();

  /*  MouseInput mouseInput = new MouseInput(); */

    // A player for the user to control
    Player player = new Player(this, input);

    public CollisionHandler collisionHandler = new CollisionHandler(this);

    public TileManager tileManager = new TileManager(this);

    public TitleScreenPanel titleScreenPanel = new TitleScreenPanel(this);

    // Creates the game window
    public GameScreen(){

        JFrame gameFrame = new JFrame("276 game");
        gameFrame.setPreferredSize(new Dimension(tileSize * gameCol, tileSize * gameRow));

        setBackground(Color.black);
        setDoubleBuffered(true);
        addKeyListener(input);
        addMouseListener(mouseInput);

        gameFrame.setResizable(false);
        gameFrame.pack();
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.add(this);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);
        setFocusable(true);
    }

    //add rewards
    public Reward rewards[] = new Reward[10]; //can display up to 10 rewards at the same time
    public RewardSetter rSetter = new RewardSetter(this);

    public void setUpRewards(){
        rSetter.setRewards();
    }

    //add punishments
    public Punishment punishments[] = new Punishment[10];
    public PunishmentSetter pSetter = new PunishmentSetter(this);

    public void setUpPunishments(){
        pSetter.setPunishments();
    }


    // Starts the game
    public void startGameThread () {
        gameThread = new Thread(this);
        gameThread.start();

        score = 0;
        Object graphic = new Object();
        Graphics2D G2D = (Graphics2D) graphic;
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

        switch (GameState.gameState){
            case MENU:
                titleScreenPanel.draw(G2D);
                G2D.dispose();
                break;

            case PLAYING:
                tileManager.draw(G2D);

                //draw stationary rewards before drawing player
                for (int i=0; i < rewards.length; i++){
                    if (rewards[i] != null){
                        rewards[i].draw(G2D, this);
                    }
                }
                //draw stationary punishments before drawing player
                for (int i=0; i < punishments.length; i++){
                    if (punishments[i] != null){
                        punishments[i].draw(G2D, this);
                    }
                }

                player.draw(G2D);
                G2D.dispose();
                break;
            
            case EXIT:
                System.exit(0);
                break;

            case SETTINGS:
                

        }
        
    }



//     private void death(){
//        if (score<=0){
//            gameThread.stop();
//        }
//        //also need to die if player collides with moving enemy
//    }


}
