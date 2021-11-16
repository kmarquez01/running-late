package game.objects;

import game.GameScreen;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Object {

    public BufferedImage image;

    public String name;

    public boolean collision = false;
    public boolean hurtful = false;
    public boolean isExit = false;

    public int worldX, worldY;

    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public void draw(Graphics2D g2, GameScreen gs) {
        g2.drawImage(image,worldX,worldY,gs.tileSize,gs.tileSize,null);
    }
}
