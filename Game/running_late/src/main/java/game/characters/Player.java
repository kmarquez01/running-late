package game.characters;

import game.GameScreen;
import game.stats.Score;
import game.utils.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Player extends Character {

    private GameScreen screen;
    private KeyHandler input;
    private Score score;
    private boolean hurt = false;
    public boolean enemyCollision = false;


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

    public void setDefaultValues() {
        worldX = screen.tileSize * 23;
        worldY = screen.tileSize * 21;
    }

    public void setPlayerImage(){
        try {
            up1 = ImageIO.read( (new FileInputStream ("resources/player/idleup.png") ) );
            up2 = ImageIO.read( (new FileInputStream ("resources/player/up1.png")));
            up3 = ImageIO.read( (new FileInputStream ("resources/player/up2.png")));
            down1 = ImageIO.read( (new FileInputStream ("resources/player/idledown.png")));
            down2 = ImageIO.read( (new FileInputStream ("resources/player/down1.png")));
            down3 = ImageIO.read( (new FileInputStream ("resources/player/down2.png")));
            left1 = ImageIO.read( (new FileInputStream ("resources/player/idleleft.png")));
            left2 = ImageIO.read( (new FileInputStream ("resources/player/left1.png")));
            right1 = ImageIO.read( (new FileInputStream ("resources/player/idleright.png")));
            right2 = ImageIO.read( (new FileInputStream ("resources/player/right1.png")));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void move() {

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
                screen.collisionHandler.checkTile(this);

                // check for enemy collision
                screen.collisionHandler.checkTile(this);

                // check object collision
                // basically objectIndex is by default set to 999
                // if you are touching an object, then objectIndex becomes its index
                // you then pick it up in the next call (if its 999 u dont pick up anything)
                int objectIndex = screen.collisionHandler.checkObject(this, true);
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

    public void pickUpObject(int i){
        if ( i != 999){
            if (!screen.obj[i].hurtful) {
                score.currentScore++;
             }
            else if (screen.obj[i].hurtful) {
                score.currentScore--;
                hurt = true;
                // wait 2/3 second
                hurt = false;

            }
            screen.obj[i] = null;
        }
    }

    public void draw(Graphics2D G2D){

        BufferedImage sprite = null;

        if (hurt) {
            // make the player red
        }

        if (direction == "up") {
            if(spriteNum == 1) {
                sprite = up1;
            } else if (spriteNum == 2) {
                sprite = up2;
            } else if (spriteNum == 3) {
                sprite = up1;
            } else if (spriteNum == 4) {
                sprite = up3;
            }
        } else if (direction == "down"){
            if(spriteNum == 1) {
                sprite = down1;
            } else if(spriteNum == 2) {
                sprite = down2;
            } else if(spriteNum == 3) {
                sprite = down1;
            } else if(spriteNum == 4) {
                sprite = down3;
            }
        } else if (direction == "left"){
            if (spriteNum == 1) {
                sprite = left1;
            } else if (spriteNum == 2) {
                sprite = left2;
            } else if (spriteNum == 3) {
                sprite = left1;
            } else if (spriteNum == 4) {
                sprite = left2;
            }
        } else if (direction == "right") {
            if(spriteNum == 1) {
                sprite = right1;
            } else if (spriteNum == 2) {
                sprite = right2;
            } else if (spriteNum == 3) {
                sprite = right1;
            } else if (spriteNum == 4) {
                sprite = right2;
            }
        }

        // board sets the size of the player
        G2D.drawImage(sprite, position.x, position.y, screen.tileSize, screen.tileSize, null);

    }

    public int[] getCoordinates(){
        return (new int[]{position.x, position.y});
    }

}
