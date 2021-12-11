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
import javax.swing.*;
import java.awt.*;



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

    public int counter = 480; //For drawing Bonus Rewards at random spots and staying for a specific time
    public boolean isBonusSet = false; //checks if bonus reward has been set in ObjectSetter
    public int musicHandler = -1;  //Makes sure appropriate music is being played during each state

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
                menuCase(G2D);
                break;

            case PLAYING:
                playingCase(G2D);
                break;
            
            case EXIT:
                System.exit(0);
                break;

            case PAUSED:
                pausedCase(G2D);
                break;

            case RESTART:
                restartCase();
                break;

            case GAMEOVER:
                gameOverCase(G2D);
                break;

            case GAMEWIN:
                gameWinCase(G2D);
                break;
        }

    }

    private void gameOverCase(Graphics2D G2D) {
        soundPlayer(7,1);
        gameOverMenu.draw(G2D);
        player.position = new Point(100,100);
        setupGame();
        score.draw(G2D);
        timeLabel.stopTimer();
        timeLabel.draw(G2D);
        G2D.dispose();
    }

    private void gameWinCase(Graphics2D G2D) {
        soundPlayer(8,1);
        gameWinMenu.draw(G2D);
        score.draw(G2D);
        timeLabel.stopTimer();
        timeLabel.draw(G2D);
    }

    private void restartCase() {
        player.resetInputs();
        player.resetStats();
        player.position = new Point(100,100);
        setupGame();
        // Resets the score
        score.currentScore = 0;
        // Resets the timer
        timeLabel.resetClock();
        GameState.gameState = GameState.PLAYING;
    }

    private void pausedCase(Graphics2D G2D) {
        player.resetInputs();
        soundPlayer(4,1);
        gamePauseMenu.draw(G2D);
        score.draw(G2D);
        timeLabel.stopTimer();
        timeLabel.draw(G2D);
    }

    private void playingCase(Graphics2D G2D) {
        soundPlayer(1,0);
        // Draws the tiles
        tileManager.draw(G2D);
        timeLabel.startTimer();
        // Draws the objects and exit (except Bonus Rewards)
        drawObjects(G2D);
        //For making bonus reward appear/disappear at a certain amount of time
        drawBonusReward(G2D);
        // Draws the enemies
        drawEnemies(G2D);
        // Draws the player
        player.draw(G2D, this);
        // Draws the score
        score.draw(G2D);
        // Draws the timer
        timeLabel.draw(G2D);
        G2D.dispose();
    }

    private void menuCase(Graphics2D G2D) {
        score.currentScore = 0;
        player.resetStats();
        soundPlayer(0,0);
        titleScreenPanel.draw(G2D);
    }



    // For drawing all objects in array
    public void drawObjects(Graphics2D G2D){
        for(int i = 0; i < obj.length-1; i++){
            if(obj[i] != null){
                obj[i].draw(G2D,this);
            }
        }
    }

    // For drawing all enemies in array
    public void drawEnemies(Graphics2D G2D){
        for(int i = 0; i < enemy.length; i++){
            if(enemy[i] != null){
                enemy[i].draw(G2D, this);
            }
        }
    }

    // For drawing bonus reward (when to appear/ disappear)
    public void drawBonusReward(Graphics2D G2D){

        if(counter > 60){
            if(!isBonusSet){
                aSetter.setBonusReward();
                isBonusSet = true;
            }
            if(obj[19] != null){
                obj[19].draw(G2D,this);
            }
            counter --;
        }
        else if(counter > 0){
            obj[19] = null;
            counter --;
        }
        else{
            isBonusSet = false; //for resetting a new bonus reward
            counter = 480; //Reset counter
        }
    }

    // For handling which sounds should be playing at a given state
    public void soundPlayer(int soundIndex, int soundChooser){
        if(musicHandler != soundIndex){
            if(music.clip != null){stopSound();}
            if(soundChooser == 0){ // 0 means it wants to play music
                playMusic(soundIndex);
            }
            else{ // !0 means it wants to play effects
                playEffects(soundIndex);
            }
            musicHandler = soundIndex;
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