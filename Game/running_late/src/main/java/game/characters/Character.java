package game.characters;

import java.awt.*;

import java.awt.image.BufferedImage;

/**
 * Blueprint for all characters in the game
 */
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
    public int speed;

    // For sprites and drawing:
    int spriteCounter = 0;
    int spriteNum = 1;

    // For collisions:
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;

}