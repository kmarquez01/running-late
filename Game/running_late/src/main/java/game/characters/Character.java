package game.characters;

import java.awt.*;

import java.awt.image.BufferedImage;

import game.GameScreen;

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

    /**
     * This method checks for collisions between tiles and the character
     */

    public void checkTile(GameScreen screen) {

        int entityLeftWorldX = position.x + solidArea.x;
        int entityRightWorldX = position.x + solidArea.x + solidArea.width;
        int entityTopWorldY = position.y + solidArea.y;
        int entityBottomWorldY = position.y + solidArea.y + solidArea.height;

        int entityLeftCol = entityLeftWorldX/screen.tileSize;
        int entityRightCol = entityRightWorldX/screen.tileSize;
        int entityTopRow = entityTopWorldY/screen.tileSize;
        int entityBottomRow = entityBottomWorldY/screen.tileSize;

        int tileNum1, tileNum2;

        if (direction == "up") {
            entityTopRow = (entityTopWorldY - speed) / screen.tileSize;
            tileNum1 = screen.tileManager.MapTileNum[entityLeftCol][entityTopRow];
            tileNum2 = screen.tileManager.MapTileNum[entityRightCol][entityTopRow];
            if (screen.tileManager.tile[tileNum1].collision || screen.tileManager.tile[tileNum2].collision) {
                collisionOn = true;
            }
        } else if (direction == "down") {
            entityBottomRow = (entityBottomWorldY + speed) / screen.tileSize;
            tileNum1 = screen.tileManager.MapTileNum[entityLeftCol][entityBottomRow];
            tileNum2 = screen.tileManager.MapTileNum[entityRightCol][entityBottomRow];
            if (screen.tileManager.tile[tileNum1].collision || screen.tileManager.tile[tileNum2].collision) {
                collisionOn = true;
            }
        } else if (direction == "left") {
            entityLeftCol = (entityLeftWorldX - speed) / screen.tileSize;
            tileNum1 = screen.tileManager.MapTileNum[entityLeftCol][entityTopRow];
            tileNum2 = screen.tileManager.MapTileNum[entityLeftCol][entityBottomRow];
            if (screen.tileManager.tile[tileNum1].collision || screen.tileManager.tile[tileNum2].collision) {
                collisionOn = true;
            }
        } else if (direction == "right") {
            entityRightCol = (entityRightWorldX + speed) / screen.tileSize;
            tileNum1 = screen.tileManager.MapTileNum[entityRightCol][entityTopRow];
            tileNum2 = screen.tileManager.MapTileNum[entityRightCol][entityBottomRow];
            if (screen.tileManager.tile[tileNum1].collision || screen.tileManager.tile[tileNum2].collision) {
                collisionOn = true;
            }
        }

    }

    /**
     * Responsible for drawing the character onto the screen
     * @param G2D - graphics
     */
    public void draw(Graphics2D G2D, GameScreen screen){

        BufferedImage sprite = null;

        //animation for hurt player
        if (this instanceof Player) {
            if (((Player) this).isHurt() == true) {
                G2D.setXORMode(new Color(200, 0, 0));
            }
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

        // board sets the size of the character
        G2D.drawImage(sprite, position.x, position.y, screen.tileSize, screen.tileSize, null);

    }

}