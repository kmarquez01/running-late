package game.characters;

import game.GameScreen;
import game.utils.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class Player extends Character {

    private GameScreen screen;
    private KeyHandler input;

    public Player (GameScreen screen, KeyHandler input) {

        this.screen = screen;
        this.input = input;

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
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

        // If the key is pressed
        if ( input.up || input.down || input.left || input.right ) {

            if (input.up) {
                direction = "up";
                //playerPosition.y -= speed;
            } else if (input.left) {
                direction = "left";
                //playerPosition.x -= speed;
            } else if (input.right) {
                direction = "right";
                //playerPosition.x += speed;
            } else if (input.down) {
                direction = "down";
                //playerPosition.y += speed;
            }

            // check tile for collision
            collisionOn = false;
            screen.collisionHandler.checkTile(this);

            // if collision:
            if (collisionOn == false) {
                if (direction == "up") {
                    playerPosition.y -= speed;
                } else if (direction == "down") {
                    playerPosition.y += speed;
                } else if (direction == "left") {
                    playerPosition.x -= speed;
                }else if (direction == "right") {
                    playerPosition.x += speed;
                }

            }

            // for drawing the character moving:
            spriteCounter++;
            if (spriteCounter > 4) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2){
                    spriteNum = 3;
                } else if(spriteNum == 3){
                    spriteNum = 4;
                } else if(spriteNum == 4){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }

        } else {
            spriteNum = 1;
        }

    }

    public void draw(Graphics2D G2D){

        BufferedImage sprite = null;

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
        G2D.drawImage(sprite,playerPosition.x, playerPosition.y, screen.tileSize, screen.tileSize, null);

    }

}
