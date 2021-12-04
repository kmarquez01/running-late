package game.objects;

import game.GameScreen;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Blueprint for all the different objects in the game
 */
public class Object {

    // object's sprite
    public BufferedImage image;

    // name of the object
    public String name;

    // for collisions:
    public boolean collision = false;
    public boolean hurtful = false;
    public boolean isExit = false;

    // for location:
    public int worldX, worldY;

    // for detecting collisions
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    /**
     * Draws the object onto the game screen
     * @param g2 - graphics
     * @param gs - game screen
     */
    public void draw(Graphics2D g2, GameScreen gs) {
        g2.drawImage(image,worldX,worldY,gs.tileSize,gs.tileSize,null);
    }
}
