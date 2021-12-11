package game.characters;

import game.GameScreen;
import game.objects.Reward;
import game.states.GameState;
import game.stats.Score;
import game.utils.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * The class that represents the player
 */
public class Player extends Character {

    private GameScreen screen;
    private KeyHandler input;
    private Score score;
    public boolean hurt = false;
    public boolean enemyCollision = false;
    int numRewardsCollected = 0;

    /**
     * Constructor
     * @param screen - the game screen
     * @param input - the key handler
     * @param score - the score
     * @param x - where on the x-axis we want to create the player
     * @param y - where on the y-axis we want to create the player
     */
    public Player (GameScreen screen, KeyHandler input, Score score, int x, int y) {

        position = new Point(x,y);
        speed = 4;

        this.screen = screen;
        this.input = input;
        this.score = score;

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        setPlayerImage();
        direction = "down";
    }

    /**
     * Sets all the default values
     */
    public void setDefaultValues() {
        worldX = screen.tileSize * 23;
        worldY = screen.tileSize * 21;
    }

    /**
     * Sets all the player sprites
     */
    public void setPlayerImage(){
        try {
            up1 = ImageIO.read(getClass().getResource("/player/idleup.png"));
            up2 = ImageIO.read(getClass().getResource("/player/up1.png"));
            up3 = ImageIO.read(getClass().getResource("/player/up2.png"));
            down1 = ImageIO.read(getClass().getResource("/player/idledown.png"));
            down2 = ImageIO.read(getClass().getResource("/player/down1.png"));
            down3 = ImageIO.read(getClass().getResource("/player/down2.png"));
            left1 = ImageIO.read(getClass().getResource("/player/idleleft.png"));
            left2 = ImageIO.read(getClass().getResource("/player/left1.png"));
            right1 = ImageIO.read(getClass().getResource("/player/idleright.png"));
            right2 = ImageIO.read(getClass().getResource("/player/right1.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Takes in key inputs and moves the player accordingly
     */
    public void move() {

        screen.collisionHandler.checkEnemy(this);


        if (enemyCollision) {
            GameState.gameState = GameState.GAMEOVER;
        }

        if (score.getScoreNum() < 0){
            GameState.gameState = GameState.GAMEOVER;
        }

        if (hurt) {
            // wait 1/5 second
            Thread imageLoadingThread = new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(500);
                        hurt = false;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // end loop
                }
            });
            imageLoadingThread.start();

        }

        if (!hurt) {
            // If the key is pressed
            if (input.up || input.down || input.left || input.right) {

                if (input.up) {
                    direction = "up";
                } else if (input.left) {
                    direction = "left";
                } else if (input.right) {
                    direction = "right";
                } else if (input.down) {
                    direction = "down";
                }

                // check tile for collision
                collisionOn = false;
//                screen.collisionHandler.checkTile(this);
                this.checkTile(screen);

                // check for enemy collision
                screen.collisionHandler.checkEnemy(this);

                // check object collision
                // basically objectIndex is by default set to 999
                // if you are touching an object, then objectIndex becomes its index
                // you then pick it up in the next call (if its 999 u dont pick up anything)
                int objectIndex = screen.collisionHandler.checkReward(this);
                pickUpObject(objectIndex);

                // if there is no collision, move:
                if (!collisionOn) {
                    if (direction == "up") {
                        position.y -= speed;
                    } else if (direction == "down") {
                        position.y += speed;
                    } else if (direction == "left") {
                        position.x -= speed;
                    } else if (direction == "right") {
                        position.x += speed;
                    }
                }

                // for drawing the character moving:
                spriteCounter++;
                if (spriteCounter > 4) {
                    if (spriteNum == 1) {
                        spriteNum = 2;
                    } else if (spriteNum == 2) {
                        spriteNum = 3;
                    } else if (spriteNum == 3) {
                        spriteNum = 4;
                    } else if (spriteNum == 4) {
                        spriteNum = 1;
                    }
                    spriteCounter = 0;
                }

            } else {
                spriteNum = 1;
            }
        }

    }

    /**
     * For testing movement in the unit tests
     * @param direction - the direction to move the player
     */
    public void moveDirection(String direction) {
        screen.collisionHandler.checkEnemy(this);

        if (enemyCollision) {
            GameState.gameState = GameState.GAMEOVER;
        }

        if (score.getScoreNum() < 0){
            GameState.gameState = GameState.GAMEOVER;
        }

        if (hurt) {
            // wait 1/5 second
            Thread imageLoadingThread = new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(500);
                        hurt = false;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // end loop
                }
            });
            imageLoadingThread.start();

        }

        if (!hurt) {

                // check tile for collision
                collisionOn = false;
//                screen.collisionHandler.checkTile(this);
            this.checkTile(screen);

                // check for enemy collision
                screen.collisionHandler.checkEnemy(this);

                // check object collision
                // basically objectIndex is by default set to 999
                // if you are touching an object, then objectIndex becomes its index
                // you then pick it up in the next call (if its 999 u dont pick up anything)
                int objectIndex = screen.collisionHandler.checkReward(this);
                pickUpObject(objectIndex);

                // if there is no collision, move:
                if (!collisionOn) {
                    if (direction == "up") {
                        position.y -= speed;
                    } else if (direction == "down") {
                        position.y += speed;
                    } else if (direction == "left") {
                        position.x -= speed;
                    } else if (direction == "right") {
                        position.x += speed;
                    }
                }

                // for drawing the character moving:
                spriteCounter++;
                if (spriteCounter > 4) {
                    if (spriteNum == 1) {
                        spriteNum = 2;
                    } else if (spriteNum == 2) {
                        spriteNum = 3;
                    } else if (spriteNum == 3) {
                        spriteNum = 4;
                    } else if (spriteNum == 4) {
                        spriteNum = 1;
                    }
                    spriteCounter = 0;
                }

            } else {
                spriteNum = 1;
            }

    }

    /**
     * If the player collided with a reward, pick up that reward
     * @param i - the reward to pick up
     */
    public void pickUpObject(int i){
        if ( i != 999){
            if (screen.obj[i].isExit) {
                if (numRewardsCollected == 7) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                    GameState.gameState = GameState.GAMEWIN;
                } else {
                    GameState.gameState = GameState.PLAYING;
                }
            }

            else {
                if (!screen.obj[i].hurtful && !screen.obj[i].isExit) {
                    screen.playEffects(2);
                    score.currentScore++;
                    if (screen.obj[i] instanceof Reward) {
                        numRewardsCollected += 1;
                    }
                } else if (screen.obj[i].hurtful) {
                    screen.playEffects(3);
                    score.currentScore--;
                    hurt = true;
                }
                screen.obj[i] = null;
            }
        }
    }

    //getter for hurt status
    public boolean isHurt(){
        return hurt;
    }

    public int[] getCoordinates(){
        return (new int[]{position.x, position.y});
    }

    public void resetInputs(){
        input.up = false;
        input.down = false;
        input.left = false;
        input.right = false;
        input.esc = false;
    }

    public void resetStats(){
        enemyCollision = false;
        numRewardsCollected = 0;
    }

}
