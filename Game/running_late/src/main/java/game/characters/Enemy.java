package game.characters;

import game.GameScreen;
import game.states.GameState;
import game.utils.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * The class that represents enemies
 */
public class Enemy extends Character {

    private GameScreen screen;
    private KeyHandler input;
    private Player player;

    /**
     * Creates the enemy
     * @param screen - the game screen
     * @param x - where on the x-axis we want to create the player
     * @param y - where on the y-axis we want to create the player
     * @param player - the player character
     */
    public Enemy(GameScreen screen, int x, int y, Player player) {

        position = new Point(x,y);
        speed = 2;

        this.screen = screen;
        this.input = input;
        this.player = player;

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        setEnemyImage();
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
     * Sets the enemies' sprites
     */
    public void setEnemyImage(){
        try {
            up1 = ImageIO.read(getClass().getResource("/enemy/MovEn_idleup.png"));
            up2 = ImageIO.read(getClass().getResource("/enemy/MovEn_up1.png"));
            up3 = ImageIO.read(getClass().getResource("/enemy/MovEn_up2.png"));
            down1 = ImageIO.read(getClass().getResource("/enemy/MovEn_idledown.png"));
            down2 = ImageIO.read(getClass().getResource("/enemy/MovEn_down1.png"));
            down3 = ImageIO.read(getClass().getResource("/enemy/MovEn_down2.png"));
            left1 = ImageIO.read(getClass().getResource("/enemy/MovEn_idleleft.png"));
            left2 = ImageIO.read(getClass().getResource("/enemy/MovEn_left1.png"));
            right1 = ImageIO.read(getClass().getResource("/enemy/MovEn_idleright.png"));
            right2 = ImageIO.read(getClass().getResource("/enemy/MovEn_right1.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Moves the enemy closer to the player character
     */
    public void move() {

        if(GameState.gameState == GameState.PLAYING) {

            if (player.position.y != position.y || player.position.x != position.x) {

                if (player.position.y < position.y) {
                    direction = "up";
                } else if (player.position.y > position.y) {
                    direction = "down";
                } else if (player.position.x > position.x) {
                    direction = "right";
                } else if (player.position.x < position.x) {
                    direction = "left";
                }

                // check tile for collision
                collisionOn = false;
//                screen.collisionHandler.checkTile(this);
                this.checkTile(screen);

                // have to do where if collison, change either 90 or 180 degree
                if (collisionOn) {
                    if (direction == "up") {
                        direction = "right";
                        collisionOn = false;
                    } else if (direction == "up") {
                        direction = "left";
                        collisionOn = false;
                    } else if (direction == "down") {
                        direction = "right";
                        collisionOn = false;
                    } else if (direction == "down") {
                        direction = "left";
                        collisionOn = false;
                    } else if (direction == "left") {
                        direction = "up";
                        collisionOn = false;
                    } else if (direction == "left") {
                        direction = "down";
                        collisionOn = false;
                    } else if (direction == "right") {
                        direction = "up";
                        collisionOn = false;
                    } else if (direction == "right") {
                        direction = "down";
                        collisionOn = false;
                    }
                }

                // if collision:
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

}