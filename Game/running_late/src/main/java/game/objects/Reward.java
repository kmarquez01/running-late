package game.objects;

import game.GameScreen;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;



public class Reward {  //stationary rewards
    public BufferedImage image;
    public boolean collision = false;
    public int points;
    public int x;
    public int y;

    //added for collisions:
    public Rectangle solidArea = new Rectangle(0, 0, 10, 10);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;



    //constructor
    public Reward(){
        points = 3;
        try {
            //update image later
            image = ImageIO.read((new FileInputStream("resources/rewards/star.png")));
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    //draw the rewards on the screen
    public void draw(Graphics2D G2D, GameScreen screen){
        G2D.drawImage(image, x, y, screen.tileSize, screen.tileSize, null);
    }

    //detect collision with player: (also add points to player's score)
    //TODO



    //disappears & replaced with tile:
    //TODO

}
