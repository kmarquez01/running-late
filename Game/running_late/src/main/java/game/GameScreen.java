package game;
import game.characters.Enemy;
import game.characters.EnemySetter;
import game.characters.Player;
import game.objects.Object;
import game.stats.Score;
import game.stats.Stopwatch;
import game.tiles.TileManager;
import game.objects.ObjectSetter;
import game.states.GameState;
import game.utils.CollisionHandler;
import game.utils.KeyHandler;
import game.utils.MouseInput;
import game.states.*;
import game.objects.Exit;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;

public class GameScreen extends JPanel implements Runnable {

    // These are for setting the size of the game window:
    public int tileSize = 48;
    public int gameCol = 30;
    public int gameRow = 20;

    // The thread that will run the game:
    private Thread gameThread;

    // A score label:
    public Score score = new Score();

    //Stopwatch:
    public Stopwatch timeLabel = new Stopwatch();

    // A KeyHandler to handle all input
    public KeyHandler input = new KeyHandler();
    public MouseInput mouseInput = new MouseInput();

    // A Collision Handler to handle all collisions
    public CollisionHandler collisionHandler = new CollisionHandler(this);

    // A tile manager to draw the tiles
    public TileManager tileManager = new TileManager(this);

    // A player for the user to control
    public Player player = new Player(this, input, score, 100, 100);

    // A list of all the game's objects
    public Object obj[] = new Object[24];

    // A list of all the game's enemies
    public Enemy enemy[] = new Enemy[12];

    // A setter to set the location of each object
    public ObjectSetter aSetter = new ObjectSetter(this);

    // A setter to set the location of each enemy
    public EnemySetter eSetter = new EnemySetter(this, player);

    // Title screen or opening landing page
    public TitleScreenPanel titleScreenPanel = new TitleScreenPanel(this);

    //Paused Menu
    public GamePauseMenu gamePauseMenu = new GamePauseMenu(this);

    public GameOverMenu gameOverMenu = new GameOverMenu(this);

    public GameWinMenu gameWinMenu = new GameWinMenu(this);

    

    // Creates the game window
    public GameScreen(){

        JFrame gameFrame = new JFrame("Running Late");
        gameFrame.setPreferredSize(new Dimension(tileSize * gameCol, tileSize * gameRow));

        setBackground(Color.black);
        setDoubleBuffered(true);
        addKeyListener(input);
        addMouseListener(mouseInput);

        gameFrame.setResizable(false);
        gameFrame.pack();
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.add(this);
        gameFrame.pack();
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);
        setFocusable(true);

    }

    // sets up the game's objects
    public void setupGame(){
        aSetter.setObject();
        eSetter.setEnemy();
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

       
            // player movement
            player.move();

            // enemy movement
            for(int i = 0; i < enemy.length; i++){
                if(enemy[i] != null){
                    enemy[i].move();
                }
            }
        
          
    }

    // This is called everytime repaint() is called
    // It's responsible for displaying everything to the screen
    // So every graphic must go here
    // this is how RNG works in case we add more BRs: (int)Math.floor(Math.random()*(max-min+1)+min)
    int random_int = (int)Math.floor(Math.random()*(5)+18); //Random chooses Bonus Rewards to draw
    int counter = 480; //For drawing Bonus Rewards at random spots and staying at for a specific time
    public void paintComponent(Graphics graphic) {

        super.paintComponent(graphic);
        Graphics2D G2D = (Graphics2D)graphic;

        switch (GameState.gameState){
            case MENU:
                titleScreenPanel.draw(G2D);
                G2D.dispose();
                break;

            case PLAYING:
                // Draws the tiles
                tileManager.draw(G2D);
                timeLabel.startTimer();

                
        
                // Draws the objects (except Bonus Rewards)
                for(int i = 0; i < obj.length-6; i++){
                    if(obj[i] != null){
                        obj[i].draw(G2D,this);
                    }
                }
                //draw exit
                obj[23].draw(G2D,this);

                //Draws Bonus at random
                if(counter > 60){
                    if(obj[random_int] != null){
                        obj[random_int].draw(G2D,this);
                    }
                    counter --;
                }
                else if(counter <= 60 && counter > 0){
                    counter --;
                }
                else{
                    counter = 480; //Reset counter
                    random_int = (int)Math.floor(Math.random()*(5)+18); //Re-choose another Bonus Reward
                }
        
                // Draws the enemies
                for(int i = 0; i < enemy.length; i++){
                    if(enemy[i] != null){
                        enemy[i].draw(G2D);
                    }
                }
        
                // Draws the player
                player.draw(G2D);
        
                // Draws the score
                score.draw(G2D);

                // Draws the timer
                timeLabel.draw(G2D);
        
                G2D.dispose();
                break;
            
            case EXIT:
                System.exit(0);
                break;

            case PAUSED:
                gamePauseMenu.draw(G2D);
                score.draw(G2D);
                timeLabel.stopTimer();
                timeLabel.draw(G2D);
                if(GameState.gameState == GameState.RESTART){
                    G2D.dispose();
                }
                break;

            case RESTART:

                score.draw(G2D);
                timeLabel.stopTimer();
                timeLabel.draw(G2D);
                player.position = new Point(100,100);
                
                

                setupGame();
                
                tileManager.draw(G2D);
                timeLabel.startTimer();

                timeLabel.draw(G2D);
        
               GameState.gameState = GameState.PLAYING;
       
                break;

            case GAMEOVER:
                gameOverMenu.draw(G2D);
                score.draw(G2D);
                timeLabel.stopTimer();
                timeLabel.draw(G2D);
                break;

            case GAMEWIN:
                gameWinMenu.draw(G2D);
                score.draw(G2D);
                timeLabel.stopTimer();
                timeLabel.draw(G2D);
                break;
        }

    }
}
