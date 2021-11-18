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
import game.sound.Sound;
import game.states.*;
import game.objects.Exit;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;

/**
 * Responsible for creating the game screen and game loop
 */
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

    //Music and Sound effects for the game
    Sound music = new Sound();
    Sound effects = new Sound();

    // A Collision Handler to handle all collisions
    public CollisionHandler collisionHandler = new CollisionHandler(this);

    // A tile manager to draw the tiles
    public TileManager tileManager = new TileManager(this);

    // A player for the user to control
    public Player player = new Player(this, input, score, 100, 100);

    // A list of all the game's objects
    public Object obj[] = new Object[20];

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

    // Game Over Menu
    public GameOverMenu gameOverMenu = new GameOverMenu(this);

    // Victory Menu
    public GameWinMenu gameWinMenu = new GameWinMenu(this);

    /**
     * Creates the game window
     */
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

    /**
     * Sets up the game's objects and enemies
     */
    public void setupGame(){
        aSetter.setObject();
        eSetter.setEnemy();
    }

    /**
     * Starts the Game
     */
    public void startGameThread () {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * Runs the game
     * This method is responsible for the GameLoop
     */
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

    /**
     * Everything that can change with each frame goes here:
     */
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

    //For drawing Bonus Rewards at random spots and staying for a specific time
    int counter = 480;
    //checks if bonus reward has been set in ObjectSetter
    boolean isBonusSet = false;
    //Makes sure appropriate music is being played during each state
    int musicFlag = 0;

    /**
     * This is called everytime repaint() is called
     * It's responsible for drawing all the garphics
     * @param graphic - the graphics
     */
    public void paintComponent(Graphics graphic) {

        super.paintComponent(graphic);
        Graphics2D G2D = (Graphics2D)graphic;

        switch (GameState.gameState){
            case MENU:

                score.currentScore = 0;
                player.resetStats();
                if(musicFlag != 1){ // plays Main Menu music
                    playMusic(0);
                    musicFlag = 1;
                }
                titleScreenPanel.draw(G2D);
                break;

            case PLAYING:

                if(musicFlag != 2){ // plays in Game music
                    stopSound();
                    playMusic(1);
                    musicFlag = 2;
                }
                // Draws the tiles
                tileManager.draw(G2D);
                timeLabel.startTimer();

                
        
                // Draws the objects and exit (except Bonus Rewards)
                for(int i = 0; i < obj.length-1; i++){
                    if(obj[i] != null){
                        obj[i].draw(G2D,this);
                    }
                }

                //Draws Bonus at random
                if(counter > 60){
                    if(isBonusSet == false){
                        aSetter.setBonusReward(); // makes and sets bonus reward at random possible location
                        isBonusSet = true;
                    }
                    if(obj[19] != null){
                        obj[19].draw(G2D,this);
                    }
                    counter --;
                }
                else if(counter <= 60 && counter > 0){
                    counter --;
                }
                else{
                    isBonusSet = false; //for resetting a new bonus reward
                    counter = 480; //Reset counter
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
                player.resetInputs();
                if(musicFlag != 3){ // plays Pause screen effect
                    stopSound();
                    playEffects(4);
                    musicFlag = 3;
                }
                gamePauseMenu.draw(G2D);
                score.draw(G2D);
                timeLabel.stopTimer();
                timeLabel.draw(G2D);
                
                break;

            case RESTART:

                player.resetInputs();
                player.resetStats();
                player.position = new Point(100,100);

                setupGame();
                
                 // Draws the objects and exit (except Bonus Rewards)
                 for(int i = 0; i < obj.length-1; i++){
                    if(obj[i] != null){
                        obj[i].draw(G2D,this);
                    }
                }

                //Draws Bonus at random
                if(counter > 60){
                    if(isBonusSet == false){
                        aSetter.setBonusReward();
                        isBonusSet = true;
                    }
                    if(obj[19] != null){
                        obj[19].draw(G2D,this);
                    }
                    counter --;
                }
                else if(counter <= 60 && counter > 0){
                    counter --;
                }
                else{
                    isBonusSet = false; //for resetting a new bonus reward
                    counter = 480; //Reset counter
                }
        
                // Draws the enemies
                for(int i = 0; i < enemy.length; i++){
                    if(enemy[i] != null){
                        enemy[i].draw(G2D);
                    }
                }
        
                // Draws the player
                player.draw(G2D);
        
                // Draws and resets the score
                score.currentScore = 0;
                score.draw(G2D);

                // Draws and resets the timer
                timeLabel.resetClock();
                timeLabel.draw(G2D);
        
               
        
                GameState.gameState = GameState.PLAYING;
       
                break;

            case GAMEOVER:
                if(musicFlag != 4){ // plays Game Over effect
                    stopSound();
                    playEffects(7);
                    musicFlag = 4;
                }
                gameOverMenu.draw(G2D);
                player.position = new Point(100,100);
                setupGame();
                score.draw(G2D);
                timeLabel.stopTimer();
                timeLabel.draw(G2D);

                G2D.dispose();

                
                break;

            case GAMEWIN:
                if(musicFlag != 5){ // plays Game Win effect
                    stopSound();
                    playEffects(8);
                    musicFlag = 5;
                }
                gameWinMenu.draw(G2D);
                score.draw(G2D);
                timeLabel.stopTimer();
                timeLabel.draw(G2D);
                break;
        }

    }

    //Responsible for playing all music and sound effects for the game
    public void playMusic(int index){

        music.setFile(index);
        music.play();
        music.loop();
    }

    public void playEffects(int index){

        effects.setFile(index);
        effects.play();
    }

    public void stopSound(){

        music.stop();
    }

}
