package game.characters;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Character {

    // the character's animation sprites
    public BufferedImage up1, up2, up3,
            down1, down2, down3,
            left1, left2, left3,
            right1, right2, right3;

    public int worldX, worldY;

    // the direction the character is facing
    public String direction;

    // the character's position
    public Point position;

    // the character's speed
    public int speed = 4;

    int spriteCounter = 0;
    int spriteNum = 1;

    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;



/*    public boolean checkWall(int currX, int currY) {
        int newX = (currX - Board.X_OFFSET) / Board.TILE_SIZE;
        int newY = (currY - Board.Y_OFFSET) / Board.TILE_SIZE;

        if (newX < 0 || newY < 0 || newX >= Board.COLUMNS || newY >= Board.ROWS) {
            return false;
        }
        return Board.Boardstatus[newY][newX] != 4;
    }*/
}