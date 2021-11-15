package game.characters;

import game.GameScreen;
import game.utils.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class Enemy extends Character {

    private GameScreen screen;
    private KeyHandler input;
    private Player player;

    public Enemy(GameScreen screen, KeyHandler input, int x, int y, Player player) {

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

    public void setDefaultValues() {
        worldX = screen.tileSize * 23;
        worldY = screen.tileSize * 21;
    }

    public void setEnemyImage(){
        try {
            up1 = ImageIO.read( (new FileInputStream("resources/enemy/MovEn_idleup.png") ) );
            up2 = ImageIO.read( (new FileInputStream ("resources/enemy/MovEn_up1.png")));
            up3 = ImageIO.read( (new FileInputStream ("resources/enemy/MovEn_up2.png")));
            down1 = ImageIO.read( (new FileInputStream ("resources/enemy/MovEn_idledown.png")));
            down2 = ImageIO.read( (new FileInputStream ("resources/enemy/MovEn_down1.png")));
            down3 = ImageIO.read( (new FileInputStream ("resources/enemy/MovEn_down2.png")));
            left1 = ImageIO.read( (new FileInputStream ("resources/enemy/MovEn_idleleft.png")));
            left2 = ImageIO.read( (new FileInputStream ("resources/enemy/MovEn_left1.png")));
            right1 = ImageIO.read( (new FileInputStream ("resources/enemy/MovEn_idleright.png")));
            right2 = ImageIO.read( (new FileInputStream ("resources/enemy/MovEn_right1.png")));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void move() {

        if (player.position.y != position.y || player.position.x != position.x) {

            if (player.position.y < position.y) {
                direction = "up";
            } else if (player.position.y > position.y) {
                direction = "down";
            }else if (player.position.x > position.x) {
                direction = "right";
            } else if (player.position.x < position.x) {
                direction = "left";
            }

            // check tile for collision
            collisionOn = false;
            screen.collisionHandler.checkTile(this);

            // have to do where if collison, change either 90 or 180 degree

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

        }
        else {
            spriteNum = 1;
        }
    }

    public void draw(Graphics2D G2D) {
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

        G2D.drawImage(sprite, position.x, position.y, screen.tileSize, screen.tileSize, null);
    }

}