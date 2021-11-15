package game.characters;

import game.GameScreen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class Enemy extends Character {

    private GameScreen screen;
    private Player player;

    public Enemy(GameScreen screen, int x, int y, Player player) {

        position = new Point(x,y);

        this.screen = screen;
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
            up1 = ImageIO.read( (new FileInputStream("resources/enemy/idleup.png") ) );
            up2 = ImageIO.read( (new FileInputStream ("resources/enemy/up1.png")));
            up3 = ImageIO.read( (new FileInputStream ("resources/enemy/up2.png")));
            down1 = ImageIO.read( (new FileInputStream ("resources/enemy/idledown.png")));
            down2 = ImageIO.read( (new FileInputStream ("resources/enemy/down1.png")));
            down3 = ImageIO.read( (new FileInputStream ("resources/enemy/down2.png")));
            left1 = ImageIO.read( (new FileInputStream ("resources/enemy/idleleft.png")));
            left2 = ImageIO.read( (new FileInputStream ("resources/enemy/left1.png")));
            right1 = ImageIO.read( (new FileInputStream ("resources/enemy/idleright.png")));
            right2 = ImageIO.read( (new FileInputStream ("resources/enemy/right1.png")));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void move() {
        // player is now a field inside enemy
        // this will implement a pathfinding algortihm with player.position as its target
        // it will also take into consideration walls and punishments

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
